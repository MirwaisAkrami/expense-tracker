package com.example.expensetracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensetracker.R;
import com.example.expensetracker.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    private List<String> usersList = new ArrayList<String>();

    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.gender)
    RadioGroup genderGroup;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.password_conf)
    EditText passwordConf;
    @InjectView(R.id.age)
    EditText age;

    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);

        // See if the username is unique
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Some thing", "onDataChange: " + dataSnapshot.getValue());
                Iterable<DataSnapshot> users = dataSnapshot.getChildren();

                for (DataSnapshot u : users) {
                    Log.d("Users: ------ ", "onDataChange: " + u.getKey());
                    usersList.add(u.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("Sign Up", "onCheckedChanged: " + i);
                RadioButton rd = findViewById(i);
                gender = rd.getText().toString();
                Toast.makeText(SignUpActivity.this, "Gender Value: " + gender, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.login_link)
    public void login(View v) {
        Intent intent  = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.signup)
    public void signUp() {
       
        if(!usersList.contains(email.getText().toString())) {
            User user = new User(
                    name.getText().toString(),
                    email.getText().toString(),
                    gender.toLowerCase(),
                    password.getText().toString(),
                    Integer.parseInt(age.getText().toString())
            );
            
            myRef.child(user.getMail()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("Sign Up", "onComplete: Added User " + email.getText().toString());
                }
            });

        } else {
            Toast.makeText(this, "Username Already Exist", Toast.LENGTH_SHORT).show();
        }


    }
}
