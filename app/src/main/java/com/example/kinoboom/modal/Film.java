package com.example.kinoboom.modal;


public class Film {
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
}
