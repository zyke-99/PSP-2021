package com.zyke.libraryrest.dto;

public class UserRegisterDto {

    private String email;
    private String phoneNumber;
    private String phoneNumberCountry;
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String phoneNumber, String phoneNumberCountry, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.phoneNumberCountry = phoneNumberCountry;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberCountry() {
        return phoneNumberCountry;
    }

    public void setPhoneNumberCountry(String phoneNumberCountry) {
        this.phoneNumberCountry = phoneNumberCountry;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberCountry='" + phoneNumberCountry + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
