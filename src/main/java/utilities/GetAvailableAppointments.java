package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class GetAvailableAppointments extends base {
	
	static int member = 248;
	static int itemId = 215;
	static int resourceId = 40;
	static int resourceTypeId = 1;
	static String sDateTimeNoOffset = ReusableDates.getCurrentDate();
	static String eDateTimeNoOffset = ReusableDates.getCurrentDatePlusOneWeek();

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="AppointmentsFound_NoResources",description="PBI:127498")
	public void AppointmentsFound_NoResources() {

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+itemId)
						.then()
//						.log().body()
						;
	}
	
	@Test (testName="AppointmentsFound_WithResourcess",description="PBI:127498")
	public void AppointmentsFound_WithResources() {

				Response res = given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
//						.queryParam("ResourceTypeId", resourceTypeId) // only used if resource type is selectable
						.queryParam("ResourceId", resourceId)
					.when()
					.get("/api/v3/appointment/getavailableappointments/"+member+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset+"/"+itemId)
						.then()
//						.log().body()
						.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				String dateTime = js.get("Result.BooksAndAvailability[0].StartingTimes[0]");
				System.out.println(dateTime);
	}
	
	
}
