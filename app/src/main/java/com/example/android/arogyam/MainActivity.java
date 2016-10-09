package com.example.android.arogyam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    public static final String PREFS_NAME = "LoginPrefs";
//    public boolean isLoggedIn() {
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        return accessToken != null;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(MainActivity.this, Page3.class);
            startActivity(intent);
            finish();
        }
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        loginButton.setReadPermissions(permissionNeeds);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                finish();
//                Intent intent=new Intent(MainActivity.this,Page2.class);
//                startActivity(intent);
                System.out.print("onSucess");
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("logged", "logged");
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Page3.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
//    private LoginButton loginButton;
//    private CallbackManager callbackManager;
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(this.getApplicationContext());
//        setContentView(R.layout.activity_main);
//        callbackManager = CallbackManager.Factory.create();
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
//        loginButton.setReadPermissions(permissionNeeds);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
//        {
//            @Override
//            public void onSuccess(LoginResult loginResult)
//            {
//                System.out.println("onSuccess");
//            }
//
//            @Override
//            public void onCancel()
//            {
//                System.out.println("onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException exception)
//            {
//                Log.v("LoginActivity", exception.getCause().toString());
//            }
//        });
//
//}

