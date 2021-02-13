package com.Idms.util;

import com.Idms.DBUtil.DBConnection;
import com.Idms.beans.*;
import java.sql.*;
import java.util.Random;
import BCrypt.src.org.mindrot.jbcrypt.BCrypt;



public class Users {
    Connection connect = DBConnection.getDBConnection();
    public String createDoctor(Doctor doc) throws SQLException {
        try {
            String password = BCrypt.hashpw(doc.getPassword(), BCrypt.gensalt(12));
            PreparedStatement state = connect.prepareStatement("insert into doctor values(?,?,?,?,?,?,?)");
            state.setInt(1, doc.getRegistrationNo());
            state.setString(2, doc.getName());
            state.setString(3, generateDocUsername(doc.getName()));
            state.setString(4, doc.getEmail().toLowerCase());
            state.setString(5, password);
            state.setInt(6, (int) doc.getPhoneNo());
            state.setBoolean(7,false);
            if (state.executeUpdate() > 0)
                return "Inserted";
        }catch(SQLIntegrityConstraintViolationException e){
            return e.toString();
        }
        return "error";
    }

     public String generateDocUsername(String name) throws SQLException {
        Random random = new Random();
        name = name.split("\\s")[0];
        String username = "DR"+name+ random.nextInt(100);
        PreparedStatement state = connect.prepareStatement("select * from doctor where username=?");
        while(true){
            state.setString(1,username.toUpperCase());
            ResultSet rs = state.executeQuery();
            if(rs.next())
                continue;
            return username;
        }
    }


    public String generatePhUsername(String name) throws SQLException {
        Random random = new Random();
        String username = "PH"+name.split(" ")[0]+ random.nextInt(100);
        PreparedStatement state = connect.prepareStatement("select * from pharma where medStoreId=?");
        while(true){
            state.setString(1,username.toUpperCase());
            ResultSet rs = state.executeQuery();
            if(rs.next())
                continue;
            return username;
        }
    }

    public String createPharma(Pharma pharma) throws SQLException {
        String password = BCrypt.hashpw(pharma.getPassword(),BCrypt.gensalt(12));
        try {
            PreparedStatement state = connect.prepareStatement("insert into pharma values(?,?,?,?,?,?,?)");
            state.setString(1, generatePhUsername(pharma.getName()));
            state.setString(2, pharma.getName());
            state.setString(3, password);
            state.setString(4, pharma.getGstNo());
            state.setString(5, pharma.getEmail().toLowerCase());
            state.setInt(6, (int) pharma.getPhoneNo());
            state.setBoolean(7, false);
            if (state.executeUpdate() > 0)
                return "Inserted";
        }catch (SQLIntegrityConstraintViolationException e){
            return e.toString();
        }
        return "error";
    }

    public String doctorchangePassword(String newPassword,String username) throws SQLException {
        String password = BCrypt.hashpw(newPassword,BCrypt.gensalt(12));
        PreparedStatement state = connect.prepareStatement("update doctor set password = ? where email = ?");
        state.setString(1,password);
        state.setString(2,username);
        if(state.executeUpdate()>0){
            return "Password has been changed";
        }
        return "Some issue occured contact the admin or please try again later";

    }
    public String getNameForgotPassword(String username) throws SQLException {
        if(username.contains("@")){
            PreparedStatement state = connect.prepareStatement("select name from doctor where email = ?");
            state.setString(1,username);
            ResultSet rs = state.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        }else{
            PreparedStatement state = connect.prepareStatement("select name from doctor where username=?");
            state.setString(1,username);
            ResultSet rs = state.executeQuery();
            if(!rs.next()){
                return rs.getString(1);
            }
        }
        return null;
    }

}
