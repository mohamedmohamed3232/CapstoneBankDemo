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
    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView getLoginPage = new ModelAndView("register");
        System.out.println("Register page Contorller Working");
        getLoginPage.addObject("PageTitle", "Register");
        return getLoginPage;

    }
    //Mapping the error page
    @GetMapping("/error")
    public ModelAndView getError() {
        ModelAndView getErrorPage = new ModelAndView("error");
        getErrorPage.addObject("PageTitle", "Errors");
        System.out.println("Error Page controller Working");
        return getErrorPage;
    }
}
