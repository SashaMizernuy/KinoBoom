package com.example.kinoboom.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {

         public String posterPath;
         public String title;
         public Double popularity;
         public String releaseDate;
         public String overview;

     public Film(String posterPath, String title,
                 Double popularity, String releaseDate,
                 String overview) {
        this.posterPath = posterPath;
        this.title = title;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    protected Film(Parcel in) {
        posterPath = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        releaseDate = in.readString();
        overview = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(posterPath);
        parcel.writeString(title);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
    }

    public String getPosterPath() {
         return this.posterPath;
     }

    public void setPosterPath(String posterPath) {
         this.posterPath = posterPath;
     }

    public String getTitle() {
         return this.title;
     }

    public void setTitle(String title) {
         this.title = title;
     }

    public Double getPopularity()  {
         return this.popularity;
     }

    public void setPopularity(Double popularity) {
         this.popularity = popularity;
     }

    public String getReleaseDate() {
         return this.releaseDate;
     }

    public void setReleaseDate(String releaseDate) {
         this.releaseDate = releaseDate;
     }

    public String getOverview() {
         return this.overview;
     }
    public void setOverview(String overview) {
         this.overview = overview;
     }

    @Override
    public int describeContents() {
        return 0;
    }
}