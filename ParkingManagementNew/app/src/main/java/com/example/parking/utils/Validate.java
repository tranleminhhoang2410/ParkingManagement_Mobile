package com.example.parking.utils;

import java.util.regex.Pattern;

public class Validate {
    public static boolean validateEmail(String email){
        return Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
                .matcher(email)
                .matches();
    }
    public static boolean validatePhone(String phone){
        return Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$")
                .matcher(phone)
                .matches();
    }
    public static boolean validateVehicleId(String id){
        return Pattern.compile("^(^[A-Z][0-9]{2}[ -][0-9]{4,6}$)")
                .matcher(id)
                .matches();
    }
}
