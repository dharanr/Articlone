package dharanyuvi.android.com.articles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, Notification.class);
        service1.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        String time = intent.getStringExtra("time");

        if(time.equals("morning"))
        {
            service1.putExtra("time","morning");
        }
        else if(time.equals("noon"))
        {
            service1.putExtra("time","noon");
        }
        else if(time.equals("evening"))
        {
            service1.putExtra("time","evening");
        }
        else if(time.equals("night"))
        {
            service1.putExtra("time","night");
        }

        context.startService(service1);

    }
}
