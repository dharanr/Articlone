package dharanyuvi.android.com.articles;

import java.util.ArrayList;
import java.util.List;

public class AppConstants {

    public static AppConstants Instance = new AppConstants();
    public static String TheHinduUrl = "https://www.thehindu.com/opinion/editorial/feeder/default.rss";

    public static String TheHinduLeadUrl = "https://www.thehindu.com/opinion/lead/feeder/default.rss";

    public List<String> LoadHindu()
    {
        List<String> list = new ArrayList<>();
        list.add(TheHinduUrl);
        list.add(TheHinduLeadUrl);

        return list;
    }

}
