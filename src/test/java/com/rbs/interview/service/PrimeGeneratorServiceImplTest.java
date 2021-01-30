package com.rbs.interview.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.exceptions.UnSupportedStrategyAlgorithm;
import com.rbs.interview.impl.PrimeGeneratorService;

@SpringBootTest
class PrimeGeneratorServiceImplTest {

	@Autowired
	private PrimeGeneratorService primeGeneratorService;

	@Test
	void generatePrimeNumbersStreamTest() {
		List<Long> primeNumbers = primeGeneratorService.generatePrimeNumbers(CommonConstants.STREAM, 10L);
		Assertions.assertEquals(4, primeNumbers.size());
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L)), primeNumbers);
	}

	@Test
	void generatePrimeNumbersParallelStream() {
		List<Long> primeNumbers = primeGeneratorService.generatePrimeNumbers(CommonConstants.PARALLEL_STREAM, 28L);
		Assertions.assertEquals(9, primeNumbers.size());
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L)), primeNumbers);
	}

	@Test
	void generatePrimeNumbersIterativeTest() {
		List<Long> primeNumbers = primeGeneratorService.generatePrimeNumbers(CommonConstants.ITERATIVE, 10L);
		Assertions.assertEquals(4, primeNumbers.size());
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L)), primeNumbers);
	}

	@Test
	void generatePrimeNumbersForkJoinTest() {
		List<Long> primeNumbers = primeGeneratorService.generatePrimeNumbers(CommonConstants.CONCURRENT, 28L);
		Assertions.assertEquals(9, primeNumbers.size());
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L)), primeNumbers);
	}

	@Test
	void generatePrimeNumbersSeiveTest() {
		List<Long> primeNumbers = primeGeneratorService.generatePrimeNumbers(CommonConstants.ERATOSTHENES_SIEVE, 77L);
		Assertions.assertEquals(21, primeNumbers.size());
		Assertions.assertEquals(new ArrayList<>(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L,
				41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L, 73L)), primeNumbers);
	}

	@Test
	void UnSupportedStrategyExpectedExceptionTest() {
		Assertions.assertThrows(UnSupportedStrategyAlgorithm.class, () -> {
			primeGeneratorService.generatePrimeNumbers("CUSTOM", 77L);
		});
	}
}
