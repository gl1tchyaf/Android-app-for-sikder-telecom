package com.tosfast.sikdertelecom.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class catagoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<catagoryArrayList> arrayList;
    int userOrWorker;
    public catagoryAdapter(Context context, ArrayList<catagoryArrayList> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.biddingcustomview, parent, false);



        catagoryArrayList catagoryArrayList = arrayList.get(position);
        TextView catagory = rowView.findViewById(R.id.catagoryShow);
        catagory.setText(catagoryArrayList.getName());

        System.out.println(catagoryArrayList.getName());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, webview.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("catagoryLink", catagoryArrayList.getLink());
                context.startActivity(i);
            }
        });

        return rowView;
    }

//    private void problemopenpage() {
//        Intent intent = new Intent(context, problemOpen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("postid",id);
//        intent.putExtra("personid",personid);
//        context.startActivity(intent);
//    }
}
