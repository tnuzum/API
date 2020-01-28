package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetAvailableAppointments extends base {

	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="AppointmentsFound_NoResources",description="PBI:127498")
	public void AppointmentsFound_NoResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");  
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();
//		String serviceId = prop.getProperty("service3Id");
		int serviceId = 36;

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemBarcodeId"))
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
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = ReusableDates.getCurrentDate();
		String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneYear();
		int serviceId = 36;
		int resourceId = 18;
		int resourceTypeId = 4;


				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("ResourceTypeId", resourceTypeId)
						.queryParam("ResourceId", resourceId)
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemBarcodeId"))
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
	/*
	@Test (testName="AppointmentsNotFound",description="PBI:127498")
	public void AppointmentsNotFound() {
		!! this test's behavior changed after changing
		 * the Club setting to 'restrict online scheduling to prepaid !!
		 * 
		 * This test shows that the appointment is not found
		 * because the BooksAndAvailability is Null. Date range is
		 * set far in future so no availability is found. 
		 
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = "2025-01-01T00:00";
		String eDateTimeNoOffset = "2025-01-02T00:00";
		
		int serviceId = 215;
//		int resourceId = 40;
//		int resourceTypeId = 1;

				given()
				//.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
//						.queryParam("ResourceTypeId", resourceTypeId)
//						.queryParam("ResourceId", resourceId)
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result", hasKey("ItemId"))
						.body("Result", hasKey("ItemBarcodeId"))
						.body("Result", hasKey("ItemDescription"))
						.body("Result", hasKey("Duration"))
						.body("Result", hasKey("ClubId"))
						.body("Result", hasKey("ClubName"))
						.body("Result", hasKey("Price"))
						.body("Result", hasKey("CustomerHasPunchesForItem"))
						.body("Result", hasKey("AllowOnlineMemberPurchase"))
						.body("Result", hasKey("DividePriceByMembers"))
						.body("Result", hasKey("BooksAndAvailability"))
						.body("Result.BooksAndAvailability[0]", nullValue());
	}*/
	
	@Test (testName="AppointmentsNotFound",description="PBI:127498")
	public void AppointmentsNotFound() {

		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = "2025-01-01T00:00";
		String eDateTimeNoOffset = "2025-01-02T00:00";
		
		int serviceId = 215;

				given()
				//.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Nothing found"));
	}

}
