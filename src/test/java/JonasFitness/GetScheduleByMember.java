package JonasFitness;

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
import resources.ReusableMethods;
import resources.base;

public class GetScheduleByMember extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="ValidInput",description="PBI:124954")
	public void ValidInput() {
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");


				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/schedule/getschedulebymember/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
}
