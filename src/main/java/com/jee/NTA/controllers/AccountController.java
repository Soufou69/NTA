package com.jee.NTA.controllers;

import com.jee.NTA.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

@Controller
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ServletContext servletContext;


    @GetMapping(value = "/account")
    String account(Model model) {
        model.addAttribute("user_register", new User());

        if (servletContext.getAttribute("user_logged") == null) {
            servletContext.setAttribute("user_logged", false);
        }

        servletContext.removeAttribute("error");
        System.out.println(servletContext.getAttribute("user_logged"));
        return "html/user";
    }

    @PostMapping( "/register")
    String register(@ModelAttribute User user,Model model) throws Exception {

//        servletContext.setAttribute("user_logged", false);
        model.addAttribute("user_register", user);
        registerNewUserAccount(user);
        return "html/user";
    }

    @RequestMapping( "/login")
    String login(@RequestParam("login_mail") String user_mail, @RequestParam("login_passwd") String user_passwd, Model model) throws Exception {

        User current_user = userLogin(user_mail, user_passwd);
        if (current_user == null) {
            model.addAttribute("user_register", new User());
//            servletContext.setAttribute("user_logged", false);
            servletContext.setAttribute("error", "Erreur : email ou mot de passe invalide !");
        } else {
            model.addAttribute("user_register", new User());
            servletContext.setAttribute("user_logged", true);
            servletContext.setAttribute("logged_in_user", current_user);
        }
        return "html/user";
    }

    public void registerNewUserAccount(User user) throws Exception {

        UUID uuid = UUID.randomUUID();
        user.setAdmin(false);
        user.setId(uuid.toString());

        Connection con = ConnectToDB();
        try {

            String user_pass_encoded = passwordEncoder.encode(user.getPassword());

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO user values (?, ?, ?, ?, ?)");
            pstmt.setString(1, (String) user.getId());
            pstmt.setBoolean(2, (Boolean) user.isAdmin());
            pstmt.setString(3, (String) user.getMail());
            pstmt.setString(4, (String) user.getName());
            pstmt.setString(5, (String) user_pass_encoded);
            pstmt.executeUpdate();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            con.close();
        }

    }

    public User userLogin(String user_mail, String user_passwd) throws Exception {

        Connection con = ConnectToDB();
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from user u where u.mail = '"+user_mail+"'");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String user_encoded_passwd = rs.getString("password");
                System.out.println(user_encoded_passwd);
                System.out.println(passwordEncoder.matches(user_passwd, user_encoded_passwd));
                if(passwordEncoder.matches(user_passwd, user_encoded_passwd)) {

                    User current_user = new User();
                    current_user.setId(rs.getString("id"));
                    current_user.setAdmin(rs.getBoolean("is_admin"));
                    current_user.setMail(rs.getString("mail"));
                    current_user.setName(rs.getString("name"));

                    rs.close();
                    pstmt.close();
                    con.close();
                    return current_user;
                } else {
                    rs.close();
                    pstmt.close();
                    con.close();
                    return null;
                }

            } else {
                rs.close();
                pstmt.close();
                con.close();
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
            con.close();
        }

        return null;
    }

    
}
