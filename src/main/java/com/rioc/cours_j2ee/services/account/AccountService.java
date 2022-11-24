package com.rioc.cours_j2ee.services.account;

import com.rioc.cours_j2ee.exceptions.ApiException;
import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.repositories.IAccountRepository;
import com.rioc.cours_j2ee.services.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService implements IAccountService {
    private IAccountRepository repository;
    private AddressService addressService;

    public AccountService(IAccountRepository repository) {
        super();
        this.repository = repository;
        this.addressService = new AddressService();

    }

    public Account postAccounts(Account account) throws Exception {
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
        // Check Address
        try {
            addressService.checkAddress(account.getAddress().getAddressAddress(),
                    account.getAddress().getAddressCity(),
                    account.getAddress().getAddressCp());
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return repository.save(account);
    }

    public Account patchAccounts(Account account) throws Exception {
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
    public Account patchAccounts(Account account, boolean creation) throws Exception {
        if (!creation) {
            return patchAccounts(account);
        }
        Account ret = patchAccounts(account);
        if (ret == null) {
            return postAccounts(account);
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

    public Account patchAccount(Account data, Integer pk) throws Exception {
        Optional<Account> account_obj = repository.findById(pk);
        if (account_obj.isPresent()) {
            // Test s'il y a des modifications
            if (data.getLastName().equals(account_obj.get().getLastName()) &&
                data.getFirstName().equals(account_obj.get().getFirstName())
            ) {
                return account_obj.get();
            }

            Account account = account_obj.get();
            // Copy des nouvelles données sans sauvegarder
            if (data.getFirstName().length() != 0) {
                account.setFirstName(data.getFirstName());
            }
            if (data.getLastName().length() != 0) {
                account.setLastName(data.getLastName());
            }

            // Vérification s'il n'y a pas de compte déjà existant
            if (!repository.findByFirstNameAndLastName(
                    account.getFirstName(),
                    account.getLastName()
            ).isEmpty()) {
                throw new Exception("User already exists");
            }
            return repository.save(account);
        }
        throw new Exception("User not found");
    }

    public boolean deleteAccount(Integer pk) throws Exception {
        Optional<Account> account = repository.findById(pk);
        if (account.isPresent()) {
            repository.delete(account.get());
            return true;
        }
        throw new Exception("User not found");
    }

    public List<Account> getVarAccount(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return repository.findByFirstNameAndLastName(firstName, lastName);
        }
        if (firstName != null) {
            return repository.findByFirstName(firstName);
        }
        return repository.findByLastName(lastName);
    }
}
