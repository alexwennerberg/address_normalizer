package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPuncRemoval
{
	private String addrInput = "100 E. Normal St. \t Kirksville," +
			" \t MO. \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput);
	
	@Test
	public void testNoPuncStreet()
	{
		//addrOne.removeEndPunctuation();
		addr1.normalize();
		assertEquals("100 E NORMAL ST", addr1.getStreet());
	}
	
	@Test
	public void testNoPuncCity()
	{
		addr1.normalize();
		assertEquals("KIRKSVILLE", addr1.getCity());
	}
	
	@Test
	public void testNoPuncState()
	{
		addr1.normalize();
		assertEquals("MO", addr1.getState());
	}
}

