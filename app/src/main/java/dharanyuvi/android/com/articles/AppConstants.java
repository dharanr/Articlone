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

    public static String TamilHindu = "http://tamil.thehindu.com/opinion/editorial/feeder/default.rss";

    public static String TamilLeadHindu ="http://tamil.thehindu.com/opinion/columns/feeder/default.rss";

    public static String DinakaranUrl ="http://www.dinakaran.com/rss_news.asp?id=8";

    public static String Jagraan_Abhi ="http://rss.jagran.com/rss/editorial/apnibaat.xml";

    public static String Jagraan_nazariya ="http://rss.jagran.com/rss/editorial/nazariya.xml";

    public static String LiveHindustanUrl ="https://feed.livehindustan.com/rss/5170";

    public static String LiveHindustanLeadUrl ="https://feed.livehindustan.com/rss/904477";

    public static String TelegraphUrl ="https://www.telegraphindia.com/feeds/rss/opinion";

    public static String Bhaskar ="https://www.bhaskar.com/rss-feed/2089/";

    public static String ETNow ="https://economictimes.indiatimes.com/opinion/rssfeeds/897228639.cms";

    public static String BS ="https://www.tribuneindia.com/rss/feed.aspx?cat_id=34&mid=70";

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

    public List<String> LoadTamilHindu()
    {
        List<String> list = new ArrayList<>();
        list.add(TamilHindu);
        list.add(TamilLeadHindu);
        return list;
    }

    public List<String> LoadDinakaran()
    {
        List<String> list = new ArrayList<>();
        list.add(DinakaranUrl);
        return list;
    }

    public List<String> LoadJagraan()
    {
        List<String> list = new ArrayList<>();
        list.add(Jagraan_Abhi);
        list.add(Jagraan_nazariya);
        return list;
    }

    public List<String> LoadLiveHindustan()
    {
        List<String> list = new ArrayList<>();
        list.add(LiveHindustanUrl);
        list.add(LiveHindustanLeadUrl);
        return list;
    }

    public List<String> LoadTelegraph()
    {
        List<String> list = new ArrayList<>();
        list.add(TelegraphUrl);
        return list;
    }

    public List<String> LoadBhaskar()
    {
        List<String> list = new ArrayList<>();
        list.add(Bhaskar);
        return list;
    }

    public List<String> LoadETNow()
    {
        List<String> list = new ArrayList<>();
        list.add(ETNow);
        return list;
    }

    public List<String> LoadTribune() {
        List<String> list = new ArrayList<>();
        list.add(BS);
        return list;
    }
}
