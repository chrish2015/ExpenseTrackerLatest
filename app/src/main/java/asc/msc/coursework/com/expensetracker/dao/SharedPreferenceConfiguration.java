package asc.msc.coursework.com.expensetracker.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import asc.msc.coursework.com.expensetracker.MainActivity;

public class SharedPreferenceConfiguration {

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences setSharedPreferences(Context context) {
        if (sharedPreferences == null)
            return sharedPreferences = context.getSharedPreferences("asc.msc.coursework.com.expensetracker", Context.MODE_PRIVATE);
        else
            return sharedPreferences;
    }


}
