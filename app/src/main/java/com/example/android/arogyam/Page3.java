package com.example.android.arogyam;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class Page3 extends Activity {
    double latitude; // hide your data members from the client
    double longitude;
    String address;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
      /* Use the LocationManager class to obtain GPS locations */
        method1();
    }

    public void method1() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new MyLocationListener();
        try {
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /* Class My Location Listener */
    public class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {

            double a = loc.getLatitude();
            latitude = a;

            double b = loc.getLongitude();
            longitude = b;
            String Text = "My current location is: " +
                    "Latitud = " + loc.getLatitude() +
                    "Longitud = " + loc.getLongitude();

//            Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
            String x = getCompleteAddressString(a, b);
            address=x;
//            Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
            TextView text = (TextView) findViewById(R.id.tv_address);
            text.setText(x);
            //url_get(a, b);
        }

        void url_get(double lati, double longi) {
            URL url = null;
            try {
                url = new URL("https://maps.googleapis.com/maps/api/place/search/json?&location=" + String.valueOf(lati) + "," + String.valueOf(longi) + "&radius=1000&types=hospital&sensor=true&key= AIzaSyA7Aiy4Dl1Bhh1WsZkhm7Rd13U9IjC-t_I");
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
                TextView json = (TextView) findViewById(R.id.json3);
                String theString = IOUtils.toString(in, "UTF-8");
                json.setText(lati + "");
                //System.out.println(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My location:", "" + strReturnedAddress.toString());
            } else {
                Log.w("My address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My address", "Canont get Address!");
        }
        return strAdd;
    }
}
