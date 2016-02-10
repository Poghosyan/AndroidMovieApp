package com.scapegate.movieapp;

import android.app.Activity;
import android.content.res.Configuration;
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
public class MovieAdapter extends ArrayAdapter<MovieData> {

    public MovieAdapter(Activity context, List<MovieData> imageUrls) {
        super(context, 0, imageUrls);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieData movie = getItem(position);
        int rotation = getContext().getResources().getConfiguration().orientation;
        ImageView imageView;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }
        
        Point size = new Point();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getSize(size);
        imageView = (ImageView) convertView.findViewById(R.id.movie_poster);

        if(rotation == Configuration.ORIENTATION_PORTRAIT) {
            imageView.setLayoutParams(new GridView.LayoutParams(size.x/2, size.y/3));
            Picasso.with(getContext()).load(movie.imageUrl).resize(size.x/2, size.y/3).centerCrop().into(imageView);
        }
        else {
            imageView.setLayoutParams(new GridView.LayoutParams(size.x/3, size.y/2));
            Picasso.with(getContext()).load(movie.imageUrl).resize(size.x/3, size.y/2).centerCrop().into(imageView);
        }

        return imageView;
    }
}
