package com.Idms.main;

import com.Idms.util.*;
import com.Idms.beans.*;
import com.mysql.cj.result.SqlDateValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;



public class Main {
    static Login login;
    VerifyLogin verifyLogin = new VerifyLogin();
    Users users = new Users();
    Prescription prescription = new Prescription();
    Display display = new Display();
    static DoctorDisplay docDisplay;
    static PharmacyDisplay pharmacyDisplay;


    public String verifyLogin(String person,String username,String password) throws SQLException, IOException {
        if(person.equals("doctor")) {
            Doctor doc =  verifyLogin.verifyCredsDoc(username,password);
            if(doc!=null){
                if(!doc.isVerified()){
                    return "notVerified";
                }
                doctor(doc);
                return "Okay";
            }
        }
        else{
            Pharma pharma = verifyLogin.verifyCredsPh(username,password);
            if(pharma!=null){
                if(!pharma.isVerified())
                    return "notVerified";
                pharmacy(pharma);
                return "Okay";
            }
        }
        return "*Invalid Credential";
    }

    public boolean verifyUser(String person,String username){
        if(person.equals("doctor")){
           return verifyLogin.verifyDoc(username);
        }else{
            return verifyLogin.verifyPh(username);
        }
    }

    public String createUser(String person,String name,String email,String password,String regisNo) throws SQLException {
        if(person.equals("doctor")){
            Doctor doc = new Doctor();
            doc.setRegistrationNo(Integer.parseInt(regisNo));
            doc.setName(name);
            doc.setEmail(email);
            doc.setUsername(users.generateDocUsername(name));
            doc.setPassword(password);
            return users.createDoctor(doc);
        } else{
            Pharma ph = new Pharma();
            ph.setName(name);
            ph.setEmail(email);
            ph.setMedStoreId(users.generatePhUsername(name));
            ph.setPassword(password);
            ph.setGstNo(regisNo);
            return users.createPharma(ph);
        }
    }
    public void doctor(Doctor doctor) {
        try {
            doctor.setPatient(display.PatientData(doctor.getRegistrationNo()));
        }catch (SQLException e){
            e.printStackTrace();
        }
        login.setVisible(false);
        login.dispose();
        docDisplay = new DoctorDisplay(doctor);
        docDisplay.setVisible(true);
        docDisplay.setLocationRelativeTo(null);
    }

    public void pharmacy(Pharma pharma){
        try{
            pharma.setCustomer(display.CustomerList(pharma.getMedStoreId()));
        }catch (SQLException e){
            e.printStackTrace();
        }
        login.setVisible(false);
        login.dispose();
        pharmacyDisplay = new PharmacyDisplay(pharma);
        pharmacyDisplay.setVisible(true);
        pharmacyDisplay.setLocationRelativeTo(null);
    }

    public Receipt createReceipt(int docRegisNo,String name,int age,String[] medicine,long ph){
        Receipt receipt = new Receipt();
        receipt.setMedicine(medicine);
        receipt.setPatientAge(age);
        receipt.setPatientPhNo(ph);
        receipt.setDocRegisNo(docRegisNo);
        receipt.setPatientName(name);
        try {
            return prescription.generateReceipt(receipt);
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public Receipt searchReceipt(long phoneNo){
        try {
            return prescription.checkRecord(phoneNo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String changePassword(String current,String newPassword,String email,String person){
        if(person.equals("doctor")){
            try {
                if(verifyLogin.verifyCredsDoc(email, current)!=null){
                    return users.doctorchangePassword(newPassword,email);
                }
                return "*Invalid Password";
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return "Some Issue occur please contact admin";
    }

    public String resetPassword(String password,String username,String person){
        if(person.equals("doctor")){
            try {
                return users.doctorchangePassword(password, username);
            }catch (Exception e){
                System.out.print(e.toString());
            }
        }
        return null;
    }
    public String sendPasswordMail(String username){
        String name;
        String resetCode = randomPassword(6);
        try {
            name = users.getNameForgotPassword(username);
        }catch (Exception e){
            System.out.println(e);
            return "*User doesn't exist";
        }
        if(name!=null) {
            Thread p = new Thread(() -> {
                synchronized (this) {
                    new SendMail().forgotPasswordMail(username, name, resetCode);
                }
            });
            p.start();
            return resetCode;
        }
            return "*Email or username is not registered";
    }

    String randomPassword(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(12);
        for(int i=0;i<n;i++){
            int j = (int)(AlphaNumericString.length()*Math.random());
            sb.append(AlphaNumericString.charAt(j));
        }
        return sb.toString();
    }

    public Receipt purchase(Receipt receipt){

        new Thread(()->{
            if(!prescription.removeActiveReceipt(receipt)){
                System.out.println("the row is not deleted");
            }
        }).start();
        return prescription.doneReceipt(receipt);
    }


    public void logout(String person){
        if(person.equals("doctor")){
            docDisplay.dispose();
        }
         else { pharmacyDisplay.dispose(); };
        login = new Login();
        login.setVisible(true);

    }

    public static void main(String[] args) {
        login = new Login();
        login.setVisible(true);
    }
}

