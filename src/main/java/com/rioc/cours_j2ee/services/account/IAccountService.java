package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.models.dao.Account;

import java.util.List;

public interface IAccountService {
    Account postAccounts(Account account) throws Exception;
    Account patchAccounts(Account account) throws Exception;
    Account patchAccounts(Account account, boolean creation) throws Exception;
    List<Account> getAccounts();

    Account getAccount(Integer pk) throws Exception;
    Account patchAccount(Account data, Integer pk) throws Exception;
    // boolean deleteAccount(Integer pk) throws Exception;
}
