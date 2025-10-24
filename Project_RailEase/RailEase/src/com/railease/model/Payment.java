package com.railease.model;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String paymentId;
    private String bookingId;
    private double amount;
    private String paymentDate;
    private String paymentMethod; // CARD, UPI, NET_BANKING
    private String status; // SUCCESS, FAILED, PENDING
    
    public Payment(String paymentId, String bookingId, double amount, String paymentDate, 
                   String paymentMethod) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = "SUCCESS";
    }
    
    // Getters and Setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("| %-12s | %-12s | ₹%-8.2f | %-12s | %-10s | %-8s |",
                paymentId, bookingId, amount, paymentDate, paymentMethod, status);
    }
}