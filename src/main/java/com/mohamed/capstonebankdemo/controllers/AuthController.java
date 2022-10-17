package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView getLoginPage = new ModelAndView("login");
        System.out.println("Login Page Controller Working");
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;

    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        // Validating if the email and password are filled
        if (email.isEmpty() || email == null || password.isEmpty() || password == null) {
            model.addAttribute("error", "Username or Password can not be empty");
            return "login";
        }
        // Checking if email exists in the database
        String getEmailInDatabase = userRepository.getUserEmail(email);
        if (getEmailInDatabase != null) {
            //Getting the password in the database
            String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);

            //Validating the password using BCrypt function checking to see
            // if the password inputted is the same stored in the database
            if (!BCrypt.checkpw(password, getPasswordInDatabase)) {
                model.addAttribute("error", "Password or Username is incorrect please try again");
                return "login";
            }
        } else {
            model.addAttribute("error", "Something Blew up please rerun or just RUN!");
            return "error";
        }
        User user = userRepository.getUserDetails(getEmailInDatabase);
        session.setAttribute("user", user);
        session.setAttribute("authenticated", true);
        return "redirect:/app/dashboard";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out","You have logged out successfully");
        return "redirect:/login";
    }
}
