package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.models.dao.Account;

import java.util.List;

public interface IAccountService {
    Account postAccount(Account account) throws Exception;
    Account patchAccount(Account account) throws Exception;
    Account patchAccount(Account account, boolean creation) throws Exception;
    List<Account> getAccounts();

    Account getAccount(Integer pk) throws Exception;
}
