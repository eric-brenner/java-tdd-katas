package tdd.fizzbuzz;

public class FizzBuzzCounter {

	private FizzBuzz fizzBuzz = new FizzBuzz();

	public String countTo(int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < number+1; i++) {
			sb.append(fizzBuzz.convert(i)).append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		FizzBuzzCounter counter = new FizzBuzzCounter();
		System.out.print(counter.countTo(100));
	}
}
