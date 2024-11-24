package com.rarksule.train_ticket.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rarksule.train_ticket.R;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.MyViewHolder> {

    private static final String TAG = "my tag";
    List<UserData> CustomerDataList;
    public UserDataAdapter(List<UserData> CustomerDataList) {
        this.CustomerDataList = CustomerDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_data_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserData customerData = CustomerDataList.get(position);
        holder.TicketID.setText(customerData.getID());
        holder.Departure.setText(customerData.getDeparture());
        holder.Destination.setText(customerData.getDestination());
        holder.Date.setText(customerData.getSelectedDate());

        if(customerData.getStatus()==0)
        {
            holder.status.setText("pending");
            holder.status.setTextColor(ContextCompat.getColor(holder.status.getContext(), R.color.pink));
        }
        else {
            holder.status.setText("Verified");
            holder.status.setTextColor(ContextCompat.getColor(holder.status.getContext(), R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return CustomerDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TicketID,Departure,Destination,Date,status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TicketID = itemView.findViewById(R.id.userTicketID);
            Departure = itemView.findViewById(R.id.Fromlocation);
            Destination = itemView.findViewById(R.id.Tolocation);
            Date = itemView.findViewById(R.id.DateUser);
            status = itemView.findViewById(R.id.UserStatus);

        }
    }




}
