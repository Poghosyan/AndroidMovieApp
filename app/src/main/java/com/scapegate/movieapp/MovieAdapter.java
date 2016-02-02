package com.scapegate.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Mher on 1/27/2016.
 */
public class MovieAdapter extends BaseAdapter {
    int size;
    Context c;

    public MovieAdapter(Context context, int boxSize) {
        c = context;
        size = boxSize;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.movie_item, parent, false);
        }
        imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        imageView.setLayoutParams(new GridView.LayoutParams(size, size));
        imageView.setImageResource(R.drawable.sample_0);
        return imageView;
    }
}
