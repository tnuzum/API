package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetClassesAndCoursesByMember extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ClassesFound",description="PBI:124953")
	public void ClassesFound() {
		String customerId = prop.getProperty("standbyAId");
		String sDateTimeNoOffset = ReusableDates.getCurrentDatePlusFiveYears();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
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
	
	@Test (testName="CoursesFound",description="PBI:124953")
	public void CoursesFound() {
		String customerId = prop.getProperty("standbyCId");
		String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusTenYears();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentMembers"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("BookedMembers"))
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
	
	@Test (testName="Classes Courses Not Found",description="PBI:124953")
	public void ClassesCoursesNotFound() {
//		String customerId = prop.getProperty("activeMember5_CustomerId");
		String customerId = prop.getProperty("availableId");
		String sDateTimeNoOffset = "2119-01-01";
		String eDateTimeNoOffset = "2120-01-01";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
;
	}
	
	@Test (testName="Class Not Found",description="PBI:124953")
	public void invalidClassCourse() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = "2200-01-01";
				String eDateTimeNoOffset = "2200-01-02";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Invalid Date Range",description="PBI:124953")
	public void invalidDateRange() {
		
				String customerId = prop.getProperty("availableId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
}
