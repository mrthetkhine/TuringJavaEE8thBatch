package com.turing.javaee8.jpamvc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.BankAccount;
import com.turing.javaee8.jpamvc.model.Movie;

import jakarta.persistence.LockModeType;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long>{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select ba from BankAccount ba where ba.id = ?1")
	Optional<BankAccount> getAccountById(Long id);
}
