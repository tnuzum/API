package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class CancelAppointmentByEmployee extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	/*
	 * See BookAppointmentByEmployee test case for the test that cancels
	 * an appointment by employee.
	*/
	@Test (testName="ApptNotFound",description="PBI:141862")
	public void ApptNotFound() { 

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/cancelappointmentbyemployee/916375")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="NotCancelled_ApptDatePast",description="PBI:141862")
	public void NotCancelled_ApptDatePast() { 

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/cancelappointmentbyemployee/10")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Status", equalTo("Failed"))
						.body("Result.ConfirmationCode", nullValue())
						.body("Result", hasKey("Reason"))
						.body("Result.Reason", equalTo("TimeRestriction"));
	}
}
