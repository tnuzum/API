package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetScheduleByMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="ClassFound",description="PBI:124954")
	public void classFound() {
		
				String c = prop.getProperty("standbyCId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey( "CanCancel"))
						.body("Result[0]", hasKey("CanChange"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"));
	}
	
	@Test (testName="AppointmentFound",description="PBI:124954")
	public void appointmentFound() {
		
				String customerId = prop.getProperty("appointmentId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();

				given()

				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey( "CanCancel"))
						.body("Result[0]", hasKey("CanChange"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0]", hasKey ("ForCustomerId"))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"));
	}
	
	@Test (testName="ClassOrAppointmentNotFound",description="PBI:124954")
	public void classNotFound() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = "2119-01-01";
				String eDateTimeNoOffset = "2120-01-01";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
}
