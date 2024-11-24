package com.rarksule.train_ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class DevelopersActivity extends AppCompatActivity {

Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        toolbar = findViewById(R.id.Toolbardevelopers);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}