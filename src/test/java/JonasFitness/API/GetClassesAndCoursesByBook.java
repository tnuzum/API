package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetClassesAndCoursesByBook extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	@Test (testName="ClassesCoursesFound",description="PBI:140729")
	public void ClassesCoursesFound() {
//		String resourceId = prop.getProperty("resource1Id");
		int resourceId = 13;
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
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
}
