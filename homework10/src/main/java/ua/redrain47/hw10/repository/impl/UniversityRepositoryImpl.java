package ua.redrain47.hw10.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.entity.University;
import ua.redrain47.hw10.repository.UniversityRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class UniversityRepositoryImpl implements UniversityRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UniversityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(University university) {
        sessionFactory.getCurrentSession().save(university);
    }

    @Override
    @Transactional(readOnly = true)
    public University getById(UUID id) {
        return sessionFactory.getCurrentSession()
                .get(University.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<University> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM University u", University.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        University university = getById(id);
        sessionFactory.getCurrentSession().delete(university);
    }
}
