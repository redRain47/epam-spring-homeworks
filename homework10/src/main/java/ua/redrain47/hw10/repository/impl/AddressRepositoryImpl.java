package ua.redrain47.hw10.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.entity.Address;
import ua.redrain47.hw10.repository.AddressRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AddressRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Address address) {
        sessionFactory.getCurrentSession().save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public Address getById(UUID id) {
        return sessionFactory.getCurrentSession()
                .get(Address.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        Address address = getById(id);
        sessionFactory.getCurrentSession().delete(address);
    }
}
