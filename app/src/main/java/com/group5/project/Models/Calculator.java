package com.group5.project.Models;

public class Calculator {
        public static String SeatType, Destination, Departure;
     public static String priceCalculator(String seat, String depart, String dest) {
            SeatType = seat;
            Destination = dest;
            Departure = depart;
            int price;
            switch (SeatType) {
                case "Hard Level2": {
                    switch (Departure) {
                        case "Addis Ababa":
                            if (Destination.equals("Adama")) {
                                price = 105;
                            } else if(Destination.equals("Methara")) {
                                price = 490;
                            }
                            else{
                                price=750;
                            }
                            break;

                        case "Adama":
                            if (Destination.equals("Addis Ababa")) {
                                price = 105;
                            } else if(Destination.equals("methara")) {
                                price = 385;
                            }
                            else{
                                price=105;
                            }
                            break;
                        case "Methara":
                            if (Destination.equals("Addis Ababa")) {
                                price = 105;
                            } else if (Destination.equals("Adama")) {
                                price = 105;
                            } else {
                                price = 385;
                            }
                            break;
                        default:
                            if (Destination.equals("Adama")) {
                                price = 385;
                            } else {
                                price = 105;
                            }
                            break;
                    }
                    break;
                }
                case "Hard Level1": {
                    switch (Departure) {
                        case "Addis Ababa":
                            if (Destination.equals("Adama")) {
                                price = 140;
                            } else {
                                price = 561;
                            }
                            break;
                        case "Adama":
                            if (Destination.equals("Addis Ababa")) {
                                price = 105;
                            } else if(Destination.equals("methara")) {
                                price = 400;
                            }
                            else{
                                price=200;
                            }
                            break;
                        default:
                            if (Destination.equals("Adama")) {
                                price = 525;
                            } else {
                                price = 561;
                            }
                            break;
                    }
                    break;
                }
                case "Soft Level2": {
                    switch (Departure) {
                        case "Addis Ababa":
                        if (Destination.equals("Adama")) {
                            price = 165;
                        } else if(Destination.equals("Methara")) {
                            price = 690;
                        }
                        else{
                            price=750;
                        }
                        break;

                        case "Adama":
                            if (Destination.equals("Addis Ababa")) {
                                price = 205;
                            } else if(Destination.equals("methara")) {
                                price = 485;
                            }
                            else{
                                price=205;
                            }
                            break;
                        default:
                            if (Destination.equals("Adama")) {
                                price = 765;
                            } else {
                                price = 800;
                            }
                            break;
                    }
                    break;
                }
                default: {
                    switch (Departure) {
                        case "Addis Ababa":
                        if (Destination.equals("Adama")) {
                            price = 165;
                        } else if(Destination.equals("Methara")) {
                            price = 690;
                        }
                        else{
                            price=750;
                        }
                        break;
                        case "Adama":
                            if (Destination.equals("Addis Ababa")) {
                                price = 205;
                            } else if(Destination.equals("methara")) {
                                price = 485;
                            }
                            else{
                                price=205;
                            }
                            break;
                        default:
                            if (Destination.equals("Adama")) {
                                price = 765;
                            } else {
                                price = 800;
                            }
                            break;
                    }
                    break;
                }

            }
            return String.valueOf(price);
        }
}
