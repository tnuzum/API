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
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class BookAppointment extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="BookAppt",description="PBI:146227")
	public void BookAppt() { 

		int member = 230;
		
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
						"\"ItemId\": 215,"+ 
						"\"Occurrence\": \"2019-11-18T12:00:00-05:00\","+ 
						"\"CustomerId\": "+member+","+ 
						"\"RequestedBooks\": [40],"+ 
						"\"UserDisplayedPrice\": 0.00"+
						"}")
					.post("/api/v3/appointment/bookappointmentbymember")
					.then()
							.log().body()
					.assertThat().statusCode(200);
	}	
}
