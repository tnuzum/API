package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class GetAppointmentsByMember extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="AppointmentsFound",description="PBI:124124")
	public void AppointmentsFound() {
		
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();
		String member = "230";
				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam(member)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
						.body("Result[0].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[0].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[0].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[0].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[0].BookedMembers[0]", hasKey("Id"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey("CanCancel"))
						.body("Result[0]", hasKey("CanChange"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey("CategoryDescription"))
						.body("Result[0]", hasKey("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey("ClubName"))
						.body("Result[0]", hasKey("ClubNumber"))
						.body("Result[0]", hasKey("DurationInMinutes"))
						.body("Result[0]", hasKey("ForCustomerId"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsRecurring"))
						.body("Result[0]", hasKey("ItemBarcodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("LongDescription"))
						.body("Result[0]", hasKey("OriginalInstructorName"))
						.body("Result[0]", hasKey("ScheduleDateTime"))
						.body("Result[0]", hasKey("ScheduleInstanceType"))
						.body("Result[0]", hasKey("StartDateTime"))
						.body("Result[0]", hasKey("SubstituteInstructorName"));
	}
	@Test (testName="AppointmentsNotFound",description="PBI:124124")
	public void AppointmentsNotFound() {
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = "2025-11-13T00:00";
		String eDateTimeNoOffset = "2025-11-14T00:00";

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/appointment/getappointmentsbymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	@Test (testName="InvalidDateRange",description="PBI:124124")
	public void InvalidDateRange() {
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = "2025-11-13T00:02";
		String eDateTimeNoOffset = "2025-11-13T00:01";

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/appointment/getappointmentsbymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(412)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
	
	
}
