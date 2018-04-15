package tdd.fizzbuzz;

public class FizzBuzz {

	public String convert(int number) {
		StringBuilder result = new StringBuilder();
		if (isFizz(number)) {
			result.append("Fizz");
		}
		if (isBuzz(number)) {
			result.append("Buzz");
		}
		
		return result.length() == 0 ? ""+number : result.toString();
	}

	private boolean isFizz(int number) {
		return number % 3 == 0;
	}

	private boolean isBuzz(int number) {
		return number % 5 == 0;
	}
}
