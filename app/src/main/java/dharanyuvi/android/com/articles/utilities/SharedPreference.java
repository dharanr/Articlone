package dharanyuvi.android.com.articles.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference   {
    private String PREFS_NAME = "wishlist";
    public static SharedPreference Instance = new SharedPreference();


    //Function to store the wishlist of the user for their favourites
    public void storeWishList(Context context, String Name, Boolean bool) {
    // used for store arrayList in json format
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();


        editor.putString(Name ,bool+"");
        editor.apply();
    }

    //Function to get the List of Urls to load
    public String read(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFS_NAME, MODE_PRIVATE);

        String str = sharedPreferences.getString(key, null);
        if(str!=null)
        {
            return str;
        }
        else
            return null;

    }

    //Happens during the first time,  when the application got opened
    public void FirstTime(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFS_NAME, MODE_PRIVATE);

        String str = sharedPreferences.getString("TheFirstTime", null);
        if(str==null)
        {
            storeWishList(context,"TheHindu",true);
            storeWishList(context,"IE",true);
            storeWishList(context,"LiveMint",true);
            storeWishList(context,"BusinessLine",true);
            storeWishList(context,"Hindustan",true);
            storeWishList(context,"BusinessWorld",true);

            storeWishList(context,"TheFirstTime",true);
        }
    }

}

