package com.ecommerce.backend.registration;

public record RegistrationRequest(String firstName, String lastName, String password, String email) {
}