package dharanyuvi.android.com.articles;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dharanyuvi.android.com.articles.XmlParsing.IndianExpress;
import dharanyuvi.android.com.articles.XmlParsing.LiveMint;
import dharanyuvi.android.com.articles.XmlParsing.XmlParser;
import dharanyuvi.android.com.articles.adpaters.HomeAdapter;
import dharanyuvi.android.com.articles.models.TheHinduArticle;
import dharanyuvi.android.com.articles.utilities.NetInfo;
import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String a;
    TextView textView;
    private ProgressBar simpleProgressBar;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout relativeLayout,NoArticles;
    WebView webView;
    TextView no_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        SharedPreference.Instance.FirstTime(getApplicationContext());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);

        //progress bar
        simpleProgressBar=findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(100); // 100 maximum value for the progress value
        simpleProgressBar.setProgress(20); // 50 default progress value for the progress bar
        simpleProgressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        //declaration
        relativeLayout = findViewById(R.id.web);
        webView = findViewById(R.id.nointernet);
        no_connection =findViewById(R.id.nointernettext);

        //Layouts if there is No Artices
        NoArticles = findViewById(R.id.NoArticles);



        //fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Code for make drawer open and close
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //navigationBar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);




        //checking for the internet connection for the application
        if(NetInfo.Instance.checkConnection(getApplicationContext()))
        {
            List<String> URLlist = new ArrayList<String>();
            //accessing the wishlist
            URLlist = LoadSharedPreferences();

            TheHindu task =new TheHindu(URLlist);
            task.setProgressBar(simpleProgressBar);
            task.execute();
        }
        else
        {
            webView.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='"+ "nointernet" + "' style='width:100%' />", "text/html", "utf-8", null);
            relativeLayout.setVisibility(View.VISIBLE);
            no_connection.setVisibility(View.VISIBLE);
        }


        //function for the refresh bar actions
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        doYourUpdate();
                    }


                }
        );


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();    //close the app with the alert box in it
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this,Article_Webview.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this,Splash.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Function to update the refresh activity
    private void doYourUpdate() {
        swipeRefreshLayout.setRefreshing(false);
        if(NetInfo.Instance.checkConnection(getApplicationContext()))
        {
            if(relativeLayout.getVisibility()==View.VISIBLE) {
                relativeLayout.setVisibility(View.GONE);
                no_connection.setVisibility(View.GONE);
            }
            recyclerView.setVisibility(View.VISIBLE);

            List<String> URLlist = new ArrayList<>();
            //accessing the wishlist
            URLlist = LoadSharedPreferences();

            TheHindu task =new TheHindu(URLlist);
            task.setProgressBar(simpleProgressBar);
            task.execute();
        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            webView.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='"+ "nointernet" + "' style='width:100%' />", "text/html", "utf-8", null);
            relativeLayout.setVisibility(View.VISIBLE);
            no_connection.setVisibility(View.VISIBLE);
        }

    }

    //returns the list of selected agencies
    private List<String> LoadSharedPreferences(){

        List<String> URLlist = new ArrayList<>();
        if(SharedPreference.Instance.read(getApplicationContext(),"TheHindu").equals("true"))
        {
           URLlist.add("TheHindu");
        }

        if(SharedPreference.Instance.read(getApplicationContext(),"IE").equals("true"))
        {
            URLlist.add("IE");
        }

        if(SharedPreference.Instance.read(getApplicationContext(),"LiveMint").equals("true"))
        {
            URLlist.add("LiveMint");
        }

        if(SharedPreference.Instance.read(getApplicationContext(),"BusinessLine").equals("true"))
        {
            URLlist.add("BusinessLine");
        }
        else
        {
            Toast.makeText(MainActivity.this,"sharedPreferences- false",Toast.LENGTH_LONG).show();
        }
        return URLlist;
    }



    //AsyncTask class to run the background task to obtain the rss data
    public class TheHindu extends AsyncTask<String, String, String> {
        private List<TheHinduArticle> list = new ArrayList<>();
        private List<String> listOfSelect;

        private TheHindu(List<String> list)
        {
            this.listOfSelect = list;
        }

        @SuppressLint("StaticFieldLeak")
        ProgressBar bar;

        private void setList(List<TheHinduArticle> list) {
            this.list = list;
        }


        public List<TheHinduArticle> getList() {
            return list;
        }

        @Override
        protected void onPreExecute() {
            bar.setVisibility(View.VISIBLE);
            bar.setProgress(10);
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection;
        try {
                bar.setProgress(30);

                list = null;

                for(int i=0;i<listOfSelect.size();i++) {
                    String name = listOfSelect.get(i);
                    List<String> HinduURLlist;
                    HinduURLlist = AppConstants.Instance.LoadHindu();

                    List<String> IE;
                    IE = AppConstants.Instance.LoadIE();

                    List<String> LMList;
                    LMList=AppConstants.Instance.LoadLM();

                    List<String> BusinessLine;
                    BusinessLine=AppConstants.Instance.LoadBusinessLine();

                    if(name.equals("TheHindu")) {
                       int count=0;
                        while(count<2)
                        {
                            String geturl = HinduURLlist.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                                list=(XmlParser.Instance.parse(in,bar,name));
                            else
                            list.addAll(XmlParser.Instance.parse(in,bar,name));

                            count++;
                        }

                    }
                    else if(name.equals("IE")){
                        int count=0;
                        while(count<2)
                        {
                            String geturl = IE.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =IndianExpress.Instance.parse(in,bar,name);
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp =IndianExpress.Instance.parse(in,bar,name);
                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }
                    }
                    else if(name.equals("LiveMint"))
                    {
                        int count=0;
                        while(count<1)
                        {
                            String geturl = LMList.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name);
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name);
                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }
                    }
                    else if(name.equals("BusinessLine"))
                    {
                        int count=0;
                        while(count<1)
                        {
                            String geturl = BusinessLine.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);



                            List<TheHinduArticle> temp =XmlParser.Instance.parse(in,bar,name);
                            if(temp!=null)
                            {

                                for (Iterator<TheHinduArticle> iter = temp.listIterator(); iter.hasNext(); ) {
                                    TheHinduArticle a = iter.next();
                                    if( !(("BusinessLine - EDITORIAL".equalsIgnoreCase(a.getCategory())) || ("BusinessLine - OPINION".equalsIgnoreCase(a.getCategory()))) )
                                    {
                                        iter.remove();
                                    }
                                }

                                if(list==null)
                                    list=(temp);
                                else
                                    list.addAll(temp);
                            }

                            count++;
                        }
                    }

                }
            }
            catch (Exception e)
            {
                Log.d("Error",e.getMessage());
            }

            return "success";
        }


        @Override
        protected void onPostExecute(String result) {
            bar.setVisibility(View.INVISIBLE);

            if (result.equals("success"))
                    setList(list);

            if(list!=null)
            {
                if(NoArticles.getVisibility()==View.VISIBLE)
                    NoArticles.setVisibility(View.GONE);


                homeAdapter = new HomeAdapter(MainActivity.this,list);

            //recyclerView.setAnimation(new DefaultItemAnimator());
                recyclerView.setAdapter(homeAdapter);


            }
            else
            {
                NoArticles.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,"No Articles today. Enjoy!!",Toast.LENGTH_LONG).show();
            }
            bar.setProgress(100);

        }

        private void setProgressBar(ProgressBar bar) {
            this.bar = bar;
        }
    }
}
