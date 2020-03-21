package com.epam.hw5.controller;

import com.epam.hw5.model.Account;
import com.epam.hw5.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = accountService.getAll();

        return (accounts != null && accounts.size() > 0)
                ? ResponseEntity.ok(accounts)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        Optional<Account> account = accountService.getById(id);

        return account.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
}
