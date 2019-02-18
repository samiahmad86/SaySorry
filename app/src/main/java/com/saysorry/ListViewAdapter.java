package com.saysorry;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.saysorry.ItemData;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<String> {
    int groupid;
    Activity context;
    ArrayList<String> list;
    LayoutInflater inflater;
    public ListViewAdapter(Activity context, int groupid, int id, ArrayList<String>
            list){
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);
        TextView textView=(TextView)itemView.findViewById(R.id.tv_time);
        textView.setText(list.get(position));
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }

}