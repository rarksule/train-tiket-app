package com.rarksule.train_ticket;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rarksule.train_ticket.Models.TrainAdapter;
import com.rarksule.train_ticket.Models.TrainData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AvailableTrain extends AppCompatActivity {
    RecyclerView recyclerView;
    Button Search;
    EditText date;
    String searchDate = "" ;
    List<TrainData> TrainList;
    ProgressDialog  progressDialog;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabe_trains);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Downloading Info");
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(true);
        date = findViewById(R.id.DateTrain);
        Search = findViewById(R.id.SearchDate);
        recyclerView = findViewById(R.id.recyclerTrain);
        toolbar = findViewById(R.id.Toolbaravailabletrain);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickDate();

            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valid()) LoadData(searchDate);
                else Toast.makeText(AvailableTrain.this,"choose date or Enter date",Toast.LENGTH_SHORT).show();

            }
        });

        LoadData("");

    }

    private boolean valid() {
        if(searchDate.toString().equals(""))
        {
            return false;
        }
        return true;
    }


    void LoadData(String Date) {

        progressDialog.show();
         FirebaseFirestore db = FirebaseFirestore.getInstance();
        TrainList = new ArrayList<TrainData>();
         String mydate = Date;
        CollectionReference customRef = db.collection("Trains");
        Query query;
        if(mydate.equals(""))
         {
             query = customRef;
         }
        else{
            query = customRef.whereEqualTo("Date", mydate);
        }

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 if (task.isSuccessful()) {
                     for (QueryDocumentSnapshot document : task.getResult()) {
                             TrainList.add(
                                     new TrainData(
                                             document.getId(),
                                             document.getData().get("Departing City").toString(),
                                             document.getData().get("Destination City").toString(),
                                             document.getData().get("Date").toString(),
                                             document.getData().get("StartTime").toString(),
                                             document.getData().get("EndTime").toString()
                                     )
                                     );
                     }
                     recyclerView.setAdapter(new TrainAdapter(TrainList));
                     LinearLayoutManager llm = new LinearLayoutManager(AvailableTrain.this);
                     llm.setStackFromEnd(false);
                     recyclerView.setLayoutManager(llm);
                     progressDialog.dismiss();
                 }
                 else {
                 }
             }
         });
    }
    private void PickDate() {
        int mYear,mMonth,mDay;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        searchDate = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date.setText(searchDate);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
}
}