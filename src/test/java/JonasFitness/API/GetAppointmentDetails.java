package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentDetails extends base {
		
// https://compete-api-future2.test-jfisoftware.com:8252/api/v3/appointment/getappointmentdetails/16362
	
	
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="AppointmentsFound",description="PBI:139310")
	public void AppointmentsFound() {
		
		String appointment = prop.getProperty("appointmentInFuture1Id");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test (testName="AppointmentsNotFound",description="PBI:139310")
	public void AppointmentsNotFound() {

		String appointment = prop.getProperty("appointmentInFuture1Id");  

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/appointment/getappointmentdetails/9"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 404 Not Found");

	}
}
