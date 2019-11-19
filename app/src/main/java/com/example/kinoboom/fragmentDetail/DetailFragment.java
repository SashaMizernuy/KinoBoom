package com.example.kinoboom.fragmentDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.kinoboom.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment implements DeatailFragmentContract.View {

    protected DeatailFragmentContract.Presenter detailPresenter;

    @BindView(R.id.textContainer)
    TextView updateBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, v);
        initDetailPresenter();
        return v;
    }

    public void initDetailPresenter(){
        detailPresenter = new DetailPresenter(this);
        detailPresenter.detailPresenterIsCreated();
    }

    @Override
    public void overviewFilm() {
        if (getArguments() != null) {
            updateBox.setVisibility(View.VISIBLE);
            String text = getArguments().getString("overview");
            updateBox.setText(text);
        }
    }
}