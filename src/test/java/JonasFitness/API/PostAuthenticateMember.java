package JonasFitness.API;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostAuthenticateMember {

	@Test
	public void Test1() {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = ("https://compete-api-future2.test-jfisoftware.com:8252");

		Response res =		
			given()
			.header("accept", "application/json")
			.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
			.header("X-CompanyId", "101")
			.header("X-ClubId", "1")
			.header("Content-Type", "application/json-patch+json")
			.when()
				.body("{"+
						  "\"Username\": \"zauto\","+
						  "\"Password\": \"5669\""+
						"}").
			when()
				.post("/api/v3/member/authenticatememberbyusercredentials").
			then().log().body()
			.assertThat().statusCode(200)
			.time(lessThan(5L),TimeUnit.SECONDS).extract().response();
		
//		String responseString = res.asString();
//		System.out.println(responseString);
		
			
	}
}
