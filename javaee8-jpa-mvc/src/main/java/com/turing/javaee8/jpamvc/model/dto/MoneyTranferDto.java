package com.turing.javaee8.jpamvc.model.dto;

import lombok.Data;

@Data
public class MoneyTranferDto {
	Long fromAccount;
	Long toAccount;
	Integer amount;
}
