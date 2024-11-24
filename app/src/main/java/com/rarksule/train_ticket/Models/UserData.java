package com.rarksule.train_ticket.Models;

import com.google.firebase.firestore.Exclude;

public class UserData {
    String SeatType,Departure,Destination ,UID,SelectedDate,SeatNo,Price,ID;
    int Status;
    String CustomerName = "SULEY";

    public UserData(String SeatType, String Departure, String Destination, String CustomerName, String UID, String SelectedDate, String SeatNo, String Price, String ID, int Status) {
        this.SeatType = SeatType;
        this.Departure = Departure;
        this.Destination = Destination;
        this.CustomerName = CustomerName;
        this.UID = UID;
        this.SelectedDate = SelectedDate;
        this.SeatNo = SeatNo;
        this.Price = Price;
        this.ID = ID;
        this.Status = Status;
    }

    public UserData() {
    }

    public String getSeatType() {
        return SeatType;
    }

    public void setSeatType(String seatType) {
        SeatType = seatType;
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

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSelectedDate() {
        return SelectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        SelectedDate = selectedDate;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Exclude
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
