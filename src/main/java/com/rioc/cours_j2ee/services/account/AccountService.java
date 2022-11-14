package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.repositories.IAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements IAccountService {
    private IAccountRepository repository;

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

    public Account patchAccount(Account account) throws Exception {
        if (account.getAccountId() == 0) {
            throw new Exception("Invalid ID");
        }
        Optional<Account> opt_account = repository.findById(account.getAccountId());
        if (opt_account.isPresent()) {
            Account db_account = opt_account.get();
            db_account.setFirstName(account.getFirstName());
            db_account.setLastName(account.getLastName());
            repository.save(db_account);
            return db_account;
        }
        return null;
    }

    /**
     * @param account Compte utilisateur à comparer
     * @param creation Effectuer la création si introuvable
     * @return Retourne le compte modifié ou ajouté, retourne null sinon
     * @throws Exception Exception concernant un ID invalide
     */
    public Account patchAccount(Account account, boolean creation) throws Exception {
        if (!creation) {
            return patchAccount(account);
        }
        Account ret = patchAccount(account);
        if (ret == null) {
            return postAccount(account);
        }
        return ret;
    }

    public List<Account> getAccounts() {
        return repository.findAll();
    }

    public Account getAccount(Integer pk) throws Exception {
        Optional<Account> account = repository.findById(pk);
        if (account.isPresent()) {
            return account.get();
        }
        throw new Exception("Invalid ID");
    }
}
