package com.example.expensetracker.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.models.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<Expense> expenseList;

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, description, address;
        public ImageView expenseType;

        public ExpenseViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.exp_amount);
            description = (TextView) view.findViewById(R.id.exp_description);
            address = (TextView) view.findViewById(R.id.exp_address);
            expenseType = (ImageView) view.findViewById(R.id.exp_type);
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

//        holder.expenseType.setBackgroundResource(R.drawable.ic_phone_iphone_white_24dp);

        Drawable imageBg = holder.expenseType.getBackground();
        GradientDrawable shDrawable = (GradientDrawable) imageBg;
//        shDrawable.setColor(holder.expenseType.getResources().getColor(R.color.colorAccentDarker));
        shDrawable.setColor(Color.parseColor("#FFD044"));
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }


}
