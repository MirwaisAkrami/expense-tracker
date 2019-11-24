package com.example.expensetracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.expensetracker.R;

public class SplashScreenActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2500;
    private static SharedPreferences prefs;
    private static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}


        prefs = getSharedPreferences("expenseTracker", MODE_PRIVATE);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if(isLoggedIn()) {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    SplashScreenActivity.this.startActivity(mainIntent);
                    SplashScreenActivity.this.finish();
                } else {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    SplashScreenActivity.this.startActivity(mainIntent);
                    SplashScreenActivity.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    public static boolean isLoggedIn() {
        String mail = "";
        if(prefs.contains("email")) {
            mail = prefs.getString("email", "");
        }

        email = mail;
        Log.d("Splash Screen", "isLoggedIn: " + mail);
        return !mail.equals("");
    }

    public static String getUserEmail() {
        return email;
    }
}
