package dharanyuvi.android.com.articles.utilities;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import dharanyuvi.android.com.articles.MainActivity;

public class NetInfo {

    public static NetInfo Instance = new NetInfo();

    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo!= null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkConnection(Context context){
        if(isOnline(context)){
            //Toast.makeText(context, "You are connected to Internet", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }
}
