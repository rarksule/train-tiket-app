package com.group5.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private CardView Book, Available, Ticket, free,Login, Users, support;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Initialize();
        Buttons();
    }

    private void Buttons() {
        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookTicket.class);
                startActivity(intent);
            }
        });
        Ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckTicket.class);
                startActivity(intent);
            }
        });
        Available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AvailableTrain.class);
                startActivity(intent);
            }
        });
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevelopersActivity.class);
                startActivity(intent);
            }
        });

        Users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null)
                {
                    Intent intent = new Intent(MainActivity.this, UsersActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SupportActivity.class);
            startActivity(intent);
            }
        });


    }

    private void Initialize() {
        Toolbar toolbar = findViewById(R.id.HomeToolbar);
        toolbar.setTitle("Train Ticket");
        setSupportActionBar(toolbar);
        Book = findViewById(R.id.bookCard);
        Available = findViewById(R.id.AvailableCard);
        Ticket = findViewById(R.id.TicketCard);
        free = findViewById(R.id.ViewPassengersCard);
        Users = findViewById(R.id.UsersCard);
        support = findViewById(R.id.SupportCard);

    }
}