package com.equadis;

import com.equadis.bank.domain.Customer;
import com.equadis.bank.domain.TransactionType;
import com.equadis.bank.domain.dto.BankAccountDto;
import com.equadis.bank.domain.dto.TransactionDto;
import com.equadis.bank.service.BankAccountService;
import com.equadis.bank.service.CustomerService;
import com.equadis.bank.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration( classes = {MiniBankApplication.class})
class MiniBankApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private BankAccountService bankAccountService;

	@MockBean @Qualifier("transactionServiceImpl")
	private TransactionService transactionService;

	@Test
	@DisplayName("Validating new Customer Bank Account Creation")
	public void shouldCreateNewCustomer() throws Exception {
		Customer newCustomerDto = this.createCustomerDto();
		BankAccountDto newBankDto = this.createNewBankAccountDto();

		when(this.customerService.getCustomerByID(newCustomerDto.getCustID())).thenReturn(newCustomerDto);

		when(this.bankAccountService.createNewBankAccount(newBankDto.getBalance(), newBankDto.getAccountNo(), newCustomerDto)).thenReturn(newBankDto);

		this.mockMvc.perform(post("/equadis-bank/create-account/1234").
				accept(MediaType.APPLICATION_JSON).
				contextPath("/equadis-bank").
						content(newBankDto.toString()).
						contentType(MediaType.APPLICATION_JSON)).
				andExpect(status().isCreated()).
				andReturn();
	}



	@Test
	public void shouldCreateNewBankAccount(){

	}

	@Test
	public void shouldCreateNewTransaction(){

	}

	@Test
	public void shouldDepositInAccount(){

	}

	@Test
	public void shouldNotDepositInAccountSpec(){

	}

	@Test
	public void shouldCreditAccountSpec(){

	}

	@Test
	public void shouldNotCreditAccountSpec(){

	}

	@Test
	@DisplayName("Validating Transactions View history by transactions type")
	public void shouldGetTransactionsByTypeSpec() throws Exception {

		when(this.transactionService.getTransactionsByType(1234, TransactionType.CREDIT)).thenReturn(createTransactionDto());

		String transReport = "{\"transId\":123, \"transType\":\"CREDIT\"}";

		this.mockMvc.perform(get("/equadis-bank/transactions-type/1234").
						contextPath("/equadis-bank").
						contentType(MediaType.APPLICATION_JSON).
						accept(MediaType.APPLICATION_JSON).
						content(transReport)).
				andExpect(status().isOk()).
				andReturn();

	}

	private List<TransactionDto> createTransactionDto() {
		List<TransactionDto> transactionDtos = new ArrayList<>();
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAccountNo(1234);
		transactionDto.setTransactionTime(LocalDateTime.now());
		transactionDto.setTransactionAmount(1000d);
		transactionDto.setTransId(1234);

		transactionDtos.add(transactionDto);
		return transactionDtos;
	}

	private Customer createCustomerDto() {
        return new Customer(1234,"Habeeb Animashaun");
	}

	private BankAccountDto createNewBankAccountDto(){
        return new BankAccountDto(1234, 40d);
	}

}
