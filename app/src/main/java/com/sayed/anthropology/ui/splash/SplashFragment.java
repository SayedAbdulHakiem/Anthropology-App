package com.sayed.anthropology.ui.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sayed.anthropology.R;

public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }

//    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}