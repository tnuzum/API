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
	
	@Test (testName="ClassFound - Enrolled",description="PBI:124954")
	public void classFoundEnrolled() {
		
				String c = prop.getProperty("standbyCId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-12-30";
				String eDateTimeNoOffset = "2026-01-01";

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
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("InvoiceDetailId"))
						.body("Result[0]", hasKey ("IsCancellationAllowed"))
						.body("Result[0]", hasKey ("IsChangeAllowed"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("IsVirtual"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("ItemEndDateTime"))
						.body("Result[0]", hasKey ("ItemStartDateTime"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"))
						.body("Result[0].ClassEnrollmentStatusDte", equalTo("Enrolled"))
						.body("Result[0].ScheduleInstanceType", equalTo("Class"));
	}
	
	@Test (testName="ClassFound - Standby",description="PBI:124954")
	public void classFoundStandby() {
		
				String c = prop.getProperty("standbyDId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-12-30";
				String eDateTimeNoOffset = "2026-01-01";

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
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0].ClassEnrollmentStatusDte", equalTo("Standby"))
						.body("Result[0].ScheduleInstanceType", equalTo("Class"));
	}
	
	@Test (testName="CourseFound - Enrolled",description="PBI:124954")
	public void courseFoundEnrolled() {
		
				String c = prop.getProperty("standbyAId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-12-30";
				String eDateTimeNoOffset = "2026-01-01";

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
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("InvoiceDetailId"))
						.body("Result[0]", hasKey ("IsCancellationAllowed"))
						.body("Result[0]", hasKey ("IsChangeAllowed"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("IsVirtual"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("ItemEndDateTime"))
						.body("Result[0]", hasKey ("ItemStartDateTime"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"))
						.body("Result[0].ClassEnrollmentStatusDte", equalTo("Enrolled"))
						.body("Result[0].ScheduleInstanceType", equalTo("Course"));
	}
	
	@Test (testName="CourseFound - Standby",description="PBI:124954")
	public void courseFoundStandby() {
		
				String c = prop.getProperty("standbyBId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-12-30";
				String eDateTimeNoOffset = "2026-01-01";

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
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0].ClassEnrollmentStatusDte", equalTo("Standby"))
						.body("Result[0].ScheduleInstanceType", equalTo("Course"));
	}
	
	@Test (testName="Training Found",description="PBI:124954", enabled = false) // disabled due to bug
	public void trainingFound() {

				String c = prop.getProperty("appointmentId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-03-24";
				String eDateTimeNoOffset = "2025-04-01";

				given()
				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/schedule/getschedulebymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("InvoiceDetailId"))
						.body("Result[0]", hasKey ("IsCancellationAllowed"))
						.body("Result[0]", hasKey ("IsChangeAllowed"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("IsVirtual"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("ItemEndDateTime"))
						.body("Result[0]", hasKey ("ItemStartDateTime"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"))
						.body("Result[0].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[0].ScheduleInstanceType", equalTo("Training"));
	}
	
	@Test (testName="Service Found",description="PBI:124954")
	public void serviceFound() {
		
				String c = prop.getProperty("appointmentId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-03-23";
				String eDateTimeNoOffset = "2025-03-25";

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
						.body("Result[0].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[0].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[0].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[0].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DaysOfWeek"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0]", hasKey ("ForCustomerId"))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("InvoiceDetailId"))
						.body("Result[0]", hasKey ("IsCancellationAllowed"))
						.body("Result[0]", hasKey ("IsChangeAllowed"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("IsVirtual"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[0].IsRecurring", equalTo(true))
						.body("Result[0].ScheduleInstanceType", equalTo("Visit"));
	}
	
	@Test (testName="Recurring Service Found",description="PBI:124954")
	public void recurringServiceFound() {
		
				String c = prop.getProperty("appointmentId");
				int customerId = Integer.parseInt(c);
				String sDateTimeNoOffset = "2025-04-01";
				String eDateTimeNoOffset = "2025-06-01";

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
						.body("Result[0].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[0].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[0].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[0].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey ("CategoryDescription"))
						.body("Result[0]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey ("ClubName"))
						.body("Result[0]", hasKey ("ClubNumber"))
						.body("Result[0]", hasKey ("DaysOfWeek"))
						.body("Result[0]", hasKey ("DurationInMinutes"))
						.body("Result[0]", hasKey ("ForCustomerId"))
						.body("Result[0]", hasKey ("Id"))
						.body("Result[0]", hasKey ("InvoiceDetailId"))
						.body("Result[0]", hasKey ("IsCancellationAllowed"))
						.body("Result[0]", hasKey ("IsChangeAllowed"))
						.body("Result[0]", hasKey ("IsRecurring"))
						.body("Result[0]", hasKey ("IsVirtual"))
						.body("Result[0]", hasKey ("ItemBarcodeId"))
						.body("Result[0]", hasKey ("ItemDescription"))
						.body("Result[0]", hasKey ("LongDescription"))
						.body("Result[0]", hasKey ("OriginalInstructorName"))
						.body("Result[0]", hasKey ("ScheduleDateTime"))
						.body("Result[0]", hasKey ("ScheduleInstanceType"))
						.body("Result[0]", hasKey ("StartDateTime"))
						.body("Result[0]", hasKey ("SubstituteInstructorName"))
						.body("Result[0].ForCustomerId", equalTo(customerId))
						.body("Result[0].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[0].IsRecurring", equalTo(true))
						.body("Result[0].ScheduleInstanceType", equalTo("Visit"))
						
						.body("Result[1]", hasKey("AppointmentMembers"))
						.body("Result[1]", hasKey("AppointmentNotes"))
						.body("Result[1]", hasKey("BookedMembers"))
						.body("Result[1].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[1].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[1].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[1].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[1]", hasKey("BookedResources"))
						.body("Result[1]", hasKey("CancellationDateTime"))
						.body("Result[1]", hasKey ("CategoryDescription"))
						.body("Result[1]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[1]", hasKey ("ClubName"))
						.body("Result[1]", hasKey ("ClubNumber"))
						.body("Result[1]", hasKey ("DaysOfWeek"))
						.body("Result[1]", hasKey ("DurationInMinutes"))
						.body("Result[1]", hasKey ("ForCustomerId"))
						.body("Result[1]", hasKey ("Id"))
						.body("Result[1]", hasKey ("InvoiceDetailId"))
						.body("Result[1]", hasKey ("IsCancellationAllowed"))
						.body("Result[1]", hasKey ("IsChangeAllowed"))
						.body("Result[1]", hasKey ("IsRecurring"))
						.body("Result[1]", hasKey ("IsVirtual"))
						.body("Result[1]", hasKey ("ItemBarcodeId"))
						.body("Result[1]", hasKey ("ItemDescription"))
						.body("Result[1]", hasKey ("LongDescription"))
						.body("Result[1]", hasKey ("OriginalInstructorName"))
						.body("Result[1]", hasKey ("ScheduleDateTime"))
						.body("Result[1]", hasKey ("ScheduleInstanceType"))
						.body("Result[1]", hasKey ("StartDateTime"))
						.body("Result[1]", hasKey ("SubstituteInstructorName"))
						.body("Result[1].ForCustomerId", equalTo(customerId))
						.body("Result[1].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[1].IsRecurring", equalTo(true))
						.body("Result[1].ScheduleInstanceType", equalTo("Visit"))
						
						.body("Result[2]", hasKey("AppointmentMembers"))
						.body("Result[2]", hasKey("AppointmentNotes"))
						.body("Result[2]", hasKey("BookedMembers"))
						.body("Result[2].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[2].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[2].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[2].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[2]", hasKey("BookedResources"))
						.body("Result[2]", hasKey("CancellationDateTime"))
						.body("Result[2]", hasKey ("CategoryDescription"))
						.body("Result[2]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[2]", hasKey ("ClubName"))
						.body("Result[2]", hasKey ("ClubNumber"))
						.body("Result[2]", hasKey ("DaysOfWeek"))
						.body("Result[2]", hasKey ("DurationInMinutes"))
						.body("Result[2]", hasKey ("ForCustomerId"))
						.body("Result[2]", hasKey ("Id"))
						.body("Result[2]", hasKey ("InvoiceDetailId"))
						.body("Result[2]", hasKey ("IsCancellationAllowed"))
						.body("Result[2]", hasKey ("IsChangeAllowed"))
						.body("Result[2]", hasKey ("IsRecurring"))
						.body("Result[2]", hasKey ("IsVirtual"))
						.body("Result[2]", hasKey ("ItemBarcodeId"))
						.body("Result[2]", hasKey ("ItemDescription"))
						.body("Result[2]", hasKey ("LongDescription"))
						.body("Result[2]", hasKey ("OriginalInstructorName"))
						.body("Result[2]", hasKey ("ScheduleDateTime"))
						.body("Result[2]", hasKey ("ScheduleInstanceType"))
						.body("Result[2]", hasKey ("StartDateTime"))
						.body("Result[2]", hasKey ("SubstituteInstructorName"))
						.body("Result[2].ForCustomerId", equalTo(customerId))
						.body("Result[2].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[2].IsRecurring", equalTo(true))
						.body("Result[2].ScheduleInstanceType", equalTo("Visit"))
						
						.body("Result[3]", hasKey("AppointmentMembers"))
						.body("Result[3]", hasKey("AppointmentNotes"))
						.body("Result[3]", hasKey("BookedMembers"))
						.body("Result[3].BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result[3].BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result[3].BookedMembers[0]", hasKey("DisplayName"))
						.body("Result[3].BookedMembers[0]", hasKey("ExternalId"))
						.body("Result[3]", hasKey("BookedResources"))
						.body("Result[3]", hasKey("CancellationDateTime"))
						.body("Result[3]", hasKey ("CategoryDescription"))
						.body("Result[3]", hasKey ("ClassEnrollmentStatusDte"))
						.body("Result[3]", hasKey ("ClubName"))
						.body("Result[3]", hasKey ("ClubNumber"))
						.body("Result[3]", hasKey ("DaysOfWeek"))
						.body("Result[3]", hasKey ("DurationInMinutes"))
						.body("Result[3]", hasKey ("ForCustomerId"))
						.body("Result[3]", hasKey ("Id"))
						.body("Result[3]", hasKey ("InvoiceDetailId"))
						.body("Result[3]", hasKey ("IsCancellationAllowed"))
						.body("Result[3]", hasKey ("IsChangeAllowed"))
						.body("Result[3]", hasKey ("IsRecurring"))
						.body("Result[3]", hasKey ("IsVirtual"))
						.body("Result[3]", hasKey ("ItemBarcodeId"))
						.body("Result[3]", hasKey ("ItemDescription"))
						.body("Result[3]", hasKey ("LongDescription"))
						.body("Result[3]", hasKey ("OriginalInstructorName"))
						.body("Result[3]", hasKey ("ScheduleDateTime"))
						.body("Result[3]", hasKey ("ScheduleInstanceType"))
						.body("Result[3]", hasKey ("StartDateTime"))
						.body("Result[3]", hasKey ("SubstituteInstructorName"))
						.body("Result[3].ForCustomerId", equalTo(customerId))
						.body("Result[3].BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result[3].IsRecurring", equalTo(true))
						.body("Result[3].ScheduleInstanceType", equalTo("Visit"))
						;
	}
	
	@Test (testName="Schedule Not Found",description="PBI:124954")
	public void scheduleNotFound() {
		
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
	
	@Test (testName="Customer Not Found",description="PBI:124954")
	public void customerNotFound() {
		
				String customerId = "99999";
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
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
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Customer Required",description="PBI:124954")
	public void customerRequired() {
		
				String customerId = prop.getProperty("NOTavailableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
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
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for CustomerId."));
	}
	
	@Test (testName="Start Date Required",description="PBI:124954")
	public void startDateRequired() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = prop.getProperty("nullValue");
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneDay();

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
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for StartDateTime."));
	}
	
	@Test (testName="End Date Required",description="PBI:124954")
	public void endDateRequired() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = prop.getProperty("nullValue");

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
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for EndDateTime."));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
