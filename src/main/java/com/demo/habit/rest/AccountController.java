package com.demo.habit.rest;

import com.demo.habit.model.Account;
import com.demo.habit.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Account> getAccount(@PathVariable String email) {
        return accountService.getAccount(email);
    }

    @PostMapping("/update/{email}")
    public ResponseEntity<String> updateAccount(@PathVariable String email) {
        return accountService.updateAccount(email);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteAccount(@PathVariable String email) {
        return accountService.deleteAccount(email);
    }

}
