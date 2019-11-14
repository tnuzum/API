package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetPurchasedPackagesByMember extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="PackagesFound",description="PBI:124125")
	public void PackagesFound() {
		
//		String member = prop.getProperty("activeMember7_CustomerId");
		int member = 224;
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
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
						.body("Result[0]", hasKey("UnitsRemaining"))
						.body("Result[0].InvoiceNumber", not(nullValue()))
						.body("Result[0].PackageName", not(nullValue()))
						.body("Result[0].PackagePrice", not(nullValue()))
						.body("Result[0].PunchcardType", not(nullValue()))
						.body("Result[0].PurchaseDate", not(nullValue()))
						.body("Result[0].SaleClubNumber", not(nullValue()))
						.body("Result[0].SoldInEme", not(nullValue()))
						.body("Result[0].TotalUnitsRedeemed", not(nullValue()))
						.body("Result[0].UnitsPurchased", not(nullValue()))
						.body("Result[0].UnitsRemaining", not(nullValue()));
	}
	@Test (testName="PackagesNotFound",description="PBI:124125")
	public void PackagesNotFound() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
						.body("Result", not(hasKey("ExpirationDate")))
						.body("Result", not(hasKey("InvoiceNumber")))
						.body("Result", not(hasKey("PackageName")))
						.body("Result", not(hasKey("PackagePrice")))
						.body("Result", not(hasKey("PunchcardType")))
						.body("Result", not(hasKey("PurchaseDate")))
						.body("Result", not(hasKey("SaleClubNumber")))
						.body("Result", not(hasKey("SoldInEme")))
						.body("Result", not(hasKey("TotalUnitsRedeemed")))
						.body("Result", not(hasKey("UnitsPurchased")))
						.body("Result", not(hasKey("UnitsRemaining")));
	}
}
