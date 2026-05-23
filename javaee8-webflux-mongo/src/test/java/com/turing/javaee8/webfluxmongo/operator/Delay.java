package com.turing.javaee8.webfluxmongo.operator;

public class Delay {

	public static void delay(long ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
