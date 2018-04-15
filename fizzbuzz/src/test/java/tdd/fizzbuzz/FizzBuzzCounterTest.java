package tdd.fizzbuzz;

import static org.junit.Assert.*;

import org.junit.Test;

import tdd.fizzbuzz.FizzBuzzCounter;

public class FizzBuzzCounterTest {

	private FizzBuzzCounter counter = new FizzBuzzCounter();
	
	@Test
	public void nothingDisplayedIfNumberToCountToIsZero() {
		String result = counter.countTo(0);
		
		assertEquals("", result);
	}
	
	@Test
	public void nothingDisplayedIfNumberToCountToIsLessThanZero() {
		String result = counter.countTo(-1);
		
		assertEquals("", result);
	}
	
	@Test
	public void numberOfLinesDisplayedEqualsNumberToCountTo() {
		String result = counter.countTo(100);
		
		String[] lines = result.split("\n");
		assertEquals(100, lines.length);
	}
}
