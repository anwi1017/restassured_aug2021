package org.ra;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static org.hamcrest.CoreMatchers.equalTo;;


public class RestAssuredSOAPRequest {
	
		
	//https://reqres.in/api/users
	@Before
	public void before() {
		//create base path
		RestAssured.baseURI="https://www.dataaccess.com";
	}
	
	@Test
	public void soapRequest() {
	
		String payload ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>1002</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//Content-Type: text/xml; charset=utf-8
		request
		.header("Content-Type","text/xml; charset=utf-8")
				.and().body(payload)
				.when().post("/webservicesserver/NumberConversion.wso")
				.then().log().body();
					
		
		
	}
	

	
	@Test
	public void soapRequestWithAssertion() {
	
		String payload ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>1002</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
		
		// create a request  (blank)
		RequestSpecification request= RestAssured.given();
		
		
		//Content-Type: text/xml; charset=utf-8
		Response response = request
		.header("Content-Type","text/xml; charset=utf-8")
				.and().body(payload)
				.when().post("/webservicesserver/NumberConversion.wso");
				
		
		
		/*<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
		  <soap:Body>
		    <m:NumberToWordsResponse xmlns:m="http://www.dataaccess.com/webservicesserver/">
		      <m:NumberToWordsResult>one thousand two </m:NumberToWordsResult>
		    </m:NumberToWordsResponse>
		  </soap:Body>
		</soap:Envelope>*/

		response.then().assertThat()
		.body("Envelope.Body.NumberToWordsResponse.NumberToWordsResult", equalTo("one thousand two "));
		
	}
	
	

}