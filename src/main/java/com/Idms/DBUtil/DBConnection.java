package com.Idms.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    public static Connection connect;


    static{

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/idms","root","");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDBConnection(){
        return connect;
    }
}
