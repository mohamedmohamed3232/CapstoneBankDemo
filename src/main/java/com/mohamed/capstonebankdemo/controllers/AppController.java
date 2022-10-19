package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.Account;
import com.mohamed.capstonebankdemo.models.PaymentHistory;
import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.AccountRepository;
import com.mohamed.capstonebankdemo.repository.PaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    User user;

    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session){
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        // We are getting all the details for the user who logged in
        User user = (User)session.getAttribute("user");
        // Getting the accounts of the logged in User
        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id());

        //Getting the balance of the user fron the database
        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        // Setting the User accont and the Account Balance
        getDashboardPage.addObject("userAccounts", getUserAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);

        return getDashboardPage;
    }
    @GetMapping("/payment_history")
    public ModelAndView getPaymentHistory(HttpSession session){
        //Setting the payment history view
        ModelAndView getPaymentHistoryPage = new ModelAndView("payment_history");

        // Getting the logged in user
        user = (User) session.getAttribute("user");

        //Getting the payment history records
        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentHistoriesByID(user.getUser_id());
        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);
        return getPaymentHistoryPage;
    }
}
