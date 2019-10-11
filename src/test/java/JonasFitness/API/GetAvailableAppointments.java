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
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test
	public void GetAvailableAppointments_WithResources() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
		String serviceId = prop.getProperty("service3Id");
		String resourceId = prop.getProperty("resource6Id");
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
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
}
