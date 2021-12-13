package com.example.timetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentStat extends Fragment {

    private Button buttonNextAct;
    private EditText multiListOfDeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_stat);
        //buttonNextAct = (Button) find
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        multiListOfDeal = (EditText) view.findViewById(R.id.dealSet);
        String newNameDealList = "Программирование\n" + "Прогулка\n" + "Тренировка\n" ;
        buttonNextAct = (Button) view.findViewById(R.id.buttonNextAct2);
        buttonNextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StatActivityGraph.class);
                startActivity(intent);
            }
        });
        /*
        List<String> nameDealList = Arrays.asList("Программирование", "Прогулка", "Тренировка");
        StringBuilder newNameDealList = new StringBuilder();
        for(String i : nameDealList){
            newNameDealList.append(i).append("\n");
        }

         */
        multiListOfDeal.setText(newNameDealList);
        return view;
    }
}