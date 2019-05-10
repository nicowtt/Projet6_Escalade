package com.ocr.nicolas.escalade.business.contract;

public interface PasswordEncoder {

    String hashPassword(String password_plaintext);
}
