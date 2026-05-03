package com.turing.javaee8.jpamvc.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.model.BankAccount;
import com.turing.javaee8.jpamvc.repository.BankAccountRepository;
import com.turing.javaee8.jpamvc.service.MoneyTransferService;
import com.turing.javaee8.jpamvc.service.exception.FinancialException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoneyTransferServiceImpl implements MoneyTransferService{

	@Autowired
	BankAccountRepository bankAccountDao;
	
	@Transactional(rollbackOn = { FinancialException.class })
	@Override
	public void transfer(Long fromAccount, Long toAccount, Integer amount) throws Exception {
	
		this.debit(fromAccount, amount);//-
		this.credit(toAccount, amount);//+
	}
	void debit(Long fromAccount,Integer amount) throws Exception,FinancialException
	{
		Optional<BankAccount> result = this.bankAccountDao.getAccountById(fromAccount);
		if(result.isPresent())
		{
			BankAccount account = result.get();
			if(account.getBalance()>= amount)
			{
				log.info("Getbalance in debit ==>"+ account.getBalance());
				account.setBalance( account.getBalance()-amount);
				
				this.bankAccountDao.save(account);
			}
			else
			{
				throw new FinancialException("Invalid debit amount");
			}
			try
			{
				//Thread.sleep(500);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			throw new FinancialException("Invalid account");
		}
	}
	void credit(Long fromAccount,Integer amount) throws Exception,FinancialException
	{
		Optional<BankAccount> result = this.bankAccountDao.getAccountById(fromAccount);
		log.info("Account Name "+result.get().getAccountName());
		if(result.isPresent())
		{
			BankAccount account = result.get();
			
			if(amount >0)
			{
				account.setBalance( account.getBalance()+amount);
				this.bankAccountDao.save(account);
			}
			else
			{
				throw new FinancialException("Invalid credit amount");
			}
		}
		else
		{
			throw new FinancialException("Invalid account");
		}
	}
}
