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

public class GetProductByBook extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:138968")
	public void ValidInput() {
		
//		String resourceId = prop.getProperty("resource2Id");
		int resourceId = 32;
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/bookview/getproductbybook/"+resourceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ProductId"))
						.body("Result[0]", hasKey("BarcodeId"))
						.body("Result[0]", hasKey("ProductDescription"))
						.body("Result[0]", hasKey("ProductType"))
						.body("Result[0]", hasKey("ProductLongDescription"))
						.body("Result[0]", hasKey("ProductCategoryId"))
						.body("Result[0]", hasKey("ProductCategoryDescription"))
						.body("Result[0]", hasKey("AllowedStartTimes"))
						.body("Result[0]", hasKey("BookingDuration"))
						.body("Result[0]", hasKey("MemberLimit"));
	}
}
