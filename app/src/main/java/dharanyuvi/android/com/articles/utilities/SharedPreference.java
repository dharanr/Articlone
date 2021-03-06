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

    //Function to store the digest timings
    public void storeDigest(Context context, String Name, String Time) {
        // used for store arrayList in json format
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();


        editor.putString(Name ,Time+"");
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
            storeWishList(context,"TamilHindu",true);
            storeWishList(context,"Dinakaran",true);
            storeWishList(context,"Jagraan",true);
            storeWishList(context,"LiveHindustan",true);
            storeWishList(context,"Telegraph",true);
            storeWishList(context,"Bhaskar",true);
            storeWishList(context,"ETNow",true);
            storeWishList(context,"Tribune",true);
            storeWishList(context,"DinaMani",true);

            storeWishList(context,"Morning",true);
            storeWishList(context,"Noon",true);
            storeWishList(context,"Evening",true);
            storeWishList(context,"Night",true);

            storeDigest(context,"MorningTime","8 00");
            storeDigest(context,"NoonTime","14 00");
            storeDigest(context,"EveningTime","19 00");
            storeDigest(context,"NightTime","1 00");


            storeWishList(context,"TheFirstTime",true);


        }
    }

}

