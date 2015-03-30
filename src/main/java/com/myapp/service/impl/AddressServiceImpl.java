package com.myapp.service.impl;

import com.myapp.dao.api.IAddressDAO;
import com.myapp.dao.api.IPersonDAO;
import com.myapp.model.Address;
import com.myapp.model.Person;
import com.myapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private IAddressDAO addressDao;
 @Autowired
    private IPersonDAO personDAO;

    @Override
    public void deleteAddress(Long addrId, Long personId) {
        Person person = personDAO.getById(personId);
        Address address = addressDao.getById(addrId);
        person.getAddresses().remove(address);
        personDAO.saveOrUpdate(person);
    }
    @Override
    public void saveOrUpdate(Address address) {
        addressDao.saveOrUpdate(address);
    }

    @Override
    public Address getById(Long id) {
        return addressDao.getById(id);
    }
}
