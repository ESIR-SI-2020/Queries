package com.jxc.app.services;

import com.jxc.app.models.Address;
import com.jxc.app.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAllAddress(){
        return this.addressRepository.findAll();
    }

    public Address getAddressById(long id){
        Optional<Address> optionalAddress = this.addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }
}
