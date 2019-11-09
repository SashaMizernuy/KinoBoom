package com.example.kinoboom.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FilmModal {
    @SerializedName("results")
    @Expose
    private List<Result> mResults = null;
    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("total_results")
    @Expose
    private Integer mTotalResults;
    @SerializedName("dates")
    @Expose
    private Dates mDates;
    @SerializedName("total_pages")
    @Expose
    private Integer mTotalPages;

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        this.mResults = results;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        this.mPage = page;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.mTotalResults = totalResults;
    }

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        this.mDates = dates;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.mTotalPages = totalPages;
    }

    public class Dates {
        @SerializedName("maximum")
        @Expose
        private String mMaximum;
        @SerializedName("minimum")
        @Expose
        private String mMinimum;

        public String getMaximum() {
            return mMaximum;
        }

        public void setMaximum(String maximum) {
        }

        public String getMinimum() {
            return mMinimum;
        }

        public void setMinimum(String minimum) {
            this.mMinimum = minimum;
        }
    }

    public class Result {
        @SerializedName("popularity")
        @Expose
        private Double mPopularity;
        @SerializedName("vote_count")
        @Expose
        private Integer mVoteCount;
        @SerializedName("video")
        @Expose
        private Boolean mVideo;
        @SerializedName("poster_path")
        @Expose
        private String mPosterPath;
        @SerializedName("id")
        @Expose
        private Integer mId;
        @SerializedName("adult")
        @Expose
        private Boolean mAdult;
        @SerializedName("backdrop_path")
        @Expose
        private String mBackdropPath;
        @SerializedName("original_language")
        @Expose
        private String mOriginalLanguage;
        @SerializedName("original_title")
        @Expose
        private String mOriginalTitle;
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> mGenreIds = null;
        @SerializedName("title")
        @Expose
        private String mTitle;
        @SerializedName("vote_average")
        @Expose
        private Double mVoteAverage;
        @SerializedName("overview")
        @Expose
        private String mOverview;
        @SerializedName("release_date")
        @Expose
        private String mReleaseDate;

        public Double getPopularity() {
            return mPopularity;
        }

        public void setPopularity(Double popularity) {
            this.mPopularity = popularity;
        }

        public Integer getVoteCount() {
            return mVoteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.mVoteCount = voteCount;
        }

        public Boolean getVideo() {
            return mVideo;
        }

        public void setVideo(Boolean video) {
            this.mVideo = video;
        }

        public String getPosterPath() {
            return mPosterPath;
        }

        public void setPosterPath(String posterPath) {
            this.mPosterPath = posterPath;
        }

        public Integer getId() {
            return mId;
        }

        public void setId(Integer id) {
            this.mId = id;
        }

        public Boolean getAdult() {
            return mAdult;
        }

        public void setAdult(Boolean adult) {
            this.mAdult = adult;
        }

        public String getBackdropPath() {
            return mBackdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.mBackdropPath = backdropPath;
        }

        public String getOriginalLanguage() {
            return mOriginalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.mOriginalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return mOriginalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.mOriginalTitle = originalTitle;
        }

        public List<Integer> getGenreIds() {
            return mGenreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.mGenreIds = genreIds;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            this.mTitle = title;
        }

        public Double getVoteAverage() {
            return mVoteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.mVoteAverage = voteAverage;
        }

        public String getOverview() {
            return mOverview;
        }

        public void setOverview(String overview) {
            this.mOverview = overview;
        }

        public String getReleaseDate() {
            return mReleaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.mReleaseDate = releaseDate;
        }
    }
}