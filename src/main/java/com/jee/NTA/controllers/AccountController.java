package com.jee.NTA.controllers;

import com.jee.NTA.crud.UserCRUD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class AccountController extends UserCRUD {

    @GetMapping(value = "/account")
    String home(@RequestParam(name="page", required=false, defaultValue="") String page, Model model) {
        model.addAttribute("page", page);
        return "html/user";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    String register(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String user_name = request.getParameter("name");
        String user_mail = request.getParameter("email_register");
        String user_pass = request.getParameter("register_pwd");



        UserCRUD.registerNewUserAccount(user_name, user_mail, user_pass);
        return "index";
    }

    
}
