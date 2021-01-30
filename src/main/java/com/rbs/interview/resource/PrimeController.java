package com.rbs.interview.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.interview.dto.ResponseStructure;
import com.rbs.interview.exceptions.MinimumSupportedValueException;
import com.rbs.interview.impl.PrimeGeneratorService;

@RestController
@RequestMapping("/primes")
public class PrimeController {

	@Autowired
	private PrimeGeneratorService primeService;

	@GetMapping(value = "/{number}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_XML_VALUE })
	public ResponseEntity<ResponseStructure> getPrimes(@PathVariable Long number,
			@RequestParam(name = "algorithm", defaultValue = "STREAM", required = false) String algorithm) {
		if (number < 2)
			throw new MinimumSupportedValueException("Please specify at lease 2 or greater than 2.");

		ResponseStructure response = new ResponseStructure(number,
				primeService.generatePrimeNumbers(algorithm, number));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
