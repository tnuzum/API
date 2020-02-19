package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetBookAvailabilityCalendar extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Book Availability Calendar Found",description="PBI:140727")
	public void bookAvailabilityCalendarFound() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
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
						.time(lessThan(60L),TimeUnit.SECONDS)
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
