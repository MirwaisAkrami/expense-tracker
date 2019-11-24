package com.example.expensetracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
    @InjectView(R.id.login_email)
    EditText email;
    @InjectView(R.id.login_password)
    EditText password;
    private User loggedInUser;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);



    }

    @OnClick(R.id.loginbtn)
    public void login(View v) {
        mReference.child("users").orderByChild("mail").equalTo(email.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Log.d("Login Activity: ", "onDataChange: " + dataSnapshot.getChildrenCount());
                    Iterable<DataSnapshot> users = dataSnapshot.getChildren();

                    for (DataSnapshot u : users) {
                        Log.d("Users: ------ ", "onDataChange: " + u.getValue().toString());
                        // usersList.add(u.getKey());
                        loggedInUser = u.getValue(User.class);
                        Log.d("Username: ", "onDataChange: " + loggedInUser.getMail());
                    }

                    if(loggedInUser.getPassword().equals(password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        storeCredentialsLocally();
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Log.d("Login Activity: ", "onDataChange: There is no such child");
                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void storeCredentialsLocally() {
        SharedPreferences.Editor editor = getSharedPreferences("expenseTracker", MODE_PRIVATE).edit();
        editor.putString("email", email.getText().toString());
        editor.apply();
    }
}
