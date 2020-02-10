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
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Free Appointment Single Member",description="PBI:127168")
	public void FreeAppointment_SingleMember() { 

		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("demoId");
		String occurrence = prop.getProperty("demoOccurrence");
		String customerId = prop.getProperty("availableId");
		String requestedBooks = prop.getProperty("demoBookId");
		String userDisplayedPrice = prop.getProperty("demoPrice");
		
	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBooks+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
				.assertThat().statusCode(200)
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int appointmentId = book_js.get("Result.AppointmentId");

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
				"\"AppointmentClubId\": "+appointmentClubId+","+ 
				"\"ItemId\": "+itemId+","+ 
				"\"Occurrence\": \""+occurrence+"\","+
				"\"CustomerId\": "+customerId+","+ 
				"\"RequestedBooks\": ["+requestedBooks+"],"+ 
				"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
			"}")
		.post("/api/v3/appointment/bookappointmentbymember")
		.then()
//				.log().body()
				.assertThat().statusCode(404)
		.body("Message", equalTo("FailAppointmentNotAvailable"));

		// ** Cancel Appointment **
				given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbymember/"+appointmentId+"/"+customerId)
				.then()
//				.log().body()
				.assertThat().statusCode(200)
				.body("Status", equalTo("Success"))
				.body("Result", hasKey("ConfirmationCode"))
				.body("Result.ConfirmationCode", not(empty()))
				.body("Result", hasKey("Reason"))
				.body("Result.Reason", nullValue());
	}
	
	@Test (testName="Paid Appointment Single Member",description="PBI:127168")
	public void PaidAppointment_SingleMember() { 
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("paidTId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("availableId");
		String requestedBooks = prop.getProperty("pTBook1Id");
		String userDisplayedPrice = prop.getProperty("paidTPrice");

	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBooks+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
//				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.body("Result.AppointmentId", not(empty()))
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int appointmentId = book_js.get("Result.AppointmentId");
	
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
//				.post("/api/v3/appointment/cancelappointmentbymember/"+appointmentId+"/"+member)
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
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
	
	@Test (testName="Product Price Changed",description="PBI:127168")
	public void productPriceChanged() { 
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("paidTId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("availableId");
		String requestedBooks = prop.getProperty("pTBook1Id");
		double userDisplayedPrice = 0.01;

	given()
//						
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", 3)
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBooks+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(404)
				.body("Message", equalTo("FailPriceChanged"))
				;
	}
	
	@Test (testName="PunchcardAppointment_SingleMember",description="PBI:127168")
	public void punchcardAppointment_SingleMember() { 
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("multipleResourcesTrainingId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("availableId");
		String requestedBook1 = prop.getProperty("pTBook2Id");
		String requestedBook2 = prop.getProperty("pTBook3Id");
		String userDisplayedPrice = prop.getProperty("multipleResourcesTrainingPrice");

	Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBook1+","+requestedBook2+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
//				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.body("Result.AppointmentId", not(empty()))
				.extract().response();
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int appointmentId = book_js.get("Result.AppointmentId");
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
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
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("paidTId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("availableId");
		String AdditionalCustomerIds = prop.getProperty("noFOPId");
		String requestedBooks = prop.getProperty("pTBook1Id");
		String userDisplayedPrice = prop.getProperty("paidTPrice");
		
		Response book_res = given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"AdditionalCustomerIds\": ["+AdditionalCustomerIds+"],"+
					"\"RequestedBooks\": ["+requestedBooks+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(200)
//				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Result.Result", equalTo("Success"))
				.extract().response();
		// CANCEL APPOINTMENT
		JsonPath book_js = ReusableMethods.rawToJson(book_res);
		int appointmentId = book_js.get("Result.AppointmentId");
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/appointment/cancelappointmentbyemployee/"+appointmentId)
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
	
	@Test (testName="notValidBookableItem",description="PBI:127168")
	public void notValidBookableItem() { 
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("noOnlineId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("availableId");
//		String requestedBooks = prop.getProperty("pTBook1Id");
		String userDisplayedPrice = prop.getProperty("paidTPrice");
	
	given()
//						.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": [],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//						.log().body()
						.assertThat().statusCode(500)
//				.time(lessThan(5L),TimeUnit.SECONDS)
				.body("Message", equalTo("Internal server error - Item with ID "+itemId+" is not a valid bookable appointment item."));
	}
	
	@Test (testName="Not Enough Punches",description="PBI:127168")
	public void notEnoughPunches() { 
		
		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("multipleResourcesTrainingId");
		String occurrence = prop.getProperty("paidTOccurrence");
		String customerId = prop.getProperty("noFOPId");
		String requestedBook1 = prop.getProperty("pTBook2Id");
		String requestedBook2 = prop.getProperty("pTBook3Id");
		String userDisplayedPrice = prop.getProperty("multipleResourcesTrainingPrice");

	given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBook1+","+requestedBook2+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
			.post("/api/v3/appointment/bookappointmentbymember")
			.then()
//			.log().body()
			.assertThat().statusCode(404)
			.body("Message", equalTo("FailNotEnoughPunches"));
	}
	
	@Test (testName="Appointment Not Found",description="PBI:127168")
	public void appointmentNotFound() { 

		String appointmentClubId = prop.getProperty("club1Id");
		String itemId = prop.getProperty("paidTId");
		String occurrence = "2520-03-09T11:00:00-05:00";
		String customerId = prop.getProperty("availableId");
		String requestedBooks = prop.getProperty("pTBook1Id");
		String userDisplayedPrice = prop.getProperty("paidTPrice");
		
		given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
			.body("{" + 
					"\"AppointmentClubId\": "+appointmentClubId+","+ 
					"\"ItemId\": "+itemId+","+ 
					"\"Occurrence\": \""+occurrence+"\","+
					"\"CustomerId\": "+customerId+","+ 
					"\"RequestedBooks\": ["+requestedBooks+"],"+ 
					"\"UserDisplayedPrice\": "+userDisplayedPrice+""+
					"}")
				.post("/api/v3/appointment/bookappointmentbymember")
				.then()
//				.log().body()
				.assertThat().statusCode(404)
				.body("Message", equalTo("FailAppointmentNotAvailable"));
	}

}
