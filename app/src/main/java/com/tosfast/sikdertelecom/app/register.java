package com.tosfast.sikdertelecom.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private WebView webView;
    private AlertDialog.Builder dialogeBuilder;
    private AlertDialog myDialog;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        webView= findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sikdertelecom.com/register");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Button home = findViewById(R.id.home);
        Button contact = findViewById(R.id.Contact);
        Button shop = findViewById(R.id.Shop);

        ImageButton b=findViewById(R.id.menu);
        b.setOnClickListener(this::showPopup);

        contact.setOnClickListener(v -> contact());
        shop.setOnClickListener(v -> shop());
        home.setOnClickListener(v -> home());

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
        if(item.getItemId()==R.id.login){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            return true;
        }
        if(item.getItemId()==R.id.register){
            Intent i = new Intent(this, register.class);
            startActivity(i);
            return true;
        }
        if(item.getItemId()==R.id.wholesaller){
            Intent i = new Intent(this, wholesaller.class);
            startActivity(i);
            return true;
        }
        if(item.getItemId()==R.id.about){
            System.out.println("about");
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
        Intent intent= new Intent(this, contact.class);
        startActivity(intent);
    }

    void shop(){
        Intent intent= new Intent(this, shop.class);
        startActivity(intent);
    }

    void home(){
        Intent intent = new Intent(this, webview.class);
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

}