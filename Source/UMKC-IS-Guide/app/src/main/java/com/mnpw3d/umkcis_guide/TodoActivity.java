package com.mnpw3d.umkcis_guide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    boolean passport=false,shop=false,visa=false,i20=false,gre=false,ielts=false,apply=false,funds=false,flight=false,acco=false;
    CheckBox checkPass, checkShop, checkVisa, checki20, checkGRE, checkIelts, checkApply, checkFunds, checkFlight, checkacco;
    Button submit;
    private Handler handler = new Handler();
    private SharedPreferences preferencesetting;
    String url = "http://umkcisguide.tj5duatmpf.us-west-2.elasticbeanstalk.com/umkc/umkcis/updateprogress";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        passport = i.getBooleanExtra("passport",false);
        shop = i.getBooleanExtra("shop",false);
        visa = i.getBooleanExtra("visa",false);
        i20 = i.getBooleanExtra("i20",false);
        gre = i.getBooleanExtra("gre",false);
        ielts = i.getBooleanExtra("ielts",false);
        apply = i.getBooleanExtra("apply",false);
        funds = i.getBooleanExtra("funds",false);
        flight = i.getBooleanExtra("flight",false);
        acco = i.getBooleanExtra("acco",false);
        submit =  (Button)findViewById(R.id.updateprogress);
        checkacco = (CheckBox)findViewById(R.id.checkAcco);
        checkApply = (CheckBox)findViewById(R.id.checkApply);
        checkFlight = (CheckBox)findViewById(R.id.checkFlight);
        checkFunds = (CheckBox)findViewById(R.id.checkFunds);
        checkGRE = (CheckBox)findViewById(R.id.checkGre);
        checki20 = (CheckBox)findViewById(R.id.checkI20);
        checkIelts = (CheckBox)findViewById(R.id.checkToefl);
        checkPass = (CheckBox)findViewById(R.id.checkpassport);
        checkShop = (CheckBox)findViewById(R.id.checkShopping);
        checkVisa = (CheckBox)findViewById(R.id.checkVisa);
        checkacco.setChecked(acco);
        checkVisa.setChecked(visa);
        checkShop.setChecked(shop);
        checkPass.setChecked(passport);
        checkIelts.setChecked(ielts);
        checkApply.setChecked(apply);
        checki20.setChecked(i20);
        checkFlight.setChecked(flight);
        checkFunds.setChecked(funds);
        checkGRE.setChecked(gre);
        checkVisa.setChecked(visa);
    }

    public void progressfn(View v)
    {
        preferencesetting = getSharedPreferences("userid", MODE_WORLD_READABLE);
        String id = preferencesetting.getString("id",null);
        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, this, "Updating Progress, Please wait...");
        passport = checkPass.isChecked();
        shop = checkShop.isChecked();
        visa = checkVisa.isChecked();
        i20 = checki20.isChecked();
        gre = checkGRE.isChecked();
        ielts=checkIelts.isChecked();
        apply=checkApply.isChecked();
        funds=checkFunds.isChecked();
        flight=checkFlight.isChecked();
        acco=checkacco.isChecked();
        wst.addNameValuePair("id",id);
        wst.addNameValuePair("passport", Boolean.toString(passport));
        wst.addNameValuePair("shop", Boolean.toString(shop));
        wst.addNameValuePair("visa", Boolean.toString(visa));
        wst.addNameValuePair("i20", Boolean.toString(i20));
        wst.addNameValuePair("gre", Boolean.toString(gre));
        wst.addNameValuePair("ielts", Boolean.toString(ielts));
        wst.addNameValuePair("apply", Boolean.toString(apply));
        wst.addNameValuePair("funds", Boolean.toString(funds));
        wst.addNameValuePair("flight", Boolean.toString(flight));
        wst.addNameValuePair("acco", Boolean.toString(acco));
        wst.execute(new String[]{url});
    }

    public void handleResponse(String response) {
        if(!response.equals("") && response != null)
        {
            Intent i = new Intent(TodoActivity.this,ProfileActicity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(this, "Please Try Again", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {

        InputMethodManager inputManager = (InputMethodManager) TodoActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(
                TodoActivity.this.getCurrentFocus()
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

            //hideKeyboard();
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
