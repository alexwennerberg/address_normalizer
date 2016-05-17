package edu.truman.cs370.team6.addrSys;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCheckFields 
{
	private String addrInput1 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput3 = "E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput3);
	
	public void normalizeTestAddress()
	{
		addr1.normalize();
	}
	
	@Test
	public void testGoodAddress()
	{
		normalizeTestAddress();
		
		assertEquals(true, addr1.hasAllFields());
	}
	
	
	@Test
	public void testIncompleteAddress()
	{
		normalizeTestAddress();
		
		Address addrIncom = new Address(addrInput2);
		addrIncom.normalize();
		assertEquals(false, addrIncom.hasAllFields());
	}
	
	@Test
	public void testAddressWithoutNumber() 
	{
		assertEquals(false, addr2.normalize());
	}
	
	/*
	
	addrOne.Normalize();
	assertEquals(true, addrOne.isComplete());

	String incompleteAddr = "Kirksville" +
			" \t MO \t 63501 \t 4200";
	Address addrIncom = new Address(incompleteAddr);
	addrIncom.Normalize();
	assertEquals(false, addrIncom.isComplete());
	*/
}