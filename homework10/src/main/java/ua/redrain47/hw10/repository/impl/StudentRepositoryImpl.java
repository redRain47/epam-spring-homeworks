package ua.redrain47.hw10.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.redrain47.hw10.entity.Student;
import ua.redrain47.hw10.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getById(UUID id) {
        return sessionFactory.getCurrentSession()
                .get(Student.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        Student student = getById(id);
        sessionFactory.getCurrentSession().delete(student);
    }
}
