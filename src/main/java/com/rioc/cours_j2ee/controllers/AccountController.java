package com.rioc.cours_j2ee.controllers;


import com.rioc.cours_j2ee.exceptions.ApiException;
import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.services.account.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
    public final IAccountService service;

    public AccountController(IAccountService service) {
        this.service = service;
    }

    @PostMapping("/accounts")
    public String addNewAccount(
            @RequestBody Account account
    ) {
        try {
            service.postAccount(account);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return account.toString();
    }
}
