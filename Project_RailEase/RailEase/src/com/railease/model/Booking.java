package com.railease.model;

import java.io.Serializable;
import java.util.Random;

public class Booking implements Serializable {
    private String pnrNumber;
    private String userEmail;
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private String travelDate;
    private int numberOfSeats;
    private String passengerNames;
    private double totalFare;
    private String status;
    
    public Booking(String userEmail, String trainNumber, String trainName, 
                  String source, String destination, String travelDate, 
                  int numberOfSeats, String passengerNames, double totalFare) {
        this.pnrNumber = generatePNR();
        this.userEmail = userEmail;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.numberOfSeats = numberOfSeats;
        this.passengerNames = passengerNames;
        this.totalFare = totalFare;
        this.status = "CONFIRMED";
    }
    
    private String generatePNR() {
        Random random = new Random();
        return "PNR" + (100000 + random.nextInt(900000));
    }
    
    // Getters
    public String getPnrNumber() { return pnrNumber; }
    public String getUserEmail() { return userEmail; }
    public String getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getTravelDate() { return travelDate; }
    public int getNumberOfSeats() { return numberOfSeats; }
    public String getPassengerNames() { return passengerNames; }
    public double getTotalFare() { return totalFare; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return pnrNumber + "|" + userEmail + "|" + trainNumber + "|" + trainName + "|" +
               source + "|" + destination + "|" + travelDate + "|" + numberOfSeats + "|" +
               passengerNames + "|" + totalFare + "|" + status;
    }
}