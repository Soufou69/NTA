package com.jee.NTA.entities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


// THIS CLASS WILL EXTRACT DATA FROM JSON FILE (DB BACKUP) TO POPULATE DB
public class addDataToDB {

    public static Connection ConnectToDB() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        Resource resource = new ClassPathResource("/application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        Connection con = DriverManager.getConnection(props.getProperty("spring.datasource.url")+"&characterEncoding=utf8", props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
        System.out.println("Connection established......");
        return con;
    }

    public static void main(String[] args)
    {

        try (FileReader reader = new FileReader("src/main/resources/templates/html/products/product_database.json"))
        {


            JSONParser parser = new JSONParser();
            Object obj  = parser.parse(reader);
            JSONArray array = new JSONArray();
            array.add(obj);
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
        boolean addProduct= true;
        boolean addAdmin = true;
        boolean addSupport = true;
        // Check if products already exists
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produit;");
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs.getRow());
            int count_products = 0;
            while(rs.next()) {
                count_products++;
            }
            if(count_products==15) addProduct=false;

            pstmt = con.prepareStatement("SELECT * FROM nta.user WHERE is_admin=1;");
            rs = pstmt.executeQuery();
            int count_admin = 0;
            while(rs.next()) {
                count_admin++;
            }
            if(count_admin==1) addAdmin=false;

            pstmt = con.prepareStatement("SELECT * FROM nta.contact_msg;");
            rs = pstmt.executeQuery();
            int count_support = 0;
            while(rs.next()) {
                count_support++;
            }
            if(count_support==2) addSupport=false;

            if (!addProduct && !addAdmin && !addSupport) {
                rs.close();
                pstmt.close();
                return;
            }
        } finally {
            System.out.println("Aucun problème rencontré");
        }

        if(addProduct) {
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
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO nta.produit values (?, ?, ?, ?, ?, ?, ? , ?)");
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
                        sb.append(";");

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
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO nta.produit values (?, ?, ?, ?, ?, ?, ? , ?)");
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
                        sb.append(";");

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
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO nta.produit values (?, ?, ?, ?, ?, ?, ? , ?)");
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
                        sb.append(";");
                    }
                    String str = sb.toString();

                    pstmt.setString(2, str);
                    pstmt.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }


            });

        }

        if(addAdmin){
            //Get users
            JSONObject usersObject = (JSONObject) data.get("users");
            //Get admin
            JSONArray admin = (JSONArray) usersObject.get("admin");

            admin.forEach(o -> {
                JSONObject ad = (JSONObject) o;
                try {
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO nta.user values (?, ?, ?, ?, ?)");
                    pstmt.setString(1, (String) ad.get("id"));
                    boolean isAdmin =(boolean) ad.get("is_admin");
                    byte tmp=(byte) (isAdmin ? 1 : 0);
                    pstmt.setByte(2,tmp);
                    pstmt.setString(3, (String) ad.get("mail"));
                    pstmt.setString(4, (String) ad.get("name"));
                    pstmt.setString(5, (String) ad.get("password"));


                    pstmt.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }


            });
        }

        if(addSupport){
            //Get msg support
            JSONArray supportsObject = (JSONArray) data.get("support");

            supportsObject.forEach(o -> {
                JSONObject msg = (JSONObject) o;
                try {
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO nta.contact_msg values (?, ?, ?, ?, ?,?,?)");
                    pstmt.setString(1, (String) msg.get("id"));
                    pstmt.setString(2,(String) msg.get("contenu_mail"));
                    pstmt.setString(3, (String) msg.get("date_naissance"));
                    pstmt.setString(4, (String) msg.get("email"));
                    pstmt.setString(5, (String) msg.get("genre"));
                    pstmt.setString(6, (String) msg.get("name"));
                    pstmt.setString(7, (String) msg.get("sujet_mail"));


                    pstmt.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
        }



        con.close();

    }

}
