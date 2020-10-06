package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetPurchasedPackagesByMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="Packages Found",description="PBI:124125")
	public void PackagesFound() {
		
				String customerId = prop.getProperty("availableId");
				
				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(60L),TimeUnit.SECONDS)
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
	
	@Test (testName="Package Not Found",description="PBI:124125")
	public void packageNotFound() {
		
				String customerId = "11";
				
				given()

				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:124125")
	public void customerNotFound() {
		
				String customerId = "99999";
				
				given()

				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"));
	}
	
	@Test (testName="Customer Required",description="PBI:124125")
	public void customerRequired() {
		
				String customerId = prop.getProperty("nullValue");
				
				given()

				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpurchasedpackagesbymember/"+customerId)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid."));
	}
}
