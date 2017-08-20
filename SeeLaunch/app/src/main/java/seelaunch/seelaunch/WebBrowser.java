package seelaunch.seelaunch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

public class WebBrowser extends AppCompatActivity {
    public WebView webView1;
    EditText SearchText;
    Button GoButton;
    public  String currenturl;
    public  Button MainMenuButton;
    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_browser);
        getSupportActionBar().hide();
        MainMenuButton=(Button)findViewById(R.id.btn_mainmenu);
        SearchText=(EditText)findViewById(R.id.editText_search);
        GoButton=(Button) findViewById(R.id.btn_go);
        webView1=(WebView)findViewById(R.id.webview_browser);
        WebSettings webSettings=webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        relativeLayout=(RelativeLayout) findViewById(R.id.id_relative);
        MainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PopupMenu popup = new PopupMenu(WebBrowser.this, MainMenuButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.mainscreenmenu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.id_changetheme1)
                        {
                         GoButton.setBackgroundColor(Color.parseColor("#808080"));
                            relativeLayout.setBackgroundColor(Color.parseColor("#FF7F50"));


                        }
                        if(item.getItemId()==R.id.id_changetheme2)
                        {
                            GoButton.setBackgroundColor(Color.parseColor("#F0E68C"));
                            relativeLayout.setBackgroundColor(Color.parseColor("#808080"));



                        }
                        if(item.getItemId()==R.id.id_changetheme3)
                        {
                            GoButton.setBackgroundColor(Color.parseColor("#F5DEB3"));
                            relativeLayout.setBackgroundColor(Color.parseColor("#FF8C00"));



                        }
                        if(item.getItemId()==R.id.id_changethemereset)
                        {
                            GoButton.setBackgroundColor(Color.parseColor("#4CAF50"));
                            relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));



                        }

                        return true;
                    }
                });

                popup.show();
            }
        });


        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchstring= SearchText.getText().toString();
                if(searchstring.contains("http://"))
                {
                    webView1.loadUrl(searchstring);
                    currenturl=webView1.getUrl();
                    SearchText.setText(currenturl);


                }
                else
                {                    webView1.loadUrl("http://"+searchstring);
                                     currenturl=webView1.getUrl();
                                     SearchText.setText(currenturl);

                }
            }
        });
        webView1.loadUrl("https://www.google.com");
       webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setDisplayZoomControls(false);
        webView1.setWebViewClient(new WebViewController());


    }


    public class WebViewController extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }
    }

    @Override
    public void onBackPressed()
    {
        if(webView1.canGoBack())
            webView1.goBack();
        else
            super.onBackPressed();
    }

}
