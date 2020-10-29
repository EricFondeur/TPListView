package com.example.listview3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class AdapterPerso extends BaseAdapter {

    private Context leContext;
    private LayoutInflater objeInflater;
    private ArrayList<Mois> LesMois;
    public AdapterPerso (Context context, ArrayList<Mois> ArrayMois){
        leContext = context;
        objeInflater= LayoutInflater.from(context);
        LesMois = ArrayMois;
    }

    public ArrayList<Mois> getArrayMois(){
        return LesMois;
    }

    @Override
    public int getCount() {
        return LesMois.size();
    }

    @Override
    public Object getItem(int position) {
        return LesMois.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout ItemView;
        if (convertView == null) {
            ItemView=(ConstraintLayout)objeInflater.inflate(R.layout.itemviewperso, parent, false);
        }else{
            ItemView=(ConstraintLayout) convertView;
        }
        TextView mois = ItemView.findViewById(R.id.Mois);
        if (LesMois.get(position).est_affiche()){
            mois.setText(LesMois.get(position).toString());
        }
        if(LesMois.get(position).isSelecte()){
            mois.setBackgroundColor(Color.parseColor("#6E7783"));
        }else{
            mois.setBackgroundColor(Color.parseColor("#FFFFF3"));
        }
        return ItemView;
    }
}
