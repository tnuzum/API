package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetScheduleByBook extends base{

	@BeforeTest
	public void getData() {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="GetClasses",description="PBI:140730")
	public void GetClasses() {
//		String resourceId = prop.getProperty("resource3Id"); 
		int resourceId = 32; 
		String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("ScheduledClassesCourses"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("BookedResourcesDTOs"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("BookDescription"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("BookId"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("BookName"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result.ScheduledClassesCourses[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ClassCourseEnrollmentStatus"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ClassCourseId"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ClubName"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("DurationInMinutes"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ItemBarCodeId"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ItemDescription"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ItemLongDescription"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("OriginalInstructorName"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ProductCategoryDescription"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("ScheduledInstanceType"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("StartDateTime"))
						.body("Result.ScheduledClassesCourses[0]", hasKey("SubstituteInstructorName"))
						.body("Result", hasKey("ScheduledAppointments"))
						.body("Result.ScheduledAppointments[0]", hasKey("AppointmentId"))
						.body("Result.ScheduledAppointments[0]", hasKey("AppointmentNotes"))
						.body("Result.ScheduledAppointments[0]", hasKey("CancellationDateTime"))
						.body("Result.ScheduledAppointments[0]", hasKey("ClubId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ClubName"))
						.body("Result.ScheduledAppointments[0]", hasKey("DurationInMinutes"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemBarCodeId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemLongDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("ProductCategoryDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("RecurringId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledAppointmentBookDTOs"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookDescription"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookName"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledAppointmentMemberDTOs"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("BarcodeId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("CustomerId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("DisplayName"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("Outcome"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledDateTime"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledInstanceType"))
						.body("Result.ScheduledAppointments[0]", hasKey("StartDateTime"));

	}
	@Test (testName="ClassesNotFound",description="PBI:140730")
	public void ClassesNotFound() {
//		String resourceId = prop.getProperty("resource3Id"); 
		int resourceId = 32; 
		String sDateTimeNoOffset = "2119-01-01";
		String eDateTimeNoOffset = "2120-01-01";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
;
	}
	@Test (testName="GetAppointments",description="PBI:140730")
	public void GetAppointments() {
//		String resourceId = prop.getProperty("resource3Id"); 
		int resourceId = 34; 
		String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("ScheduledAppointments"))
						.body("Result.ScheduledAppointments[0]", hasKey("AppointmentId"))
						.body("Result.ScheduledAppointments[0]", hasKey("AppointmentNotes"))
						.body("Result.ScheduledAppointments[0]", hasKey("CancellationDateTime"))
						.body("Result.ScheduledAppointments[0]", hasKey("ClubId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ClubName"))
						.body("Result.ScheduledAppointments[0]", hasKey("DurationInMinutes"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemBarCodeId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("ItemLongDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("ProductCategoryDescription"))
						.body("Result.ScheduledAppointments[0]", hasKey("RecurringId"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledAppointmentBookDTOs"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookDescription"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookName"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledAppointmentMemberDTOs"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("BarcodeId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("CustomerId"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("DisplayName"))
						.body("Result.ScheduledAppointments[0].ScheduledAppointmentMemberDTOs[0]", hasKey("Outcome"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledDateTime"))
						.body("Result.ScheduledAppointments[0]", hasKey("ScheduledInstanceType"))
						.body("Result.ScheduledAppointments[0]", hasKey("StartDateTime"));

	}
	@Test (testName="AppointmentsNotFound",description="PBI:140730")
	public void AppointmentsNotFound() {
//		String resourceId = prop.getProperty("resource3Id"); 
		int resourceId = 34; 
		String sDateTimeNoOffset = "2119-01-01";
		String eDateTimeNoOffset = "2120-01-01";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
;
	}
	}
