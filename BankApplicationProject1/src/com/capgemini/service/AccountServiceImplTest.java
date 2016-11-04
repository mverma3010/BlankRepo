package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exception.DuplicateAccountNumberException;
import com.capgemini.exception.InsufficientInitialBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.model.Customer;

public class AccountServiceImplTest {
	AccountService acctService;
	Customer customer;
	Account acct;
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer.setFirstName("Mahima");
		customer.setLastName("Verma");
		customer.setCity("Pune");
		acct = new Account();
		acct.setAccountNumber(1001);
		acct.setAmount(5000);
		acct.setCustomer(customer);		
		acctService=new AccountServiceImpl();
	}

	@Test
	public void testCreateAccountIfValidInfoIsPassed() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException {
		assertEquals(acct, acctService.createAccount(1001, 5000, customer));
	}
	
	@Test(expected = com.capgemini.exception.InsufficientInitialBalanceException.class)
	public void testCreateAccountIfAmountIsLessThan500() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException {
		Customer customer = new Customer();
		customer.setFirstName("Mahima");
		customer.setLastName("Verma");
		customer.setCity("Pune");
		acctService.createAccount(1001, 400, customer);
	}
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void testCreateAccountIfInvalidInfoIsPassed() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException {
		Customer customer = new Customer();
		customer.setFirstName("Mahima");
		customer.setLastName("Verma");
		customer.setCity("Pune");
		acctService.createAccount(-1001, 5000, customer);
	}
	
	@Test(expected = com.capgemini.exception.DuplicateAccountNumberException.class)
	public void testCreateAccountDuplicateAccountNumber() throws InsufficientInitialBalanceException, DuplicateAccountNumberException, InvalidAccountNumberException {
		Customer customer1 = new Customer();
		customer1.setFirstName("Mahima");
		customer1.setLastName("Verma");
		customer1.setCity("Pune");
		acctService.createAccount(1001, 5000, customer1);
	}

//	@Test
//	public void testDepositAmount() throws InvalidAccountNumberException {
//		acctService.depositAmount(1001, 5000);
//	}
//
//	@Test
//	public void testWithdrawAmount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFundTransfer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testShowBalance() {
//		fail("Not yet implemented");
//	}

}
