package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class BookAppointmentByMember extends base {
	
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
					"\"Occurrence\": \"2020-12-28T12:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
				.assertThat().statusCode(200)
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");

		// ** Attempt to book same appointment
				given()
		//		.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
		.when()
		.body("{" + 
			"\"AppointmentClubId\": 1,"+ 
			"\"ItemId\": 215,"+ 
			"\"Occurrence\": \"2019-11-18T12:00:00-05:00\","+ 
			"\"CustomerId\": 230,"+ 
			"\"RequestedBooks\": [40],"+ 
			"\"UserDisplayedPrice\": 0.00"+
			"}")
		.post("/api/v3/appointment/bookappointmentbymember")
		.then()
//				.log().body()
				.assertThat().statusCode(404)
		.time(lessThan(5L),TimeUnit.SECONDS)
		.body("Message", equalTo("FailAppointmentNotAvailable"));

		// ** Cancel Appointment **
				given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbymember/"+AppointmentId+"/"+member)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="PaidAppointment_SingleMember",description="PBI:146227")
	public void PaidAppointment_SingleMember() { 
		
		int member = 224;

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
					"\"ItemId\": 46,"+ 
					"\"Occurrence\": \"2020-11-16T16:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [35],"+ 
					"\"UserDisplayedPrice\": 40.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.body("Result.AppointmentId", not(empty()))
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");
	
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
//				.post("/api/v3/appointment/cancelappointmentbymember/"+AppointmentId+"/"+member)
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+AppointmentId)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="PunchcardAppointment_SingleMember",description="PBI:146227")
	public void punchcardAppointment_SingleMember() { 

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
					"\"ItemId\": 25,"+ 
					"\"Occurrence\": \"2025-01-01T16:00:00-05:00\","+ 
					"\"CustomerId\": 224,"+ 
					"\"RequestedBooks\": [3,18],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.body("Result.AppointmentId", not(empty()))
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+AppointmentId)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="PaidAppointment_MultipleMember",description="PBI:146227")
	public void PaidAppointment_MultipleMember() { 
		Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 38,"+ 
					"\"Occurrence\": \"2024-11-18T16:00:00-04:00\","+ 
					"\"CustomerId\": 229,"+
					"\"AdditionalCustomerIds\": [230],"+
					"\"RequestedBooks\": [30],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.extract().response();
		// CANCEL APPOINTMENT
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+AppointmentId)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
		
	}
	
	@Test (testName="notValidBookableItem",description="PBI:146227")
	public void notValidBookableItem() { 
	
	given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 13,"+ 
					"\"Occurrence\": \"2025-01-02T16:00:00-05:00\","+ 
					"\"CustomerId\": 230,"+ 
					"\"RequestedBooks\": [4],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(500)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Message", equalTo("Internal server error - Item with ID 13 is not a valid bookable appointment item."));
	}
	
}
