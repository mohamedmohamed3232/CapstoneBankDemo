package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
public class RegisterController {
    //Creating an instance of the repository in order to use
    @Autowired
    private UserRepository userRepository;
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
            registrationPage.addObject("confirm_pass", "The confirm field is required");
            return registrationPage;
        }
        // Check if the password matches
        if (!password.equals(confirm_password)) {
            registrationPage.addObject("passwordMisMatch", "The passwords did not match" );
            return registrationPage;
        }
        //Checking to see if the name fields are filled
        if(first_name.isEmpty() || last_name.isEmpty()) {
            registrationPage.addObject("empty-name", "The names can not be empty" );
            return registrationPage;
        }
        // Hashing the password for security
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        // Here we will register the user
        userRepository.registerUser(first_name, last_name, email, hashed_password);

        //Successful Registration method
        String successMessage = "Account was registered successfully!";
        registrationPage.addObject("success", successMessage);
        return registrationPage;
    }
}
