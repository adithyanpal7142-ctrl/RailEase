package com.railease.service;

import java.util.*;
import com.railease.model.*;

public class TrainService {
    
    public List<Train> searchTrains(String source, String destination) {
        List<Train> allTrains = FileStorageService.readTrains();
        List<Train> availableTrains = new ArrayList<>();
        
        for (Train train : allTrains) {
            if (train.getSource().equalsIgnoreCase(source) && 
                train.getDestination().equalsIgnoreCase(destination) &&
                train.getAvailableSeats() > 0) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }
    
    public void displayAllTrains() {
        List<Train> trains = FileStorageService.readTrains();
        if (trains.isEmpty()) {
            System.out.println("❌ No trains available in the system!");
            return;
        }
        
        for (Train train : trains) {
            System.out.printf("┌─ %s ───────────────────────────────────┐\n", train.getTrainNumber());
            System.out.printf("│ 🚂 %-40s │\n", train.getTrainName());
            System.out.printf("│ 📍 %s → %-30s │\n", train.getSource(), train.getDestination());
            System.out.printf("│ ⏰ %s - %s 💺 %-3d 💰 ₹%-8.2f │\n", 
                train.getDepartureTime(), train.getArrivalTime(), 
                train.getAvailableSeats(), train.getFare());
            System.out.println("└────────────────────────────────────────────────────┘");
        }
    }
    
    public boolean addTrain(Train train) {
        try {
            List<Train> trains = FileStorageService.readTrains();
            trains.add(train);
            FileStorageService.writeTrains(trains);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void updateTrain(Train updatedTrain) {
        try {
            List<Train> trains = FileStorageService.readTrains();
            for (int i = 0; i < trains.size(); i++) {
                if (trains.get(i).getTrainNumber().equals(updatedTrain.getTrainNumber())) {
                    trains.set(i, updatedTrain);
                    break;
                }
            }
            FileStorageService.writeTrains(trains);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}