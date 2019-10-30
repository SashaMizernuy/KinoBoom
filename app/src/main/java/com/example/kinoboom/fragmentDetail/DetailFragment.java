package com.example.kinoboom.fragmentDetail;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kinoboom.R;


public class DetailFragment extends Fragment {


    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_detail, container, false);
        final TextView updateBox = (TextView) v.findViewById(R.id.textContainer);
        if(getArguments()!=null){
            updateBox.setVisibility(View.VISIBLE);
            String text=getArguments().getString("params");
            updateBox.setText(text);
        }
        return v;
    }
}
