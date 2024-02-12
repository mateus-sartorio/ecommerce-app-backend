package com.ecommerce.backend.utils.email;

public interface EmailSender {
    void send(String to, String email);
}