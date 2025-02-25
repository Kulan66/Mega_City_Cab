package com.example.mega_city_cab.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class OtpService {
    private static final int OTP_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();
    private static final Map<String, String> otpStorage = new HashMap<>();

    public String generateOtp(String email) {
        String otp = String.format("%06d", random.nextInt(999999));
        otpStorage.put(email, otp);
        System.out.println("OTP for " + email + ": " + otp); // Logging OTP to console for testing
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }

    public void clearOtp(String email) {
        otpStorage.remove(email);
    }
}