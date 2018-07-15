package dharanyuvi.android.com.articles;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {

    public static AppConstants Instance = new AppConstants();
    public static String TheHinduUrl = "https://www.thehindu.com/opinion/editorial/feeder/default.rss";

    public static String TheHinduLeadUrl = "https://www.thehindu.com/opinion/lead/feeder/default.rss";

    public static String IEUrl ="https://indianexpress.com/section/opinion/feed/";

    public static String IELeadUrl ="https://indianexpress.com/section/opinion/editorials/feed/";

    public static String LMUrl ="https://www.livemint.com/rss/opinion";

    public static String BusinessLine ="https://www.thehindubusinessline.com/opinion/feeder/default.rss";

    public static String HindustanUrl = "https://www.hindustantimes.com/rss/editorials/rssfeed.xml";

    public static String HindustanLeadUrl = "https://www.hindustantimes.com/rss/opinion/rssfeed.xml";

    public static String BusinessWorldUrl = "http://www.businessworld.in/rss/todays-article.xml";

    //public static String IELeadUrl ="https://indianexpress.com/section/opinion/editorials/feed/";

    public List<String> LoadHindu()
    {
        List<String> list = new ArrayList<>();
        list.add(TheHinduUrl);
        list.add(TheHinduLeadUrl);
        return list;
    }

    public List<String> LoadIE()
    {
        List<String> list = new ArrayList<>();
        list.add(IEUrl);
        list.add(IELeadUrl);
        return list;
    }

    public List<String> LoadLM()
    {
        List<String> list = new ArrayList<>();
        list.add(LMUrl);
        return list;
    }

    public List<String> LoadBusinessLine()
    {
        List<String> list = new ArrayList<>();
        list.add(BusinessLine);
        return list;
    }

    public List<String> LoadHindustan()
    {
        List<String> list = new ArrayList<>();
        list.add(HindustanUrl);
        list.add(HindustanLeadUrl);
        return list;
    }

    public List<String> LoadBusinessWorld()
    {
        List<String> list = new ArrayList<>();
        list.add(BusinessWorldUrl);
        return list;
    }


}
