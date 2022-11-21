package com.rioc.cours_j2ee.repositories;

import com.rioc.cours_j2ee.models.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByFirstNameAndLastName(@Param(value = "firstName") String firstName,
                                             @Param(value = "lastName") String lastName);

    List<Account> findByFirstName(@Param(value = "firstName") String firstName);

    List<Account> findByLastName(@Param(value = "lastName") String lastName);
}
