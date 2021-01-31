# rbs-interview!

This prject source code will demonstrate how to generate prime numbers to a specific range using variety of algorithms.

# Rest End Point
http://localhost:8080/primes/20
This follows the default algorithm to generate first 20 prime numbers. Default algorithm is STREAM based. Making above api call will give you this expected response

    {
		"initial": 20,
		"primes": [2, 3, 5, 7, 11, 13, 17, 19]
	}

## Different MIME type response formats supported by the endpoint are 
You can easily switch between these MIME types using a simple header parameter, header name to support such activities is **Accept**
 - application/json
 - application/xml

## Different algorithms supported by the system

 - STREAM
 - PARALLEL_STREAM
 - ITERATIVE
 - CONCURRENT
 - SIEVE_ALGORITHM

> http://localhost:8080/primes/20?algorithm=CONCURRENT
> Using this request parameters we can easily switch between the various implementation provided by the application. Using strategy design patterns system identifies which implementation needs to be invoked. This also leverage the EHCachev3 cache mechanism to store algorithm wise as a cache key.
