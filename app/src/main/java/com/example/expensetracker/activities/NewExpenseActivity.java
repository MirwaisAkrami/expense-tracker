package com.example.expensetracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.models.Expense;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewExpenseActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("expenses");

    @InjectView(R.id.amount)
    EditText amount;

    @InjectView(R.id.description)
    EditText description;
    @InjectView(R.id.address)
    EditText address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.add_expense)
    public void addNewExpense(View view) {
        Double amount1 = Double.parseDouble(amount.getText().toString());
        Expense newExpense = new Expense(
                amount1,
                description.getText().toString(),
                SplashScreenActivity.getUserEmail(),
                address.getText().toString()
        );

        ref.child(UUID.randomUUID().toString()).setValue(newExpense).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(NewExpenseActivity.this, "Expense Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewExpenseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewExpenseActivity.this, "Error Adding the expense", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
