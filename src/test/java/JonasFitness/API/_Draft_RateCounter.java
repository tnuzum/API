package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class _Draft_RateCounter extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (priority=4, description="Rate-counter Limitation")
	public void PBI132893_Test3() {
		//** This is not working because it doesn't always send 3 requests within 1 second
		String member = prop.getProperty("activeMember1_CustomerId");
				for (int i=1; i<5; i++) {
					given()
					.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
						.log().all()
						.assertThat().header("X-Rate-Limit-Limit", "1s");	
				}
						
	}
}
