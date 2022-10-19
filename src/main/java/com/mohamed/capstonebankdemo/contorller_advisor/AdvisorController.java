package com.mohamed.capstonebankdemo.contorller_advisor;

import com.mohamed.capstonebankdemo.models.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
This allows the handling of the registered user in all controllers in the application
 */
@ControllerAdvice
public class AdvisorController {
    @ModelAttribute("registerUser")
    public User getUserDefaults() {
        return new User();
    }

}
