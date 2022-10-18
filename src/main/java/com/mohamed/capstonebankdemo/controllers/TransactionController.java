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
    // This is postmapping to retrieve transfer details
    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount") String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){
        //Checking for empty transfer fields
        if (transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty() || transfer_amount.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Transfer names and amount can not be empty");
            return "redirect:/app/dashboard";
        }
        // Converting ids and amounts
        double transferAmt= Double.parseDouble(transfer_amount);
        int transferFromId = Integer.parseInt(transfer_from);
        int transferToId = Integer.parseInt(transfer_to);

        //Checking to see if user is transferring to same account

        if (transferFromId == transferToId) {
            redirectAttributes.addFlashAttribute("error", "Can not transfer to same account please choose another account");
            return "redirect:/app/dashboard";
        }


        //Checking to see if the transfer amount is below zero

        if(transferAmt <=0) {
            redirectAttributes.addFlashAttribute("error", "Transfer amount must be greater than zero");
            return "redirect:/app/dashboard";
        }
        //

        //Getting the logged in user

        user = (User)session.getAttribute("user");


        // Getting the users current balance

        double currentBalanceAccFrom = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);
        double currentBalanceAccTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);

        //Checking to see if there is sufficient funds
        if (transferAmt > currentBalanceAccFrom) {


            return "redirect:/app/dashboard";
        }

        // Setting the new Balance
        double newBalanceTransferFrom = currentBalanceAccFrom - transferAmt;

        double newBalanceTransferTo = currentBalanceAccTo + transferAmt;

        // Changing the balance of the account transferring from
        accountRepository.changeAccountBalanceById(newBalanceTransferFrom, transferFromId);

        // Change the Balance from the account transferring to
        accountRepository.changeAccountBalanceById(newBalanceTransferTo, transferToId);

        redirectAttributes.addFlashAttribute("success", "Amount was transferred Successfully!");
        return "redirect:/app/dashboard";


    }
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount") String withdrawalAmount,
                           @RequestParam("account_id") String accountId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        //Checking for empty
        if (withdrawalAmount.isEmpty() || accountId.isEmpty() || withdrawalAmount.isBlank() ) {
            redirectAttributes.addFlashAttribute("error", "Must have a valid withdrawal amount and account");
            return "redirect:/app/dashboard";
        }
        //Converting amount and id to numbers
        double withdrawalAmt = Double.parseDouble(withdrawalAmount);
        int account_Id = Integer.parseInt(accountId);

        //Checking to see if the amount is less than or equal to zero
        if (withdrawalAmt <= 0) {
            redirectAttributes.addFlashAttribute("error", "Withdrawal amount must be greater than zero");
            return "redirect:/app/dashboard";
        }
        //Getting the logged in user through the active session
        user =(User)session.getAttribute("user");

        //Getting the account balance currently
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_Id);

        //Setting the new balance after the user withdraws
        if (withdrawalAmt>currentBalance) {

            redirectAttributes.addFlashAttribute("error", "Insufficient funds ");
            return "redirect:/app/dashboard";
        }
        new_balance = currentBalance - withdrawalAmt;

        // Updating the users account balance
        accountRepository.changeAccountBalanceById(new_balance, account_Id);
        redirectAttributes.addFlashAttribute("success", "Withdrawal was Successful!");
        return "redirect:/app/dashboard";
    }
}