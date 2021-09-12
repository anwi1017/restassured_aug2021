package org.ra;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssured2Test {
	
		
	//https://reqres.in/api/users
	@Before
	public void before() {
		//create base path
		RestAssured.baseURI="https://reqres.in";
	}
	
	@Test
	public void getRequest() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//setup and executing request
		request.get("/api/users?page=2").then().log().all();
		
	}
	
	@Test
	public void getRequest2() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//setup and executing request
		//request.get("/api/users?page=2").then().log().headers();
		
		request.get("/api/users?page=2").then().log().body();
	}
	

	@Test
	public void getRequest3() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//setup and executing request
		//request.get("/api/users?page=2").then().log().headers();
		
		String respBody = request.get("/api/users?page=2").getBody().asString();
		
		System.out.println("response Body ="+respBody);
		
	}

	@Test
	public void postRequest3() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		String requestBody=" {\"name\": \"morpheus\", \"job\": \"leader\"}  ";
		
		//setup and executing request
		request.body(requestBody)
				.post("/api/users").then().log().all();
		
//		String respBody = request.get("/api/users?page=2").getBody().asString();
//		System.out.println("response Body ="+respBody);
	}
	

	@Test
	public void postRequestWithHeaders() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		String requestBody=" {\"name\": \"morpheus\", \"job\": \"leader\"}  ";
		
		//setup and executing request
		request.header("content-type","application/json")
				.header("header2","value2")
				.header("header","value3")
				.body(requestBody)
				.log().all()
				.post("/api/users").then().log().all();
		
//		String respBody = request.get("/api/users?page=2").getBody().asString();
//		System.out.println("response Body ="+respBody);
	}
	

	@Test
	public void postRequestWithHeaders2() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		String headers= "header1=value1;header2=value2;header3=value3";
		String requestBody=" {\"name\": \"morpheus\", \"job\": \"leader\"}  ";
		
		String[] headerArray= headers.split(";");
		
		for(String header:headerArray) {

			String headerKeyValue[] =header.split("=");
			
			//System.out.println("header=>"+headerKeyValue[0] );
			//System.out.println("value=>"+headerKeyValue[1] );
			
			request.header(headerKeyValue[0],headerKeyValue[1]);
		}
		
		
				
		//setup and executing request
		request.body(requestBody)
				.log().all()
				.post("/api/users").then().log().all();
		
//		String respBody = request.get("/api/users?page=2").getBody().asString();
//		System.out.println("response Body ="+respBody);
	}
	
	@Test
	public void forAuthUrls() {

		System.out.println("test stared");
		RestAssured.baseURI="https://postman-echo.com";
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		String username="postman1";
		String password="password";
		
		
		request
		.auth().preemptive().basic(username, password)
		
		.when().get("basic-auth")
		
		.then().log().ifError();
		
		System.out.println("test completed");
	
	}
	

	@Test
	public void forAuthUrls2() {

		System.out.println("test stared");
		RestAssured.baseURI="https://postman-echo.com";
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		String username="postman";
		String password="password";
		
		
		request
		.auth().preemptive().basic(username, password)
		
		.when().get("basic-auth")
		
		.then().log().ifStatusCodeIsEqualTo(201);
		
		System.out.println("test completed");
	
	}
	
	
}


