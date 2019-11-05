package JonasFitness;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchMembersByProduct extends base{
	
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
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	
	}
	@Test (testName="SearchMembersByProduct_FirstName",description="PBI:139726")
	public void SearchMembersByProduct_FirstName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");
		String fName = prop.getProperty("activeMember1_fName");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+fName+"/"+associatedClub+"/"+serviceId)
						.then()
  				    .log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]",  hasKey("AddressLine1"))
						.body("Result[0]",  hasKey("AddressLine2"))
						.body("Result[0]",  hasKey("BarcodeId"))
						.body("Result[0]",  hasKey("CellPhone"))
						.body("Result[0]",  hasKey("City"))
						.body("Result[0]",  hasKey("Country"))
						.body("Result[0]",  hasKey("DisplayName"))
						.body("Result[0]",  hasKey("EmailAddress"))
						.body("Result[0]",  hasKey("FirstName"))
						.body("Result[0].FirstName", equalTo("Robert"))
						.body("Result[0]",  hasKey("HomePhone"))
						.body("Result[0]",  hasKey("Id"))
						.body("Result[0]",  hasKey("LastName"))
						.body("Result[0]",  hasKey("MiddleInitial"))
						.body("Result[0]",  hasKey("PostalCode"))
						.body("Result[0]",  hasKey("PreferredName"))
						.body("Result[0]",  hasKey("PreferredPhoneType"))
						.body("Result[0]",  hasKey("StateProvince"))
						.body("Result[0]",  hasKey("WorkPhone"));

	}
	@Test (testName="SearchMembersByProduct_LastName",description="PBI:139726")
	public void SearchMembersByProduct_LastName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id"); 
		String lName = prop.getProperty("activeMember1_lName");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+lName+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo("Auto"));

	}
	@Test (testName="SearchMembersByProduct_HomePhoneWithDashes",description="PBI:139726")
	public void SearchMembersByProduct_HomePhoneWithDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");
		String hPhoneD = prop.getProperty("activeMember4_hPhoneD");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhoneD+"/"+associatedClub+"/"+serviceId)
						.then()
//				     	.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo("6142001003"));

	}
	@Test (testName="SearchMembersByProduct_HomePhoneWithoutDashes",description="PBI:139726")
	public void SearchMembersByProduct_HomePhoneWithoutDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");
		String hPhone = prop.getProperty("activeMember4_hPhone");
		
				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhone+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo("6142001003"));

	}
	@Test (testName="SearchMembersByProduct_Email",description="PBI:139726")
	public void SearchMembersByProduct_Email() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String serviceId = prop.getProperty("service2Id");
		String email = prop.getProperty("activeMember4_email");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+email+"/"+associatedClub+"/"+serviceId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].EmailAddress", equalTo("tnuzum.auto@gmail.com"));

	}
}
