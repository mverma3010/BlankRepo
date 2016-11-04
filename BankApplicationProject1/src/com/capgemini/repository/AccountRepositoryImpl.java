package com.capgemini.repository;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public class AccountRepositoryImpl implements AccountRepository{

	List<Account> accounts = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see com.capgemini.repository.AccountRepository#save(com.capgemini.model.Account)
	 */
	@Override
	public boolean save(Account account) {
		if(account != null) {
			accounts.add(account);
		} else {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.capgemini.repository.AccountRepository#searchAccount(int)
	 */
	@Override
	public Account searchAccount(int accountNum) {
//		Customer customer = new Customer();
//		customer.setFirstName("Mahima");
//		customer.setLastName("Verma");
//		customer.setCity("Pune");
//		Account acct = new Account();
//		acct.setAccountNumber(1001);
//		acct.setAmount(5000);
//		acct.setCustomer(customer);	
//		
//		Customer customer1 = new Customer();
//		customer1.setFirstName("Mahima");
//		customer1.setLastName("Verma");
//		customer1.setCity("Pune");
//		Account acct1 = new Account();
//		acct1.setAccountNumber(1001);
//		acct1.setAmount(5000);
//		acct1.setCustomer(customer1);	
		
//		accounts.add(acct);
		if(accounts !=null){
			for(Account account : accounts) {
				if(account.getAccountNumber() == accountNum) {
					return account;
				} 
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.capgemini.repository.AccountRepository#updateAccount(com.capgemini.model.Account)
	 */
	@Override
	public boolean updateAccount(Account account) {
		Account account1 = searchAccount(account.getAccountNumber());
		if(account1 != null){
			account1.setAmount(account.getAmount());
			account1.setCustomer(account.getCustomer());
		} else {
			return false;
		}
		return true;
	}

}
