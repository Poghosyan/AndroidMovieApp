package com.scapegate.movieapp;

import android.app.Activity;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mher on 1/27/2016.
 */
public class MovieAdapter extends ArrayAdapter<String> {

    public MovieAdapter(Activity context, List<String> imageUrls) {
        super(context, 0, imageUrls);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url = getItem(position);
        ImageView imageView;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }
        Point size = new Point();
        Activity act = (Activity) getContext();
        act.getWindowManager().getDefaultDisplay().getSize(size);
        imageView = (ImageView) convertView.findViewById(R.id.movie_poster);
        imageView.setLayoutParams(new GridView.LayoutParams(size.x/2,size.x/2));
        Picasso.with(getContext()).load(url).into(imageView);
        return imageView;
    }
}
