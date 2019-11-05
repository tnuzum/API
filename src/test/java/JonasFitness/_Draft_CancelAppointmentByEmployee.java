package JonasFitness;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class _Draft_CancelAppointmentByEmployee extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="ApptCancelled",description="PBI:141862")
	public void ApptCancelled() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.post("/api/v3/appointment/cancelappointmentbyemployee/16398")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Status", equalTo("Success"))
						.body("Result", hasKey("ConfirmationCode"))
						.body("Result", hasKey("Reason"))
						.body("Result.Reason", nullValue());
	}
	@Test (testName="ApptNotFound",description="PBI:141862")
	public void ApptNotFound() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.post("/api/v3/appointment/cancelappointmentbyemployee/916375")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS);
	}
	@Test (testName="NotCancelled_ApptDatePast",description="PBI:141862")
	public void NotCancelled_ApptDatePast() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.post("/api/v3/appointment/cancelappointmentbyemployee/16169")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Status", equalTo("Failed"))
						.body("Result.ConfirmationCode", nullValue())
						.body("Result", hasKey("Reason"))
						.body("Result.Reason", equalTo("TimeRestriction"));
	}
}
