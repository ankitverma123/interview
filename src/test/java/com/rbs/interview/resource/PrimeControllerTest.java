package com.rbs.interview.resource;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.exceptions.UnSupportedStrategyAlgorithm;
import com.rbs.interview.impl.PrimeGeneratorService;

@WebMvcTest(PrimeController.class)
@ExtendWith(SpringExtension.class)
class PrimeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PrimeGeneratorService primeService;

	@Test
	void testGetMapping() throws Exception {
		ArrayList<Long> numbers = new ArrayList<>(Arrays.asList(2l, 3l, 5l, 7l));
		when(primeService.generatePrimeNumbers(CommonConstants.STREAM, 10L)).thenReturn(numbers);
		mockMvc.perform(MockMvcRequestBuilders.get("/primes/{number}", 10).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.initial").value(10))
				.andExpect(MockMvcResultMatchers.jsonPath("$.primes").isArray());
		verify(primeService).generatePrimeNumbers("STREAM", 10L);
	}

	@Test
	void testGetMappingWithExpectedUnSupportedAlgorithmException() throws Exception {
		doThrow(UnSupportedStrategyAlgorithm.class).when(primeService).generatePrimeNumbers("CUSTOM", 10L);
		mockMvc.perform(MockMvcRequestBuilders.get("/primes/{number}?algorithm={strategy}", 10, "CUSTOM")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
		verify(primeService).generatePrimeNumbers("CUSTOM", 10L);
	}

}
