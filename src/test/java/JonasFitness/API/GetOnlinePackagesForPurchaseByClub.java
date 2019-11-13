package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetOnlinePackagesForPurchaseByClub extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="PackagesFound",description="PBI:143537")
	public void PackagesFound() { 
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String club = prop.getProperty("X-Club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getonlinepackagesforpurchasebyclub/"+member+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						;
	}
	@Test (testName="PackageNotAllowed",description="PBI:143537")
	public void PackageNotAllowed() { 
		
// this is not found because the item is not allowed for MSS (online) purchase
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String club = prop.getProperty("X-Club1Id");
		
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getonlinepackagesforpurchasebyclub/"+member+"/"+club)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						;
// need to assert that a package that is not allowed for MSS purchase is not contained in the response
	}
}
