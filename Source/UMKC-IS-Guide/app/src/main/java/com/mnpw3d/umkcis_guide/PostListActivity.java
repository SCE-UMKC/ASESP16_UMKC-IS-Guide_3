package com.mnpw3d.umkcis_guide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class PostListActivity extends AppCompatActivity {
    ArrayList<com.com.umkcis.bean.Post> posts;
    MyCustomAdapter dataAdapter = null;
    String url = "http://umkcisguide.tj5duatmpf.us-west-2.elasticbeanstalk.com/umkc/umkcis/getRecord";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fetchData(url);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostListActivity.this, com.mnpw3d.umkcis_guide.Post.class);
                startActivity(intent);
            }
        });
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.refresh);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData(url);
            }
        });
    }
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        Intent i = new Intent(PostListActivity.this,HomeActivity.class);
        startActivity(i);
    }
    public void fetchData(String url) {
        WebServiceTask wst = new WebServiceTask(WebServiceTask.GET_TASK, this, "Fetching Posts, Please wait...");
        // the passed String is the URL we will POST to
        wst.execute(new String[]{url});
    }
    private class WebServiceTask extends AsyncTask<String, Integer, String> {

        public static final int POST_TASK = 1;
        public static final int GET_TASK = 2;

        private static final String TAG = "WebServiceTask";

        // connection timeout, in milliseconds (waiting to connect)
        private static final int CONN_TIMEOUT = 3000;

        // socket timeout, in milliseconds (waiting for data)
      //  private static final int SOCKET_TIMEOUT = 10000;

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
        //    HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

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
    public void handleResponse(String response) {

        displayPost(response);
    }

    private void hideKeyboard() {

        InputMethodManager inputManager = (InputMethodManager) PostListActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(
                PostListActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
            JSONArray postListObj = responseObj.getJSONArray("posts");

            posts = new ArrayList<com.com.umkcis.bean.Post>();
            for (int i=postListObj.length()-1; i>=0; i--){

                //get the country information JSON object
                String postInfo = postListObj.getJSONObject(i).toString();
                //create java object from the JSON object
                Post post = gson.fromJson(postInfo, Post.class);
                //add to country array list
                posts.add(post);
            }

            //create an ArrayAdaptar from the String Array
            dataAdapter = new MyCustomAdapter(this,
                    R.layout.postlistrow, posts);
            ListView listView = (ListView) findViewById(R.id.posts);
            // Assign adapter to ListView
            listView.setAdapter(dataAdapter);

            //enables filtering for the contents of the given ListView
            listView.setTextFilterEnabled(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // When clicked, show a toast with the TextView text
                    Post post = (Post) parent.getItemAtPosition(position);
                    Intent i = new Intent(PostListActivity.this,PostViewActivity.class);
                    i.putExtra("postid",post.getPostid());
                    i.putExtra("posttext",post.getPosttext());
                    i.putExtra("userid",post.getUserid());
                    i.putExtra("username",post.getUsername());
                    startActivity(i);
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class MyCustomAdapter extends ArrayAdapter<com.com.umkcis.bean.Post> {

        private ArrayList<com.com.umkcis.bean.Post> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<com.com.umkcis.bean.Post> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<com.com.umkcis.bean.Post>();
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

            com.com.umkcis.bean.Post post  = countryList.get(position);
            holder.username.setText(post.getUsername());
            holder.postdata.setText(post.getPosttext());
            return convertView;

        }

    }
}
