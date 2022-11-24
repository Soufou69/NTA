package com.jee.NTA.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;



public class addDataToDB {

    public static Connection ConnectToDB() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/java";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established......");
        return con;
    }
    public static void main(String args[]) {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();
        try {
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/static/js/product_database.json"));
            //Retrieving the array
            JSONArray jsonArray = (JSONArray) jsonObject.get("players_data");
            Connection con = ConnectToDB();
            //Insert a row into the MyPlayers table
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO MyPlayers values (?, ?, ?, ?, ?, ? )");
            for(Object object : jsonArray) {
                JSONObject record = (JSONObject) object;
                int id = Integer.parseInt((String) record.get("ID"));
                String first_name = (String) record.get("First_Name");
                String last_name = (String) record.get("Last_Name");
                String date = (String) record.get("Date_Of_Birth");
                long date_of_birth = Date.valueOf(date).getTime();
                String place_of_birth = (String) record.get("Place_Of_Birth");
                String country = (String) record.get("Country");
                pstmt.setInt(1, id);
                pstmt.setString(2, first_name);
                pstmt.setString(3, last_name);
                pstmt.setDate(4, new Date(date_of_birth));
                pstmt.setString(5, place_of_birth);
                pstmt.setString(6, country);
                pstmt.executeUpdate();
            }
            System.out.println("Records inserted.....");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
