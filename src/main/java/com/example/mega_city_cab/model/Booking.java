package com.example.mega_city_cab.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int customerId;
    private int driverId;
    private int carId;
    private String destination;
    private String paymentMethod;
    private double distanceKm;
    private double totalPrice;
    private Timestamp bookingDate;

    public Booking(int bookingId, int customerId, int driverId, int carId, String destination, String paymentMethod, double distanceKm, double totalPrice, Timestamp bookingDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.driverId = driverId;
        this.carId = carId;
        this.destination = destination;
        this.paymentMethod = paymentMethod;
        this.distanceKm = distanceKm;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
}