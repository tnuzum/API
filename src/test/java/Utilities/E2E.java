package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class E2E extends base {
	
	static int itemId = 215;
	static int resourceId = 40;
	static int resourceTypeId = 1;
	static String sDateTimeNoOffset = ReusableDates.getCurrentDate();
	static String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneWeek();
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Get Member",description="PBI:139705")
	public int getMember() {

			Response res = given()
//			.log().all()
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
			.when()
				.body("{"+
						  "\"Username\": \"timauto\","+
						  "\"Password\": \"Testing1!\""+
						"}")
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then()
//			.log().all()
			.assertThat().statusCode(200)
//			.time(lessThan(5L),TimeUnit.SECONDS)			
			.extract().response();
			
			JsonPath js = ReusableMethods.rawToJson(res);
			int memberId = js.get("Result.CustomerId");
			
			return memberId;	
	}

	@Test (priority = 1, testName="GetAvailableAppointment")
	public String getAvailableAppointment()
	{
		Response res = given()
//					.log().all()
					.header("accept", prop.getProperty("accept"))
					.header("X-Api-Key", prop.getProperty("X-Api-Key"))
					.header("X-CompanyId", prop.getProperty("X-CompanyId"))
					.header("X-ClubId", prop.getProperty("X-Club1Id"))
							.queryParam("ResourceId", resourceId)
						.when()
						.get("/api/v3/appointment/getavailableappointments/"+getMember()+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+itemId)
							.then()
//							.log().body()
							.extract().response();
					
					JsonPath js = ReusableMethods.rawToJson(res);
					String apptDateTime = js.get("Result.BooksAndAvailability[0].StartingTimes[0]");	
		
		return apptDateTime;
	}
	
	@Test (priority = 2, testName="BookAppt")
	public int bookAppt() { 

		int member = 230;
		int itemId = 215;
		int requestedBooks = 40;
				
			Response res = given()
//						.log().all()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.header("Content-Type", "application/json")
				.when()
				.body("{" + 
						"\"AppointmentClubId\": 1,"+ 
						"\"ItemId\": "+itemId+","+ 
						"\"Occurrence\": \""+getAvailableAppointment()+"\","+
						"\"CustomerId\": "+member+","+ 
						"\"RequestedBooks\": ["+requestedBooks+"],"+ 
						"\"UserDisplayedPrice\": 0.00"+
						"}")	
					.post("/api/v3/appointment/bookappointmentbymember")
					.then()
							.log().body()
					.extract().response();
			
			JsonPath js = ReusableMethods.rawToJson(res);
			int AppointmentId = js.get("Result.AppointmentId");
			
			return AppointmentId;
	}	
	@Test (priority = 3, testName="ApptCancelled")
	public void apptCancelled() { 
		
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.post("/api/v3/appointment/cancelappointmentbyemployee/"+bookAppt())
						.then()
						.log().body();
				
	}
	
	
	
	
	
}
