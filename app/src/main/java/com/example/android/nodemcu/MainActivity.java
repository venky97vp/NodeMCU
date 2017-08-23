package com.example.android.nodemcu;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button toggleOn, toggleOff;
    HttpRequest request;
    AlertDialog builder;

    private static final String TAG = MainActivity.class.getName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleOn = (Button) findViewById(R.id.toggleON);
        toggleOff = (Button) findViewById(R.id.toggleOFF);
        mTextView = (TextView) findViewById(R.id.text);

        toggleOn.setOnClickListener(this);
        request = new HttpRequest();
        builder = new AlertDialog.Builder(this)
                .setTitle("Loading")
                .setCancelable(false)
                .setMessage("Please wait till the loading completes")
                .create();
    }

//    String base_url = "http://192.168.43.209";
//
//    Button toggleOn = (Button) findViewById(R.id.toggleButton);
//    toggleOn.setOnClickListener(new View.OnClickListener()
//    {
//        public void onClick (View v)
//        {
//            String on = base_url + "/LED=ON";
//            try {
//                URL url = new URL("on");
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("GET");
//            } catch (Exception e) {
//            }
//        }
//    });
//    hi from github
//hi from studio

    public void toggleON() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.137.96/LED=ON";

// HttpRequest a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

//        String paramValue="/LED=ON";
//
//        //String on ="http://192.168.43.209";
//        try {
//
//           // String yourURLStr = "http://192.168.43.209" + java.net.URLEncoder.encode(paramValue, "UTF-8");
//
//            InetAddress addr= InetAddress.getByName("192.168.137.96"+"/LED=ON");
//            URL url = new URL("http://"+addr.getHostAddress());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setReadTimeout(10000 /* milliseconds */);
//            con.setConnectTimeout(15000 /* milliseconds */);
//            con.setRequestMethod("GET");
//            con.connect();
//            if(con.getResponseCode() == 200)
//            {
//                TextView x= (TextView) findViewById(R.id.text);
//                x.setText("Turned ON");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }

    }

    private class HttpRequest extends AsyncTask<Boolean, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            builder.dismiss();
        }

        @Override
        protected Void doInBackground(Boolean... params) {
            if (params[0] == true)
                toggleON();
            else if (params[0] == false)
                toggleOFF();
            return null;
        }

        @Override
        protected void onPreExecute() {
            builder.show();
        }
    }


    public void toggleOFF() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.google.com";

        builder.show();

// HttpRequest a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                }
                );
// Add the request to the RequestQueue.
        queue.add(stringRequest);

//        String paramValue="/LED=OFF";
//
//       // String on ="http://192.168.43.209";
//        try {
//
//            //String yourURLStr = "http://192.168.43.209" + java.net.URLEncoder.encode(paramValue, "UTF-8");
//
//            InetAddress addr= InetAddress.getByName("192.168.137.96"+"/LED=OFF");
//            URL url = new URL("http://"+addr.getHostAddress());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setReadTimeout(10000 /* milliseconds */);
//            con.setConnectTimeout(15000 /* milliseconds */);
//            con.setRequestMethod("GET");
//            con.connect();
//            if(con.getResponseCode() == 200)
//            {
//                TextView x= (TextView) findViewById(R.id.text);
//                x.setText("Turned OFF");
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Couldn't make request in OFF", e);
//        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toggleON) {
            request.execute(true);
        } else if (v.getId() == R.id.toggleOFF) {
            request.execute(false);
        }
    }
}
