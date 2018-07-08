package dharanyuvi.android.com.articles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dharanyuvi.android.com.articles.XmlParsing.XmlParser;
import dharanyuvi.android.com.articles.adpaters.HomeAdapter;
import dharanyuvi.android.com.articles.models.TheHinduArticle;
import dharanyuvi.android.com.articles.utilities.NetInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String a;
    TextView textView;
    private ProgressBar simpleProgressBar;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout relativeLayout;
    WebView webView;
    TextView no_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);



        simpleProgressBar=findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(100); // 100 maximum value for the progress value
        simpleProgressBar.setProgress(20); // 50 default progress value for the progress bar

        simpleProgressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        relativeLayout = findViewById(R.id.web);
        webView = findViewById(R.id.nointernet);
        no_connection =findViewById(R.id.nointernettext);



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


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        //checking for the internet connection for the application
        if(NetInfo.Instance.checkConnection(getApplicationContext()))
        {
            TheHindu task =new TheHindu();
            task.setProgressBar(simpleProgressBar);
            task.execute();
        }
        else
        {
            webView.loadDataWithBaseURL("file:///android_res/drawable/", "<img src='"+ "nointernet" + "' style='width:100%' />", "text/html", "utf-8", null);
            relativeLayout.setVisibility(View.VISIBLE);
            no_connection.setVisibility(View.VISIBLE);
        }


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
            super.onBackPressed();
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void doYourUpdate() {
        swipeRefreshLayout.setRefreshing(false);
        if(NetInfo.Instance.checkConnection(getApplicationContext()))
        {
            if(relativeLayout.getVisibility()==View.VISIBLE) {
                relativeLayout.setVisibility(View.GONE);
                no_connection.setVisibility(View.GONE);
            }
            recyclerView.setVisibility(View.VISIBLE);
            TheHindu task =new TheHindu();
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
        private List<TheHinduArticle> list = new ArrayList<>();

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
            try {
                bar.setProgress(30);
                URL url = new URL(AppConstants.TheHinduUrl);
                list=null;

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    bar.setProgress(50);
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();


                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    bar.setProgress(60);

                    list = XmlParser.Instance.parse(in,bar);

                } finally {
                    urlConnection.disconnect();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success";
        }


        @Override
        protected void onPostExecute(String result) {
            bar.setVisibility(View.INVISIBLE);

            if (result.equals("success"))
                setList(list);

            homeAdapter = new HomeAdapter(MainActivity.this,list);

            //recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(homeAdapter);

            bar.setProgress(100);

        }

        private void setProgressBar(ProgressBar bar) {
            this.bar = bar;
        }
    }
}
