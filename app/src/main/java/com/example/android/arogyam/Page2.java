package com.example.android.arogyam;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Swastik on 08-10-2016.
 */
public class Page2 extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        Button btn = (Button) findViewById(R.id.help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("9740641023", "Help");
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Page2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.arogyam/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Page2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.arogyam/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

//    void sendSMS(String phoneNumber, String message)
//    {
//        String SENT = "SMS_SENT";
//        String DELIVERED = "SMS_DELIVERED";
//
//        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
//                new Intent(SENT), 0);
//
//        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
//                new Intent(DELIVERED), 0);
//
//        //---when the SMS has been sent---
//        registerReceiver(new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode())
//                {
//                    case Activity.RESULT_OK:
//                        Toast.makeText(getBaseContext(), "SMS sent",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                        Toast.makeText(getBaseContext(), "Generic failure",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_NO_SERVICE:
//                        Toast.makeText(getBaseContext(), "No service",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_NULL_PDU:
//                        Toast.makeText(getBaseContext(), "Null PDU",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_RADIO_OFF:
//                        Toast.makeText(getBaseContext(), "Radio off",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        }, new IntentFilter(SENT));
//
//        //---when the SMS has been delivered---
//        registerReceiver(new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode())
//                {
//                    case Activity.RESULT_OK:
//                        Toast.makeText(getBaseContext(), "SMS delivered",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case Activity.RESULT_CANCELED:
//                        Toast.makeText(getBaseContext(), "SMS not delivered",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        }, new IntentFilter(DELIVERED));
//
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
//    }
//    private void sendSms(String phonenumber,String message, boolean isBinary)
//    {
//        SmsManager manager = SmsManager.getDefault();
//
//        PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
//        PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);
//
//        if(isBinary)
//        {
//            byte[] data = new byte[message.length()];
//
//            for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
//            {
//                data[index] = (byte)message.charAt(index);
//            }
//
//            manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
//        }
//        else
//        {
//            int length = message.length();
//
//            if(length > MAX_SMS_MESSAGE_LENGTH)
//            {
//                ArrayList<String> messagelist = manager.divideMessage(message);
//
//                manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
//            }
//            else
//            {
//                manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
//            }
//        }
//    }
}
