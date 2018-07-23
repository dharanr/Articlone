package dharanyuvi.android.com.articles;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class Article_Webview extends AppCompatActivity {

    WebView webView;
    String url, title = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        ImageView imageView = findViewById(R.id.cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebView webView = findViewById(R.id.web);
        url = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("URL");
            title = extras.getString("Title");
            if ((extras.getString("Bhaskar").equalsIgnoreCase(true + "")) || (extras.getString("ETNow").equalsIgnoreCase(true + ""))
                    || (extras.getString("DinaMani").equalsIgnoreCase(true + "")))
                webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);

        }

        ImageView share = findViewById(R.id.share);
        ImageView pocket = findViewById(R.id.Pocket);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl();
            }
        });

        pocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePocket();
            }
        });

    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Hey!! I found this Interesting for you..." + title);
        share.putExtra(Intent.EXTRA_TEXT, url);

        startActivity(Intent.createChooser(share, "Share link!"));
    }


    private void sharePocket() {

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        if(info.activityInfo.packageName.contains("pocket"))  {
//            intent.putExtra(Intent.EXTRA_TEXT, url);
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.ideashower.readitlater.pro", PackageManager.GET_ACTIVITIES);
            intent.putExtra(Intent.EXTRA_TEXT, url);
            startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            // Pocket app not installed
            Toast.makeText(Article_Webview.this,"Pocket App not installed",Toast.LENGTH_LONG).show();
        }

//        Intent i =new Intent("com.example.intent.action.Dream");
//        i.setPackage(""); //The package name of the app to which intent is to be sent
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);

        }
    }
