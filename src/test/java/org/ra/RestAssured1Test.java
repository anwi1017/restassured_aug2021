package org.ra;

import org.junit.Before;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssured1Test {
	
		
	//https://reqres.in/api/users
	
	public static void main(String[] arg) {
	
		//create base path
		RestAssured.baseURI="https://reqres.in";
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//setup and executing request
		request.get("/api/users?page=2").then().log().all();
		
	}
}
