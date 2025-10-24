package com.railease.service;

import java.util.*;
import com.railease.model.*;

public class BookingService {
    
    public static Booking createBooking(User user, Train train, String source, 
                                      String destination, String travelDate, 
                                      int seats, String passengerNames, double totalFare) {
        try {
            Booking booking = new Booking(user.getEmail(), train.getTrainNumber(), 
                train.getTrainName(), source, destination, travelDate, seats, 
                passengerNames, totalFare);
            
            // Update train seats
            train.setAvailableSeats(train.getAvailableSeats() - seats);
            TrainService.updateTrain(train);
            
            // Save booking
            List<Booking> bookings = FileStorageService.readBookings();
            bookings.add(booking);
            FileStorageService.writeBookings(bookings);
            
            return booking;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Booking> getUserBookings(String userEmail) {
        List<Booking> allBookings = FileStorageService.readBookings();
        List<Booking> userBookings = new ArrayList<>();
        
        for (Booking booking : allBookings) {
            if (booking.getUserEmail().equals(userEmail)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
    
    public static Booking getBookingByPNR(String pnr) {
        List<Booking> bookings = FileStorageService.readBookings();
        for (Booking booking : bookings) {
            if (booking.getPnrNumber().equals(pnr)) {
                return booking;
            }
        }
        return null;
    }
}