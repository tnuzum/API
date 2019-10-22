package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class _Draft_BookAppointmentByMember extends base {
	
	/*
	 * this is not booking appt yet; bug has been created
	 * 
	 * 
	 */
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:127168")
	public void ValidInput() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
				.header("Content-Type", "application/json")// ??? why is this using content-type instead of accept???
					.when()
					.body("{"+
						  "\"CustomerId\": 29947,"+
						  "\"AppointmentClubId\": 1,"+
						  "\"ItemId\": 4534,"+
						  "\"AdditionalCustomerIds\": [0],"+
						  "\"RequestedBooks\": [226],"+
						  "\"Occurrence\": \"2019-10-21T14:00:00\","+
						  "\"EnforcePunchesRequired\": false,"+
						  "\"UserDisplayedPrice\": 0.00"+
						"}")
						.post("/api/v3/appointment/bookappointmentbymember")
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}

}
