package dharanyuvi.android.com.articles.XmlParsing;

import android.util.Xml;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dharanyuvi.android.com.articles.models.TheHinduArticle;

public class IndianExpress {
    private TheHinduArticle theHinduArticle;

    private static final String ns = null;
    public static IndianExpress Instance = new IndianExpress();

    public List<TheHinduArticle> parse(InputStream in,ProgressBar bar,String Name) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            //bar.setProgress(70);
            parser.nextTag();
            return readFeed(parser,bar,Name);
        } finally {
            in.close();
        }
    }

    private List<TheHinduArticle> readFeed(XmlPullParser parser,ProgressBar bar,String Name) throws XmlPullParserException, IOException {
        List<TheHinduArticle> entries = new ArrayList<>();


        try {

            String text = "";
            int flag=0,count=0,limit=0;
            //bar.setProgress(80);

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(limit==1)
                {
                    entries.remove(entries.size() - 1);
                    break;
                }
                String tagname = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        if (tagname.equalsIgnoreCase("item")) {
                            flag=1;
                            theHinduArticle = new TheHinduArticle();
                        }

                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equals("item")) {
                            entries.add(theHinduArticle);
                            flag=0;
                        }
                        if(flag==1)
                        {
                            if (tagname.equalsIgnoreCase("title")) {
                                theHinduArticle.SetTitle(text.trim());
                            } else if (tagname.equalsIgnoreCase("dc:creator")) {
                                theHinduArticle.SetAuthor(text.trim());
                            } else if (tagname.equalsIgnoreCase("category")) {
                                theHinduArticle.SetCategory(Name+" - " + text.trim());
                            } else if (tagname.equalsIgnoreCase("link")) {
                                theHinduArticle.SetLink(text.trim());
                            } else if (tagname.equalsIgnoreCase("media:title")) {
                                if(++count==3)
                                {
                                    theHinduArticle.SetDescription(text.trim());
                                    count=0;
                                }
                            } else if (tagname.equalsIgnoreCase("pubDate")) {
                                  String str = getYesterdayDateString();
                                if(text.contains(str))
                                  theHinduArticle.SetPubDate(text.trim());
                                else
                                    limit =1;
                            }
                        }
//                        else
//                        {
//                            if(tagname.equalsIgnoreCase("title"))
//
//                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }




private String getYesterdayDateString() {
    final Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    Date str = cal.getTime();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
    return dateFormat.format(str);
        }
}

