package com.example.kinoboom.recyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kinoboom.R;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.presenter.FilmListContract;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.FilmAdapterViewHolder> {
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    private List<Film> filmList;
    private Film filmDetail;

    public RecyclerAdapter(List<Film> filmList,
                           OnItemClickListener clickListener,
                           OnItemLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void startFragment(Film film);
    }

    public interface OnItemLongClickListener {
        void deleteItem(Film film,int position);
    }

    @Override
    public FilmAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);
        return new FilmAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmAdapterViewHolder holder, int position) {
        filmDetail = filmList.get(position);
        holder.bindData(filmDetail);
        holder.click(filmDetail,clickListener);
        holder.longClick(filmDetail,longClickListener,position);
    }

    @Override
    public int getItemCount() {
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
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+filmDetail.
                    getPosterPath()).fit().into(image);
            title.setText(filmDetail.getTitle());
            popular.setText(String.valueOf(filmDetail.getPopularity()));
            releases.setText(filmDetail.getReleaseDate());
        }

        public void click(Film film,OnItemClickListener listener){
            image.setOnClickListener(view -> listener.startFragment(film));
        }

        public void longClick(Film film, OnItemLongClickListener longListener,int position){
            image.setOnLongClickListener(view -> {
                longListener.deleteItem(film,position);
                return false;
            });
        }
    }
}