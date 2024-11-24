package com.rarksule.train_ticket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rarksule.train_ticket.Models.UserData;
import com.rarksule.train_ticket.Models.UserDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserData> userDataList;
    FirebaseAuth mAuth;
    String UID;
    FirebaseUser user;
    Toolbar toolbar;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        toolbar = findViewById(R.id.toolbaruserdata);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        //noinspection deprecation
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Downloading Info");
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        mAuth  = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
            recyclerView = findViewById(R.id.recyclerUser);
            LoadData();

    }
     void LoadData() {
         FirebaseFirestore db = FirebaseFirestore.getInstance();
         userDataList = new ArrayList<>();
         UID = user.getUid();
         CollectionReference customRef = db.collection("Customers");
         customRef.whereEqualTo("uid",UID)
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         if (task.isSuccessful()) {
                             int stat;
                             for (QueryDocumentSnapshot document : task.getResult()) {
                                 stat = ((Long) document.getData().get("status")).intValue();
                                 userDataList.add(
                                     new UserData(
                                             document.getData().get("seatType").toString(),
                                             document.getData().get("departure").toString(),
                                             document.getData().get("destination").toString(),
                                             document.getData().get("customerName").toString(),
                                             document.getData().get("uid").toString(),
                                             document.getData().get("selectedDate").toString(),
                                             document.getData().get("seatNo").toString(),
                                             document.getData().get("price").toString(),
                                             document.getId(),
                                             stat
                                     )
                             );

                             }
                             recyclerView.setAdapter(new UserDataAdapter(userDataList));
                             LinearLayoutManager llm = new LinearLayoutManager(UsersActivity.this);
                             llm.setStackFromEnd(false);
                             recyclerView.setLayoutManager(llm);
                             progressDialog.dismiss();
                         } else {

                         }
                     }
                 });
     }



}

