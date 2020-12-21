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

public class GetBookAvailabilityCalendar extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Resource Calendar Found",description="PBI:140727")
	public void resourceCalendarFound() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
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
	
	@Test (testName="Facility Calendar Found",description="PBI:140727")
	public void facilityCalendarFound() { 

				String bookId = prop.getProperty("pTBook3Id");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
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
						.body("Result[0].BookType", equalTo("Facility"));
	}
	
	@Test (testName="Resource Type Not Required",description="PBI:140727")
	public void resourceTypeNotRequired() { 

				String bookId = prop.getProperty("demoBookId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Calendar Not Found",description="PBI:140727")
	public void calendarNotFound() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = "2125-11-13T00:00";
				String eDateTimeNoOffset = "2125-11-14T00:00";
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
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
						.body("Result[0].IsBookingBasedOnAvailability", equalTo(false));
	}

	@Test (testName="Book Not Found",description="PBI:140727")
	public void bookNotFound() { 

				String bookId = "99999";
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
								
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Start Date Required",description="PBI:140727")
	public void startDateRequired() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = prop.getProperty("nullValue");
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
								
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for StartDateTime."));
	}
	
	@Test (testName="End Date Required",description="PBI:140727")
	public void endDateRequired() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = prop.getProperty("nullValue");
				String associatedClubId = prop.getProperty("club1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
								
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for EndDateTime."));
	}
	
	@Test (testName="Associated Club Required",description="PBI:140727")
	public void associatedClubRequired() { 

				String bookId = prop.getProperty("demoBookId");
				String resourceTypeId = prop.getProperty("resourcePTId");
				String sDateTimeNoOffset = ReusableDates.getCurrentDate();
				String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
				String associatedClubId = prop.getProperty("NOTclub1Id");
		
				given()
	//					.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.queryParam("BookId", bookId)
				.queryParam("ResourceTypeId", resourceTypeId)
					.when()
						.get("api/v3/bookview/getbookavailabilitycalendar/"+associatedClubId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"")
								
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for ClubId."));
	}
	
}
