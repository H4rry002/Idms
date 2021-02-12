package com.Idms.util;

import com.Idms.beans.Receipt;
import com.Idms.DBUtil.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Prescription {
    Connection connect = DBConnection.getDBConnection();

    public String generateReceipt(Receipt receipt) throws SQLException {
        PreparedStatement state = connect.prepareStatement("insert into activeReceipt values(?,?,?,?,?,?)");
        state.setInt(1,receipt.getDocRegisNo());
        state.setString(2,receipt.getPatientName());
        state.setLong(3,receipt.getPatientPhNo());
        state.setTimestamp(4,new Timestamp(new Date().getTime()));
        state.setInt(5,receipt.getPatientAge());
        state.setString(6,receipt.getMedicine());
        if(state.executeUpdate()>0)
            return "Inserted";
        return "error";
    }

    public Receipt checkRecord(long phNo) throws SQLException {
        PreparedStatement state = connect.prepareStatement("select * from activeReceipt where patientPhNo=?");
        state.setLong(1, phNo);
        ResultSet rs = state.executeQuery();
        if(!rs.next())
            return null;
        Receipt receipt = new Receipt();
        receipt.setDocRegisNo(rs.getInt(1));
        receipt.setPatientName(rs.getString(2));
        receipt.setPatientPhNo(rs.getLong(3));
        receipt.setGenerateTime(rs.getDate(4));
        receipt.setPatientAge(rs.getInt(5));
        receipt.setMedicine(rs.getString(6).split(","));
        return receipt;
    }
    public String doneReceipt(Receipt receipt,String medStoreId) throws SQLException{
        PreparedStatement state = connect.prepareStatement("insert into doneReceipt values(?,?,?,?,?,?,?,?)");
        state.setInt(1,receipt.getDocRegisNo());
        state.setString(2,receipt.getPatientName());
        state.setLong(3,receipt.getPatientPhNo());
        state.setTimestamp(4,new Timestamp(receipt.getGenerateTime().getTime()));
        state.setInt(5,receipt.getPatientAge());
        state.setString(6,receipt.getMedicine());
        state.setString(7,medStoreId);
        state.setTimestamp(8,new Timestamp(new Date().getTime()));
        if(state.executeUpdate()>0)
            return "inserted";
        return "error";
    }


    public void addPreActiveRecord(Date date){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    PreparedStatement state = connect.prepareStatement("select * from activeReceipt");
                    ResultSet rs = state.executeQuery();
                    while(rs.next()){
                        System.out.println(i++);
                        Date date = rs.getDate(4);
                        if(date.before(new Date(System.currentTimeMillis()- 7L * 3600 * 24 *1000))){
                            state = connect.prepareStatement("Delete from activeReceipt where patientPhNo=?");
                            state.setInt(1,rs.getInt(3));
                            if(state.executeUpdate()>0) System.out.println("deleted");
                            state = connect.prepareStatement("Insert into preActiveReceipt values(?,?,?,?,?,?)");
                            state.setInt(1,rs.getInt(1));
                            state.setString(2,rs.getString(2));
                            state.setInt(3,rs.getInt(3));
                            state.setTimestamp(4,rs.getTimestamp(4));
                            state.setInt(5,rs.getInt(5));
                            state.setString(6,rs.getString(6));
                            if(state.executeUpdate()>0) System.out.println("inserted");
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        },date,1000*24*60*60);
    }

}
