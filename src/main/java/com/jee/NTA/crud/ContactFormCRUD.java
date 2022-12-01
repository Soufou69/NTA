package com.jee.NTA.crud;

import org.json.simple.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

public class ContactFormCRUD {

    public static void createMsgToDB(ContactForm cf) throws Exception {

        Connection con = ConnectToDB();
        try {
            UUID uuid = UUID.randomUUID();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO contact_msg values (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, (String) uuid.toString());
            pstmt.setString(2, (String) cf.getName());
            pstmt.setString(3, (String) cf.getEmail());
            pstmt.setString(4, (String) cf.getGenre());
            pstmt.setString(5, (String) cf.getDateNaissance());
            pstmt.setString(6, (String) cf.getSujetMail());
            pstmt.setString(7, (String) cf.getContenuMail());
            pstmt.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
        }

    }

    public static ContactForm retrieveMsgFromDB(String id) throws Exception {
        ContactForm cf = new ContactForm();
        Connection con = ConnectToDB();
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from contact_msg;");
            pstmt.execute();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            con.close();
        }

        return cf;
    }


}
