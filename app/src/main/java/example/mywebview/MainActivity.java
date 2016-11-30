package example.mywebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.my_webview);
        button = (Button) findViewById(R.id.my_btn);
        textView = (TextView) findViewById(R.id.my_tv);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new AndroidBridge(textView),"android");

        webView.loadUrl("file:///android_asset/frist.html");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.loadUrl("javascript:useJs()");
            }
        });


    }

    private class AndroidBridge {
        private TextView tv;
        public AndroidBridge(TextView tv){
            this.tv = tv;
        }
        @JavascriptInterface
        public void changeText(){
            tv.setText("收到消息");
        }
    }
}
