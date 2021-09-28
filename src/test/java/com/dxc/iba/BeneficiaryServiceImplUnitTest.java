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

import com.dxc.iba.entity.Beneficiary;
import com.dxc.iba.exception.BeneficiaryException;
import com.dxc.iba.repo.BeneficiaryRepository;
import com.dxc.iba.service.BeneficiaryService;
import com.dxc.iba.service.BeneficiaryServiceImpl;

@SpringJUnitConfig
public class BeneficiaryServiceImplUnitTest {
	
	@TestConfiguration
	static class BeneficiaryServiceImplTestContextConfiguration {
		
		@Bean
		public BeneficiaryService beneficiaryService() {
			return new BeneficiaryServiceImpl();
		}
	}
	

	@MockBean
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	private Beneficiary[] testData;
	
	@BeforeEach
	public void fillTestData() {
		testData = new Beneficiary[] { new Beneficiary(80403719, "Prashant","Gaurav","8859487812","SBIN9980", "NEFT"),
				new Beneficiary(8685769, "Anjali","Lama","9097867812","SBIN0098", "IMPS")};
		}
	@AfterEach
	public void clearDatabase() {
		testData = null;
	}
	@Test
	public void getAllBeneficiaryWhenDataExists() {
		List<Beneficiary> expected =Arrays.asList(testData);
		
		Mockito.when(beneficiaryRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,beneficiaryService.getAllBeneficiary());
	}
	
	@Test
	public void getAllBeneficiaryWhenNoDataExists() {
		List<Beneficiary> expected =new ArrayList<>();
		
		Mockito.when(beneficiaryRepository.findAll())
		.thenReturn(expected);
		
		Assertions.assertEquals(expected,beneficiaryService.getAllBeneficiary());
	}

	@Test
	public void updateExistingBeneficiaryTest() {

		Mockito.when(beneficiaryRepository.save(Mockito.any(Beneficiary.class))).thenReturn(null);

		Mockito.when(beneficiaryRepository.existsById(testData[0].getAccount_Number())).thenReturn(true);

		try {
			Beneficiary actual = beneficiaryService.update(testData[0]);
			Assertions.assertEquals(testData[0], actual);
		} catch (BeneficiaryException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void updateNonExistingBeneficiaryTest() {
		Mockito.when(beneficiaryRepository.save(Mockito.any(Beneficiary.class))).thenReturn(null);

		Mockito.when(beneficiaryRepository.existsById(testData[0].getAccount_Number())).thenReturn(false);

		Assertions.assertThrows(BeneficiaryException.class, () -> {
			beneficiaryService.update(testData[0]);
		});

	}

	}

