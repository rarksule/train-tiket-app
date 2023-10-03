package com.group5.project;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CheckTicket extends AppCompatActivity {
    CardView cardView;
    EditText ticketID,destinationcity,departingCity,TravelDate;
    Button search;
    TextView status,customerName;
    Toolbar toolbar;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ticket);
        toolbar = findViewById(R.id.toolbarcheck);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Downloading Info");
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(true);

        customerName = findViewById(R.id.tvName);
        cardView = findViewById(R.id.cardview);
        ticketID = findViewById(R.id.TicketID);
        destinationcity = findViewById(R.id.destinationCity);
        departingCity = findViewById(R.id.departingCity);
        TravelDate = findViewById(R.id.TravelDate);
        status = findViewById(R.id.status);
        search = findViewById(R.id.SearchTicket);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ticketID.getText().toString().equals("")) {
                    Toast.makeText(CheckTicket.this, "Enter your ticket ID", Toast.LENGTH_SHORT).show();

                } else {
                    Loaddata(ticketID.getText().toString());
                }

            }
        });

//

    }

    void Loaddata(String Id)
    {
        progressDialog.show();
        String TicketId = Id;
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        DocumentReference docRef = db.collection("Customers").document(TicketId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cardView.setVisibility(View.VISIBLE);
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData() + "  " + document.getData().get("departure"));
                        Long stat;
                        destinationcity.setText(document.getData().get("departure").toString());
                        departingCity.setText(document.getData().get("destination").toString());
                        customerName.setText(document.getData().get("customerName").toString());
                        TravelDate.setText(document.getData().get("selectedDate").toString());
                        ticketID.setText("");

                        stat = (Long) document.getData().get("status");
                        if(stat==1)
                        {status.setText("Verified"); status.setTextColor(ContextCompat.getColor(status.getContext(), R.color.green));}
                        else {status.setText("Pending Verification"); status.setTextColor(ContextCompat.getColor(status.getContext(), R.color.pink));}

                        progressDialog.dismiss();
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }
}
