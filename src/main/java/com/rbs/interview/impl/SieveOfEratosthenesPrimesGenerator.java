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
@Qualifier(value = CommonConstants.ERATOSTHENES_SIEVE)
public class SieveOfEratosthenesPrimesGenerator implements IPrimeGenerator {

	private static final Logger log = LoggerFactory.getLogger(SieveOfEratosthenesPrimesGenerator.class);
	
	@Override
	public List<Long> generatePrimeNumbers(Long number) {
		log.debug("{} >> generatePrimeNumbers() for {}", SieveOfEratosthenesPrimesGenerator.class.getName(), number);
		List<Long> primes = new ArrayList<>();
        boolean[] isComposite = new boolean[number.intValue()+1];
        isComposite[1] = true;

        for (int i = 2; i <= number; i++) {
            if (!isComposite[i]) {
                primes.add(Long.valueOf(i));
                int multiple = 2;
                while (i * multiple <= number) {
                    isComposite[i * multiple] = true;
                    multiple++;
                }
            }
        }
        return primes;
	}

}
