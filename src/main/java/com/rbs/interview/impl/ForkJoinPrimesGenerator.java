package com.rbs.interview.impl;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rbs.interview.dto.CommonConstants;
import com.rbs.interview.service.IPrimeGenerator;

@Service
@Qualifier(value = CommonConstants.CONCURRENT)
public class ForkJoinPrimesGenerator extends RecursiveTask<List<Long>> implements IPrimeGenerator {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3490004954587850841L;

	
	private static final Logger log = LoggerFactory.getLogger(ForkJoinPrimesGenerator.class);

	public static final long THRESHOLD = 5000;
	private Long upperLimit = null;
	private Long lowerLimit = 2L;

	@Override
	public List<Long> generatePrimeNumbers(Long number) {
		log.debug("{} >> generatePrimeNumbers() for {}", ForkJoinPrimesGenerator.class.getName(), number);
		upperLimit = number;
		return compute();
	}
	
	private ForkJoinPrimesGenerator() {
		upperLimit=2L;
	}

	private ForkJoinPrimesGenerator(Long lowerLimit, Long upperLimit) {
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}

	@Override
	protected List<Long> compute() {
		Long length = upperLimit - lowerLimit;
		if (length <= THRESHOLD) {
			return computeSequentially();
		}
		ForkJoinPrimesGenerator leftTask = new ForkJoinPrimesGenerator(lowerLimit, lowerLimit + length / 2 - 1L);
		leftTask.fork();
		ForkJoinPrimesGenerator rightTask = new ForkJoinPrimesGenerator(lowerLimit + length / 2, upperLimit);
		List<Long> rightResult = rightTask.compute();
		List<Long> leftResult = leftTask.join();

		log.debug("Lower {}, Upper {}", lowerLimit, upperLimit);
        log.debug("Left {}", leftResult);
        log.debug("Right {}", rightResult);
		
		rightResult.addAll(leftResult);
		return rightResult;
	}

	private List<Long> computeSequentially() {
		log.debug("Computing Sequentially Lower {}, Upper {}", lowerLimit, upperLimit);
		List<Long> primes = LongStream.rangeClosed(lowerLimit, upperLimit)
				.filter(i -> LongStream.rangeClosed(2L, (long) (Math.sqrt(i))).allMatch(n -> i % n != 0)).boxed()
				.collect(Collectors.toList());
		log.debug("Computed {}", primes);
		return primes;
	}

}
