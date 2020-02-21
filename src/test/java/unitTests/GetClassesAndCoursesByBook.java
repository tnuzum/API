package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetClassesAndCoursesByBook extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="ClassesCoursesFound",description="PBI:140729")
	public void ClassesCoursesFound() {

				String resourceId = prop.getProperty("classBookId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusFiveYears();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbybook/"+resourceId +"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BookedResourcesDTOs"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("BookDescription"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("BookId"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("BookName"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result[0].BookedResourcesDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result[0]", hasKey("ClassCourseEnrollmentStatus"))
						.body("Result[0]", hasKey("ClassCourseId"))
						.body("Result[0]", hasKey("ClubId"))
						.body("Result[0]", hasKey("ClubName"))
						.body("Result[0]", hasKey("DurationInMinutes"))
						.body("Result[0]", hasKey("ItemBarCodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("ItemLongDescription"))
						.body("Result[0]", hasKey("OriginalInstructorName"))
						.body("Result[0]", hasKey("ProductCategoryDescription"))
						.body("Result[0]", hasKey("ScheduledInstanceType"))
						.body("Result[0]", hasKey("StartDateTime"))
						.body("Result[0]", hasKey("SubstituteInstructorName"));
	}
	
	@Test (testName="Classes Courses Not Found",description="PBI:140729")
	public void ClassesCoursesNotFound() {

				String resourceId = prop.getProperty("availableBookId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneDay();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbybook/"+resourceId +"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Classes Courses Not Found",description="PBI:140729")
	public void ClassesCoursesNotFound2() {

				String resourceId = prop.getProperty("availableBookId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDate();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbybook/"+resourceId +"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(412)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
	
	@Test (testName="Invalid ClassCourse",description="PBI:140729")
	public void invalidClassCourse() {

				String resourceId = prop.getProperty("availableId");// using a member Id instead of resource/book
				String sDateTimeNoOffset = ReusableDates.getCurrentDateMinusOneYear();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbybook/"+resourceId +"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Invalid Date Range",description="PBI:140729")
	public void invalidDateRange() {

				String resourceId = prop.getProperty("availableId");// using a member Id instead of resource/book
				String sDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbybook/"+resourceId +"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(412)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Invalid date range"));
	}
}
