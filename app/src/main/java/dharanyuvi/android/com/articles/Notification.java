package dharanyuvi.android.com.articles;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class Notification extends IntentService {
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private static int NOTIFICATION_ID = 1;
    android.app.Notification notification;


    public Notification() {
        super("name");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, Write.class);
        Bundle bundle = new Bundle();
        bundle.putString("test", "test");
        String data="";
        mIntent.putExtras(bundle);

        if(intent.getStringExtra("time").equals("morning"))
        {
            pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            data="Morning Digest Ready";
        }
        else if(intent.getStringExtra("time").equals("noon"))
        {
            pendingIntent = PendingIntent.getActivity(context, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            data="Good AfterNoon...Here's your digest";
        }
        else if(intent.getStringExtra("time").equals("evening"))
        {
            pendingIntent = PendingIntent.getActivity(context, 2, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            data="Yipee Evening starts with your digest";
        }
        else if(intent.getStringExtra("time").equals("night"))
        {
            pendingIntent = PendingIntent.getActivity(context, 3, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            data="Early digest...Here's your digest";
        }

//        Resources res = this.getResources();
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notification = new NotificationCompat.Builder(this,"12")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.down)
                .setTicker("ticker value")
                .setPriority(8)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentTitle(data)
                .setContentText("Keep updated with the recent articles and opinions...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Keep updated with the recent editorials and opinions..Come Lets Explore..!! "))
                .build();
//                   notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
//                    notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        //        notification.ledARGB = 0xFFFFA500;
        //        notification.ledOnMS = 800;
        //        notification.ledOffMS = 1000;
            notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(NOTIFICATION_ID, notification);
            Log.i("notif","Notifications sent.");


    }
}
