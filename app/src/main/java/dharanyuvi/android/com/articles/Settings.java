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
        Switch sh2 = findViewById(R.id.SwitchLiveMint);
        Switch sh3 = findViewById(R.id.SwitchBusinessLine);
        Switch sh4 = findViewById(R.id.SwitchHindustan);
        Switch sh5 = findViewById(R.id.SwitchBusinessWorld);
        Switch sh6 = findViewById(R.id.SwitchTamilHindu);
        Switch sh7 = findViewById(R.id.SwitchDinakaran);
        Switch sh8 = findViewById(R.id.SwitchJagraan);
        Switch sh9 = findViewById(R.id.SwitchLiveHindustan);
        Switch sh10 = findViewById(R.id.SwitchTelegraph);
        Switch sh11 = findViewById(R.id.SwitchBhaskar);
        Switch sh12 = findViewById(R.id.SwitchETNow);
        Switch sh13 = findViewById(R.id.SwitchTribune);



        String TheHinduToggleValue = SharedPreference.Instance.read(getApplicationContext(),"TheHindu");
        String TheIndianExpress =SharedPreference.Instance.read(getApplicationContext(),"IE");
        String LiveMint =SharedPreference.Instance.read(getApplicationContext(),"LiveMint");
        String BusinessLine =SharedPreference.Instance.read(getApplicationContext(),"BusinessLine");
        String Hindustan =SharedPreference.Instance.read(getApplicationContext(),"Hindustan");
        String BusinessWorld =SharedPreference.Instance.read(getApplicationContext(),"BusinessWorld");
        String TamilHindu =SharedPreference.Instance.read(getApplicationContext(),"TamilHindu");
        String Dinakaran =SharedPreference.Instance.read(getApplicationContext(),"Dinakaran");
        String Jagraan =SharedPreference.Instance.read(getApplicationContext(),"Jagraan");
        String LiveHindustan =SharedPreference.Instance.read(getApplicationContext(),"LiveHindustan");
        String Telegraph =SharedPreference.Instance.read(getApplicationContext(),"Telegraph");
        String Bhaskar =SharedPreference.Instance.read(getApplicationContext(),"Bhaskar");
        String ETNow =SharedPreference.Instance.read(getApplicationContext(),"ETNow");
        String Tribune =SharedPreference.Instance.read(getApplicationContext(),"Tribune");


        //Toggle control - the hindu
        if(TheHinduToggleValue.equals("true"))
        {
            sh.setChecked(true);
        }
        else
            sh.setChecked(false);

        //Toggle control - IndianExpress
        if(TheIndianExpress.equals("true"))
        {
            sh1.setChecked(true);
        }
        else
            sh1.setChecked(false);

        //Toggle control - LivesMint
        if(LiveMint.equals("true"))
        {
            sh2.setChecked(true);
        }
        else
            sh2.setChecked(false);

        //Toggle control - BusinessLine
        if(BusinessLine.equals("true"))
        {
            sh3.setChecked(true);
        }
        else
            sh3.setChecked(false);

        //Toggle control - Hindustan
        if(Hindustan.equals("true"))
        {
            sh4.setChecked(true);
        }
        else
            sh4.setChecked(false);

        //Toggle control - BusinessWorld
        if(BusinessWorld.equals("true"))
        {
            sh5.setChecked(true);
        }
        else
            sh5.setChecked(false);

        //Toggle control - TamilHindu
        if(TamilHindu.equals("true"))
        {
            sh6.setChecked(true);
        }
        else
            sh6.setChecked(false);

        //Toggle control - Dinakaran
        if(Dinakaran.equals("true"))
        {
            sh7.setChecked(true);
        }
        else
            sh7.setChecked(false);

        //Toggle control - Jagraan
        if(Jagraan.equals("true"))
        {
            sh8.setChecked(true);
        }
        else
            sh8.setChecked(false);

        //Toggle control - LiveHindustan
        if(LiveHindustan.equals("true"))
        {
            sh9.setChecked(true);
        }
        else
            sh9.setChecked(false);

        //Toggle control - Telegraph
        if(Telegraph.equals("true"))
        {
            sh10.setChecked(true);
        }
        else
            sh10.setChecked(false);


        //Toggle control - Bhaskar
        if(Bhaskar.equals("true"))
        {
            sh11.setChecked(true);
        }
        else
            sh11.setChecked(false);

        //Toggle control - ETNow
        if(ETNow.equals("true"))
        {
            sh12.setChecked(true);
        }
        else
            sh12.setChecked(false);

        //Toggle control - BS
        if(Tribune.equals("true"))
        {
            sh13.setChecked(true);
        }
        else
            sh13.setChecked(false);


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

        //Toggle function for the IE
        sh1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"IE",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"IE",false);
                }
            }
        });

        //Toggle function for the LM
        sh2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"LiveMint",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"LiveMint",false);
                }
            }
        });

        //Toggle function for the BusinessLine
        sh3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"BusinessLine",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"BusinessLine",false);
                }
            }
        });

        //Toggle function for the Hindustan
        sh4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Hindustan",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Hindustan",false);
                }
            }
        });

        //Toggle function for the BusinessWorld
        sh5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"BusinessWorld",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"BusinessWorld",false);
                }
            }
        });

        //Toggle function for the TamilHindu
        sh6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"TamilHindu",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"TamilHindu",false);
                }
            }
        });

        //Toggle function for the Dinakaran
        sh7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Dinakaran",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Dinakaran",false);
                }
            }
        });

        //Toggle function for the Jagraan
        sh8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Jagraan",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Jagraan",false);
                }
            }
        });

        //Toggle function for the LiveHindustan
        sh9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"LiveHindustan",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"LiveHindustan",false);
                }
            }
        });

        //Toggle function for the Telegraph
        sh10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Telegraph",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Telegraph",false);
                }
            }
        });


        //Toggle function for the Telegraph
        sh11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Bhaskar",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Bhaskar",false);
                }
            }
        });

        //Toggle function for the ETNow
        sh12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"ETNow",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"ETNow",false);
                }
            }
        });

        //Toggle function for the ETNow
        sh13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Tribune",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Tribune",false);
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
