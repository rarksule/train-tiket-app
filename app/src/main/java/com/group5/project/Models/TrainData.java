package com.group5.project.Models;

public class TrainData {
    String TrainNo,Departure,Destination,Date,StartTime,EndTime;

    public TrainData(String TrainNo, String Departure, String Destination, String Date, String StartTime,String EndTime) {
        this.TrainNo = TrainNo;
        this.Departure = Departure;
        this.Destination = Destination;
        this.Date = Date;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public void setTrainNo(String trainNo) {
        TrainNo = trainNo;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String time) {
        StartTime = time;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public TrainData() {
    }
}
