package com.equadis;

import com.equadis.bank.service.BankAccountService;
import com.equadis.bank.service.CustomerService;
import com.equadis.bank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ContextConfiguration( classes = {MiniBankApplication.class})
class MiniBankApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private BankAccountService bankAccountService;

	@MockBean
	private TransactionService transactionService;

	@Test
	public void shouldCreateNewCustomer()
	{

	}

	@Test
	public void shouldCreateNewBankAccount(){

	}

	@Test
	public void shouldCreateNewTransaction(){

	}

}
