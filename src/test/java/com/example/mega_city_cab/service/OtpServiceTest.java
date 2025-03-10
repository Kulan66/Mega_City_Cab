package com.example.mega_city_cab.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtpServiceTest {

    private OtpService otpService;

    @BeforeEach
    void setUp() {
        otpService = new OtpService();
    }

    @Test
    void generateOtp() {
        String email = "test@example.com";
        String otp = otpService.generateOtp(email);
        assertNotNull(otp);
        assertEquals(6, otp.length());
        assertTrue(otp.matches("\\d{6}"));
    }

    @Test
    void validateOtp() {
        String email = "test@example.com";
        String otp = otpService.generateOtp(email);
        assertTrue(otpService.validateOtp(email, otp));
        assertFalse(otpService.validateOtp(email, "000000"));
    }

    @Test
    void clearOtp() {
        String email = "test@example.com";
        String otp = otpService.generateOtp(email);
        assertTrue(otpService.validateOtp(email, otp));
        otpService.clearOtp(email);
        assertFalse(otpService.validateOtp(email, otp));
    }
}