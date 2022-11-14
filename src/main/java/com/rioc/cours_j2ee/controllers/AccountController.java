package com.rioc.cours_j2ee.controllers;


import com.google.gson.Gson;
import com.rioc.cours_j2ee.exceptions.ApiException;
import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.services.account.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AccountController {
    public final IAccountService service;

    public AccountController(IAccountService service) {
        this.service = service;
    }

    /**
     * @param account Compte utilisateur récupéré dans le corps de la requête POST
     * @return Retourne le compte utilisateur nouvellement créé
     */
    @PostMapping("/accounts")
    public String addNewAccount(
            @RequestBody Account account
    ) {
        try {
            service.postAccount(account);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new Gson().toJson(account);
    }

    @PatchMapping("/accounts")
    public String updateAccounts(
            @RequestBody List<Account> accounts
    ) {
        for (Account account:accounts) {
            try {
                service.patchAccount(account, true);
            } catch (Exception e) {
                throw new ApiException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return "Update success";
    }

    @GetMapping("/accounts")
    public String getAccounts() {
        StringBuilder output = new StringBuilder("{'accounts': [");
        for (Account account: service.getAccounts()) {
            output.append(new Gson().toJson(account));
            output.append(",");
        }
        output.deleteCharAt(output.length() - 1);
        output.append("]}");
        return output.toString();
    }

    @DeleteMapping("/accounts")
    public String delAccounts() {
        throw new ApiException("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping("/account/{pk}")
    public String getAccount(
            @PathVariable("pk") Integer pk) {
        try {
            return new Gson().toJson(service.getAccount(pk));
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
