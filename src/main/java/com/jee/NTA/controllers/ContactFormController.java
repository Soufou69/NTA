package com.jee.NTA.controllers;

import com.jee.NTA.crud.ContactForm;
import com.jee.NTA.crud.ContactFormCRUD;
//import com.jee.NTA.crud.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactFormController {



    @GetMapping("/form")
    public String contactForm(Model model) {
        model.addAttribute("form", new ContactForm());
        return "html/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute ContactForm cf, Model model) throws Exception {
        model.addAttribute("form", cf);
        ContactFormCRUD.createMsgToDB(cf);
        return "test_form";
    }

//    @GetMapping("/admin-get-msg")
//    public String getMsgFromDB(Model model) throws Exception {
//        ContactFormCRUD.retrieveMsgFromDB("cf");
//        return "html/form";
//    }

}
