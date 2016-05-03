package com.mnpw3d.umkcis_guide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.com.umkcis.bean.*;
import com.com.umkcis.bean.Post;
import com.google.gson.Gson;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostViewActivity extends AppCompatActivity {
    ArrayList<Comment> comments;
    MyCustomAdapter dataAdapter = null;
    String url = "http://umkcisguide.tj5duatmpf.us-west-2.elasticbeanstalk.com/umkc/umkcis/getComment";
    private static final String SERVICE_URL = "http://umkcisguide.tj5duatmpf.us-west-2.elasticbeanstalk.com/umkc/umkcis/postComment";
    private static final String TAG = "post";
    private SharedPreferences preferencesetting;
    private SharedPreferences.Editor preferenceEditor;
    TextView usernameText;
    TextView posttextText;
    EditText commentData;
    String postid, posttext, userid,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        postid = i.getStringExtra("postid");
        posttext = i.getStringExtra("posttext");
        userid = i.getStringExtra("userid");
        username = i.getStringExtra("username");
        usernameText = (TextView)findViewById(R.id.username);
        posttextText = (TextView)findViewById(R.id.postdata);
        commentData = (EditText)findViewById(R.id.txt_comment);
        usernameText.setText(username);
        posttextText.setText(posttext);
        fetchData(url);

    }

    public void comment(View v)
    {
        preferencesetting = getSharedPreferences("userid",MODE_WORLD_READABLE);
        preferenceEditor = preferencesetting.edit();
        String id = preferencesetting.getString("id",null);
        String commentText = commentData.getText().toString();
        WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, this, "Please wait...");
        wst.addNameValuePair("postid",postid);
        wst.addNameValuePair("userid",id);
        wst.addNameValuePair("comment", commentText);

        // the passed String is the URL we will POST to
        wst.execute(new String[]{SERVICE_URL});
    }

    public void handleResponse(String response) {

        //  Toast.makeText(this,"Registration successfull" + response + " 1",Toast.LENGTH_SHORT).show();
        if(!response.equals("") && response != null)
        {
            Toast.makeText(this, "Commented Successfully", Toast.LENGTH_SHORT).show();
            fetchData(url);
            commentData.setText("");
        }
        else
        {
            Toast.makeText(this,"Please Try Again",Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {

        InputMethodManager inputManager = (InputMethodManager) PostViewActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(
                PostViewActivity.this.getCurrentFocus()
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

            hideKeyboard();
            showProgressDialog();

        }

        protected String doInBackground(String... urls) {

            String url = urls[0];
            String result = "";
            HttpResponse response = doResponse(url);
            //    Log.d("register", "hi response" + response);

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

            // Return full string
            return total.toString();
        }

    }

    public void fetchData(String url) {
        WebServiceTaskCommentList wst = new WebServiceTaskCommentList(WebServiceTask.POST_TASK, this, "Fetching Comments, Please wait...");
        // the passed String is the URL we will POST to
        Log.d(TAG,postid);
        wst.addNameValuePair("postid",postid);
        wst.execute(new String[]{url});
    }
    private class WebServiceTaskCommentList extends AsyncTask<String, Integer, String> {

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

        public WebServiceTaskCommentList(int taskType, Context mContext, String processMessage) {

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
            showProgressDialog();

        }

        protected String doInBackground(String... urls) {

            String url = urls[0];
            String result = "";
            HttpResponse response = doResponse(url);
            //    Log.d("register", "hi response" + response);

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
            handleResponseComment(response);
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

            // Return full string
            return total.toString();
        }

    }
    public void handleResponseComment(String response) {

        displayPost(response);
    }

    private void displayPost(String response){

        JSONObject responseObj = null;

        try {

            Gson gson = new Gson();
            try {
                responseObj = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray postListObj = responseObj.getJSONArray("comments");

            comments = new ArrayList<Comment>();
            for (int i=0; i<postListObj.length(); i++){

                //get the country information JSON object
                String postInfo = postListObj.getJSONObject(i).toString();
                Log.d(TAG,postInfo);
                //create java object from the JSON object
                Comment com  = gson.fromJson(postInfo, Comment.class);
                //add to country array list
                comments.add(com);
            }

            //create an ArrayAdaptar from the String Array
            dataAdapter = new MyCustomAdapter(this,
                    R.layout.postlistrow, comments);
            ListView listView = (ListView) findViewById(R.id.comments);
            // Assign adapter to ListView
            listView.setAdapter(dataAdapter);
            //enables filtering for the contents of the given ListView
            setListViewHeightBasedOnChildren(listView);
            listView.setTextFilterEnabled(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // When clicked, show a toast with the TextView text

                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class MyCustomAdapter extends ArrayAdapter<Comment> {

        private ArrayList<Comment> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Comment> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Comment>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView username;
            TextView postdata;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.postlistrow, null);

                holder = new ViewHolder();
                holder.username = (TextView) convertView.findViewById(R.id.username);
                holder.postdata = (TextView) convertView.findViewById(R.id.postdata);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Comment com  = countryList.get(position);
            holder.username.setText(com.getUsername());
            holder.postdata.setText(com.getCommentText());
            return convertView;

        }

    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
