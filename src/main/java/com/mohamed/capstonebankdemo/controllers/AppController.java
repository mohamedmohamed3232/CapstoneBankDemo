package com.mohamed.capstonebankdemo.controllers;

import com.mohamed.capstonebankdemo.models.Account;
import com.mohamed.capstonebankdemo.models.PaymentHistory;
import com.mohamed.capstonebankdemo.models.TransactionHistory;
import com.mohamed.capstonebankdemo.models.User;
import com.mohamed.capstonebankdemo.repository.AccountRepository;
import com.mohamed.capstonebankdemo.repository.PaymentHistoryRepository;
import com.mohamed.capstonebankdemo.repository.TransactHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
/**
This is the controller for the dashboard as well as the menu navigation at the top of the page once logged in
 */
@Controller
@RequestMapping("/app")
public class AppController {
    User user;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private TransactHistoryRepository transactHistoryRepository;

    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        // We are getting all the details for the user who logged in
        User user = (User) session.getAttribute("user");
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
    public ModelAndView getPaymentHistory(HttpSession session) {
        //Setting the payment history view
        ModelAndView getPaymentHistoryPage = new ModelAndView("payment_history");

        // Getting the logged in user
        user = (User) session.getAttribute("user");

        //Getting the payment history records
        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentHistoriesByID(user.getUser_id());
        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);
        return getPaymentHistoryPage;
    }

    @GetMapping("/transact_history")
    public ModelAndView getTransactHistory(HttpSession session) {
        // Set the transaction history view
        ModelAndView getTransactHistoryPage = new ModelAndView("transact_history");

        // Get Logged In User:\
        user = (User) session.getAttribute("user");

        // Get Payment History / Records:
        List<TransactionHistory> userTransactHistory = transactHistoryRepository.getTransactionRecordsById(user.getUser_id());

        getTransactHistoryPage.addObject("transact_history", userTransactHistory);

        return getTransactHistoryPage;

    }
}
