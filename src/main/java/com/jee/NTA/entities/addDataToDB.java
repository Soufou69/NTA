package com.jee.NTA.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;



// THIS CLASS WILL EXTRACT DATA FROM JSON FILE (DB BACKUP) TO POPULATE DB
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

    public static void main(String[] args)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/templates/html/products/product_database.json"))
        {


            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(reader);
            JSONArray array = new JSONArray();
            array.add(obj);
            System.out.println(obj);
            array.forEach( emp -> {
                try {
                    sendDataIntoDB( (JSONObject) emp );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static void sendDataIntoDB(JSONObject data) throws Exception {

        Connection con = ConnectToDB();

        //Get products
        JSONObject productsObject = (JSONObject) data.get("products");

        //Get stockCG
        JSONArray stockCG = (JSONArray) productsObject.get("stockCG");

        //Get stockPROC
        JSONArray stockPROC = (JSONArray) productsObject.get("stockPROC");

        //Get stockRAM
        JSONArray stockRAM = (JSONArray) productsObject.get("stockRAM");

        stockCG.forEach(o -> {
            JSONObject cg = (JSONObject) o;
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO produit values (?, ?, ?, ?, ?, ?, ? , ?)");
                pstmt.setString(1, (String) cg.get("id"));
                pstmt.setString(3, (String) cg.get("img_src"));
                pstmt.setDouble(4, (Double) cg.get("price"));
                pstmt.setString(5, (String) cg.get("ref"));
                pstmt.setLong(6, (Long) cg.get("stock"));
                pstmt.setString(7, (String) cg.get("title"));
                pstmt.setString(8, (String) cg.get("type"));

                JSONArray desc = (JSONArray) cg.get("desc");
                StringBuilder sb = new StringBuilder();
                for (Object value : desc) {
                    sb.append(value);
                }
                String str = sb.toString();

                pstmt.setString(2, str);
                pstmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        stockPROC.forEach(o -> {
            JSONObject proc = (JSONObject) o;
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO produit values (?, ?, ?, ?, ?, ?, ? , ?)");
                pstmt.setString(1, (String) proc.get("id"));
                pstmt.setString(3, (String) proc.get("img_src"));
                pstmt.setDouble(4, (Double) proc.get("price"));
                pstmt.setString(5, (String) proc.get("ref"));
                pstmt.setLong(6, (Long) proc.get("stock"));
                pstmt.setString(7, (String) proc.get("title"));
                pstmt.setString(8, (String) proc.get("type"));

                JSONArray desc = (JSONArray) proc.get("desc");
                StringBuilder sb = new StringBuilder();
                for (Object value : desc) {
                    sb.append(value);
                }
                String str = sb.toString();

                pstmt.setString(2, str);
                pstmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        stockRAM.forEach(o -> {
            JSONObject ram = (JSONObject) o;
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO produit values (?, ?, ?, ?, ?, ?, ? , ?)");
                pstmt.setString(1, (String) ram.get("id"));
                pstmt.setString(3, (String) ram.get("img_src"));
                pstmt.setDouble(4, (Double) ram.get("price"));
                pstmt.setString(5, (String) ram.get("ref"));
                pstmt.setLong(6, (Long) ram.get("stock"));
                pstmt.setString(7, (String) ram.get("title"));
                pstmt.setString(8, (String) ram.get("type"));

                JSONArray desc = (JSONArray) ram.get("desc");
                StringBuilder sb = new StringBuilder();
                for (Object value : desc) {
                    sb.append(value);
                }
                String str = sb.toString();

                pstmt.setString(2, str);
                pstmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }


        });


    }

}
