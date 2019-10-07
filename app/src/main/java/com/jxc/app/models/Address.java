package com.jxc.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String postalCode;
    private String street;
    private Integer streetNumber;
    private String complement;

    public Address(String postalCode, String street, Integer streetNumber, String complement) {
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.complement = complement;
    }

    public long getId () {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Address{" +
                "postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber=" + streetNumber +
                ", complement='" + complement + '\'' +
                '}';
    }
}
