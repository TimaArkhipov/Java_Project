package com.example.timetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentStatOneDeal extends Fragment {

    private Button buttonNextAct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_one_deal, container, false);
        buttonNextAct = (Button) view.findViewById(R.id.buttonNextAct1);
        buttonNextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatActivityGraph.class);
                startActivity(intent);
            }
        });
        return view;

    }
}