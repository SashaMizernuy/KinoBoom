package com.example.kinoboom.recyclerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kinoboom.R;
import com.example.kinoboom.modal.Film;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FilmAdapterViewHolder>{

    private List<Film> filmList;
    private Film filmDetail;

    public RecyclerAdapter() {this.filmList = Collections.emptyList();}

    @Override
    public FilmAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);

        return new FilmAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmAdapterViewHolder holder, int position) {
        filmDetail = filmList.get(position);
        holder.bindData(filmDetail);
    }

    @Override
    public int getItemCount() {
        if(filmList !=null){
            Log.d("getItemCOunt: ",String.valueOf(filmList.size()));
        }
        else{
            Log.d("getItemCOunt: ","SONGLIST IS NULL");
        }
        return filmList.size();
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    public static class FilmAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleFilm) ImageView image;
        @BindView(R.id.txtTitle)public TextView title;
        @BindView(R.id.popular)public TextView popular;
        @BindView(R.id.release)public TextView releases;


        public FilmAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        public void bindData(final Film filmDetail) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+filmDetail.getPosterPath()).fit().into(image);
            title.setText(filmDetail.getTitle());
            popular.setText(String.valueOf(filmDetail.getPopularity()));
            releases.setText(filmDetail.getReleaseDate());
        }
    }
}