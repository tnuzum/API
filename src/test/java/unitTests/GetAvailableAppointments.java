package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetAvailableAppointments extends base {
	
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
	
	@Test (testName="AppointmentsFound_NoResources",description="PBI:127498")
	public void AppointmentsFound_NoResources() {
		
		String member = prop.getProperty("availableId");  
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneMonth();
		String serviceId = prop.getProperty("freeTId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("Duration"))
						.body("Result", hasKey("ClubId"))
						.body("Result", hasKey("ClubName"))
						.body("Result", hasKey("Price"))
						.body("Result", hasKey("CustomerHasPunchesForItem"))
						.body("Result", hasKey("AllowOnlineMemberPurchase"))
						.body("Result", hasKey("DividePriceByMembers"))
						.body("Result", hasKey("BooksAndAvailability"))
						.body("Result.BooksAndAvailability[0]", hasKey("Books"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("Id"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("Name"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("ResourceTypeId"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("AssignedResourceId"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("IsAssignedResourceSelectable"))
						.body("Result.BooksAndAvailability[0]", hasKey("StartingTimes"));
	}
	
	@Test (testName="AppointmentsFound_WithResourcess",description="PBI:127498")
	public void AppointmentsFound_WithResources() {
		
		String member = prop.getProperty("availableId");  
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneWeek();
		String serviceId = prop.getProperty("freeTId");
		String resourceId = prop.getProperty("demoBookId");
		String resourceTypeId = prop.getProperty("resourcePTId");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
						.queryParam("ResourceTypeId", resourceTypeId)
						.queryParam("ResourceId", resourceId)
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("Duration"))
						.body("Result", hasKey("ClubId"))
						.body("Result", hasKey("ClubName"))
						.body("Result", hasKey("Price"))
						.body("Result", hasKey("CustomerHasPunchesForItem"))
						.body("Result", hasKey("AllowOnlineMemberPurchase"))
						.body("Result", hasKey("DividePriceByMembers"))
						.body("Result", hasKey("BooksAndAvailability"))
						.body("Result.BooksAndAvailability[0]", hasKey("Books"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("Id"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("Name"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("ResourceTypeId"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("AssignedResourceId"))
						.body("Result.BooksAndAvailability[0].Books[0]", hasKey("IsAssignedResourceSelectable"))
						.body("Result.BooksAndAvailability[0]", hasKey("StartingTimes"));
	}
	
	@Test (testName="AppointmentsNotFound",description="PBI:127498")
	public void AppointmentsNotFound() {

		String member = prop.getProperty("noPunchesId");
		String sDateTimeNoOffset = "2025-01-01T00:00";
		String eDateTimeNoOffset = "2025-01-02T00:00";
		String serviceId = prop.getProperty("freePId");

				given()
				//.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Nothing found"));
	}

}
