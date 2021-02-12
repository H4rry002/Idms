package com.Idms.beans;

import java.util.Date;

public class Receipt {
    private int docRegisNo;
    private String patientName;
    private String medStoreId;
    private long patientPhNo;
    private Date purchaseTime;
    private Date generateTime;
    private String[] medicine;


    public String getMedicine() {
        String a = "";
        for(int i=0;i<this.medicine.length;i++){
            a += medicine[i] +",";
        }
        return a;
    }

    public void setMedicine(String[] medicine) {
        this.medicine = medicine;
    }


    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    private int patientAge;

    public int getDocRegisNo() {
        return docRegisNo;
    }

    public void setDocRegisNo(int docRegisNo) {
        this.docRegisNo = docRegisNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedStoreId() {
        return medStoreId;
    }

    public void setMedStoreId(String medStoreId) {
        this.medStoreId = medStoreId;
    }

    public long getPatientPhNo() {
        return patientPhNo;
    }

    public void setPatientPhNo(long patientPhNo) {
        this.patientPhNo = patientPhNo;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }
}
