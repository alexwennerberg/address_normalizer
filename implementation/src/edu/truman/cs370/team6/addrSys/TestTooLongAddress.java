package edu.truman.cs370.team6.addrSys;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestTooLongAddress 
{
	private String addrInput1 = "100 NOT QUITE AS LONG ST \t KIRKSVILLE" + 
			" \t MO \t 63501 \t 4200";
	private String addrInput2 = "100 REALLY REALLY REALLY WAY TOO EXCEEDINGLY LONG STREET AVE \t Kirksville" +
			" \t MO \t 63501 \t 4200";
	
	private Address addr1 = new Address(addrInput1);
	private Address addr2 = new Address(addrInput2);
	
	@Test
	public void testTooLong()
	{
		assertEquals(true, addr1.normalize());
		assertEquals(false, addr2.normalize());
	}
}