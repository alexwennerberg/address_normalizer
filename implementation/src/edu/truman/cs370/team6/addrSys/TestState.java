package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestState 
{
	private String addrInput1 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "100 E. Normal Street \t Kirksville" +
			" \t M \t 63501 \t 4200";
	private String addrInput3 = "100 E. Normal Street \t Kirksville" +
			" \t Missouri \t 63501 \t 4200";
	private String addrInput4 = "100 E. Normal Street \t Kirksville" +
			" \t Missour \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	private Address addr3 = new Address(addrInput3);
	private Address addr4 = new Address(addrInput4);
	
	@Test
	public void testValidState() 
	{
		assertEquals(true, addr1.normalize());
	}
	
	@Test
	public void testInvalidState() 
	{
		assertEquals(false, addr2.normalize());
	}
	
	@Test
	public void testSpelledState() 
	{
		assertEquals(true, addr3.normalize());
	}
	
	@Test
	public void testMisspelledState() 
	{
		assertEquals(false, addr4.normalize());
	}
}
