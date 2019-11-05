package JonasFitness;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
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

public class _Draft_E2E extends base {
	
	/* *** TEST CASE SUMMARY ***
	 * Schedule an appointment
	 * Attempt to schedule appointment in same time slot
	 * Cancel appointment
	 * 
	 * *** TASKS IN PROGRESS ***
	 *  
	 *  
	 *  
	 */ 
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="FreeAppointment_SingleMember",description="PBI:127168")
	public void FreeAppointment_SingleMember() { 

		/* 
		 * 
GET CUSTOMER ID FROM USER NAME; PUT INTO CustomerId object to be used later in test		 * 
		 * 			given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-ClubId"))
			.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
				.body("{"+
						  "\"Username\": \"rauto\","+
						  "\"Password\": \"Testing1!\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(200)
			.time(lessThan(5L),TimeUnit.SECONDS)			
			.body("Result.AuthenticationResult", equalTo("Success"))
			.body("Result.CustomerId", equalTo(member));
			
	}
GET ITEM ID, RESOURCE ID AND DATETIME FOR APPT
					given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
	
		 */
		
		
		
	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
		.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
			.body("{\"Occurrence\":{\"DateTime\":\"2024-10-25T12:00:00Z\",\"MinuteOffset\":\"0\"},\"AppointmentClubId\":1,\"ItemId\":4534,\"CustomerId\":29947,\"RequestedBooks\":[226],\"UserDisplayedPrice\":0,\"EnforcePunchesRequired\":false}")
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
		String Result = book_js.get("Result.Result");
//		System.out.println("Book Appointment Result: "+Result);
		
// ** Cancel Appointment **
	Response cancel_res	= given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
			.when()
				.post("/api/v3/appointment/cancelappointmentbyemployee/"+AppointmentId)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue())
				.extract().response();
	JsonPath cancel_js = ReusableMethods.rawToJson(cancel_res);
	String Status = cancel_js.get("Status");
//	System.out.println("Cancel Appointment Result: "+Status);
	}
	
	// GET APPT DETAILS TO CONFIRM SCHEDULED AS EXPECTED
}
