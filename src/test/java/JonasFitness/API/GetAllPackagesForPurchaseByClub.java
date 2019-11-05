package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAllPackagesForPurchaseByClub extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="PackageFound",description="PBI:143540")
	public void PackageFound() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/29947/1")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						;
// assert that a specific package that is available at club is found
	}
	/*@Test (testName="OnlineNotAllowed_PackageFound",description="PBI:143540")
	public void ValidInput() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/29947/1")
						.then()
						.log().body()
//						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);
				// need to assert that a package that is not allowed for MSS purchase is not contained in the response
				// use same package as negative test in getOnlinePackage...
	}
	@Test (testName="PackageNotFound",description="PBI:143540")
	public void PackageNotFound() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/package/getallpackagesforpurchasebyclub/29947/1")
						.then()
						.log().body()
//						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);
// assert that a specific package that is NOT available at club is NOT found
	}*/
}
