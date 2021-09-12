package org.ra;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class RestAssured2Test3 {
	
		
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
		Response response =request.get("/api/users?page=2");
		
		response.then().log().all();
		
		JsonPath jp =response.jsonPath();
		
		
		System.out.println(jp.getInt("total"));
		System.out.println(jp.getString("data[0].email"));
//		 "data": [
//		          {
//		              "id": 7,
//		              "email": "michael.lawson@reqres.in",
//		              "first_name": "Michael",
//		              "last_name": "Lawson",
//		              "avatar": "https://reqres.in/img/faces/7-image.jpg"
//		          },
		
	}
	
	@Test
	public void getRequest3Assert() {
	
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//setup and executing request
		Response response =request.get("/api/users?page=2");
		
		JsonPath jp =response.jsonPath();
		
		Assert.assertEquals("michael.lawson@reqres.com", jp.getString("data[0].email"));
		
	}
	
	@Test
	public void httpsvalidationErros() {
	
		// create a request  (blank)
		RestAssured.given().baseUri("https://www.picturematters.in")
		.get().then().log().all();
//		javax.net.ssl.SSLHandshakeException: 
		//PKIX path validation failed: 
		//java.security.cert.CertPathValidatorException: validity check failed
		
	}
	
	@Test
	public void httpsvalidationErrosFIXED() {
	
		// create a request  (blank)
		RestAssured
		.given().relaxedHTTPSValidation()
		.baseUri("https://www.picturematters.in").get().then().log().all();
		
	}
	
	@Test
	public void httpsvalidationErrosFIXED2() {
	
		RestAssured.baseURI="https://www.picturematters.in";
		
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		request.relaxedHTTPSValidation()
		.get().then().log().all();
		
	}
}


