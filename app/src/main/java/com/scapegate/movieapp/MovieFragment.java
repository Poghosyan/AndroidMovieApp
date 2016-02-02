package com.scapegate.movieapp;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.movie_grid, container, false);
        GridView gView = (GridView) rootView.findViewById(R.id.gridView);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        //gView.setColumnWidth(size.x / 2);
        gView.setAdapter(new MovieAdapter(getActivity(), size.x / 2));

        return rootView;
    }
}
