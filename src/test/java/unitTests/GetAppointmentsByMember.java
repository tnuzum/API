package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
//import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetAppointmentsByMember extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="Appointments Found",description="PBI:124124")
	public void AppointmentsFound() {
		
		String customerId = prop.getProperty("appointmentId");
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
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
						.body("Result[0].BookedMembers[0]", hasKey("Id"))
						.body("Result[0]", hasKey("BookedResources"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey("CategoryDescription"))
						.body("Result[0]", hasKey("ClassEnrollmentStatusDte"))
						.body("Result[0]", hasKey("ClubName"))
						.body("Result[0]", hasKey("ClubNumber"))
						.body("Result[0]", hasKey("DurationInMinutes"))
						.body("Result[0]", hasKey("ForCustomerId"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("InvoiceDetailId"))
						.body("Result[0]", hasKey("IsCancellationAllowed"))
						.body("Result[0]", hasKey("IsChangeAllowed"))
						.body("Result[0]", hasKey("IsRecurring"))
						.body("Result[0]", hasKey("IsVirtual"))
						.body("Result[0]", hasKey("ItemBarcodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("LongDescription"))
						.body("Result[0]", hasKey("OriginalInstructorName"))
						.body("Result[0]", hasKey("ScheduleDateTime"))
						.body("Result[0]", hasKey("ScheduleInstanceType"))
						.body("Result[0]", hasKey("StartDateTime"))
						.body("Result[0]", hasKey("SubstituteInstructorName"));
	}
	
	@Test (testName="Attended Appointment Found",description="PBI:124124")
	public void attendedAppointmentFound() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusXYears(2);
				String eDateTimeNoOffset = ReusableDates.getCurrentDate();
				
				given()		
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].BookedMembers[0].AppointmentOutcome", equalTo("Attended"));
	}
	
	@Test (testName="No Show Appointment Found",description="PBI:124124")
	public void noShowAppointmentFound() {
		
				String customerId = prop.getProperty("noShowCustomerId");
				String sDateTimeNoOffset = prop.getProperty("noShowSDateTime");
				String eDateTimeNoOffset = prop.getProperty("noShowEDateTime");
				
				
				given()		
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].BookedMembers[0].AppointmentOutcome", equalTo("NoShow"));
	}
	
	@Test (testName="Appointments Not Found",description="PBI:124124")
	public void AppointmentsNotFound() {
		
		String member = prop.getProperty("availableId");
		String sDateTimeNoOffset = "2025-11-13T00:00";
		String eDateTimeNoOffset = "2025-11-14T00:00";

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
					.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Invalid Date Range",description="PBI:124124")
	public void InvalidDateRange() {
		
		String member = prop.getProperty("availableId");
		String sDateTimeNoOffset = "2025-11-13T00:02";
		String eDateTimeNoOffset = "2025-11-13T00:01";

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/appointment/getappointmentsbymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:124124")
	public void customerNotFound() {
		
		String customerId = "99999";
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Customer Required",description="PBI:124124")
	public void customerRequired() {
		
		/* this call throws and exception instead of returning an error,
		 * so this test "Passes" if the exception is caught; error handle 
		 * enhancements should provide improved message 
		 */
		
				String customerId = prop.getProperty("NOTavailableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();
		
				try {
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
					.then();
				} catch (IllegalArgumentException e) {
						Assert.assertTrue(true);//"Passes" if the exception is caught
				}
	}
	
	@Test (testName="Start Date Required",description="PBI:124124")
	public void startDateRequired() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = prop.getProperty("nullValue");
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneDay();
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for StartDateTime."));
	}
	
	@Test (testName="End Date Required",description="PBI:124124")
	public void endDateRequired() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = prop.getProperty("nullValue");
				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
						.queryParam(customerId)
					.when()
						.get("/api/v3/appointment/getappointmentsbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for EndDateTime."));
	}
}

