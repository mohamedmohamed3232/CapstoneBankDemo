package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
/*
This is the controller to control the registration page of the application
 */
@Controller
public class RegisterContorller {
//Mapping for registration page
    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView getLoginPage = new ModelAndView("register");
        System.out.println("Register page Contorller Working");
        getLoginPage.addObject("PageTitle", "Register");
        return getLoginPage;

    }
//Post mapping in order to retrieve the information form the user to register
    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerUser")
                                 User user, BindingResult result,
                                 @RequestParam("first_name") String first_name,
                                 @RequestParam("last_name") String last_name,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirm_password") String confirm_password) {
        ModelAndView registrationPage = new ModelAndView("register");
        // Check for errors:
        if (result.hasErrors() && confirm_password.isEmpty()) {
            registrationPage.addObject("confirm_pass", "The comfirm field is required");
            return registrationPage;
        }
        return registrationPage;
    }
}
