package com.mnpw3d.umkcis_guide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.util.JSON;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProfileActicity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private SharedPreferences preferencesetting;
    String url = "http://umkcisguide.tj5duatmpf.us-west-2.elasticbeanstalk.com/umkc/umkcis/profile";
    private int progressStatus=0;
    private TextView textView;
    TextView fname;
    TextView lname;
    TextView username;
    TextView email;
    boolean passport=false,shop=false,visa=false,i20=false,gre=false,ielts=false,apply=false,funds=false,flight=false,acco=false;
    int progressPer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acticity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fname = (TextView)findViewById(R.id.fname);
        lname = (TextView)findViewById(R.id.lname);
        username = (TextView)findViewById(R.id.uname);
        email = (TextView)findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        fetchData(url);
    }
    public void fetchData(String url) {
        preferencesetting = getSharedPreferences("userid", MODE_WORLD_READABLE);
        String id = preferencesetting.getString("id",null);
        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, this, "Please wait...");
        wst.addNameValuePair("id",id);
        wst.execute(new String[]{url});
    }
    public void progressfn(View v)
    {
        Intent i=new Intent(ProfileActicity.this,TodoActivity.class);
        i.putExtra("passport",passport);
        i.putExtra("shop", shop);
        i.putExtra("visa", visa);
        i.putExtra("i20", i20);
        i.putExtra("gre",gre);
        i.putExtra("ielts",ielts);
        i.putExtra("apply",apply);
        i.putExtra("funds",funds);
        i.putExtra("flight",flight);
        i.putExtra("acco", acco);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        Intent i = new Intent(ProfileActicity.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            Intent i = new Intent(ProfileActicity.this,ProfileActicity.class);
            startActivity(i);
        }
        if( id == R.id.action_settings)
        {
            Intent i = new Intent(ProfileActicity.this,ChangePassword.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }
    public void handleResponse(String response) {
        JSONObject userJson;
        //  Toast.makeText(this,"Registration successfull" + response + " 1",Toast.LENGTH_SHORT).show();
        if(!response.equals("") && response != null)
        {
            try {
                userJson = new JSONObject(response);
                fname.setText(userJson.getJSONObject("user").getString("fname"));
                lname.setText(userJson.getJSONObject("user").getString("lname"));
                username.setText(userJson.getJSONObject("user").getString("username"));
                email.setText(userJson.getJSONObject("user").getString("email"));
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("flight"))
                {
                    progressPer = progressPer + 1;
                    flight=true;
                }
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("apply"))
                {
                    progressPer = progressPer + 1;
                    apply=true;
                }
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("shop"))
                {
                    progressPer = progressPer + 1;
                    shop=true;
                }
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("funds"))
                {
                    progressPer = progressPer + 1;
                    funds=true;
                }
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("ielts"))
                {
                    progressPer = progressPer + 1;
                    ielts=true;
                }
                if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("i20"))
                {
                    progressPer = progressPer + 1;
                    i20=true;
                }if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("visa"))
                {
                    progressPer = progressPer + 1;
                    visa=true;
                }if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("passport"))
                {
                    progressPer = progressPer + 1;
                    passport=true;
                }if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("acco"))
                {
                    progressPer = progressPer + 1;
                    acco=true;
                }if(userJson.getJSONObject("user").getJSONObject("progress").getBoolean("gre"))
                {
                    progressPer = progressPer + 1;
                    gre=true;
                }
                progressPer = (progressPer * 100)/10;
                new Thread(new Runnable() {
                    public void run() {
                /*while (progressStatus < 100) {
                    progressStatus += 1;*/
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressPer);
                                textView.setText(progressPer+"/"+progressBar.getMax());
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else
        {
            Toast.makeText(this,"Please Try Again",Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {

        InputMethodManager inputManager = (InputMethodManager) ProfileActicity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(
                ProfileActicity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    private class WebServiceTask extends AsyncTask<String, Integer, String> {

        public static final int POST_TASK = 1;
        public static final int GET_TASK = 2;

        private static final String TAG = "WebServiceTask";

        // connection timeout, in milliseconds (waiting to connect)
        private static final int CONN_TIMEOUT = 3000;

        // socket timeout, in milliseconds (waiting for data)
        private static final int SOCKET_TIMEOUT = 10000;

        private int taskType = GET_TASK;
        private Context mContext = null;
        private String processMessage = "Processing...";

        private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

        private ProgressDialog pDlg = null;

        public WebServiceTask(int taskType, Context mContext, String processMessage) {

            this.taskType = taskType;
            this.mContext = mContext;
            this.processMessage = processMessage;
        }

        public void addNameValuePair(String name, String value) {

            params.add(new BasicNameValuePair(name, value));
        }

        private void showProgressDialog() {

            pDlg = new ProgressDialog(mContext);
            pDlg.setMessage(processMessage);
            pDlg.setProgressDrawable(mContext.getWallpaper());
            pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDlg.setCancelable(false);
            pDlg.show();

        }

        @Override
        protected void onPreExecute() {

          //  hideKeyboard();
            showProgressDialog();

        }

        protected String doInBackground(String... urls) {

            String url = urls[0];
            String result = "";
            HttpResponse response = doResponse(url);
            if (response == null) {
                return result;
            } else {

                try {

                    result = inputStreamToString(response.getEntity().getContent());
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);

                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                }

            }
            try {
                finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            handleResponse(response);
            pDlg.dismiss();

        }

        // Establish connection and socket (data retrieval) timeouts
        private HttpParams getHttpParams() {

            HttpParams htpp = new BasicHttpParams();

            HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
            HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

            return htpp;
        }

        private HttpResponse doResponse(String url) {

            // Use our connection and data timeouts as parameters for our
            // DefaultHttpClient
            HttpClient httpclient = new DefaultHttpClient(getHttpParams());

            HttpResponse response = null;

            try {
                switch (taskType) {

                    case POST_TASK:
                        HttpPost httppost = new HttpPost(url);
                        // Add parameters
                        httppost.setEntity(new UrlEncodedFormEntity(params));
                        response = httpclient.execute(httppost);
                        break;
                    case GET_TASK:
                        HttpGet httpget = new HttpGet(url);
                        response = httpclient.execute(httpget);
                        break;
                }
            } catch (Exception e) {

                Log.e(TAG, e.getLocalizedMessage(), e);

            }
            try {
                finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return response;
        }

        private String inputStreamToString(InputStream is) {

            String line = "";
            StringBuilder total = new StringBuilder();

            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                // Read response until the end
                while ((line = rd.readLine()) != null) {
                    total.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }
            try {
                rd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Return full string
            return total.toString();
        }

    }
}


