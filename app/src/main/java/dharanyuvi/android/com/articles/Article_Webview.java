package dharanyuvi.android.com.articles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Article_Webview extends AppCompatActivity {

    WebView webView;

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
        String url = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("URL");
            if( (extras.getString("Bhaskar").equalsIgnoreCase(true+"")) || (extras.getString("ETNow").equalsIgnoreCase(true+""))
                    || (extras.getString("DinaMani").equalsIgnoreCase(true+"")))
                webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);

        }

    }
}
