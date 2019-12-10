package com.example.expensetracker.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.expensetracker.R;
import com.example.expensetracker.activities.LoginActivity;
import com.example.expensetracker.activities.NewExpenseActivity;
import com.example.expensetracker.activities.SignUpActivity;
import com.example.expensetracker.adapters.ExpenseAdapter;
import com.example.expensetracker.models.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExpenseListFragment extends Fragment {

    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();

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

        final ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> exps = dataSnapshot.getChildren();

                for(DataSnapshot d: exps) {
                    expenseList.add((d.getValue(Expense.class)));
                }
                progressBar.setVisibility(View.INVISIBLE);

                expenseAdapter = new ExpenseAdapter(expenseList);

                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext());

                expenseRecyclerView.setLayoutManager(manager);
                expenseRecyclerView.setItemAnimator(new DefaultItemAnimator());
                expenseRecyclerView.setAdapter(expenseAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//        expenseList.add(new Expense(34.43, "this is description", "user", "address"));
//





        // Inflate the layout for this fragment
        return view;
    }




}
