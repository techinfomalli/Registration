package com.malli.Registraion.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.malli.Registraion.AppConstants.AppConstants;
import com.malli.Registraion.controllers.UserController;
import com.malli.Registraion.models.Country;
import com.malli.Registraion.service.UserService;

@WebMvcTest(value = UserController.class)
public class RegistrationRestControllerTest {

	@MockBean
	UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void emailCheckTest1() throws Throwable {

		when(userService.emailCheckTest("mallisrirama@gmail.com")).thenReturn("UNIQUE");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emailCheckTest/mallisrirama@gmail.com");
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(AppConstants.UNIQUE, response.getContentAsString());

	}

	@Test
	public void emailCheckTest2() throws Throwable {

		when(userService.emailCheckTest("mallisrirama@gmail.com")).thenReturn("DELETED");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/emailCheckTest/mallisrirama@gmail.com");
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(AppConstants.DELETED, response.getContentAsString());

	}

	@Test
	public void countryesTest() throws Throwable {

		
		/*
		 * HashMap<Integer,String> map= new HashMap(); map.put(1, "INDIA"); map.put(2,
		 * "US");
		 */
		  List<Country> counties= new ArrayList<Country>();
		  counties.add(new Country(1,"INDIA"));
		  counties.add(new Country(2,"US"));
		  when(userService.getConties()).thenReturn(counties); 
		  
		  MockHttpServletRequestBuilder builder =
		  MockMvcRequestBuilders.get("/countryes");
		  MvcResult result = mockMvc.perform(builder).andReturn();
		  MockHttpServletResponse response = result.getResponse();
		  assertEquals(result.getResponse()	.getStatus(),200);
		 // response.getContentAsByteArray()
		  System.out.println(result.toString());
		 

	}

	@Test
	public void emailCheckTes1t() throws Throwable {

		assertEquals(AppConstants.UNIQUE, AppConstants.UNIQUE);

	}
}
