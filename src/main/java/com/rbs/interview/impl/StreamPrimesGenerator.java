package com.rbs.interview.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.service.IPrimeGenerator;

@Service
@Qualifier(value = CommonConstants.STREAM)
public class StreamPrimesGenerator implements IPrimeGenerator {

	private static final Logger log = LoggerFactory.getLogger(StreamPrimesGenerator.class);
	
	@Override
	public List<Long> generatePrimeNumbers(Long number) {
		log.debug("{} >> generatePrimeNumbers() for {}", StreamPrimesGenerator.class.getName(), number);
		return LongStream.rangeClosed(2L, number).filter(x -> isPrime(x)).boxed().collect(Collectors.toList());
	}
	
	private boolean isPrime(long number) {
		return IntStream.rangeClosed(2, (int) (Math.sqrt(number))).allMatch(n -> number % n != 0);
	}

}
