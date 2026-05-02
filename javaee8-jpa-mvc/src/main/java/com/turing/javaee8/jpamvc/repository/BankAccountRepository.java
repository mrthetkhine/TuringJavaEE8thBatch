package com.turing.javaee8.jpamvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.BankAccount;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,Long>{

}
