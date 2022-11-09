package com.example.hotelapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection conn;
    String uname,pass,ip,port,database;

    @SuppressLint("NewApi")
    public Connection connectionClass(){
        ip="86.121.45.177";
        database="HotelDB";
        uname="test";
        pass="test";
        port="1433";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection =null;
        String ConnectionURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+uname+";password="+pass+";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}