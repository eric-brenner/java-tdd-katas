package tdd.fizzbuzz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tdd.fizzbuzz.FizzBuzz;

public class FizzBuzzTest {

	private FizzBuzz fizzBuzz = new FizzBuzz();
	
	@Test
	public void aNumberIsConvertedToFizzIfDivisibleByThree() {
		String result = fizzBuzz.convert(3);
		
		assertEquals("Fizz", result);
	}
	
	@Test
	public void aNumberIsConvertedToBuzzIfDivisibleByFive() {
		String result = fizzBuzz.convert(5);
		
		assertEquals("Buzz", result);
	}
	
	@Test
	public void aNumberDivisibleByThreeAndFiveIsConvertedToFizzBuzz() {
		String result = fizzBuzz.convert(15);
		
		assertEquals("FizzBuzz", result);
	}
	
	@Test
	public void aNumberIsNotConvertedIfNotDivisibleByThreeOrFive() {
		String result = fizzBuzz.convert(1);
		
		assertEquals("1", result);
	}
}
