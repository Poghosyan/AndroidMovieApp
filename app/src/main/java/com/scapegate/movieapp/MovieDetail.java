package com.scapegate.movieapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, new MovieDetailFragment())
                    .commit();
        }
    }

    public static class MovieDetailFragment extends Fragment {


        public MovieDetailFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            MovieData data = getActivity().getIntent().getExtras().getParcelable("myMovieData");

            View rootView = inflater.inflate(R.layout.activity_movie_detail, container, false);
            ((TextView)rootView.findViewById(R.id.movie_title)).setText(data.title);
            ((TextView)rootView.findViewById(R.id.movie_rating)).setText(String.valueOf(data.rating));
            ((TextView)rootView.findViewById(R.id.movie_overview)).setText(data.synopsis);
            ((TextView)rootView.findViewById(R.id.movie_rel_date)).setText(data.releaseDate);
            Picasso.with(getActivity()).load(data.imageUrl).into(((ImageView) rootView.findViewById(R.id.movie_detail_poster)));
            return rootView;
        }
    }
}
