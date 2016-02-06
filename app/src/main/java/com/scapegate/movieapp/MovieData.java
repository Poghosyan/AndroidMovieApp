package com.scapegate.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieData implements Parcelable{
    public String title;
    public String synopsis;
    public String imageUrl;
    public Double rating;
    public String releaseDate;

    public MovieData(String ttl, String syn, String imgUrl, Double rtng, String rlsDt ) {
        title = ttl;
        synopsis = syn;
        imageUrl = imgUrl;
        rating = rtng;
        releaseDate = rlsDt;
    }

    private MovieData(Parcel in) {
        title = in.readString();
        synopsis = in.readString();
        imageUrl = in.readString();
        rating = in.readDouble();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(synopsis);
        dest.writeString(imageUrl);
        dest.writeDouble(rating);
        dest.writeString(releaseDate);
    }

    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }

        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }
    };
}
