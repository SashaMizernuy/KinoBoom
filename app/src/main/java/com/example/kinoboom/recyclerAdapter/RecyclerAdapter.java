package com.example.kinoboom.recyclerAdapter;

import android.content.Context;
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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FilmAdapterViewHolder> {

    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    private List<Film> filmList;
    private Film filmDetail;
    Context context;

    public RecyclerAdapter() {this.filmList = Collections.emptyList();}

    public RecyclerAdapter(Context context, List<Film> filmList, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        this.clickListener=clickListener;
        this.longClickListener=longClickListener;
        this.context=context;
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(Film film);
    }
    public interface OnItemLongClickListener{
        void onLongClick(Film film);
    }


    @Override
    public FilmAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);

        return new FilmAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmAdapterViewHolder holder, int position) {
        filmDetail = filmList.get(position);
        holder.bindData(filmDetail);
        holder.click(filmDetail,clickListener);
        holder.longClick(filmDetail,longClickListener);
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

    public class FilmAdapterViewHolder extends RecyclerView.ViewHolder  {

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

        public void click(Film film,OnItemClickListener listener){
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(film);
                }
            });
        }

        public void longClick(Film film,OnItemLongClickListener longListener){
            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longListener.onLongClick(film);
                    return false;
                }
            });
        }
    }
}