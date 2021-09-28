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
import com.dxc.iba.exception.CustomerException;
import com.dxc.iba.repo.CustomerRepository;
import com.dxc.iba.service.CustomerService;
import com.dxc.iba.service.CustomerServiceImpl;
import com.dxc.inventoryapi.entity.Item;
import com.dxc.inventoryapi.exception.ItemException;
import com.dxc.inventoryapi.repository.ItemRepository;
import com.dxc.inventoryapi.service.ItemService;
import com.dxc.inventoryapi.service.ItemServiceImpl;

@SpringJUnitConfig
public class CustomerServiceImplUnitTest {
	
	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration {
		
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	private Customer[] testData;
	
	@BeforeEach
	public void fillTestData() {
		testData = new Customer[] { new Customer(80403719,1001, "Prashant","Gaurav","pgaurav@gmail.com","abcde",LocalDate.now(),"8859487812", "Bangalore", "Avm9980", "Male", "SAVINGS"),
				new Customer(63733719,1004, "Zuri","Rai","zrai@gmail.com","uyjde",LocalDate.now(),"7383787812", "Manipur", "BFE9980", "Female", "CURRENT")};
		}
	@AfterEach
	public void clearDatabase() {
		testData = null;
	}
	@Test
	public void getAllCustomerWhenDataExists() {
		List<Customer> expected =Arrays.asList(testData);
		
		Mockito.when(customerRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,customerService.getAllCustomers());
	}
	
	@Test
	public void getAllCustomerWhenNoDataExists() {
		List<Customer> expected =new ArrayList<>();
		
		Mockito.when(customerRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,customerService.getAllCustomers());
	}

	@Test
	public void updateExistingCustomerTest() {

		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(null);

		Mockito.when(customerRepository.existsById(testData[0].getCustomerId())).thenReturn(true);

		try {
			Customer actual = customerService.update(testData[0]);
			Assertions.assertEquals(testData[0], actual);
		} catch (CustomerException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void updateNonExistingCustomerTest() {
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(null);

		Mockito.when(customerRepository.existsById(testData[0].getCustomerId())).thenReturn(false);

		Assertions.assertThrows(CustomerException.class, () -> {
			customerService.update(testData[0]);
		});

	}

	}

	


