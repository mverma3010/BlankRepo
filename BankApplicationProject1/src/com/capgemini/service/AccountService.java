package com.capgemini.service;

import com.capgemini.exception.DuplicateAccountNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import java.util.*;

public interface AccountService {
	
	Account createAccount(int accountNum, int amount, Customer customer) throws InsufficientInitialBalanceException,DuplicateAccountNumberException,InvalidAccountNumberException;
	
	Account depositAmount(int accountNum, int amount) throws InvalidAccountNumberException;
	
	Account withdrawAmount(int accountNum, int amount) throws InvalidAccountNumberException, InsufficientBalanceException;
	
	List<Account> fundTransfer(int fromAccount, int toAccount, int amount) throws InvalidAccountNumberException,InsufficientBalanceException;
	
	int showBalance(int accountNum) throws InvalidAccountNumberException;
}
