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

public class GetMember extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test
	public void Test1() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
				
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getmember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.Address.AddressLine1", equalTo("7965 N High St"))
						.body("Result.Address.AddressLine2", equalTo("Ste 360"))
					    .body("Result.Address", hasKey("AddressLine1")); //For not present -- .body("$", not(hasKey("age")));
//						.extract().response();

	}
}
