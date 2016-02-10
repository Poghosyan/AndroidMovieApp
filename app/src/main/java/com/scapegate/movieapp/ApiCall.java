package com.scapegate.movieapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiCall extends AsyncTask<String, Void, MovieData[]> {

    private final String LOG_TAG = ApiCall.class.getSimpleName();
    private String BASE_URL;
    private MovieAdapter movieAdapter;

    public ApiCall(String url_base, MovieAdapter adapter) {
        BASE_URL = url_base;
        movieAdapter = adapter;
    }

    @Override
    protected  MovieData[] doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonStr;

        try {
            String API_KEY = "api_key";
            String QUERY_PARAM = "year";
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, BuildConfig.MOVIE_DB_API_KEY)
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            jsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getMovieData(jsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    private MovieData[] getMovieData(String jObject) throws JSONException{
        JSONObject jsonObject = new JSONObject(jObject);
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        MovieData[] resultMovies = new MovieData[jsonArray.length()];
        MovieData current;
        for(int i = 0; i < jsonArray.length(); ++i) {
            current = new MovieData(jsonArray.getJSONObject(i).getString("original_title"),
                    jsonArray.getJSONObject(i).getString("overview"),
                    "http://image.tmdb.org/t/p/w342/" + jsonArray.getJSONObject(i).getString("poster_path"),
                    jsonArray.getJSONObject(i).getDouble("vote_average"),
                    jsonArray.getJSONObject(i).getString("release_date"));
            resultMovies[i] = current;
        }

        return resultMovies;
    }

    @Override
    protected void onPostExecute(MovieData[] strings) {
        if (strings != null) {
            movieAdapter.clear();
            for(MovieData imageUrl : strings) {
                movieAdapter.add(imageUrl);
            }
        }
    }
}
