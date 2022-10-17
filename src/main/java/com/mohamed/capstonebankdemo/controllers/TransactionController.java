package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
/*
This is the controller for the different transactions a user can do
 */
@Controller
@RequestMapping("/transact")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;

    User user;

    double new_balance;

    double currentBalance;

    //This is the postmapping to retrieve the amount that the user wants to deposit
    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("account_id") String accountId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        //Checking to see if input is empty
        if (depositAmount.isEmpty() || accountId.isEmpty() || depositAmount.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount or Account name  can not be empty");
            return "redirect:/app/dashboard";
        }
        //Get the logged in user
        user = (User) session.getAttribute("user");

        //Getting the Account Balance
        int acc_id = Integer.parseInt(accountId);

        double depositAmountValue = Double.parseDouble(depositAmount);


        // Check if deposit amount is zero or below

        if (depositAmountValue <= 0) {
            redirectAttributes.addFlashAttribute("error", "Deposit amount must be greater than zero");
            return "redirect:/app/dashboard";
        }
        // This method updates the balance
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);
        new_balance = currentBalance + depositAmountValue;

        accountRepository.changeAccountBalanceById(new_balance, acc_id);
        return "redirect:/app/dashboard";
    }
}