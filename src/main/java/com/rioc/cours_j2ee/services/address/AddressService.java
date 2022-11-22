package com.rioc.cours_j2ee.services.address;

import com.rioc.cours_j2ee.repositories.IAddressRepository;

public class AddressService {
    private IAddressRepository repository;

    public AddressService(IAddressRepository repository) {
        super();
        this.repository = repository;
    }

}
