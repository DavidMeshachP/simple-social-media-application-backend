package com.zerplabsintern.simplesocialmediawebapplication.dto;

public class LoginDto {

    private String emailIdString;

    private String password;

    public LoginDto() {

    }

    public LoginDto(String emailIdString, String password) {
        this.emailIdString = emailIdString;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailIdString() {
        return emailIdString;
    }

    public void setEmailIdString(String emailIdString) {
        this.emailIdString = emailIdString;
    }    
    
}
