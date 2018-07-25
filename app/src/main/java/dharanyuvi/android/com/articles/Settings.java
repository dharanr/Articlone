package dharanyuvi.android.com.articles;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import dharanyuvi.android.com.articles.utilities.SharedPreference;

public class Settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    int hour;
    int minute;
    AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    PendingIntent pendingIntent1;
    PendingIntent pendingIntent2;
    PendingIntent pendingIntent3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settins_main);
        final LinearLayout linearLayout = findViewById(R.id.list);
        final LinearLayout linearLayout1 = findViewById(R.id.list1);
        final LinearLayout linearLayout2 = findViewById(R.id.list2);
        final LinearLayout linearLayout3 = findViewById(R.id.list3);

        final ImageView imageView = findViewById(R.id.down1);
        final ImageView imageView1 = findViewById(R.id.down2);
        final ImageView imageView2 = findViewById(R.id.down3);
        final ImageView imageView3 = findViewById(R.id.down4);

        final TextView MorningText = findViewById(R.id.time);
        final TextView NoonText = findViewById(R.id.time2);
        final TextView EveningText = findViewById(R.id.time3);
        final TextView NightText = findViewById(R.id.time4);



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
        Switch sh14 = findViewById(R.id.SwitchDinamani);
        Switch sh15 = findViewById(R.id.SwitchMorning);
        Switch sh16 = findViewById(R.id.SwitchNoon);
        Switch sh17 = findViewById(R.id.SwitchEvening);
        Switch sh18 = findViewById(R.id.SwitchNight);




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
        String DinaMani =SharedPreference.Instance.read(getApplicationContext(),"DinaMani");
        String Morning =SharedPreference.Instance.read(getApplicationContext(),"Morning");
        String Noon =SharedPreference.Instance.read(getApplicationContext(),"Noon");
        String Evening =SharedPreference.Instance.read(getApplicationContext(),"Evening");
        String Night =SharedPreference.Instance.read(getApplicationContext(),"Night");


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

        //Toggle control - DinaMani
        if(DinaMani.equals("true"))
        {
            sh14.setChecked(true);
        }
        else
            sh14.setChecked(false);

        //Toggle control - Morning
        if(Morning.equals("true"))
        {
            sh15.setChecked(true);
            String time = SharedPreference.Instance.read(Settings.this,"MorningTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            MorningText.setText(time2);
            MorningText.setTextColor(getResources().getColor(R.color.Black));

        }
        else
        {
            sh15.setChecked(false);
            String time = SharedPreference.Instance.read(Settings.this,"MorningTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            MorningText.setText(time2);
            MorningText.setTextColor(getResources().getColor(R.color.colorAccent));
        }


        //Toggle control - Noon
        if(Noon.equals("true"))
        {
            sh16.setChecked(true);
            String time = SharedPreference.Instance.read(Settings.this,"NoonTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            NoonText.setText(time2);
            NoonText.setTextColor(getResources().getColor(R.color.Black));
        }
        else
        {
            sh16.setChecked(false);
            String time = SharedPreference.Instance.read(Settings.this,"NoonTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            NoonText.setText(time2);
            NoonText.setTextColor(getResources().getColor(R.color.colorAccent));
        }


        //Toggle control - Evening
        if(Evening.equals("true"))
        {
            sh17.setChecked(true);
            String time = SharedPreference.Instance.read(Settings.this,"EveningTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            EveningText.setText(time2);
            EveningText.setTextColor(getResources().getColor(R.color.Black));        }
        else {
            sh17.setChecked(false);
            String time = SharedPreference.Instance.read(Settings.this,"EveningTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            EveningText.setText(time2);
            EveningText.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        //Toggle control - Night
        if(Night.equals("true"))
        {
            sh18.setChecked(true);
            String time = SharedPreference.Instance.read(Settings.this,"NightTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            NightText.setText(time2);
            NightText.setTextColor(getResources().getColor(R.color.Black));
        }
        else
        {
            sh18.setChecked(false);
            String time = SharedPreference.Instance.read(Settings.this,"NightTime");
            String[] time1 = time.split(" ");
            String time2 = time1[0]+" : "+time1[1];
            NightText.setText(time2);
            NightText.setTextColor(getResources().getColor(R.color.colorAccent));
        }


        RelativeLayout relativeLayout = findViewById(R.id.wish);
        relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(linearLayout.getVisibility()==View.GONE)
                {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));
                }
            }
        });

        RelativeLayout General = findViewById(R.id.General);
        General.setOnClickListener(new View.OnClickListener(){
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

        RelativeLayout Business = findViewById(R.id.Business);
        Business.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(linearLayout1.getVisibility()==View.GONE)
                {
                    linearLayout1.setVisibility(View.VISIBLE);
                    imageView1.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    linearLayout1.setVisibility(View.GONE);
                    imageView1.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));

                }
            }
        });


        RelativeLayout Hindi = findViewById(R.id.Hindi);
        Hindi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(linearLayout2.getVisibility()==View.GONE)
                {
                    linearLayout2.setVisibility(View.VISIBLE);
                    imageView2.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    linearLayout2.setVisibility(View.GONE);
                    imageView2.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));

                }
            }
        });


        RelativeLayout Tamil = findViewById(R.id.Tamil);
        Tamil.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(linearLayout3.getVisibility()==View.GONE)
                {
                    linearLayout3.setVisibility(View.VISIBLE);
                    imageView3.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    linearLayout3.setVisibility(View.GONE);
                    imageView3.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));

                }
            }
        });


        RelativeLayout Digest = findViewById(R.id.Digest);
        final LinearLayout DigestLayout = findViewById(R.id.digest);
        final ImageView DigestImage = findViewById(R.id.digestdown);
        Digest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(DigestLayout.getVisibility()==View.GONE)
                {
                    DigestLayout.setVisibility(View.VISIBLE);
                    DigestImage.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.up));
                }
                else
                {
                    DigestLayout.setVisibility(View.GONE);
                    DigestImage.setImageDrawable(ContextCompat.getDrawable(Settings.this, R.drawable.down));

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

        //Toggle function for the Dinamani
        sh14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"DinaMani",true);
                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"DinaMani",false);
                }

            }
        });

        //Toggle function for the Morning
        sh15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Morning",true);
                       TimePickerDialog timePickerDialog = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                           SetTimeFromTimePicker(hourOfDay,minute);

                            String time=hour+" "+minute;
                            SharedPreference.Instance.storeDigest(Settings.this,"MorningTime",time);

                            //Morning
                            alarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarmIntent = new Intent(Settings.this, Broadcast.class);
                            alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
                            alarmIntent.putExtra("time","morning");
                            // AlarmReceiver1 = broadcast receiver
                            pendingIntent = PendingIntent.getBroadcast(  Settings.this, 0, alarmIntent, 0);
                            Calendar alarmStartTime = AlarmManagerBroadcast.Instance.Alarm(Settings.this,hourOfDay,minute);
                            alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), android.app.AlarmManager.INTERVAL_DAY, pendingIntent);

                            time=hour+":"+minute;
                            MorningText.setText(time);
                            MorningText.setTextColor(getResources().getColor(R.color.Black));


                        }
                       },hour,minute,true);
                               timePickerDialog.show();

                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Morning",false);
                    //gets the morning value from the shared preferences!!
                    String time = SharedPreference.Instance.read(Settings.this,"MorningTime");
                    String[] timesplit = time.split(" ");
                    String timeString= timesplit[0]+" : "+timesplit[1];

                    //Noon


                    MorningText.setText(timeString);
                    MorningText.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });


        //Toggle function for the Noon
        sh16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Noon",true);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            SetTimeFromTimePicker(hourOfDay,minute);

                            String time=hour+" "+minute;
                            SharedPreference.Instance.storeDigest(Settings.this,"NoonTime",time);

                            alarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarmIntent = new Intent(Settings.this, Broadcast.class);
                            alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
                            alarmIntent.putExtra("time","noon");
                            pendingIntent1 = PendingIntent.getBroadcast(  Settings.this, 1, alarmIntent, 0);
                            Calendar alarmStartTime1 = AlarmManagerBroadcast.Instance.Alarm(Settings.this,hourOfDay,minute);
                            alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP, alarmStartTime1.getTimeInMillis(), android.app.AlarmManager.INTERVAL_DAY, pendingIntent1);

                            time=hour+":"+minute;
                            NoonText.setText(time);
                            NoonText.setTextColor(getResources().getColor(R.color.Black));

                        }
                    },hour,minute,true);
                    timePickerDialog.show();

                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Noon",false);
                    //gets the morning value from the shared preferences!!
                    String time = SharedPreference.Instance.read(Settings.this,"NoonTime");
                    String[] timesplit = time.split(" ");
                    String timeString= timesplit[0]+" : "+timesplit[1];

                    NoonText.setText(timeString);
                    NoonText.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });


        //Toggle function for the Evening
        sh17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Evening",true);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            SetTimeFromTimePicker(hourOfDay,minute);

                            String time=hour+" "+minute;
                            SharedPreference.Instance.storeDigest(Settings.this,"EveningTime",time);

                            alarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarmIntent = new Intent(Settings.this, Broadcast.class);
                            alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
                            alarmIntent.putExtra("time","evening");
                            pendingIntent2 = PendingIntent.getBroadcast(  Settings.this, 2, alarmIntent, 0);
                            Calendar alarmStartTime2 = AlarmManagerBroadcast.Instance.Alarm(Settings.this,hourOfDay,minute);
                            alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP, alarmStartTime2.getTimeInMillis(), android.app.AlarmManager.INTERVAL_DAY, pendingIntent2);

                            time=hour+":"+minute;
                            EveningText.setText(time);
                            EveningText.setTextColor(getResources().getColor(R.color.Black));

                        }
                    },hour,minute,true);
                    timePickerDialog.show();

                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Evening",false);
                    //gets the morning value from the shared preferences!!
                    String time = SharedPreference.Instance.read(Settings.this,"EveningTime");
                    String[] timesplit = time.split(" ");
                    String timeString= timesplit[0]+" : "+timesplit[1];

                    EveningText.setText(timeString);
                    EveningText.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });



        //Toggle function for the Night
        sh18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Night",true);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            SetTimeFromTimePicker(hourOfDay,minute);

                            String time=hour+" "+minute;
                            SharedPreference.Instance.storeDigest(Settings.this,"NightTime",time);

                            //Night
                            alarmManager = (android.app.AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            alarmIntent = new Intent(Settings.this, Broadcast.class);
                            alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
                            alarmIntent.putExtra("time","night");
                            pendingIntent3 = PendingIntent.getBroadcast(  Settings.this, 3, alarmIntent, 0);
                            Calendar alarmStartTime3 = AlarmManagerBroadcast.Instance.Alarm(Settings.this,hourOfDay,minute);
                            alarmManager.setRepeating(android.app.AlarmManager.RTC_WAKEUP, alarmStartTime3.getTimeInMillis(), android.app.AlarmManager.INTERVAL_DAY, pendingIntent3);

                            time=hour+":"+minute;
                            NightText.setText(time);
                            NightText.setTextColor(getResources().getColor(R.color.Black));

                        }
                    },hour,minute,true);
                    timePickerDialog.show();

                } else {
                    SharedPreference.Instance.storeWishList(getApplicationContext(),"Night",false);
                    //gets the morning value from the shared preferences!!
                    String time = SharedPreference.Instance.read(Settings.this,"NightTime");
                    String[] timesplit = time.split(" ");
                    String timeString= timesplit[0]+" : "+timesplit[1];

                    NightText.setText(timeString);
                    NightText.setTextColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });

//        //Toggle function for the Dinamani
//        sh14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    SharedPreference.Instance.storeWishList(getApplicationContext(),"DinaMani",true);
//                } else {
//                    SharedPreference.Instance.storeWishList(getApplicationContext(),"DinaMani",false);
//                }
//
//            }
//        });


    }




//

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    public void SetTimeFromTimePicker(int Hour,int Minute)
    {
        hour = Hour;
        minute = Minute;
        String time = "Selected "+hour+" : "+minute;
        Toast.makeText(Settings.this,time,Toast.LENGTH_LONG).show();
    }

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
