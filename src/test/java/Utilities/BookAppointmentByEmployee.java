package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class BookAppointmentByEmployee extends base {
	
	/*
	 * *** TEST CASE SUMMARY ***
	 * Schedule an appointment
	 * Attempt to schedule appointment in same time slot
	 * Cancel appointment
	 */ 
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="FreeAppointment_SingleMember",description="PBI:146227")
	public void FreeAppointment_SingleMember() { 
		
		int member = 230;
		
	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 215,"+ 
					"\"Occurrence\": \"2020-03-09T12:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
						.log().body()
				.assertThat().statusCode(200)
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");
	}
}
