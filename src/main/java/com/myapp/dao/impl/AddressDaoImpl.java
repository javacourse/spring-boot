package com.myapp.dao.impl;

import com.myapp.dao.api.IAddressDAO;
import com.myapp.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("addressDao")
@Transactional(readOnly = true)
public class AddressDaoImpl extends AbstractHibernateDAO<Address, Long>  implements IAddressDAO {
    @Override
    public boolean isExist(Address entity)
    {

//		return !findByCriteria(Restrictions.eq("name", entity.getName())).isEmpty();
        return false;
    }

}
