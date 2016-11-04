package com.capgemini.repository;

import com.capgemini.model.Account;

/**
 * @author mahiverm
 *
 */
public interface AccountRepository {

	/**
	 * @param account
	 * @return
	 */
	boolean save(Account account);
	
	/**
	 * @param accountNum
	 * @return
	 */
	Account searchAccount(int accountNum);
	
	/**
	 * @param account
	 * @return
	 */
	boolean updateAccount(Account account);
}
