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
        mIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Resources res = this.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "wishlist", MODE_PRIVATE);

        if( (sharedPreferences.getString("TheFirstTime", null)).equals("true") )
        {
            notification = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.down)
                    .setTicker("ticker value")
                    .setPriority(8)
                    .setSound(soundUri)
                    .setContentTitle("Welcome to Articlone")
                    .setContentText("Come lets Explore the world of editorials and opinions from Today").build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
//        notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
//        notification.ledARGB = 0xFFFFA500;
//        notification.ledOnMS = 800;
//        notification.ledOffMS = 1000;
            notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(NOTIFICATION_ID, notification);
            Log.i("notif","Notifications sent.");
        }
        else
        {
            notification = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.down)
                    .setTicker("ticker value")
                    .setPriority(8)
                    .setSound(soundUri)
                    .setContentTitle("Morning Digest Ready")
                    .setContentText("Keep upated with the recent articles and opinions...").build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
//        notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
//        notification.ledARGB = 0xFFFFA500;
//        notification.ledOnMS = 800;
//        notification.ledOffMS = 1000;
            notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(NOTIFICATION_ID, notification);
            Log.i("notif","Notifications sent.");
        }




    }
}
