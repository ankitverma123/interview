package com.rbs.interview.service;

import java.util.List;

public interface IPrimeGenerator {
	List<Long> generatePrimeNumbers(Long number);
	
	enum PrimesStrategy {
        FORK_JOIN, ITERATIVE, PARALLEL_STREAM, STREAM, ERATOSTHENES_SIEVE;
    }
}
