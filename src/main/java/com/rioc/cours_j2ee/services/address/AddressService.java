package com.rioc.cours_j2ee.services.address;

import com.rioc.cours_j2ee.models.dao.Address;
import com.rioc.cours_j2ee.repositories.IAddressRepository;

public class AddressService {
    private IAddressRepository repository;

    public boolean checkAddress(String address, String city, Integer cp) throws Exception {
        if (address.length() == 0 || city.length() == 0 || 0 > cp || cp > 99999) {
            throw new Exception("Invalid parameter");
        }
        // Check address
        // TODO

        // Check city
        // TODO

        // Check CP + City
        // TODO

        return true;
    }

}
