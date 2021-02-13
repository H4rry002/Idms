package com.Idms.util;

import com.Idms.DBUtil.DBConnection;
import com.Idms.beans.Receipt;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.*;
import java.sql.*;

public class Display {
    Connection connect = DBConnection.getDBConnection();;


//    public ArrayList<Receipt> NoOfPatient(int docRegisNo) throws SQLException {
//        assert connect != null;
//        PreparedStatement state = connect.prepareStatement("select * from activereceipt where docRegisNo=?");
//        state.setInt(1,docRegisNo);
//        ResultSet rs = state.executeQuery();
//        ArrayList<Receipt> list = new ArrayList<>();
//        while(rs.next()){
//            Receipt receipt = new Receipt();
//            receipt.setDocRegisNo(rs.getInt(1));
//            receipt.setPatientName(rs.getString(2));
//            receipt.setPatientPhNo(rs.getLong(3));
//            receipt.setGenerateTime(rs.getDate(4));
//            receipt.setPatientAge(rs.getInt(5));
//            receipt.setMedicine(rs.getString(6).split(","));
//            list.add(receipt);
//        }
//        if(list.size()<20) {
//            state = connect.prepareStatement("select * from donereceipt where docRegisNo=? limit 20");
//            state.setInt(1,docRegisNo);
//            rs = state.executeQuery();
//            while(rs.next()){
//                Receipt receipt = new Receipt();
//                receipt.setDocRegisNo(rs.getInt(1));
//                receipt.setPatientName(rs.getString(2));
//                receipt.setPatientPhNo(rs.getLong(3));
//                receipt.setGenerateTime(rs.getDate(4));
//                receipt.setPatientAge(rs.getInt(5));
//                receipt.setMedicine(rs.getString(6).split(","));
//                receipt.setMedStoreId(rs.getString(7));
//                receipt.setPurchaseTime(rs.getDate(8));
//                list.add(receipt);
//            }
//        }
//        return list;
//    }
    public ArrayList<Receipt> PatientData(int docRegisNo) throws SQLException {
        PreparedStatement state = connect.prepareStatement("SELECT * FROM activereceipt WHERE generationTime > DATE_SUB(now(),INTERVAL 1 year) and docRegisno=?");
        state.setInt(1,docRegisNo);
        ResultSet rs = state.executeQuery();

        ArrayList<Receipt> list = new ArrayList<>();

        while(rs.next()){
            Receipt receipt = new Receipt();
            receipt.setDocRegisNo(rs.getInt(1));
            receipt.setPatientName(rs.getString(2));
            receipt.setPatientPhNo(rs.getLong(3));
            receipt.setGenerateTime(rs.getTimestamp(4));
            receipt.setPatientAge(rs.getInt(5));
            receipt.setMedicine(rs.getString(6).split(","));
            list.add(receipt);
        }
        state = connect.prepareStatement("SELECT * FROM donereceipt WHERE generationTime >= CURDATE()");
        rs = state.executeQuery();
        while(rs.next()){
            Receipt receipt = new Receipt();
            receipt.setDocRegisNo(rs.getInt(1));
            receipt.setPatientName(rs.getString(2));
            receipt.setPatientPhNo(rs.getLong(3));
            receipt.setGenerateTime(rs.getDate(4));
            receipt.setPatientAge(rs.getInt(5));
            receipt.setMedicine(rs.getString(6).split(","));
            receipt.setMedStoreId(rs.getString(7));
            receipt.setPurchaseTime(rs.getDate(8));
            list.add(receipt);
        }
        return list;
    }


    public ArrayList<Receipt> CustomerList(String medStoreId) throws SQLException {
        ArrayList<Receipt> list = new ArrayList<>();
        PreparedStatement state = connect.prepareStatement("select * from donereceipt where medstoreid = ? and purchasetime>=CURDATE()");
        state.setString(1,medStoreId);
        ResultSet rs = state.executeQuery();
        while(rs.next()){
            Receipt receipt = new Receipt();
            receipt.setDocRegisNo(rs.getInt(1));
            receipt.setPatientName(rs.getString(2));
            receipt.setPatientPhNo(rs.getLong(3));
            receipt.setGenerateTime(rs.getDate(4));
            receipt.setPatientAge(rs.getInt(5));
            receipt.setMedicine(rs.getString(6).split(","));
            receipt.setMedStoreId(rs.getString(7));
            receipt.setPurchaseTime(rs.getDate(8));
            list.add(receipt);
        }
        return list;
    }


//    public static void main(String[] args) throws SQLException {
//        ArrayList<Receipt> list =  new Display().todayPatientDoc(555555);
//        for(Receipt i:list){
//            System.out.println(i.getPatientPhNo()+" "+i.getDocRegisNo()+" "+i.getGenerateTime()+" "+i.getPatientName());
//        }
//    }
}
