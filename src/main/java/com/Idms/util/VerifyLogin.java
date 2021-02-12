package com.Idms.util;

import com.Idms.DBUtil.DBConnection;
import java.sql.*;
import BCrypt.src.org.mindrot.jbcrypt.BCrypt;
import com.Idms.beans.Doctor;
import com.Idms.beans.Pharma;

public class VerifyLogin {

    static Connection connect = DBConnection.getDBConnection();

    public Doctor verifyCredsDoc(String username,String password) throws SQLException {
        PreparedStatement state;
        if(username.contains("@")){
            state = connect.prepareStatement("select * from doctor where email = ?");
            state.setString(1,username.toLowerCase());
        } else if(username.substring(0,2).toUpperCase().equals("DR") ) {
              state = connect.prepareStatement("select * from doctor where username=?");
            state.setString(1,username.toUpperCase());
        } else{
              return null;
            }
          ResultSet rs = state.executeQuery();
          if(rs.next()){
              if(BCrypt.checkpw(password,rs.getString(5))){
                  Doctor doctor = new Doctor();
                  doctor.setRegistrationNo(rs.getInt(1));
                  doctor.setName(rs.getString(2));
                  doctor.setUsername(rs.getString(3));
                  doctor.setEmail(rs.getString(4));
                  doctor.setPhoneNo(rs.getInt(6));
                  doctor.setVerified(rs.getBoolean(7));
                  return doctor;
              }
          }
        return null;
    }

    public Pharma verifyCredsPh(String username, String password) throws SQLException {
        PreparedStatement state;
            if(username.indexOf("@")>0){
                System.out.println("email");
                state = connect.prepareStatement("select * from pharma where email = ?");
                state.setString(1,username.toLowerCase());
            }
            else if(username.substring(0,2).toUpperCase().equals("PH") ) {
                state = connect.prepareStatement("select * from pharma where username=?");
                state.setString(1,username.toUpperCase());
            }
            else{
                return null;
            }

            ResultSet rs = state.executeQuery();
            if(rs.next()){
                if(BCrypt.checkpw(password,rs.getString(3))){
                    Pharma pharma = new Pharma();
                    pharma.setMedStoreId(rs.getString(1));
                    pharma.setName(rs.getString(2));
                    pharma.setGstNo(rs.getString(3));
                    pharma.setEmail(rs.getString(4));
                    pharma.setPhoneNo(rs.getInt(5));
                    return pharma;
                }
            }
        return null;
    }

    public boolean verifyDoc(String username){
        PreparedStatement state;
        try {
            if (username.indexOf("@") > 0) {
                System.out.println("email");
                state = connect.prepareStatement("Update doctor set verified=? where email = ?");
                state.setBoolean(1,true);
                state.setString(2, username.toLowerCase());
            } else if (username.substring(0, 2).toUpperCase().equals("PH")) {
                state = connect.prepareStatement("Update doctor set verified=? where email = ?");
                state.setBoolean(1,true);
                state.setString(2, username.toLowerCase());
            }else{
                return false;
            }
            if(state.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean verifyPh(String username){
        PreparedStatement state;
        try {
            if (username.indexOf("@") > 0) {
                System.out.println("email");
                state = connect.prepareStatement("Update pharma set verified=? where email = ?");
                state.setBoolean(1,true);
                state.setString(2, username.toLowerCase());
            } else if (username.substring(0, 2).toUpperCase().equals("PH")) {
                state = connect.prepareStatement("Update pharma set verified=? where email = ?");
                state.setBoolean(1,true);
                state.setString(2, username.toLowerCase());
            }else{
                return false;
            }
            if(state.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
