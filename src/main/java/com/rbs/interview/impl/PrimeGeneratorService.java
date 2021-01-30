package com.rbs.interview.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.exceptions.UnSupportedStrategyAlgorithm;
import com.rbs.interview.service.IPrimeGenerator;

@Service
public class PrimeGeneratorService {

	private static final Logger log = LoggerFactory.getLogger(PrimeGeneratorService.class);
	
	private final Map<String, IPrimeGenerator> strategies;

	public PrimeGeneratorService(List<IPrimeGenerator> currentStrategies) {
		this.strategies = currentStrategies.stream()
				.collect(Collectors.toMap(k -> k.getClass().getDeclaredAnnotation(Qualifier.class).value(),
						java.util.function.Function.identity()));
	}

	@Cacheable(value = "primes", keyGenerator = "customKeyGenerator")
	public List<Long> generatePrimeNumbers(String strategyName, Long number) {
		log.debug("{} >> generatePrimeNumbers() for= {} using algorithm= {}", PrimeGeneratorService.class.getName(), number, strategyName);
		if (!strategies.containsKey(strategyName)) {
			String[] availableStrategies = {CommonConstants.STREAM, CommonConstants.PARALLEL_STREAM, CommonConstants.ITERATIVE, CommonConstants.CONCURRENT, CommonConstants.ERATOSTHENES_SIEVE};
			throw new UnSupportedStrategyAlgorithm(String.format("%s algorithm not found. Supported Algoritms are %s", strategyName, String.join(" or ", availableStrategies)));
		}
		return strategies.get(strategyName).generatePrimeNumbers(number);
	}

}
