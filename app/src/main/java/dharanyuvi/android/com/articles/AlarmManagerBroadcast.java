package dharanyuvi.android.com.articles;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.Calendar;

public class AlarmManagerBroadcast {
    private AlarmManager alarmManager;
    private Intent alarmIntent;
    private PendingIntent pendingIntent;

    public static AlarmManagerBroadcast Instance = new AlarmManagerBroadcast();

    public Calendar Alarm (Context context,int hour, int minute)
    {


        //alarmManager.cancel(pendingIntent);

        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmStartTime.set(Calendar.MINUTE,minute);
        alarmStartTime.set(Calendar.SECOND, 0);
        if (now.after(alarmStartTime)) {
            Log.d("Hey","Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }

        Log.d("Alarm","Alarms set for everyday 8 am.");
        return alarmStartTime;
    }

}
