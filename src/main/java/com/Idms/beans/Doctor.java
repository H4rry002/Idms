package com.Idms.beans;

import java.util.ArrayList;

public class Doctor {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(int registration_no) {
        this.registrationNo = registration_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phone_no) {
        this.phoneNo = phone_no;
    }

    public ArrayList<Receipt> getPatient() {
        return patient;
    }
    public void setPatient(ArrayList<Receipt> patient) {
        this.patient = patient;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    private String name;
    private String username;
    private String password;
    private int registrationNo;
    private String email;
    private long phoneNo;
    private ArrayList<Receipt> patient;



    private boolean verified;
}
