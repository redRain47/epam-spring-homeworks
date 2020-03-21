package com.epam.hw5.repository;

import com.epam.hw5.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Override
    List<Account> findAll();

    @Override
    Optional<Account> findById(Long id);
}
