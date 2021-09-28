package com.dxc.iba;

import java.time.LocalDate;
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

import com.dxc.iba.entity.Customer;
import com.dxc.iba.entity.Transaction;
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.exception.TransactionException;
import com.dxc.iba.repo.TransactionRepository;
import com.dxc.iba.service.TransactionService;
import com.dxc.iba.service.TransactionServiceImpl;

@SpringJUnitConfig
public class TransactionServiceImplUnitTest {
	
	@TestConfiguration
	static class TransactionServiceImplTestContextConfiguration {
		
		@Bean
		public TransactionService transactionService() {
			return new TransactionServiceImpl();
		}
	}
	@MockBean
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionService transactionService;
	
private Transaction[] testData;
	
	@BeforeEach
	public void fillTestData() {
		testData = new Transaction[] { new Transaction(1002,"savings",112298392,"NEFT",LocalDate.now(),7000.0),
				new Transaction(1007,"savings",909898392,"RTGS",LocalDate.now(),8800.0)};
				
		}
	
	@AfterEach
	public void clearDatabase() {
		testData = null;
	}
	@Test
	public void getAllTransactionWhenDataExists() {
		List<Transaction> expected =Arrays.asList(testData);
		
		Mockito.when(transactionRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,transactionService.getAllTransactions());
	}
	
	@Test
	public void getAllTransactionWhenNoDataExists() {
		List<Transaction> expected =new ArrayList<>();
		
		Mockito.when(transactionRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,transactionService.getAllTransactions());
	}

	@Test
	public void updateExistingTransactionTest() {

		Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(null);

		Mockito.when(transactionRepository.existsById(testData[0].getTransactionId())).thenReturn(true);

		try {
			Transaction actual = transactionService.update(testData[0]);
			Assertions.assertEquals(testData[0], actual);
		} catch (TransactionException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void updateNonExistingTransactionTest() {
		Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(null);

		Mockito.when(transactionRepository.existsById(testData[0].getTransactionId())).thenReturn(false);

		Assertions.assertThrows(TransactionException.class, () -> {
			transactionService.update(testData[0]);
		});

	}
	
	}


