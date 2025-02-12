package com.example.mega_city_cab.service;

public class BillCalculator {

    private static final double TAX_RATE = 5.5; // Tax per km in LKR

    public static double calculateTotalPrice(double distanceKm, double pricePerKm, double taxPerKm, double discountRate) {
        double totalPrice = distanceKm * pricePerKm;
        double tax = distanceKm * taxPerKm;
        double discount = totalPrice * discountRate;
        return totalPrice + tax - discount;
    }
}