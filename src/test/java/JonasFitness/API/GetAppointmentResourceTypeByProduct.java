package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetAppointmentResourceTypeByProduct extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test
	public void GetAppointmentResourceTypeByProduct_ProductFound() {
		
		String service = prop.getProperty("service1Id");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/bookview/getappointmentresourcetypebyproduct/"+service)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.ItemHasSelectableResourceTypes",equalTo(true))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("Id"))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("Name"))
						.body("Result.PrimarySelectableResourceType.Books[0]", hasKey("ResourceTypeId"));
	}
	@Test
	public void etAppointmentResourceTypeByProduct_ProductNotFound() {
		
		String service = prop.getProperty("service3Id");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/bookview/getappointmentresourcetypebyproduct/9"+service) // '9' is passed to make Product Category Id = not on file
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.ItemHasSelectableResourceTypes",equalTo(false))
						.body("Result.PrimarySelectableResourceType.Books[0]", nullValue())
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("Id")))
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("Name")))
						.body("Result.PrimarySelectableResourceType.Books[0]", not(hasKey("ResourceTypeId")));


	}
}
