package edu.truman.cs370.team6.addrSys;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestSuffixes 
{
	private String addrInput1 = "100 E Parkway Drive \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	
	public void normalizeTestAddress()
	{
		addr1.normalize();
	}
	
	@Test
	public void testsuffix()
	{
		normalizeTestAddress();
		System.out.print(addr1.getStreet());
		assertEquals(addr1.getStreet(), "100 E PARKWAY DR");
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
