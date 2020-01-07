package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import java.io.IOException;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetAppointmentsByBook extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="AppointmentsFound",description="PBI:132256")
	public void AppointmentsFound() {
//		String resourceId = prop.getProperty("resource2Id");
		int resourceId = 4;
		String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getappointmentsbybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentId"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey("ClubId"))
						.body("Result[0]", hasKey("ClubName"))
						.body("Result[0]", hasKey("DurationInMinutes"))
						.body("Result[0]", hasKey("ItemBarCodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("ItemLongDescription"))
						.body("Result[0]", hasKey("ProductCategoryDescription"))
						.body("Result[0]", hasKey("RecurringId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookDescription"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookName"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("BarcodeId"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("CustomerId"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("DisplayName"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("Outcome"))
						.body("Result[0]", hasKey("ScheduledDateTime"))
						.body("Result[0]", hasKey("ScheduledInstanceType"))
						.body("Result[0]", hasKey("StartDateTime"));
	}
	@Test (testName="AppointmentsNotFound",description="PBI:132256")
	public void AppointmentsNotFound() {
		String resourceId = prop.getProperty("resource2Id");
		String sDateTimeNoOffset = "2025-11-13T00:00";
		String eDateTimeNoOffset = "2025-11-14T00:00";

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getappointmentsbybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	@Test (testName="InvalidDateRange",description="PBI:132256")
	public void InvalidDateRange() {
		String resourceId = prop.getProperty("resource2Id");
		String sDateTimeNoOffset = "2025-11-13T00:02";
		String eDateTimeNoOffset = "2025-11-13T00:01";

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getappointmentsbybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(412)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
}
