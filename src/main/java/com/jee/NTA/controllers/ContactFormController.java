package com.jee.NTA.controllers;

import com.jee.NTA.entities.ContactForm;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.ContactMsgService;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

@Controller
public class ContactFormController {
    private ContactMsgService contactMsgService;

    @Autowired
    public ContactFormController(ContactMsgService contactMsgService) {
        this.contactMsgService = contactMsgService;
    }
    @Autowired
    ServletContext servletContext;

    @GetMapping("/form")
    public String contactForm(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");
        if (UserService.checkIfAdmin(current_user)) {
            servletContext.setAttribute("current_user_isAdmin", true);
        } else {
            servletContext.setAttribute("current_user_isAdmin", false);
        }
        model.addAttribute("form", new ContactForm());
        return "html/form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute ContactForm cf, Model model) throws Exception {
        model.addAttribute("form", cf);
        this.contactMsgService.saveMsg(cf);
        return "test_form";
    }

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
