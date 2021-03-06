package com.Idms.beans;

import java.util.ArrayList;


public class Pharma {
    private String name;
    private String password;
    private String gstNo;
    private long phoneNo;
    private String email;
    private String medStoreId;
    private ArrayList<Receipt> customer;
    private boolean verified;


    public void addCustomer(Receipt receipt){
        customer.add(receipt);
    }
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public ArrayList<Receipt> getCustomer() {
        return customer;
    }

    public void setCustomer(ArrayList<Receipt> customer) {
        this.customer = customer;
    }




    public String getMedStoreId() {
        return medStoreId;
    }

    public void setMedStoreId(String medStoreId) {
        this.medStoreId = medStoreId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
