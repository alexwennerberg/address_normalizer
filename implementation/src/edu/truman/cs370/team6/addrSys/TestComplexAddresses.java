package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestComplexAddresses 
{
	private String addrInput1 = "100 E. N/ormal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "100 E. N.ormal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	
	@Test
	public void testFractional() 
	{
		assertEquals(false, addr1.normalize());
	}

	@Test
	public void testGridStyle() 
	{
		assertEquals(false, addr2.normalize());
	}
}
