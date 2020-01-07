package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.empty;
import java.io.IOException;
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
	
	@Test (testName="Free Appointment Single Member",description="PBI:146227")
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
					"\"Occurrence\": \"2020-03-09T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbyemployee")
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
			"\"Occurrence\": \"2020-03-09T11:00:00-05:00\","+ 
			"\"CustomerId\": "+member+","+ 
			"\"RequestedBooks\": [40],"+ 
			"\"UserDisplayedPrice\": 0.00"+
			"}")
		.post("/api/v3/appointment/bookappointmentbyemployee")
		.then()
//				.log().body()
				.assertThat().statusCode(404)
		.body("Message", equalTo("FailAppointmentNotAvailable"));

		// ** Cancel Appointment **
				// This appt can be cancelled by a member becauase the item
				// is configured with Product > Booking > Appt Fee Details = Per Appt Basis
				
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
//				//.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="Paid Appointment Single Member",description="PBI:146227")
	public void PaidAppointment_SingleMember() { 

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
					"\"Occurrence\": \"2020-11-16T12:00:00-05:00\","+ 
					"\"CustomerId\": 224,"+ 
					"\"RequestedBooks\": [35],"+ 
					"\"UserDisplayedPrice\": 40.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				//.time(lessThan(5L),TimeUnit.SECONDS)
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
				//.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="Punchcard Appointment Single Member",description="PBI:146227")
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
			.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				//.time(lessThan(5L),TimeUnit.SECONDS)
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
				//.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="Paid Appointment Multiple Member",description="PBI:146227")
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
					"\"Occurrence\": \"2024-11-25T16:00:00-04:00\","+ 
					"\"CustomerId\": 229,"+
					"\"AdditionalCustomerIds\": [230],"+
					"\"RequestedBooks\": [30],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
				//.time(lessThan(5L),TimeUnit.SECONDS)
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
				//.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
		
	}
	
	@Test (testName="Not Valid Bookable Item",description="PBI:146227")
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
				.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//						.log().body()
						.assertThat().statusCode(500)
				//.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Message", equalTo("Internal server error - Item with ID 13 is not a valid bookable appointment item."));
	}
	
	@Test (testName="Appointment Not Found",description="PBI:146227")
	public void appointmentNotFound() { 
		
		int member = 230;
		
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
					"\"Occurrence\": \"2520-03-09T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//				.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("FailAppointmentNotAvailable"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:146227")
	public void productPriceChanged() { 
		
		int member = 230;
		
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
					"\"Occurrence\": \"2020-03-09T11:00:00-05:00\","+ 
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [40],"+ 
					"\"UserDisplayedPrice\": 0.01"+
					"}")
				.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//				.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("FailPriceChanged"));
	}
	
	@Test (testName="Not Enough Punches",description="PBI:146227")
	public void notEnoughPunches() { 
		
// This call succeeds because it's sent by employee, even though the member doesn't have enough punches
		
		int member = 247;
		
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
					"\"CustomerId\": "+member+","+ 
					"\"RequestedBooks\": [3,18],"+ 
					"\"UserDisplayedPrice\": 60.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbyemployee")
				.then()
//				.log().body()
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int AppointmentId = book_js.get("Result.AppointmentId");

				given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
			.get("/api/v3/appointment/cancelappointmentbyemployee/"+AppointmentId+"")
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
}
