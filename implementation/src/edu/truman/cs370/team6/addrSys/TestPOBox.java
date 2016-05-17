package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPOBox 
{
	private String addrInput1 = "PO Box 380 \t Viola \t AR \t 72538 \t 1111";
	private String addrInput2 = "P.O. Box 380 \t Viola \t AR \t 72538 \t 1111";
	private String addrInput3 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	private Address addr3 = new Address(addrInput3);

	@Test
	public void testValidPOBox() 
	{
		addr1.normalize();
		assertEquals(true, addr1.hasPOBox());
	}
	
	@Test
	public void testPOBoxWithPeriods() 
	{
		addr2.normalize();
		assertEquals(true, addr2.hasPOBox());
	}
	
	@Test
	public void testRegularAddress() 
	{
		addr3.normalize();
		assertEquals(false, addr3.hasPOBox());
	}
}
