package com.alexneka.msttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexneka.msttest.R;
import com.alexneka.msttest.model.ITunesContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ITunesAdapter extends BaseAdapter {

    private List<ITunesContent> list = new ArrayList<>();
    private Context context;

    public ITunesAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ITunesContent getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itunes_item, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.itunes_item_image);
            holder.name = (TextView) convertView.findViewById(R.id.itunes_item_track);
            holder.collection = (TextView) convertView.findViewById(R.id.itunes_item_name);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        ITunesContent content = getItem(position);
        Picasso.with(context).load(content.getImage()).fit().centerCrop().into(holder.image);
        holder.name.setText(content.getTrack());
        holder.collection.setText(content.getArtist());

        return convertView;
    }

    public void setData(List<ITunesContent> list){
        this.list = list;
    }

    private static class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView collection;
    }
}
