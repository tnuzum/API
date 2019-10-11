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

public class GetAvailableResourceTypesByClub extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test
	public void Test1() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/bookview/getavailableresourcetypesbyclub")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0]", hasKey("ResourceTypeId"))
						.body("Result[0]", hasKey("ResourceTypeName"))
						.body("Result[1]", hasKey("ResourceTypeDescription"))
						.body("Result[1]", hasKey("ResourceTypeId"))
						.body("Result[1]", hasKey("ResourceTypeName"))
						.body("Result[2]", hasKey("ResourceTypeDescription"))
						.body("Result[2]", hasKey("ResourceTypeId"))
						.body("Result[2]", hasKey("ResourceTypeName"));
	}
}
