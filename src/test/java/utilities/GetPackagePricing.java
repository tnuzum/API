package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.base;

public class GetPackagePricing extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test ()
	public void itemFound() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				int itemId = 262;
				int quantity = 6;

		given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/package/getpackagepricing/"+customerId+"/"+itemId+"/"+quantity)
						.then()
						.log().body();
	}
	
}
