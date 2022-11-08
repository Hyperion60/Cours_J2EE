package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.models.dao.Account;

public interface IAccountService {
    public Account postAccount(Account account) throws Exception;
}
