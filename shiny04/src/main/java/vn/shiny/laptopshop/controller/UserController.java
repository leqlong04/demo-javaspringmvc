package vn.shiny.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.shiny.laptopshop.service.*;;



@RestController
public class UserController {
    
    //DI
    private UserService UserService;
    
    public UserController(UserService userService) {
        UserService = userService;
    }

    // @RequestMapping("/")    
    // public String getHomePage() {
    //     return "Hello from Controller";
    // }
    @GetMapping("")
    public String getHomePage() {
        return UserService.handleHello();
    }
    

}
