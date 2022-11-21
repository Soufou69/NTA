package com.jee.NTA.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class ProductsController {

    @GetMapping("/products")
    public String greeting(@RequestParam(name="p", required=false, defaultValue="World") String p, Model model) {

        switch (p) {
            case "1":
                return "products1";

            case "2":
                return "products2";

            case "3":
                return "products3";

            default:
                return "index";
        }

    }

}
