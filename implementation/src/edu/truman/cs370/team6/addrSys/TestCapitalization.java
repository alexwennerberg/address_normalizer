package edu.truman.cs370.team6.addrSys;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestCapitalization 
{
	private String addrInput1 = "100 Normal St \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	
	public void normalizeTestAddress()
	{
		addr1.normalize();
	}

	@Test
	public void testStreetAddress()
	{
		normalizeTestAddress();
		assertEquals("100 NORMAL ST", addr1.getStreet());
	}
	
	@Test
	public void testCity()
	{
		addr1.normalize();
		assertEquals("KIRKSVILLE", addr1.getCity());
	}
	
	@Test
	public void testState()
	{
		addr1.normalize();
		assertEquals("MO", addr1.getState());
	}
}
