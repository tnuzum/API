package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchMembersByProduct extends base{
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	
	}
	@Test (testName="SearchMembersByProduct_FirstName",description="PBI:139726")
	public void searchMembersByProduct_FirstName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String fName = prop.getProperty("activeMember1_fName");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+fName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]",  hasKey("AddressLine1"))
						.body("Result[0]",  hasKey("AddressLine2"))
						.body("Result[0]",  hasKey("BarcodeId"))
						.body("Result[0]",  hasKey("CellPhone"))
						.body("Result[0]",  hasKey("City"))
						.body("Result[0]",  hasKey("Country"))
						.body("Result[0]",  hasKey("DisplayName"))
						.body("Result[0]",  hasKey("EmailAddress"))
						.body("Result[0]",  hasKey("FirstName"))
						.body("Result[0]",  hasKey("HomePhone"))
						.body("Result[0]",  hasKey("Id"))
						.body("Result[0]",  hasKey("LastName"))
						.body("Result[0]",  hasKey("MiddleInitial"))
						.body("Result[0]",  hasKey("PostalCode"))
						.body("Result[0]",  hasKey("PreferredName"))
						.body("Result[0]",  hasKey("PreferredPhoneType"))
						.body("Result[0]",  hasKey("StateProvince"))
						.body("Result[0]",  hasKey("WorkPhone"))
					    .body("Result[0].AddressLine1", not(nullValue()))
					    .body("Result[0].City", not(nullValue()))
					    .body("Result[0].PostalCode", not(nullValue()))
					    .body("Result[0].StateProvince", not(nullValue()))
					    .body("Result[0].BarcodeId", not(nullValue()))
					    .body("Result[0].EmailAddress", not(nullValue()))
					    .body("Result[0].HomePhone", not(nullValue()))
					    .body("Result[0].Id", not(nullValue()))
					    .body("Result[0].DisplayName", not(nullValue()))
					    .body("Result[0].FirstName", not(nullValue()))
					    .body("Result[0].LastName", not(nullValue()))
					    .body("Result[0].PreferredPhoneType", not(nullValue()));
	}
	@Test (testName="SearchMembersByProduct_LastName",description="PBI:139726")
	public void searchMembersByProduct_LastName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id"); 
		String lName = prop.getProperty("activeMember1_lName");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+lName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo("Auto"));

	}
	@Test (testName="SearchMembersByProduct_LastFirstName",description="PBI:139726")
	public void searchMembersByProduct_LastFirstName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id"); 
		String name = "Auto Fred";

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+name+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo("Auto"));
	}
	@Test (testName="SearchMembersByProduct_FirstLastName",description="PBI:139726")
	public void searchMembersByProduct_FirstLastName() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id"); 
		String name = "Fred Auto";

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+name+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo("Auto"));
	}
	@Test (testName="SearchMembersByProduct_HomePhoneWithDashes",description="PBI:139726")
	public void searchMembersByProduct_HomePhoneWithDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String hPhoneD = prop.getProperty("activeMember4_hPhoneD");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhoneD+"/"+associatedClub+"/"+trainingId)
						.then()
//				     	.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo("6142001003"));
	}
	@Test (testName="SearchMembersByProduct_HomePhoneWithoutDashes",description="PBI:139726")
	public void searchMembersByProduct_HomePhoneWithoutDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String hPhone = prop.getProperty("activeMember4_hPhone");
		
				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo("6142001003"));
	}
	@Test (testName="SearchMembersByProduct_MobilePhoneWithoutDashes",description="PBI:139726")
	public void searchMembersByProduct_MobilePhoneWithoutDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String mPhone = "6141001000";
		
				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+mPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].CellPhone", equalTo("6141001000"));
	}
	@Test (testName="SearchMembersByProduct_MobilePhoneWithDashes",description="PBI:139726")
	public void searchMembersByProduct_MobilePhoneWithDashes() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String mPhone = "614-100-1000";
		
				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+mPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].CellPhone", equalTo("6141001000"));
	}
	@Test (testName="SearchMembersByProduct_WorkPhoneWithDashes",description="PBI:139726")
	public void searchMembersByProduct_WorkPhoneWithDashes() {
		/*
		 * Work phone not allowed as search criteria
		 */
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String wPhone = "614-300-1000";
		
				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+wPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						;
	}
	@Test (testName="SearchMembersByProduct_Email",description="PBI:139726")
	public void searchMembersByProduct_Email() {
		
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training24Id");
		String email = prop.getProperty("activeMember4_email");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+email+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0].EmailAddress", equalTo("fred.auto@home.com"));

	}
	
}
