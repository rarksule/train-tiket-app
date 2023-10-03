package com.group5.project.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.project.R;

import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.MyViewHolder> {


    List<TrainData> TrainList;
    public TrainAdapter(List<TrainData> trainList) {
        TrainList = trainList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrainAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.train_info,null));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TrainData traiData = TrainList.get(position);
        holder.TrainNo.setText(traiData.getTrainNo());
        holder.Departure.setText(traiData.getDeparture());
        holder.Destination.setText(traiData.getDestination());
        holder.Date.setText(traiData.getDate());
        holder.StartTime.setText(traiData.getStartTime());
        holder.EndTime.setText(traiData.getEndTime());

    }

    @Override
    public int getItemCount() {
        return TrainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView TrainNo,Departure,Destination,Date,StartTime,EndTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TrainNo = itemView.findViewById(R.id.trainNo);
            Departure = itemView.findViewById(R.id.FromLocation);
            Destination = itemView.findViewById(R.id.ToLocation);
            Date = itemView.findViewById(R.id.Date);
            StartTime = itemView.findViewById(R.id.StartTime);
            EndTime = itemView.findViewById(R.id.EndTime);


        }
    }

}
