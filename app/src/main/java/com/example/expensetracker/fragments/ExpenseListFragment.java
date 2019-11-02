package com.example.expensetracker.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.expensetracker.R;
import com.example.expensetracker.activities.LoginActivity;
import com.example.expensetracker.activities.SignUpActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpenseListFragment extends Fragment {
    public ExpenseListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);
        FloatingActionButton floatButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }




}
