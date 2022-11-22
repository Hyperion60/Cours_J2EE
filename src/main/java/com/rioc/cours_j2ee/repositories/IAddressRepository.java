package com.rioc.cours_j2ee.repositories;

import com.rioc.cours_j2ee.models.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
