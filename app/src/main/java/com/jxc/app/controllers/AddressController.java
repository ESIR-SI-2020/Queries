package com.jxc.app.controllers;

import com.jxc.app.models.Address;
import com.jxc.app.services.AddressService;
import com.jxc.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<Address>> getAllAddress(){
        return new ResponseEntity<>(this.addressService.getAllAddress(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable long id){
        return new ResponseEntity<>(this.addressService.getAddressById(id), HttpStatus.OK);
    }
}
