package JonasFitness;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class getbookavailabilitycalendar extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:140727")
	public void ValidInput() { 

		String bookId = prop.getProperty("resource6Id");
		String resourceTypeId = prop.getProperty("resourceType4Id");
		String startTime = prop.getProperty("sDateTimeNoOffset1");
		String endTime = prop.getProperty("eDateTimeNoOffset1");
		String associatedClubId = prop.getProperty("associatedClub3Id");
		
				given()
						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId1"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+startTime+"/"+endTime+"")
								
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("BookDescription"))
						.body("Result[0]", hasKey("BookId"))
						.body("Result[0]", hasKey("BookName"))
						.body("Result[0]", hasKey("BookResourceAvailability"))
						.body("Result[0].BookResourceAvailability[0]", hasKey("Availability"))
						.body("Result[0].BookResourceAvailability[0].Availability[0]", hasKey("End"))
						.body("Result[0].BookResourceAvailability[0].Availability[0]", hasKey("Start"))
						.body("Result[0].BookResourceAvailability[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0].BookResourceAvailability[0]", hasKey("ResourceTypeId"))
						.body("Result[0].BookResourceAvailability[0]", hasKey("ResourceTypeName"))
						.body("Result[0]", hasKey("BookType"))
						.body("Result[0]", hasKey("BookingBasedOnAvailabilityMinutes"))
						.body("Result[0]", hasKey("IsBookingBasedOnAvailability"));


	}
}
