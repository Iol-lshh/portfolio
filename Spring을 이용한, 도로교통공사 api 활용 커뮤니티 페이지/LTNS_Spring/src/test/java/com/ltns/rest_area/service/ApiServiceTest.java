package com.ltns.rest_area.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;

public class ApiServiceTest {

	@Test
	public void test() {
		
		String ra_name="11111111(22222)33333";
		
		String ra_destination=ra_name.replaceAll("[^(]*[(]", "");
		ra_destination=ra_destination.replaceAll("[)].*", "");
		System.out.println("ra_destination : "+ra_destination);
		ra_name=ra_name.replaceAll("[(][^)]*[)]", "");
		System.out.println("ra_name : "+ra_name);
	}

}
