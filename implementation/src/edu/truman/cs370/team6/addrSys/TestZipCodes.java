package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestZipCodes 
{
	private String addrInput1 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";	
	private String addrInput2 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 6350 \t 420";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	
	@Test
	public void testValidZip5() 
	{
		assertEquals(true, addr1.normalize());
	}
	
	@Test
	public void testInValidZip5() 
	{
		assertEquals(false, addr2.normalize());
	}
	
	@Test
	public void testValidZip4() 
	{
		assertEquals(true, addr1.normalize());
	}
	
	@Test
	public void testInValidZip4() 
	{
		assertEquals(false, addr2.normalize());
	}
}
