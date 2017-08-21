package com.example.android.nodemcu;

import android.nfc.Tag;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;

import static android.webkit.WebSettings.PluginState.ON;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    String base_url = "http://192.168.43.209";

    Button toggle = (Button) findViewById(R.id.toggleButton);
    toggle.setOnClickListener(new View.OnClickListener()
    {
        public void onClick (View v)
        {
            String on = base_url + "/LED=ON";
            try {
                URL url = new URL("on");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
            } catch (Exception e) {
            }
        }
    });
    hi from github

  */




    public void toggleON(View v)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String paramValue="/LED=ON";

        //String on ="http://192.168.43.209";
        try {

           // String yourURLStr = "http://192.168.43.209" + java.net.URLEncoder.encode(paramValue, "UTF-8");

            InetAddress addr= InetAddress.getByName("192.168.43.209"+"/LED=ON");
            URL url = new URL("http://"+addr.getHostAddress());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000 /* milliseconds */);
            con.setConnectTimeout(15000 /* milliseconds */);
            con.setRequestMethod("GET");
            con.connect();
            if(con.getResponseCode() == 200)
            {
                TextView x= (TextView) findViewById(R.id.text);
                x.setText("Turned ON");
            }
        } catch (Exception e) {
            Log.e(TAG, "Couldn't make request in ON", e);

        }

    }


    public void toggleOFF(View v)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String paramValue="/LED=OFF";

       // String on ="http://192.168.43.209";
        try {

            //String yourURLStr = "http://192.168.43.209" + java.net.URLEncoder.encode(paramValue, "UTF-8");

            InetAddress addr= InetAddress.getByName("192.168.43.209"+"/LED=OFF");
            URL url = new URL("http://"+addr.getHostAddress());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000 /* milliseconds */);
            con.setConnectTimeout(15000 /* milliseconds */);
            con.setRequestMethod("GET");
            con.connect();
            if(con.getResponseCode() == 200)
            {
                TextView x= (TextView) findViewById(R.id.text);
                x.setText("Turned OFF");
            }
        } catch (Exception e) {
            Log.e(TAG, "Couldn't make request in OFF", e);
        }
    }
}
