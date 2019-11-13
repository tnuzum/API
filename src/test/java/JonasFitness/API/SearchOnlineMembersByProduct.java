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
import resources.base;

public class SearchOnlineMembersByProduct extends base {
	
//---------------------------------------
//	** SEARCH PARAMETERS **	
	String phoneDashes = "614-200-1003";
	String phoneNoDashes = "6142001003";
	String fName = "Fred";
	String lName = "Auto";
	String email = "fred.auto@home.com";
//---------------------------------------	
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="SearchMembersByProduct_FirstName",description="PBI:139723")
	public void searchOnlineMembers_FirstName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");  

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+fName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
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
						.body("Result[0].FirstName", equalTo("Fred"))
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
	@Test (testName="SearchMembersByProduct_LasttName",description="PBI:139723")
	public void searchOnlineMembers_LastName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");   

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+lName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
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
						.body("Result[0].LastName", equalTo("Auto"))
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
	@Test (testName="SearchMembersByProduct_Email",description="PBI:139723")
	public void searchOnlineMembers_Email() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");   

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+email+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
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
						.body("Result[0].EmailAddress", equalTo("fred.auto@home.com"))
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
	
	@Test (testName="SearchMembersByProduct_PhoneWDashes",description="PBI:139723")
	public void searchOnlineMembers_PhoneWDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");   

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phoneDashes+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
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
						.body("Result[0].HomePhone", equalTo("6142001003"))
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
	
	@Test (testName="SearchMembersByProduct_PhoneNoDashes",description="PBI:139723")
	public void searchOnlineMembers_PhoneNoDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");   

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phoneNoDashes+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
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
						.body("Result[0].HomePhone", equalTo("6142001003"))
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
}
