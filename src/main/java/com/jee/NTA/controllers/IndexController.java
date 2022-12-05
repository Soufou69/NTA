package com.jee.NTA.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class IndexController {

    @GetMapping(value = "/")
    String home(@RequestParam(name="page", required=false, defaultValue="") String page, Model model) {
        model.addAttribute("page", page);
        return "index";
    }
}