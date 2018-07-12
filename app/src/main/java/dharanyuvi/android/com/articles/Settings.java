package dharanyuvi.android.com.articles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class Settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settins_main);
        final LinearLayout linearLayout = findViewById(R.id.list);
        final ImageView imageView = findViewById(R.id.down);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);

        //Code for make drawer open and close
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigationBar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Switch sh = findViewById(R.id.SwitchHindu);
        Switch sh1 = findViewById(R.id.SwitchIndianExpress);
        String TheHinduToggleValue = SharedPreference.Instance.read(getApplicationContext(),"TheHindu");
        if(TheHinduToggleValue.equals("true"))
        {
            sh.setChecked(true);
        }
        else
            sh.setChecked(false);

        RelativeLayout relativeLayout = findViewById(R.id.wish);
        relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(linearLayout.getVisibility()==View.GONE)
                {
                    linearLayout.setVisibility(View.VISIBLE);
                       imageView.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    linearLayout.setVisibility(View.GONE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));

                }
            }
        });




        //Toggle function for the hindu
        sh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"TheHindu",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"TheHindu",false);
                }
            }
        });





    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(Settings.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Settings.this,Article_Webview.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }




}
