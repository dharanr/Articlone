package dharanyuvi.android.com.articles.XmlParsing;

import android.util.Log;
import android.util.Xml;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dharanyuvi.android.com.articles.models.TheHinduArticle;

public class XmlParser {
    // We don't use namespaces
    private TheHinduArticle theHinduArticle;

    private static final String ns = null;
    public static XmlParser Instance = new XmlParser();

    public List<TheHinduArticle> parse(InputStream in,ProgressBar bar,String Name) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            bar.setProgress(70);
            parser.nextTag();
            return readFeed(parser,bar,Name);
        }
        catch (Exception e)
        {
                Log.d("Exception",e.getMessage());
        }
        finally {
            in.close();
        }
        return null;
    }

    private List<TheHinduArticle> readFeed(XmlPullParser parser,ProgressBar bar,String Name) throws XmlPullParserException, IOException {
        List<TheHinduArticle> entries = new ArrayList<>();


        try {

            String text = "";
            int flag=0;
            bar.setProgress(80);

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
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
                            } else if (tagname.equalsIgnoreCase("author")) {
                                theHinduArticle.SetAuthor(text.trim());
                            } else if (tagname.equalsIgnoreCase("category")) {
                                theHinduArticle.SetCategory(Name+" - " + text.trim());
                            } else if (tagname.equalsIgnoreCase("link")) {
                                theHinduArticle.SetLink(text.trim());
                            } else if (tagname.equalsIgnoreCase("description")) {
                                if(Name.equalsIgnoreCase("Tribune -  Editorial"))
                                    theHinduArticle.SetDescription(text.trim().substring(0,150)+"...");
                                else
                                    theHinduArticle.SetDescription(text.trim());
                            } else if (tagname.equalsIgnoreCase("pubDate")) {
                                if(Name.equalsIgnoreCase("Tribune -  Editorial"))
                                    theHinduArticle.SetCategory(Name);

                                theHinduArticle.SetPubDate(text.trim());
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
}

