package com.example.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection connect;
    String ConnectionResult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetTextFromSql(View v)
    {
        TextView tx1=(TextView) findViewById(R.id.textView);

        try{
            ConnectionHelper connectionHelper=new ConnectionHelper();
            connect=connectionHelper.connectionClass();
            if(connect!=null){
                String query="Select * from Users where Username='rares'";
                Statement st=connect.createStatement();
                ResultSet rs=st.executeQuery(query);
                String result="";
                while(rs.next()){
                    result+=rs.getString(1);
                    result+=rs.getString(2);
                    result+=rs.getString(3);
                    result+=rs.getString(4);
                }
                System.out.println(result);
                tx1.setText(result);
            }
            else{
                ConnectionResult="Check Connection";
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}