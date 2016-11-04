package com.capgemini.view;

import java.util.List;

import com.capgemini.exception.DuplicateAccountNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountView {
	
	public static void main(String[] args) throws InsufficientInitialBalanceException, InvalidAccountNumberException, InsufficientBalanceException, DuplicateAccountNumberException {
		AccountService acctService = new AccountServiceImpl();
		Customer customer = new Customer();
		customer.setFirstName("Mahima");
		customer.setLastName("Verma");
		customer.setCity("Pune");
		Customer customer1 = new Customer();
		customer1.setFirstName("Varsha");
		customer1.setLastName("Gujar");
		customer1.setCity("Pune");
		Account custAccount = acctService.createAccount(1001, 5000, customer);
		Account custAccount5 = acctService.createAccount(1002, 2000, customer1);
		System.out.println("Account detail of first customer :");
		System.out.println("Account Number :" +custAccount.getAccountNumber());
		System.out.println("Amount :" +custAccount.getAmount());
		System.out.println("City :" +custAccount.getCustomer().getCity());
		System.out.println("First Name :" +custAccount.getCustomer().getFirstName());
		System.out.println("Last Name :" +custAccount.getCustomer().getLastName());
		System.out.println("City :" +custAccount.getCustomer().getCity());
		
		System.out.println("Account detail of second customer :");
		System.out.println("Account Number :" +custAccount5.getAccountNumber());
		System.out.println("Amount :" +custAccount5.getAmount());
		System.out.println("City :" +custAccount5.getCustomer().getCity());
		System.out.println("First Name :" +custAccount5.getCustomer().getFirstName());
		System.out.println("Last Name :" +custAccount5.getCustomer().getLastName());
		System.out.println("City :" +custAccount5.getCustomer().getCity());
		
		Account custAccount1 =  acctService.depositAmount(1001, 10000);
		System.out.println("Amount after deposit :" +custAccount1.getAmount());
		
		Account custAccount2 =  acctService.withdrawAmount(1001, 2000);
		System.out.println("Amount after widthdrawl :" +custAccount2.getAmount());
		
		List<Account> accounts = acctService.fundTransfer(1001, 1002, 2000);
		for(Account account : accounts) {
			System.out.println("Amount in each account :" +account.getAmount());
		}
		
		int balance = acctService.showBalance(1001);
		System.out.println("Final balance in account of 1st customer :" +balance);
	}

}
