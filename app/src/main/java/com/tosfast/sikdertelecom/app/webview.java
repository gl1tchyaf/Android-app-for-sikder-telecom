package com.tosfast.sikdertelecom.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.Menu;
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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class webview extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private WebView webView;
    private AlertDialog.Builder dialogeBuilder;
    private AlertDialog myDialog;
    private AlertDialog myDialog3;

    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView= findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                loadErrorPage(view);
            }
        });
        webView.loadUrl("https://sikdertelecom.com/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        ImageButton home = findViewById(R.id.home);
        ImageButton cart = findViewById(R.id.Contact);
        ImageButton shop = findViewById(R.id.Shop);

        //ImageButton b=findViewById(R.id.menu);
        //b.setOnClickListener(this::showPopup);

        cart.setOnClickListener(v -> contact());
        shop.setOnClickListener(v -> shop());
        home.setOnClickListener(v -> home());



        setSupportActionBar(findViewById(R.id.toolbar));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_draw);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if(item.getItemId()==R.id.about){
            aboutus();
            return true;
        }
        if(item.getItemId()==R.id.exit){
            exit();
            return true;
        }
        else{
            return false;
        }

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

    void contact(){
        Intent intent= new Intent(this, cart.class);
        startActivity(intent);
    }

    void shop(){
        Intent intent= new Intent(this, shop.class);
        startActivity(intent);
    }

    void home(){
        Intent intent= new Intent(this, webview.class);
        startActivity(intent);
    }

    void aboutus(){
        dialogeBuilder = new AlertDialog.Builder(this);
        final View contentpopupview = getLayoutInflater().inflate(R.layout.aboutus,null);
        dialogeBuilder.setView(contentpopupview);
        myDialog3= dialogeBuilder.create();
        myDialog3.show();
        Button ok= contentpopupview.findViewById(R.id.okabout);
        TextView tosfast= contentpopupview.findViewById(R.id.tosfast);

        TextView textView = contentpopupview.findViewById(R.id.about);
        SpannableString content = new SpannableString("Tosfast ltd");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
        tosfast.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/fantosfast"));
            startActivity(intent);
        });
        ok.setOnClickListener(v -> myDialog3.cancel());
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            exit();
            //super.onBackPressed();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_draw, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_draw);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}