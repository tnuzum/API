package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchOnlineMembersByProduct extends base {
	
//---------------------------------------
//	** SEARCH PARAMETERS **	
	String phoneDashes = "614-200-1003";
	String phoneNoDashes = "6142001003";
	String fName = "Fred";
	String lName = "Auto";
	String email = "tnuzum.auto@gmail.com";
//---------------------------------------	
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="SearchMembersByProduct_FirstName",description="PBI:139723")
	public void FirstName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");  

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+fName+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
}
