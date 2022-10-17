package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.helpers.GenAccountNumber;
import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

//Account Controller for accounts
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // This allows the user to create a new account in the app
    @PostMapping("/create_account")
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        //Checking if there are empty Strings
        if (accountName.isEmpty() || accountType.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Account Name and Account Type can not be empty");
            return "redirect:/app/dashboard";
        }
        // Checking if the user is logged in
        User user = (User) session.getAttribute("user");

        // Generating a new account number for this user
        int setAccountNumber = GenAccountNumber.generateAccountNumber();
        String bankAccountNumber = Integer.toString(setAccountNumber);
        // Creating an account for the User
        accountRepository.createBankAccount(user.getUser_id(), bankAccountNumber, accountName, accountType);
        redirectAttributes.addFlashAttribute("success", "Account Created Successfully!");
        return "redirect:/app/dashboard";
    }

}