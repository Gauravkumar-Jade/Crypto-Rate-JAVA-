package com.jit.cryptoratejava.database;

import android.content.Context;
import android.content.SharedPreferences;

public class TimeStampPref {

    private final SharedPreferences preferences;
    private static final String FILE_NAME = "timeDetail";
    private static final String TIME_KEY = "timeStamp";

    public TimeStampPref(Context context) {
        preferences =  context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }


    public void onSave(Long time){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong(TIME_KEY,time);
        editor.apply();
    }

    public Long getTime(){
        if(preferences.contains(TIME_KEY)){
            return preferences.getLong(TIME_KEY,0);
        }
        return 0L;
    }
}
