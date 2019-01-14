package asc.msc.coursework.com.expensetracker.modles;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfiguration {

    private static SharedPreferences sharedPreferences;

    public static SharedPreferences setSharedPreferences(Context context) {
        if (sharedPreferences == null)
            return sharedPreferences = context.getSharedPreferences("asc.msc.coursework.com.expensetracker", Context.MODE_PRIVATE);
        else
            return sharedPreferences;
    }


}
