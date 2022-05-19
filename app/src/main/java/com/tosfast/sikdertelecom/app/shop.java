package com.tosfast.sikdertelecom.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class shop extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webView;
    private AlertDialog.Builder dialogeBuilder;
    private AlertDialog myDialog;
    private AlertDialog myDialog3;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        webView= findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                loadErrorPage(view);
            }
        });

        webView.loadUrl("https://sikdertelecom.com/shop");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageButton home = findViewById(R.id.home);
        ImageButton cart = findViewById(R.id.Contact);
        ImageButton shop = findViewById(R.id.Shop);

        //ImageButton b=findViewById(R.id.menu);
        //b.setOnClickListener(this::showPopup);

        home.setOnClickListener(v -> home());
        shop.setOnClickListener(v -> shop());
        cart.setOnClickListener(v -> contact());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        //MenuInflater inflater = popup.getMenuInflater();
        popup.inflate(R.menu.dropdown_menu);
        popup.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        if(item.getItemId()==R.id.exit){
            exit();
            return true;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    void catagory(){
        Intent i = new Intent(this, catagoryPage.class);
        startActivity(i);
    }

    void exit(){
        dialogeBuilder = new AlertDialog.Builder(this);
        final View contentpopupview = getLayoutInflater().inflate(R.layout.exitpopup,null);
        dialogeBuilder.setView(contentpopupview);
        myDialog= dialogeBuilder.create();
        myDialog.show();
        Button exitt= contentpopupview.findViewById(R.id.eixtreally);
        Button goback= contentpopupview.findViewById(R.id.goback);
        exitt.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });
        goback.setOnClickListener(v -> myDialog.cancel());

    }

    void home(){
        Intent intent = new Intent(this, webview.class);
        startActivity(intent);
    }

    void shop(){
        Intent intent= new Intent(this, shop.class);
        startActivity(intent);
    }

    void contact(){
        Intent intent= new Intent(this, cart.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public void loadErrorPage(WebView webview){
        if(webview!=null){
            webview.loadUrl("file:///android_asset/error_show.html");
            webview.invalidate();
        }
    }

}