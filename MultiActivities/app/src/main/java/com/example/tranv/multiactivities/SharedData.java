package com.example.tranv.multiactivities;

import android.content.SharedPreferences;

/**
 * Created by tranv on 4/1/2016.
 */
public class SharedData {

    SharedPreferences sharedPreferences;

    public static final String MESSAGE = "MessageKey";

    public void clearData(SharedPreferences t) {
        SharedPreferences.Editor editor = t.edit();
        editor.clear();
        editor.commit();
    }

    public void saveData(SharedPreferences t, String message) {
        SharedPreferences.Editor editor = t.edit();
        editor.putString(MESSAGE, message);
        editor.commit();
    }

    public String loadData(SharedPreferences t) {
        return t.getString(MESSAGE,"");
    }
}
