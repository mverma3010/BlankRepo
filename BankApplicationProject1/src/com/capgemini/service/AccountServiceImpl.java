package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.exception.DuplicateAccountNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.repository.AccountRepository;
import com.capgemini.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService{

	AccountRepository acctRepository = new AccountRepositoryImpl();
	@Override
	public Account createAccount(int accountNum, int amount, Customer customer)
			throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException {
		Account account = new Account();
		account.setAccountNumber(accountNum);
		account.setAmount(amount);
		account.setCustomer(customer);
		if(!checkDuplicateAccount(accountNum) && !checkValidAccount(accountNum) && amount > 500){
			acctRepository.save(account);
		} else {
			throw new InsufficientInitialBalanceException();
		}
		return account;
	}

	private boolean checkDuplicateAccount(int accountNum) throws DuplicateAccountNumberException {
		Account acct = acctRepository.searchAccount(accountNum);
		if(acct != null && acct.getAccountNumber() == accountNum){
			System.out.println(acct.getAccountNumber() == accountNum);
			throw new DuplicateAccountNumberException();
		}
		return false;
	}

	private boolean checkValidAccount(int accountNum) throws InvalidAccountNumberException {
		if(accountNum <= 0){
			throw new InvalidAccountNumberException();
		}
		return false;
	}

	@Override
	public Account depositAmount(int accountNum, int amount) throws InvalidAccountNumberException {
		Account account = new Account();
		account.setAccountNumber(accountNum);
		account.setAmount(amount);
		Account acct1 = acctRepository.searchAccount(accountNum);
		if(acct1.getAccountNumber() == accountNum) {
			amount = acct1.getAmount() + amount;
			account.setAmount(amount);
			acctRepository.updateAccount(account);
		}else {
			throw new InvalidAccountNumberException();
		}
		return account;
	}

	@Override
	public Account withdrawAmount(int accountNum, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		Account account = new Account();
		account.setAccountNumber(accountNum);
		account.setAmount(amount);
		Account acct1 = acctRepository.searchAccount(accountNum);
		if(acct1.getAccountNumber() == accountNum) {
			if(acct1.getAmount() >= amount) {
				amount = acct1.getAmount() - amount;
				account.setAmount(amount);
				acctRepository.updateAccount(account);
			} else { 
				throw new InsufficientBalanceException();
			}
		}else {
			throw new InvalidAccountNumberException();
		}
		
		return account;
	}

	@Override
	public List<Account> fundTransfer(int fromAccount, int toAccount, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		List<Account> accounts = new ArrayList<>();
		Account acct1 = acctRepository.searchAccount(fromAccount);
		Account acct2 = acctRepository.searchAccount(toAccount);
		if(acct1.getAccountNumber() == fromAccount && acct2.getAccountNumber() == toAccount){
			if(acct1.getAmount() >= amount) {
				int amount1 = acct2.getAmount() + amount;
				acct2.setAmount(amount1);
				acctRepository.updateAccount(acct2);
				accounts.add(acct1);
				accounts.add(acct2);
			} else {
				throw new InsufficientBalanceException();
			}
		} else {
			throw new InvalidAccountNumberException();
		}
		return accounts;
	}

	@Override
	public int showBalance(int accountNum) throws InvalidAccountNumberException {
		Account account = acctRepository.searchAccount(accountNum);
		return account.getAmount();
	}

}
