package com.mohamed.capstonebankdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    //Mapping the index page
    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        System.out.println("In the Index Controller");
        return getIndexPage;
    }
    //Mapping the login page
    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView getLoginPage = new ModelAndView("login");
        System.out.println("Login Page Controller Working");
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;

    }
}
