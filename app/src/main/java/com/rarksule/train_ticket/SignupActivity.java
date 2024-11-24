package com.rarksule.train_ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton btnsignup;
    EditText txtEmail, txtPassword,txtName;
    TextView login;
    String username, password,name,UID;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //initialization
        btnsignup = findViewById(R.id.signupbtn);
        login = findViewById(R.id.login);
        txtEmail = findViewById(R.id.Email);
        txtPassword = findViewById(R.id.password);
        txtName = findViewById(R.id.Name);
        //setting on click method
        btnsignup.setOnClickListener(this);
        login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.login)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.signupbtn) {
            signup();

        }
    }

    private void signup() {
        password = txtPassword.getText().toString();
        username = txtEmail.getText().toString();
        name = txtName.getText().toString();
        if (username.isEmpty() || password.isEmpty() || name.isEmpty())
        {
            Toast.makeText(this, "please enter username and password", Toast.LENGTH_SHORT).show();
        } else
        {
            mAuth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();


                            }

                        }
                    });
        }
    }
}