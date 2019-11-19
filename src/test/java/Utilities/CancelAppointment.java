package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class CancelAppointment extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test (testName="ApptCancelled",description="PBI:141862")
	public void ApptCancelled() { 
		
		int confirmationNumber = 4533;

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.post("/api/v3/appointment/cancelappointmentbyemployee/"+confirmationNumber)
						.then()
						.log().body();
	}
}
