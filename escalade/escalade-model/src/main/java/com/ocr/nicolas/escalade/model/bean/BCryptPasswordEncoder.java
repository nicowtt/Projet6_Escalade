package com.ocr.nicolas.escalade.model.bean;


import javax.inject.Named;

@Named
public class BCryptPasswordEncoder {


    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
