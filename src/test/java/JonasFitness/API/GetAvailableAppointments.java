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
		
	}
	
	@Test
	public void GetAvailableAppointments_NoResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");  
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
		String serviceId = prop.getProperty("service3Id");
		
		RestAssured.useRelaxedHTTPSValidation();

		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
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
	@Test
	public void GetAvailableAppointments_WithResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
		String serviceId = prop.getProperty("service3Id");
		String resourceId = prop.getProperty("resource2Id");
		String resourceTypeId = prop.getProperty("resourceType2Id");
		
		RestAssured.useRelaxedHTTPSValidation();

		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
				//.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
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
