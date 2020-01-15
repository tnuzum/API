package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
	
	@Test (testName="Free Appointment Single Member",description="PBI:146227")
	public void FreeAppointment_SingleMember() { 

		int member = 248;
		
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
					"\"Occurrence\": \"2020-12-28T10:00:00-05:00\","+ 
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
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="Paid Appointment Single Member",description="PBI:146227")
	public void PaidAppointment_SingleMember() { 
		
		int member = 248;

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
					"\"ItemId\": 243,"+ 
					"\"Occurrence\": \"2022-12-16T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [35],"+ 
					"\"UserDisplayedPrice\": 10.00"+
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
		
		int member = 248;
		
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
					"\"ItemId\": 243,"+ 
					"\"Occurrence\": \"2022-12-16T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"AdditionalCustomerIds\": [224],"+
					"\"RequestedBooks\": [35],"+ 
					"\"UserDisplayedPrice\": 10.00"+
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
	
	@Test (testName="Not Enough Punches",description="PBI:146227")
	public void notEnoughPunches() { 

	given()
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
					"\"CustomerId\": 247,"+ 
					"\"RequestedBooks\": [3,18],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
			.then()
//			.log().body()
			.assertThat().statusCode(404)
			.body("Message", equalTo("FailNotEnoughPunches"));
			;
	}
	
	@Test (testName="Appointment Not Found",description="PBI:146227")
	public void appointmentNotFound() { 

		int member = 248;
		
		given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 215,"+ 
					"\"Occurrence\": \"2520-12-28T12:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//				.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("FailAppointmentNotAvailable"));
	}
	//!! disabled until bug is fixed; bug allows booking even though price is different
	 /*
	@Test (testName="Product Price Changed",description="PBI:146227")
	public void productPriceChanged() { 

		int member = 248;
		
	given()
	.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 243,"+ 
					"\"Occurrence\": \"2022-12-16T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [35],"+ 
					"\"UserDisplayedPrice\": 10.01"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
						.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("FailPriceChanged"));
	} */
}
