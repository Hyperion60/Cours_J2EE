package com.rioc.cours_j2ee.controllers;


import com.google.gson.Gson;
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
}
