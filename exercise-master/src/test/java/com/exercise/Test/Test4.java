package com.exercise.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.exercise.testBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test4 extends TestBase {
	public String orderID;
	public String postData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
	public String putData = "{\"name\": \"George\", \"job\": \"Politician\"}";

	@Test
	public void testingOfGET() {
		RestAssured.baseURI = "https://reqres.in/";

		given().
			param("pages", "2").
		when().
			get("/api/users").
		then().
			assertThat().statusCode(200).
		and().
			contentType(ContentType.JSON).
		and().
			header("Server", "cloudflare");

	}

	@Test
	public void testingOfPOST() {
		RestAssured.baseURI = "https://reqres.in/";
Response res=
		given().
			body(postData).
		when().
			post("/api/users").
		then().
			assertThat().statusCode(201).
		and().
			contentType(ContentType.JSON).
		extract().response()
		;

System.out.println(res.jsonPath().getString("id"));
System.out.println(res.jsonPath().getString("name"));
	}

	
	@Test
	public void testingOfPUT() {
		RestAssured.baseURI = "https://reqres.in/";
Response res=
		given().
			body(putData).
		when().
			put("/api/users/2").
		then().
			assertThat().statusCode(200).
		and().
			contentType(ContentType.JSON).
		extract().response()
		;

System.out.println(res.jsonPath().getString("id"));
System.out.println(res.jsonPath().getString("name"));
	}
	
	
	@Test
	public void testingOfDELETE() {
		RestAssured.baseURI = "https://reqres.in/";
		given().
			body(putData).
		when().
			delete("/api/users/2").
		then().
			assertThat().statusCode(204)
		;


	}
	
	

}
