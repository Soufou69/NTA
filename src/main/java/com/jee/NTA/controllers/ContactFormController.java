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
        return "html/form";
    }


}
