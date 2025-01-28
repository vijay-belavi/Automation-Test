package Hitachi;

public class orderOfExecution {
	static {
		System.out.println("Static");
	}
	{
		System.out.println("Non-Static");
	}
	public static void main(String[] args) {
		System.out.println("Hello");
		orderOfExecution get = new orderOfExecution();
		System.out.println("Hi");
	}
}
