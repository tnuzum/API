package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetBookAvailabilityCalendar extends base {
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:140727")
	public void ValidInput() { 

//		String bookId = prop.getProperty("resource6Id");
		int bookId = 31;
//		String resourceTypeId = prop.getProperty("resourceType4Id");
		int resourceTypeId = 9;
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneDay();
		String associatedClubId = prop.getProperty("associatedClub1Id");
		
				given()
	//					.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
								
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
