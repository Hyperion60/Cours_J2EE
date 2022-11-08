package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.repositories.IAccountRepository;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import springfox.documentation.schema.Example;

@Service
public class AccountService implements IAccountService {
    private IAccountRepository repository;
    private Object Example;
    private Object Account;

    public AccountService(IAccountRepository repository) {
        super();
        this.repository = repository;
    }

    public Account postAccount(Account account) throws Exception {
        if (repository.existsById(account.getAccountId())) {
            throw new Exception("Invalid ID");
        }
        if (account.getFirstName().equals("")) {
            throw new Exception("Missing First Name field");
        }
        if (account.getLastName().equals("")) {
            throw new Exception("Missing Last Name field");
        }
        if (!repository.findByFirstNameAndLastName(
                account.getFirstName(),
                account.getLastName()
        ).isEmpty()) {
            throw new Exception("User already exists");
        }
        return repository.save(account);
    }
}
