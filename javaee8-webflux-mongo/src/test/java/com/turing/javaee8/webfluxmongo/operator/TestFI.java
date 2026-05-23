package com.turing.javaee8.webfluxmongo.operator;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class TestFI {
	static boolean isEven(int num)
	{
		return num %2==0;
	}
	static Integer getLength(String str)
	{
		return str.length();
	}
	static void doSomething(String message)
	{
		System.out.println("Do something "+message);
	}
	static String getSomething()
	{
		return "Something";
	}
	@Test
	public void testFi()
	{
		Predicate<Integer> pred = TestFI::isEven;
		System.out.println("Pred "+pred.test(3));
		System.out.println("Pred "+pred.test(2));
		
		Function<String,Integer> fun = TestFI::getLength;
		System.out.println("getLength "+fun.apply("Hello"));
		
		Consumer<String> consumer = TestFI::doSomething;
		consumer.accept("Action");
		
		Supplier<String> supplier = TestFI::getSomething;
		System.out.println("Supplier "+supplier.get());
	}
}
