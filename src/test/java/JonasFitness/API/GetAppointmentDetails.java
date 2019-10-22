package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

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
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.BookedMembers[0]", hasKey("AppointmentCharge"))
						.body("Result.BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result.BookedMembers[0]", hasKey("AttendedIndicator"))
						.body("Result.BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result.BookedMembers[0]", hasKey("CancellationFee"))
						.body("Result.BookedMembers[0]", hasKey("CustomerCanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CancellationReason"))
						.body("Result.BookedMembers[0]", hasKey("CustomerId"))
						.body("Result.BookedMembers[0]", hasKey("DisplayName"))
						.body("Result.BookedMembers[0]", hasKey("FirstName"))
						.body("Result.BookedMembers[0]", hasKey("LastName"))
						.body("Result.BookedMembers[0]", hasKey("NoShowFee"))
						.body("Result.BookedMembers[0]", hasKey("NoShowFeeIndicator"))

						.body("Result.BookedMembers[0].AppointmentCharge", equalTo(0))
						.body("Result.BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result.BookedMembers[0].AttendedIndicator", equalTo(false))
						.body("Result.BookedMembers[0].BarcodeId", equalTo("5651"))
						.body("Result.BookedMembers[0].CancellationFee", equalTo(3))
						.body("Result.BookedMembers[0].CustomerCanCancel.CanCancel", equalTo(""))
						.body("Result.BookedMembers[0].CustomerCanCancel.CancellationReason", equalTo(""))
						.body("Result.BookedMembers[0]CustomerId", equalTo(29970))
						.body("Result.BookedMembers[0].DisplayName", equalTo("Auto, Scott"))
						.body("Result.BookedMembers[0].FirstName", equalTo("Scott"))
						.body("Result.BookedMembers[0].LastName", equalTo("Auto"))
						.body("Result.BookedMembers[0].NoShowFee", equalTo(5))
						.body("Result.BookedMembers[0].NoShowFeeIndicator", equalTo(false))
						;
				

	}
	@Test (testName="AppointmentsNotFound",description="PBI:139310")
	public void AppointmentsNotFound() {

		String appointment = prop.getProperty("appointmentInFuture1Id");  

				given()
//				.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/9"+appointment)// 9 is passed to make appointment id not on file
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 404 Not Found");

	}
}
