<<<<<<<< HEAD:src/main/java/com/jee/NTA/controllers/IndexController.java
package com.jee.NTA.controllers;

========
package com.jee.NTA;
>>>>>>>> main:src/main/java/com/jee/NTA/IndexController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<<< HEAD:src/main/java/com/jee/NTA/controllers/IndexController.java
========

@Controller
class IndexController {
>>>>>>>> main:src/main/java/com/jee/NTA/IndexController.java

@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";
    }


}
