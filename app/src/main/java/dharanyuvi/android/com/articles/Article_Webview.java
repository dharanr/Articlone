package dharanyuvi.android.com.articles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
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
            webView.getSettings().setUseWideViewPort(true);
            webView.loadUrl(url);

        }

    }
}
