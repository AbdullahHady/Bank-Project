package com.revature.service.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.revature.Repository.ClientRepo;
import com.revature.Repository.EmployeeRepo;
import com.revature.service.Office;

public class OfficeTest {
	@Mock
	private ClientRepo cr;
	
	@Mock
	private EmployeeRepo er;
	//private String userName="Alif007";
	private String userName;
	
	private Office o;
	@Test
	public void testLogin() {
		
		
		o=new Office();
		
		//o.login(userName);
		cr=mock(ClientRepo.class);
		/*
		
		when(cr.matchMethod("Alif007")).thenReturn(1);
		assertEquals(1,o.login("Alif007"));
		assertEquals(0,o.login("Alif008"));
		assertEquals(1,o.login("Alif008"));
		
		when(cr.matchMethod("Hady123")).thenReturn(1);
		assertEquals(1,o.login("Hady123"));
		assertEquals(0,o.login("HaDy123"));
		assertEquals(1,o.login("hady123"));
		
		*/
		
	}
	


}
