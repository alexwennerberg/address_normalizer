package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAddressTokenizer
{
	private String addrInput = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";

	private Address addrOne = new Address(addrInput);
	
	@Test
	public void testStreetAddress()
	{
		assertEquals("100 E. Normal Street", addrOne.getStreet());
	}
	
	@Test
	public void testCity()
	{
		assertEquals("Kirksville", addrOne.getCity());
	}
	
	@Test
	public void testState()
	{
		assertEquals("MO", addrOne.getState());
	}

	public void testZipFive()
	{
		assertEquals("63501", addrOne.getZipFive());
	}
	
	@Test
	public void testZipFour()
	{
		assertEquals("4200", addrOne.getZipFour());
	}	
}

