package com.railease.model;

import java.io.Serializable;

public class Train implements Serializable {
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double fare;
    
    public Train(String trainNumber, String trainName, String source, String destination,
                String departureTime, String arrivalTime, int totalSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
    }
    
    // Getters and setters
    public String getTrainNumber() { return trainNumber; }
    public String getTrainName() { return trainName; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }
    public double getFare() { return fare; }
    
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    
    @Override
    public String toString() {
        return trainNumber + "|" + trainName + "|" + source + "|" + destination + "|" +
               departureTime + "|" + arrivalTime + "|" + totalSeats + "|" + availableSeats + "|" + fare;
    }
}