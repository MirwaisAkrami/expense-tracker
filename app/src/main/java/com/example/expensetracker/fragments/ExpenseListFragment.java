package com.example.expensetracker.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.expensetracker.R;
import com.example.expensetracker.activities.LoginActivity;
import com.example.expensetracker.activities.NewExpenseActivity;
import com.example.expensetracker.activities.SignUpActivity;
import com.example.expensetracker.adapters.ExpenseAdapter;
import com.example.expensetracker.models.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExpenseListFragment extends Fragment {

    @InjectView(R.id.expense_recyclerview)
    RecyclerView expenseRecyclerView;

    private List<Expense> expenseList;

    private ExpenseAdapter expenseAdapter;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("expenses");

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


        ButterKnife.inject(this, view);

        expenseList = new ArrayList<Expense>();

        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
        expenseList.add(new Expense(34.43, "this is description", "user", "address"));


        expenseAdapter = new ExpenseAdapter(expenseList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());

        expenseRecyclerView.setLayoutManager(manager);
        expenseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        expenseRecyclerView.setAdapter(expenseAdapter);




        // Inflate the layout for this fragment
        return view;
    }




}
