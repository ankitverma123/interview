package com.rbs.interview.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.service.IPrimeGenerator;

@Service
@Qualifier(value = CommonConstants.ITERATIVE)
public class IterativePrimesGenerator implements IPrimeGenerator {

	private static final Logger log = LoggerFactory.getLogger(IterativePrimesGenerator.class);

	@Override
	public List<Long> generatePrimeNumbers(Long number) {
		log.debug("{} >> generatePrimeNumbers() for {}", IterativePrimesGenerator.class.getName(), number);
		List<Long> primes = new ArrayList<>();
		for (long current = 2L; current <= number; current++) {
			long sqrRoot = (long) Math.sqrt(current);
			boolean isPrime = true;
			for (long i = 2L; i <= sqrRoot; i++) {
				if (current % i == 0) {
					isPrime = false;
				}
			}
			if (isPrime) {
				primes.add(current);
			}
		}
		return primes;
	}

}
