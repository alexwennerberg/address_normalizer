package edu.truman.cs370.team6.addrSys;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDirectionals 
{
	private String addrInput1 = "100 E. Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "100 Northeast Normal Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput3 = "100 East Normal Oak Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput4 = "100 East Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput5 = "100 North East Normal Oak Street \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput6 = "100 Normal Street East \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput7 = "100 Normal Street South East \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	private String addrInput8 = "100 East Normal Street North \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	private Address addr3 = new Address(addrInput3);
	private Address addr4 = new Address(addrInput4);
	private Address addr5 = new Address(addrInput5);
	private Address addr6 = new Address(addrInput6);
	private Address addr7 = new Address(addrInput7);
	private Address addr8 = new Address(addrInput8);
	
	@Test
	public void testOneDirectional() 
	{
		addr1.normalize();
		assertEquals("100 E NORMAL ST", addr1.getStreet());
	}

	@Test 
	public void testTwoDirectionals() 
	{
		addr2.normalize();
		assertEquals("100 NE NORMAL ST", addr2.getStreet());
	}
	
	@Test
	public void testTwoStreetNames() 
	{
		addr3.normalize();
		assertEquals("100 E NORMAL OAK ST", addr3.getStreet());
	}
	
	@Test 
	public void testDirectionalAsStreetName() 
	{
		addr4.normalize();
		assertEquals("100 EAST ST", addr4.getStreet());
	}
	
	@Test
	public void testTwoDirectionalsTwoStreetNames() 
	{
		addr5.normalize();
		assertEquals("100 N E NORMAL OAK ST", addr5.getStreet());
	}
	
	@Test
	public void testOnePostdirectional()
	{
		addr6.normalize();
		assertEquals("100 NORMAL ST E", addr6.getStreet());
	}
	
	@Test
	public void testTwoPostdirectionals()
	{
		addr7.normalize();
		assertEquals("100 NORMAL ST S E", addr7.getStreet());
	}
	
	@Test
	public void testOnePreOnePostdirectional() {
		addr8.normalize();
		assertEquals("100 E NORMAL ST N", addr8.getStreet());
	}
}