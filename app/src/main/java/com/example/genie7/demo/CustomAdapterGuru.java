package com.example.genie7.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by genie7 on 11/7/16.
 */
public class CustomAdapterGuru extends BaseAdapter {


    private Activity context;
    private ArrayList<ModelClassGuru> mlist;
    private static LayoutInflater inflater;


    public CustomAdapterGuru(Activity context, ArrayList<ModelClassGuru> rlist) {

        this.context = context;
        this.mlist = rlist;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }


    private class ViewHolder {
        public de.hdodenhof.circleimageview.CircleImageView ivGuruimage;
        public TextView tvGuruname;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder holder;
        View rowview = view;

        if (rowview == null) {

            rowview = inflater.inflate(R.layout.rowgurunamelayout, parent, false);
            holder = new ViewHolder();
            holder.tvGuruname = (TextView) rowview.findViewById(R.id.tv_guruname);
            holder.ivGuruimage = (de.hdodenhof.circleimageview.CircleImageView) rowview.findViewById(R.id.profileImage);


            rowview.setTag(holder);


        } else {

            holder = (ViewHolder) rowview.getTag();
        }
        holder.tvGuruname.setText(mlist.get(position).getName());
        try {

            Picasso.with(context).load(mlist.get(position).getProfile()).into(holder.ivGuruimage);

        }catch (Exception e){
            e.printStackTrace();
        }

            return rowview;
        }

    }