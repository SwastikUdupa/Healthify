package com.example.android.arogyam;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Swastik on 09-10-2016.
 */
public class Page4 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);
        url_get();
    }

    void url_get() {
        URL url = null;
        try {
            url = new URL("https://maps.googleapis.com/maps/api/place/search/json?&location=17.739290150000002,83.3071201&radius=6000&types=hospital&sensor=true&key=AIzaSyBta3HizpdwhSCFHhvF4jx0JDKdI2YSojU");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            TextView json = (TextView) findViewById(R.id.json);
            String theString = IOUtils.toString(in, "UTF-8");
            json.setText(theString);
            //System.out.println(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

    }


//    void method() {
//        try {
//            HttpPost httppost = new HttpPost("https://maps.googleapis.com/maps/api/place/search/json?&location=17.739290150000002,83.3071201&radius=6000&names=hospital&sensor=true&key=yourkeyhere");
//            HttpClient httpclient = new DefaultHttpClient();
//            response = httpclient.execute(httppost);
//            String data = EntityUtils.toString(response.getEntity());
//            System.out.println(data);
//            //parse with Json, Gson, Jackson
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
