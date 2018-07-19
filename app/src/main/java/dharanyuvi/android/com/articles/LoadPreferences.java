package dharanyuvi.android.com.articles;

import android.content.Context;
import android.content.UriMatcher;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class LoadPreferences {
    List<String> TotalList;
    private static int Length = 1;
    private static int Pointer;

    private static List<String> Previous;


    //returns the list of selected agencies
    public static  LoadPreferences Instance = new LoadPreferences();
    public List<String> LoadSharedPreferences(Context context){

        List<String> URLlist = new ArrayList<>();
        if(SharedPreference.Instance.read(context,"TheHindu").equals("true"))
        {
            URLlist.add("TheHindu");
        }

        if(SharedPreference.Instance.read(context,"IE").equals("true"))
        {
            URLlist.add("IE");
        }

        if(SharedPreference.Instance.read(context,"LiveMint").equals("true"))
        {
            URLlist.add("LiveMint");
        }

        if(SharedPreference.Instance.read(context,"BusinessLine").equals("true"))
        {
            URLlist.add("BusinessLine");
        }

        if(SharedPreference.Instance.read(context,"Hindustan").equals("true"))
        {
            URLlist.add("Hindustan");
        }

        if(SharedPreference.Instance.read(context,"BusinessWorld").equals("true"))
        {
            URLlist.add("BusinessWorld");
        }

        if(SharedPreference.Instance.read(context,"TamilHindu").equals("true"))
        {
            URLlist.add("TamilHindu");
        }

        if(SharedPreference.Instance.read(context,"Dinakaran").equals("true"))
        {
            URLlist.add("Dinakaran");
        }

        if(SharedPreference.Instance.read(context,"Jagraan").equals("true"))
        {
            URLlist.add("Jagraan");
        }

        if(SharedPreference.Instance.read(context,"LiveHindustan").equals("true"))
        {
            URLlist.add("LiveHindustan");
        }

        if(SharedPreference.Instance.read(context,"Telegraph").equals("true"))
        {
            URLlist.add("Telegraph");
        }

        if(SharedPreference.Instance.read(context,"Bhaskar").equals("true"))
        {
            URLlist.add("Bhaskar");
        }

        if(SharedPreference.Instance.read(context,"ETNow").equals("true"))
        {
            URLlist.add("ETNow");
        }

        if(SharedPreference.Instance.read(context,"Tribune").equals("true"))
        {
            URLlist.add("Tribune");
        }

        if(SharedPreference.Instance.read(context,"DinaMani").equals("true"))
        {
            URLlist.add("DinaMani");
        }
        else
        {
            Toast.makeText(context,"sharedPreferences- false",Toast.LENGTH_LONG).show();
        }
        return URLlist;
    }

    public List<String> LoadMore(Context context,Boolean isFirst)
    {
        TotalList=new ArrayList<>();
        TotalList= LoadSharedPreferences(context);

        List<String> list = new ArrayList<>();

        //during the first load of the app
        if(isFirst)
        {
            for(int i=0;i<Length;i++)
            {
                if(i==TotalList.size())
                    return list;
                else
                {
                    list.add(TotalList.get(i));
                    Pointer++;
                }
            }
            Previous = new ArrayList<>(TotalList);
            return list;
        }
        //during the load more in the app
        else
        {
            List<String> temp =new ArrayList<>(TotalList);
            temp.removeAll(Previous);

            if(temp.size()>0)
            {
              TotalList.addAll(temp);
            }

            int value =Pointer;
            for(int i=value;i<value+Length;i++)
            {
                if(i==TotalList.size())
                    return list;
                else
                {
                    list.add(TotalList.get(i));
                    Pointer++;
                }

            }
        }
        Previous =new ArrayList<>(TotalList);
      return list;
    }
}
