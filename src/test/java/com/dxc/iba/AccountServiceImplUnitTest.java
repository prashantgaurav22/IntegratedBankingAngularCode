package com.dxc.iba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.dxc.iba.entity.Account;
import com.dxc.iba.exception.AccountException;
import com.dxc.iba.repo.AccountRepository;
import com.dxc.iba.service.AccountService;
import com.dxc.iba.service.AccountServiceImpl;

@SpringJUnitConfig
public class AccountServiceImplUnitTest {
	
	@TestConfiguration
	static class AccountServiceImplTestContextConfiguration {
		
		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}
	
	@MockBean
	private  AccountRepository  accountRepository;
	
	@Autowired
	private AccountService  accountService;
	
	private Account[] testData;
	
	@BeforeEach
	public void fillTestData() {
		testData = new Account[] { new Account(233123,"SAVINGS","ghfghf ",5600.0,"SBIN0068"),
				new Account(233123,"SAVINGS","ghfghf ",5600.0,"SBIN0068")};
		}
	@AfterEach
	public void clearDatabase() {
		testData = null;
	}
	@Test
	public void getAllAccountWhenDataExists() {
		List<Account> expected =Arrays.asList(testData);
		
		Mockito.when(accountRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,accountService.getAllAccounts());
	}
	
	@Test
	public void getAllAccountWhenNoDataExists() {
		List<Account> expected =new ArrayList<>();
		
		Mockito.when(accountRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,accountService.getAllAccounts());
	}

	@Test
	public void updateExistingAccountTest() {

		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(null);

		Mockito.when(accountRepository.existsById(testData[0].getAccountNumber())).thenReturn(true);

		try {
			Account actual = accountService.update(testData[0]);
			Assertions.assertEquals(testData[0], actual);
		} catch (AccountException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void updateNonExistingAccountTest() {
		Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(null);

		Mockito.when(accountRepository.existsById(testData[0].getAccountNumber())).thenReturn(false);

		Assertions.assertThrows(AccountException.class, () -> {
			accountService.update(testData[0]);
		});

	}

	}
