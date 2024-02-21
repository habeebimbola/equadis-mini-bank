package com.equadis;

import com.equadis.bank.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class MiniBankApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

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
