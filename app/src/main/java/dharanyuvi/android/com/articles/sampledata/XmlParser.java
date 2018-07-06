package dharanyuvi.android.com.articles.sampledata;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dharanyuvi.android.com.articles.models.TheHinduArticle;

public class XmlParser {
    // We don't use namespaces
    private List<TheHinduArticle> entries = new ArrayList<>();
    private TheHinduArticle theHinduArticle;

    private static final String ns = null;
    public static XmlParser Instance = new XmlParser();

    public List<TheHinduArticle> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List<TheHinduArticle> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {


        try {
            String text = "";
            int flag=0;
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
                                theHinduArticle.SetTitle(text);
                            } else if (tagname.equalsIgnoreCase("author")) {
                                theHinduArticle.SetAuthor(text);
                            } else if (tagname.equalsIgnoreCase("category")) {
                                theHinduArticle.SetCategory(text);
                            } else if (tagname.equalsIgnoreCase("link")) {
                                theHinduArticle.SetLink(text);
                            } else if (tagname.equalsIgnoreCase("description")) {
                                theHinduArticle.SetDescription(text);
                            } else if (tagname.equalsIgnoreCase("pubDate")) {
                                theHinduArticle.SetPubDate(text);
                            }
                        }
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

