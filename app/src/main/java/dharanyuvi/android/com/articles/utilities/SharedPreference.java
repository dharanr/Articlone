package dharanyuvi.android.com.articles.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference   {
    private Set<String> str;
    private String PREFS_NAME = "wishlist";


    //Function to store the wishlist of the user for their favourites
    public void storeWishList(Context context, String Name, List<String>  URl) {
    // used for store arrayList in json format
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();

        //Creating the Set
        Set<String> set = new HashSet<String>();
        set.addAll(URl);

        editor.putStringSet(Name ,set);
        editor.apply();
    }

    //Function to get the List of Urls to load
    public List<String> read(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                PREFS_NAME, MODE_PRIVATE);


        str = sharedPreferences.getStringSet(key, null);
        if(str!=null)
        {
            List<String> list = new ArrayList<String>(str);
            return list;
        }
        else
            return null;

    }
}
