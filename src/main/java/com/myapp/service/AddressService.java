package com.myapp.service;


import com.myapp.model.Address;

public interface AddressService {
    public void deleteAddress(Long addrId, Long personId);
    public void saveOrUpdate(Address address);
    public Address getById(Long id);
}
