package com.turing.javaee8.jpamvc.service;

public interface MoneyTransferService {
	void transfer(Long fromAcc,Long toAccount,Integer amount)throws Exception;
}
