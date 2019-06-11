package com.example.lestudis.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lestudis.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mCreateAccount;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        mEmailField = findViewById(R.id.emailTxt);
        mPasswordField = findViewById(R.id.passwordTxt);
        mCreateAccount = findViewById(R.id.createAccBtn);
        mLogin = findViewById(R.id.loginBtn);

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(mEmailField.getText().toString(), mPasswordField.getText().toString())
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Main2Activity.this, "Account Created!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Main2Activity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(mEmailField.getText().toString(),mPasswordField.getText().toString())
                        .addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Main2Activity.this, "Successful Login!",
                                            Toast.LENGTH_SHORT).show();
                                    setContentView(R.layout.activity_main);
                                } else {
                                    Toast.makeText(Main2Activity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}
