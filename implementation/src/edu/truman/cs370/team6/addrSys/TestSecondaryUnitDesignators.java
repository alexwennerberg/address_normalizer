package edu.truman.cs370.team6.addrSys;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestSecondaryUnitDesignators 
{
	private String addrInput1 = "100 E FLOOR ST SUITE 10 \t KIRKSVILLE" +
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "100 E FLOOR ST HNGR \t KIRKSVILLE" +
			" \t MO \t 63501 \t 4200";
	private String addrInput3 = "100 E FLOOR ST OFFICE \t KIRKSVILLE" +
			" \t MO \t 63501 \t 4200";
	private String addrInput4 = "100 E FLOOR ST OFFICE 10 \t KIRKSVILLE" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	private Address addr3 = new Address(addrInput3);
	private Address addr4 = new Address(addrInput4);
	
	@Test
	public void testSUDWithNeededSecondaryRange() 
	{
		addr1.normalize();
		System.out.print(addr1.getStreet());
		assertEquals("100 E FLOOR ST STE 10", addr1.getStreet());
	}
	
	@Test
	public void testSUDWithoutNeededSecondaryRange() 
	{
		assertEquals(false, addr2.normalize());
	}
	
	@Test
	public void testSUDIWithoutUnnessisarySecondaryRange() 
	{
		addr3.normalize();
		assertEquals("100 E FLOOR ST OFC", addr3.getStreet());
	}
	
	@Test
	public void testSUDIWithUnnessisarySecondaryRange() 
	{
		addr4.normalize();
		assertEquals("100 E FLOOR ST OFC 10", addr4.getStreet()); 
	}
}
