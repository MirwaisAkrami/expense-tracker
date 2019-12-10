package com.example.expensetracker.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static String NAME_PREFERENCE = "expenseTracker";
    int PRIVATE_MODE = 0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public SessionManager(Context context) {
        this.context = context;

        pref = context.getSharedPreferences(NAME_PREFERENCE, PRIVATE_MODE);
        editor = pref.edit();

    }


    public void setLoggedInUser(String name, String email) {
        editor.putString("name", name);
        editor.putString("email", email);
        editor.commit();
    }

    public String getLoggedInUserName() {
        return pref.getString("name", null);
    }

    public boolean logout() {
        editor.remove("name");
        editor.remove("email");
        editor.commit();

        return true;
    }


}
