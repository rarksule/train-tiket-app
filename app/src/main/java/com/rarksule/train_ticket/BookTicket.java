package com.rarksule.train_ticket;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rarksule.train_ticket.Models.Calculator;
import com.rarksule.train_ticket.Models.UserData;

public class BookTicket extends AppCompatActivity{
//      implements AdapterView.OnItemSelectedListener

    Spinner seat,start,end;
    TextView seatno,price;
    EditText name,date;
    String seattype,departure,destination ,customerName,UID,Price,ID,seatnum;
    String selectedDate = "";
    int Day,Month,Year,mYear,mMonth,mDay,Num;
    Button book,showPrice;
    Toolbar toolbar;
    CollectionReference reference;
    ProgressDialog progressDialog;


     FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading your data");
        progressDialog.setMessage("please wait ...");
        progressDialog.setCanceledOnTouchOutside(true);


        toolbar = findViewById(R.id.toolbarbook);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        db = FirebaseFirestore.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
           UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        initialize();
        Spinnersetup();
        reference = db.collection("Customers");


        book =findViewById(R.id.bookbtn);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid())
                   Book();


            }

        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              PickDate();
            }
        });
        showPrice.setOnClickListener(v -> {Calculate();});
    }

    private void PickDate() {

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
                        Day = dayOfMonth;
                        Month = monthOfYear+1;
                        Year = year;

                       date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                       selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }



    private void Spinnersetup() {
         ArrayAdapter<CharSequence> seats = ArrayAdapter.createFromResource(
                 this,
                 R.array.seatType,
                 android.R.layout.simple_spinner_item);
         seats.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         ArrayAdapter<CharSequence> cities = ArrayAdapter.createFromResource(
                 this,
                 R.array.Cities,
                 android.R.layout.simple_spinner_item);
         cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         seat.setAdapter(seats);
         start.setAdapter(cities);
         end.setAdapter(cities);
//  Spinner methods implemntation
         seat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { seattype = parent.getItemAtPosition(position).toString(); }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {}
         });
         start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { departure = parent.getItemAtPosition(position).toString(); }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {}
         });
         end.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { destination = parent.getItemAtPosition(position).toString();}
             @Override
             public void onNothingSelected(AdapterView<?> parent) {}
         });
     }
     private void initialize() {
         seat = findViewById(R.id.seatType);
         start = findViewById(R.id.departingLocation);
         end = findViewById(R.id.destinationLocation);
         name = findViewById(R.id.customerName);
         seatno = findViewById(R.id.SeatNo);
         price = findViewById(R.id.TicketPrice);
         date = findViewById(R.id.DateAB);
         showPrice = findViewById(R.id.showpricebtn);
     }
     private void Book() {
        progressDialog.show();
        Calculate();
         UserData userData = new UserData(seattype,departure,destination,customerName,UID,selectedDate,seatnum,Price,ID,0);
         reference.add(userData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
             @Override
             public void onSuccess(DocumentReference documentReference) {

                 Intent intent = new Intent(BookTicket.this, BookedTicket.class);
                 intent.putExtra("Ticket Id",documentReference.getId());
                 progressDialog.dismiss();
                 startActivity(intent);
                 finish();
             }

         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(BookTicket.this,"failed",Toast.LENGTH_SHORT).show();
             }
         });
     }

    private void Calculate()
    {

        if(destination!=departure)
        {

            seatnum = seatNoCalculator();
            Num = 0;
            Price = Calculator.priceCalculator(seattype,destination,departure);
            seatno.setText(seatnum);
            price.setText(Price);

        }
        else if(destination==departure){
            Toast.makeText(BookTicket.this,"Destination and Departure Location cannot be the same",Toast.LENGTH_SHORT).show();
            price.setText(R.string.noprice);
            seatno.setText(R.string.noSeat);
        }
        else {
            Toast.makeText(BookTicket.this,"Fill the data please",Toast.LENGTH_SHORT).show();

        }
    }
     private boolean valid()
     {
         customerName = name.getText().toString();

               if(customerName.isEmpty()) {
                   Toast.makeText(BookTicket.this, "fill the blank", Toast.LENGTH_SHORT).show();
                   return false;
               }
               else if(selectedDate.equals(""))
               {
                   Toast.makeText(BookTicket.this, "fill the blank", Toast.LENGTH_SHORT).show();
                   return false;
               } else if (departure==destination) {
                   Toast.makeText(BookTicket.this,"Destination and Departure Location cannot be the same",Toast.LENGTH_SHORT).show();
                   return false;
               } else
               {

                   if (Year == mYear)
                   {
                       if (Month == mMonth)
                       {
                           if (Day <= mDay)
                           {}
                           else
                           {return true;}

                       }
                       else if (Month > mMonth)
                       {return true;}
                       else
                       {}
                   }
                   else if (Year > mYear)
                   {
                       return true;
                   }
                   else
                   {}
               }
         Toast.makeText(BookTicket.this, "please choose correct date", Toast.LENGTH_SHORT).show();
         return false;
     }
    public String seatNoCalculator() {
        Query query = reference.whereEqualTo("seatType", seattype)
                .whereEqualTo("departure",departure)
                .whereEqualTo("destination",destination);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Num ++;
                    }}}});






    return String.valueOf(Num + 1);}

 }
