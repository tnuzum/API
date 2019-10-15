package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchMembersByProduct extends base{
	
//---------------------------------------
//	** SEARCH PARAMETERS **	
	String phoneDashes = "614-200-1003";
	String phoneNoDashes = "6142001003";
	String fName = "Fred";
	String lName = "Auto";
	String email = "tnuzum.auto@gmail.com";
	/*
	 * LastName
	 * FirstName
	 * LastFirstName
	 * HomePhoneDashes
	 * HomePhoneNoDashes
	 * MobilePhoneDashes
	 * MobilePhoneNoDashes
	 * WorkPhoneDashes
	 * WorkPhoneNoDashes
	 * Email
	 */
	
//---------------------------------------	
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	
	}
	@Test (description="PBI:139726")
	public void SearchMembersByProduct_FirstName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+fName+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test (description="PBI:139726")
	public void SearchMembersByProduct_LastName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");  

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+lName+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test (description="PBI:139726")
	public void SearchMembersByProduct_PhoneWithDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+phoneDashes+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test (description="PBI:139726")
	public void SearchMembersByProduct_PhoneWithoutDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");
		
				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+phoneNoDashes+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
	@Test (description="PBI:139726")
	public void SearchMembersByProduct_Email() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+email+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS);

	}
}
