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

public class GetPurchasedPackagesByMember extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="ValidInput",description="PBI:124125")
	public void ValidInput() {
		
		String member = prop.getProperty("activeMember1_CustomerId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("InvoiceNumber"))
						.body("Result[0]", hasKey("PackageName"))
						.body("Result[0]", hasKey("PackagePrice"))
						.body("Result[0]", hasKey("PunchcardType"))
						.body("Result[0]", hasKey("PurchaseDate"))
						.body("Result[0]", hasKey("SaleClubNumber"))
						.body("Result[0]", hasKey("SoldInEme"))
						.body("Result[0]", hasKey("TotalUnitsRedeemed"))
						.body("Result[0]", hasKey("UnitsPurchased"))
						.body("Result[0]", hasKey("UnitsRemaining"));
	}
}
