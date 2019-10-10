package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;

public class GetClassesAndCoursesByMember {

	@Test
	public void Test1() {
		RestAssured.useRelaxedHTTPSValidation();

		RestAssured.baseURI = ("https://compete-api-future2.test-jfisoftware.com:8252");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/29947/2019-01-01T00:00/2020-12-01T00:00")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
}
