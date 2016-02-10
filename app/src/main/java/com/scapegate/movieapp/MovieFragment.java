package com.scapegate.movieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MovieFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private MovieAdapter movieAdapter;

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
        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<MovieData>());


        View rootView = inflater.inflate(R.layout.movie_grid, container, false);
        GridView gView = (GridView) rootView.findViewById(R.id.gridView);
        gView.setAdapter(movieAdapter);
        gView.setOnItemClickListener(itemClickListener);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ApiCall call = new ApiCall("https://api.themoviedb.org/3/discover/movie", movieAdapter);
        call.execute("2015");
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MovieData movieData = movieAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MovieDetail.class).putExtra("myMovieData", movieData);
            startActivity(intent);
        }
    };

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.e("Peference FOR Sort", sharedPreferences.getString(key, ""));
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        GridView gView = (GridView) getActivity().findViewById(R.id.gridView);
//        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
//            gView.setNumColumns(3);
//        else
//            gView.setNumColumns(4);
//        super.onConfigurationChanged(newConfig);
//    }
}
