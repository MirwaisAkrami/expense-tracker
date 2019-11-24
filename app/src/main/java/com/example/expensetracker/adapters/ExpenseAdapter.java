package com.example.expensetracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.models.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<Expense> expenseList;

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, description, address;

        public ExpenseViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.exp_amount);
            description = (TextView) view.findViewById(R.id.exp_description);
            address = (TextView) view.findViewById(R.id.exp_address);
        }
    }

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list_row, parent, false);

        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.amount.setText(expense.getAmount().toString());
        holder.description.setText(expense.getDescription());
        holder.address.setText(expense.getAddress());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }


}
