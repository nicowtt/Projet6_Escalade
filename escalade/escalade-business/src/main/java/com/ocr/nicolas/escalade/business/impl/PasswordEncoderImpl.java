package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Named;


@Named
public class PasswordEncoderImpl extends AbstractManager implements PasswordEncoder {

    /**
     * For hash password
     * @param pPassword_plaintext -> clear text
     * @return -> hashed password
     */
    @Override
    public String hashPassword(String pPassword_plaintext) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pPassword_plaintext);

        return hashedPassword;
    }

    /**
     * For check password
     * @param pPasswordPlainText -> pass plain text
     * @param pHashingPassword -> hashingPass
     * @return
     */
    @Override
    public boolean checkPassword(String pPasswordPlainText, String pHashingPassword) {
        boolean pass = false;

        pass = BCrypt.checkpw(pPasswordPlainText,pHashingPassword);

        return pass;
    }


}
