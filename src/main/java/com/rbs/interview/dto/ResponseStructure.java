package com.rbs.interview.dto;

import java.util.List;

public class ResponseStructure {
	private Long initial;
	private List<Long> primes;
	
	public ResponseStructure(Long initial, List<Long> primes) {
		this.initial = initial;
		this.primes = primes;
	}

	public Long getInitial() {
		return initial;
	}

	public void setInitial(Long initial) {
		this.initial = initial;
	}

	public List<Long> getPrimes() {
		return primes;
	}

	public void setPrimes(List<Long> primes) {
		this.primes = primes;
	}
	
	
}
