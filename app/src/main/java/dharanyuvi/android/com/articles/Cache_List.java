package dharanyuvi.android.com.articles;

import java.util.ArrayList;
import java.util.List;

public class Cache_List {
    public List<String> list = new ArrayList<String>();
    public static Cache_List Instance = new Cache_List();

    public void SetList(List<String> list) {
        this.list = list;
    }

    public List<String> GetFeed()
    {
        return list;
    }
}
