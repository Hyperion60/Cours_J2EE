package com.rioc.cours_j2ee.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", unique = true, nullable = false)
    private int addressId;

    @Column(name = "ADDRESS_CITY")
    private String addressCity;

    @Column(name = "ADDRESS_CP")
    private int addressCp;

    @Column(name = "ADDRESS_ADDRESS")
    private String addressAddress;

    @OneToOne(mappedBy = "address")
    private Account account;

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public int getAddressCp() {
        return addressCp;
    }

    public void setAddressCp(int addressCp) {
        this.addressCp = addressCp;
    }

    public String getAddressAddress() {
        return addressAddress;
    }

    public void setAddressAddress(String addressAddress) {
        this.addressAddress = addressAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
