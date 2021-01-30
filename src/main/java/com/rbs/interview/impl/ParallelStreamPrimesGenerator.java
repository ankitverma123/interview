package com.rbs.interview.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.service.IPrimeGenerator;

@Service
@Qualifier(value = CommonConstants.PARALLEL_STREAM)
public class ParallelStreamPrimesGenerator implements IPrimeGenerator {

	private static final Logger log = LoggerFactory.getLogger(ParallelStreamPrimesGenerator.class);
	
	@Override
	public List<Long> generatePrimeNumbers(Long number) {
		log.debug("{} >> generatePrimeNumbers() for {}", ParallelStreamPrimesGenerator.class.getName(), number);
		return LongStream.rangeClosed(2L, number).parallel().filter(i -> isPrime(i)).boxed()
				.collect(Collectors.toList());
	}

	private boolean isPrime(long x) {
		return LongStream.rangeClosed(2L, (long) (Math.sqrt(x))).parallel().allMatch(n -> x % n != 0);
	}

}
