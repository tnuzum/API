package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class _Draft_GetOnlineAvailableCoursesByMember extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Courses Found",description="PBI:144257")
	public void coursesFound() { 
		
		int CustomerId = 223;
		int FilterClubId = 1;
		String StartDateTime = ReusableDates.getCurrentDate();
		String EndDateTime = ReusableDates.getCurrentDatePlusFiveYears();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getonlineavailablecoursesbymember/"+CustomerId+"/"+FilterClubId+"/"+StartDateTime+"/"+EndDateTime)
						.then()
						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						;

	}
}
