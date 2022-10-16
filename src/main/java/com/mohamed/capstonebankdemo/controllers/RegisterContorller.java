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
@Controller
public class RegisterContorller {

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView getLoginPage = new ModelAndView("register");
        System.out.println("Register page Contorller Working");
        getLoginPage.addObject("PageTitle", "Register");
        return getLoginPage;

    }

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
