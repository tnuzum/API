package JonasFitness.API;

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

public class GetAvailableAppointments extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="NoResources",description="PBI:127498")
	public void NoResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");  
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
//		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
		String eDateTimeNoOffset = "2019-11-14T00:00";
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
						.time(lessThan(5L),TimeUnit.SECONDS)
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
	@Test (testName="WithResources",description="PBI:127498")
	public void WithResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
//		String serviceId = prop.getProperty("service3Id");
//		String resourceId = prop.getProperty("resource2Id");
//		String resourceTypeId = prop.getProperty("resourceType2Id");
		int serviceId = 36;
		int resourceId = 18;
		int resourceTypeId = 4;


				given()
				//.log().all()
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
						.time(lessThan(5L),TimeUnit.SECONDS)
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
}
