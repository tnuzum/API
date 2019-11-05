package JonasFitness;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class _Draft_BookAppointmentByMember extends base {
	
	/* *** TEST CASE SUMMARY ***
	 * Schedule an appointment
	 * Attempt to schedule appointment in same time slot
	 * Cancel appointment
	 * Repeat for all types appointments
	 */ 
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="FreeAppointment_SingleMember",description="PBI:127168")
	public void FreeAppointment_SingleMember() { 
		// This call doesn't not book appt because the parameter Compete BO > Clubs >
		// "Restrict Online Schedule to Prepaid Trainings / Services Only" is checkmarked
	given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
		.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 4534,"+ 
					"\"Occurrence\": \"2024-11-01T14:00:00-04:00\","+ 
					"\"CustomerId\": 29947,"+ 
					"\"RequestedBooks\": [226],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(404)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Message", equalTo("FailNotEnoughPunches"));
/*	
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
 */
	}

	
	@Test (testName="PaidAppointment_SingleMember",description="PBI:127168")
	public void PaidAppointment_SingleMember() { 

	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 4474,"+ 
					"\"Occurrence\": \"2024-11-05T14:00:00-04:00\","+ 
					"\"CustomerId\": 29947,"+ 
					"\"RequestedBooks\": [226,221],"+ 
					"\"UserDisplayedPrice\": 20.00"+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
						.log().body()
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
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="PunchcardAppointment_SingleMember",description="PBI:127168")
	public void PunchcardAppointment_SingleMember() { 

	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 4535,"+ 
					"\"Occurrence\": \"2024-11-03T14:00:00-04:00\","+ 
					"\"CustomerId\": 29947,"+ 
					"\"RequestedBooks\": [222],"+ 
					"\"UserDisplayedPrice\": 7.50"+
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
				.body("Result.Reason", nullValue());
	}
	@Test (testName="PaidAppointment_MultipleMember",description="PBI:127168")
	public void PaidAppointment_MultipleMember() { 
		// This call doesn't not book appt because the parameter Compete BO > Clubs >
		// "Restrict Online Schedule to Prepaid Trainings / Services Only" is checkmarked
	given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-ClubId"))
		.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
			.when()
			.body("{" + 
					"\"AppointmentClubId\": 1,"+ 
					"\"ItemId\": 4474,"+ 
					"\"Occurrence\": \"2024-11-01T16:00:00-04:00\","+ 
					"\"CustomerId\": 29947,"+
					"\"AdditionalCustomerIds\": [29970],"+
					"\"RequestedBooks\": [226,221],"+ 
					"\"UserDisplayedPrice\": 0.00"+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
						.log().body()
						.assertThat().statusCode(404)
				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Message", equalTo("FailNotEnoughPunches"));
	}
}
