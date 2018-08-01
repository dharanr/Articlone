package dharanyuvi.android.com.articles;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import dharanyuvi.android.com.articles.XmlParsing.DinaKaran;
import dharanyuvi.android.com.articles.XmlParsing.DinaManis;
import dharanyuvi.android.com.articles.XmlParsing.IndianExpress;
import dharanyuvi.android.com.articles.XmlParsing.LiveMint;
import dharanyuvi.android.com.articles.XmlParsing.XmlParser;
import dharanyuvi.android.com.articles.adpaters.HomeAdapter;
import dharanyuvi.android.com.articles.models.TheHinduArticle;
import dharanyuvi.android.com.articles.utilities.NetInfo;
import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int hour,minute;
    String a;
    TextView textView;
    TextView title;
    private ProgressBar simpleProgressBar;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout relativeLayout,NoArticles;
    WebView webView;
    TextView no_connection;
    List<String> URLlist = new ArrayList<>();
    private List<TheHinduArticle> list = new ArrayList<>();
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    String isFromNotification="",pubDate="",session="";

    String MorningTime="";
    String Noontime="";
    String EveningTime="";
    String NightTime="";

    //List<TheHinduArticle> LoadingList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        title = findViewById(R.id.title);

        //Happens during the first time
        SharedPreference.Instance.FirstTime(getApplicationContext());
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "wishlist", MODE_PRIVATE);
        String data=(sharedPreferences.getString("TheFirstTime", null));
        if( data.equals("true") )
        {
//            IntentFilter intent = new IntentFilter( "dharanyuvi.android.com.articles.Broadcast" );
//            this.registerReceiver(new Broadcast(), intent);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.drawable.down)
                        .setContentTitle("Welcome to Articlone")
                        .setContentText("You are a new member to our excited community")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("You are a new member to our excited community...Stay connected!! Let's be updated with what you liked the most"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(10, mBuilder.build());

            SharedPreference.Instance.storeWishList(MainActivity.this,"TheFirstTime",false);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);

        //To check for the intent getExtras
        Bundle intent = getIntent().getExtras();
        if(intent!=null){
            isFromNotification = intent.getString("isFromNotification");
            //pubDate = intent.getString("Date");
            session = intent.getString("Session");

           // String[] date1 = pubDate.split(" ");
//
//            hour=Integer.parseInt( date1[0]);
//            minute = Integer.parseInt(date1[1]);
        }

        //for the digest, we are enabling the green info
        if(isFromNotification.equals("true"))
        {
            title.setVisibility(View.VISIBLE);
        }
        else
        {
            title.setVisibility(View.GONE);
        }

        Toast.makeText(MainActivity.this,isFromNotification+" - "+pubDate,Toast.LENGTH_LONG).show();

        //Assigning the time for the different sessions
        if(SharedPreference.Instance.read(getApplicationContext(),"Morning").equals("true"))
            MorningTime = SharedPreference.Instance.read(getApplicationContext(),"MorningTime");

        if(SharedPreference.Instance.read(getApplicationContext(),"Noon").equals("true"))
            Noontime =  SharedPreference.Instance.read(getApplicationContext(),"NoonTime");
        if(SharedPreference.Instance.read(getApplicationContext(),"Evening").equals("true"))
            EveningTime =  SharedPreference.Instance.read(getApplicationContext(),"EveningTime");
        if(SharedPreference.Instance.read(getApplicationContext(),"Night").equals("true"))
            NightTime =  SharedPreference.Instance.read(getApplicationContext(),"NightTime");



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
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
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

//        int resId = R.anim.layout_animation_fall_down;
//        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(MainActivity.this,resId);
//        recyclerView.setLayoutAnimation(animation);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        homeAdapter = new HomeAdapter(MainActivity.this,list);
        recyclerView.setAdapter(homeAdapter);


        //checking for the internet connection for the application
        if(NetInfo.Instance.checkConnection(getApplicationContext()))
        {
            //accessing the wishlist
            URLlist = LoadPreferences.Instance.LoadMore(getApplicationContext(),true);

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



        if(isFromNotification.equals("true"))
        {
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//                // for this tutorial, this is the ONLY method that we need, ignore the rest
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//                    if(dy>0)
//                    {
//                        List<String> list= LoadPreferences.Instance.LoadMore(getApplicationContext(),false);
//                        TheHindu task =new TheHindu(list);
//                        task.setProgressBar(simpleProgressBar);
//                        task.execute();
//                    }
//                }
//            });
        }
        else
        {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                // for this tutorial, this is the ONLY method that we need, ignore the rest
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    // Recycle view scrolling downwards...
                    // this if statement detects when user reaches the end of recyclerView, this is only time we should load more
                    // if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                    // remember "!" is the same as "== false"
                    // here we are now allowed to load more, but we need to be careful
                    // we must check if itShouldLoadMore variable is true [unlocked]
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if( ((totalItemCount - pastVisiblesItems) <= 5) )
                    {
                        List<String> list= LoadPreferences.Instance.LoadMore(getApplicationContext(),false);
                        TheHindu task =new TheHindu(list);
                        task.setProgressBar(simpleProgressBar);
                        task.execute();
                    }

                    // }
                }

            });
        }



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();    //close the app with the alert box in it
                finish();
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
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this,Write.class);
            startActivity(intent);
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


            //accessing the wishlist
            List<String> list= LoadPreferences.Instance.LoadMore(getApplicationContext(),false);

            TheHindu task =new TheHindu(list);
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



    //AsyncTask class to run the background task to obtain the rss data
    public class TheHindu extends AsyncTask<String, String, String> {
        private List<String> listOfSelect;
        private TheHindu(List<String> list)
        {
            this.listOfSelect = list;
        }

        @SuppressLint("StaticFieldLeak")
        ProgressBar bar;

//        private void setList(List<TheHinduArticle> list) {
//            this.list = list;
//        }


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

//                list = null;

                for(int i=0;i<listOfSelect.size();i++) {
                    String name = listOfSelect.get(i);


                    if(name.equals("TheHindu")) {
                       int count=0;
                        List<String> HinduURLlist;
                        HinduURLlist = AppConstants.Instance.LoadHindu();
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

                            if(isFromNotification.equals("true"))
                            {
                                 List<TheHinduArticle> temp = XmlParser.Instance.parse(in,bar,name);
                                 for(TheHinduArticle theHinduArticle : temp)
                                 {
                                     if(CheckTime1( theHinduArticle.getPubDate()))
                                     {
                                         list.add(theHinduArticle);
                                     }
                                 }

                            }
                            else
                            {
                                if(list==null)
                                    list=(XmlParser.Instance.parse(in,bar,name));
                                else
                                    list.addAll(XmlParser.Instance.parse(in,bar,name));
                            }


                            count++;
                        }

                    }
                    else if(name.equals("IE")){
                        List<String> IE;
                        IE = AppConstants.Instance.LoadIE();
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

                            if(isFromNotification.equals("true"))
                            {
                                List<TheHinduArticle> temp = IndianExpress.Instance.parse(in,bar,name);
                                for(TheHinduArticle theHinduArticle : temp)
                                {
                                    if(CheckTime1( theHinduArticle.getPubDate()))
                                    {
                                        list.add(theHinduArticle);
                                    }
                                }

                            }
                            else
                            {
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

                            }
                            count++;

                        }
                    }
                    else if(name.equals("LiveMint"))
                    {
                        List<String> LMList;
                        LMList=AppConstants.Instance.LoadLM();

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
                            if(isFromNotification.equals("true"))
                            {
                                List<TheHinduArticle> temp = LiveMint.Instance.parse(in,bar,name+" -  OPINION");
                                for(TheHinduArticle theHinduArticle : temp)
                                {
                                    if(CheckTime1( theHinduArticle.getPubDate()))
                                    {
                                        list.add(theHinduArticle);
                                    }
                                }

                            }
                            else
                            {
                                if(list==null)
                                {
                                    List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  OPINION");
                                    if(temp!=null)
                                        list=(temp);
                                }
                                else
                                {
                                    List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name);
                                    if(temp!=null)
                                        list.addAll(temp);
                                }
                            }

                            count++;
                        }
                    }
                    else if(name.equals("BusinessLine"))
                    {
                        List<String> BusinessLine;
                        BusinessLine=AppConstants.Instance.LoadBusinessLine();

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
                    else if(name.equals("Hindustan"))
                    {
                        List<String> Hindustan;
                        Hindustan=AppConstants.Instance.LoadHindustan();

                        int count=0;
                        while(count<2)
                        {
                            String geturl = Hindustan.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);

                            List<TheHinduArticle> temp;
                            if(count==0)
                            temp=LiveMint.Instance.parse(in,bar,name+" -  EDITORIAL");
                            else
                                temp=LiveMint.Instance.parse(in,bar,name+" -  OPINION");

                            if(temp!=null)
                            {
                                if(list==null)
                                    list=(temp);
                                else
                                    list.addAll(temp);
                            }

                            count++;
                        }
                    }
                    else if(name.equals("BusinessWorld"))
                    {

                        List<String> BusinessWorld;
                        BusinessWorld=AppConstants.Instance.LoadBusinessWorld();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = BusinessWorld.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);



                            List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" - ARTICLES");
                            if(temp!=null)
                            {

                                if(list==null)
                                    list=(temp);
                                else
                                    list.addAll(temp);
                            }

                            count++;
                        }
                    }
                    else if(name.equals("TamilHindu")) {

                        List<String> TamilHindu;
                        TamilHindu=AppConstants.Instance.LoadTamilHindu();


                        int count=0;
                        try{
                            while(count<2)
                            {
                                String geturl = TamilHindu.get(count);
                                URL url = new URL(geturl);
                                urlConnection = (HttpURLConnection) url.openConnection();

                                bar.setProgress(50);
                                urlConnection.setRequestMethod("GET");
                                urlConnection.connect();

                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                bar.setProgress(70);

                                List<TheHinduArticle> temp = null;
                                temp = (XmlParser.Instance.parse(in,bar,name));
                                if(temp!=null)
                                {
                                    if(list==null)
                                        list=temp;
                                    else
                                        list.addAll(temp);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"temp=null",Toast.LENGTH_LONG).show();
                                }

                                count++;
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }


                    }
                    else if(name.equals("Dinakaran"))
                    {
                        List<String> Dinakaran;
                        Dinakaran=AppConstants.Instance.LoadDinakaran();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = Dinakaran.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);

                            List<TheHinduArticle> temp = DinaKaran.Instance.parse(in,bar,name+" -  OPINION");
                            String str;

                            if(temp!=null)
                            {
                                for(int k=0;k<temp.size();k++)
                                {
                                    TheHinduArticle theHinduArticle = temp.get(k);
                                    if(theHinduArticle.getDescription().length()>150)
                                        str=theHinduArticle.getDescription().substring(0,150)+"...";
                                    else
                                        str = theHinduArticle.getDescription();
                                    theHinduArticle.SetDescription(str);
                                }

                            }

                            if(list==null)
                            {
                                list=(temp);
                            }
                            else
                            {
                                list.addAll(temp);
                            }
                            count++;
                        }
                    }
                    else if(name.equals("Jagraan"))
                    {

                        List<String> Jagraan;
                        Jagraan=AppConstants.Instance.LoadJagraan();


                        int count=0;
                        while(count<2)
                        {
                            String geturl = Jagraan.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  OPINION");
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
                    else if(name.equals("LiveHindustan"))
                    {
                        List<String> LiveHindustan;
                        LiveHindustan=AppConstants.Instance.LoadLiveHindustan();

                        int count=0;
                        while(count<2)
                        {
                            String geturl = LiveHindustan.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  OPINION");
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp;
                                if(count==0)
                                   temp=LiveMint.Instance.parse(in,bar,name+" - EDITORIAL");
                                else
                                    temp =LiveMint.Instance.parse(in,bar,name+" - OPINION");

                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }
                    }
                    else if(name.equals("Telegraph")) {

                        List<String> TelegraphList;
                        TelegraphList = AppConstants.Instance.LoadTelegraph();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = TelegraphList.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  OPINION");
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
                    else if(name.equals("Bhaskar")) {
                        List<String> BhaskarList;
                        BhaskarList = AppConstants.Instance.LoadBhaskar();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = BhaskarList.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  EDITORIAL");
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  EDITORIAL");
                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }

                    }

                    else if(name.equals("ETNow")) {
                        List<String> ETNow;
                        ETNow = AppConstants.Instance.LoadETNow();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = ETNow.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  Opinion");
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp =LiveMint.Instance.parse(in,bar,name+" -  Opinion");
                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }

                    }

                    else if(name.equals("Tribune")) {

                        List<String> Tribune;
                        Tribune = AppConstants.Instance.LoadTribune();

                        int count=0;
                        while(count<1)
                        {
                            String geturl = Tribune.get(count);
                            URL url = new URL(geturl);
                            urlConnection = (HttpURLConnection) url.openConnection();

                            bar.setProgress(50);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            bar.setProgress(70);
                            if(list==null)
                            {
                                List<TheHinduArticle> temp =XmlParser.Instance.parse(in,bar,name+" -  EDITORIAL");
                                if(temp!=null)
                                    list=(temp);
                            }
                            else
                            {
                                List<TheHinduArticle> temp =XmlParser.Instance.parse(in,bar,name+" -  EDITORIAL");
                                if(temp!=null)
                                    list.addAll(temp);
                            }
                            count++;
                        }

                    }
                    else if(name.equals("DinaMani"))
                    {

                        List<String> DinaMani;
                        DinaMani = AppConstants.Instance.LoadDinamani();

                        String geturl = DinaMani.get(0);
                        URL url = new URL(geturl);
                        urlConnection = (HttpURLConnection) url.openConnection();

                        bar.setProgress(50);
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        bar.setProgress(70);

                        if(list==null)
                        {
                            List<TheHinduArticle> temp = DinaManis.Instance.parse(in,bar,name);
                            if(temp!=null)
                                list=(temp);
                        }
                        else
                        {
                            List<TheHinduArticle> temp =DinaManis.Instance.parse(in,bar,name);
                            if(temp!=null)
                                list.addAll(temp);
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

//            if (result.equals("success"))
//                    setList(list);

            if(list!=null)
            {
                if(NoArticles.getVisibility()==View.VISIBLE)
                    NoArticles.setVisibility(View.GONE);

//                LoadingList.addAll(list);
//                homeAdapter = new HomeAdapter(MainActivity.this,list);
//
//            //recyclerView.setAnimation(new DefaultItemAnimator());
//                recyclerView.setAdapter(homeAdapter);
                homeAdapter.notifyDataSetChanged();
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


    //Format - Mon, 30 jul 2018 07:47:45 GMT
    public boolean CheckTime1(String time)
    {
        Date dt=new Date(),dtMorning=new Date(),dtNoon=new Date(),dtEvening=new Date(),dtNight=new Date(),dtInitial=new Date();
        String[] time1 = time.split(" ");
        String reqTime = time1[4];

        String DateTime = time1[1]+" "+ time1[2]+" "+time1[3]+" "+time1[4];


        //time for the given post from the newspaper's article
//        time1 = reqTime.split(":");
//        int hour1 = Integer.parseInt(time1[0]);  //07
//        int minute1 = Integer.parseInt(time1[1]); //47

        //some defined variables
        //from the intent getString
        //session, date, isFromNotification

        String[] timeMorning = MorningTime.split(" ");
        String[] timeNoon = Noontime.split(" ");
        String[] timeEvening = EveningTime.split(" ");
        String[] timeNight = NightTime.split(" ");

        //Datetime for the given Post Date and time
        Date currenthDate = new Date();
        System.out.println(currenthDate);
        //Datetime for the current Date and the time will be parsed by joining with the session time
        String[] currentDateString = (currenthDate.toString()).split(" ");
        String currentDateBit = currentDateString[2]+" "+currentDateString[1]+" "+currentDateString[5];
        String InitialDateTime = currentDateBit + " "+"00:00" ;
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        try {
            dt = dateFormat.parse(DateTime);
            dtInitial = dateFormat.parse(InitialDateTime);
            dtMorning = dateFormat.parse(currentDateBit+" "+timeMorning[0]+":"+timeMorning[1]);
            dtNoon = dateFormat.parse(currentDateBit+" "+timeNoon[0]+":"+timeNoon[1]);
            dtEvening = dateFormat.parse(currentDateBit+" "+timeEvening[0]+":"+timeEvening[1]);
            dtNight = dateFormat.parse(currentDateBit+" "+timeNight[0]+":"+timeNight[1]);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        switch (session)
        {
                //after morning from 00 to the morning sh
            case "Morning" :
                 if( (dt.compareTo(dtMorning) < 0) && (dt.compareTo(dtInitial)>0) )
                 {
                     return true;
                 }
                 break;
                //after morning , and before the noon
            case "Noon" :
                if( (dt.compareTo(dtMorning))>0  && (dt.compareTo(dtNoon)<0) )
                {
                    return true;
                }
                break;
                //after Noon , and before the Evening
            case "Evening" :
                if( (dt.compareTo(dtNoon))>0  && (dt.compareTo(dtEvening)<0) )
                {
                    return true;
                }
                break;
                //after Evening , and before the Night
            case "Night" :
                if((dt.compareTo(dtEvening))>0  && (dt.compareTo(dtNight)<0) )
                {
                    return true;
                }
                break;
        }


        return false;
    }


//    public void TimingUpdate()
//    {
//
//    }
}
