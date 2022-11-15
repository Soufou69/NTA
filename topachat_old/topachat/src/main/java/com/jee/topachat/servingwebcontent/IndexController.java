package com.jee.topachat.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping(path = "/test")
    public String test() {
        return "greeting";
    }

//    @RequestMapping(path="/greeting", method= RequestMethod.GET)
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }
}
