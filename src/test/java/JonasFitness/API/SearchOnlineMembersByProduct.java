package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchOnlineMembersByProduct extends base {	
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="SearchOnlineMembersByProduct_FirstName",description="PBI:139723")
	public void searchOnlineMembers_FirstName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");  
		String fName = "Fred";

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
	@Test (testName="SearchOnlineMembersByProduct_LastName",description="PBI:139723")
	public void searchOnlineMembers_LastName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id"); 
		String lName = "Auto";

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
	@Test (testName="SearchOnlineMembersByProduct_LastFirstName",description="PBI:139723")
	public void searchOnlineMembers_LastFirstName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id"); 
		String name = "Auto Fred";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+name+"/"+associatedClub+"/"+trainingId)
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
	@Test (testName="SearchOnlineMembersByProduct_FirstLastName",description="PBI:139723")
	public void searchOnlineMembers_FirstLastName() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id"); 
		String name = "Fred Auto";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+name+"/"+associatedClub+"/"+trainingId)
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
	@Test (testName="SearchOnlineMembersByProduct_HomePhoneWithDashes",description="PBI:139723")
	public void searchOnlineMembers_HomePhoneWithDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");
		String phone = "614-200-1000";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phone+"/"+associatedClub+"/"+trainingId)
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
						.body("Result[0].HomePhone", equalTo("6142001000"))
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
	@Test (testName="SearchOnlineMembersByProduct_HomePhoneNoDashes",description="PBI:139723")
	public void searchOnlineMembers_HomePhoneNoDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");  
		String phone = "6142001000";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phone+"/"+associatedClub+"/"+trainingId)
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
						.body("Result[0].HomePhone", equalTo("6142001000"))
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
	@Test (testName="SearchOnlineMembersByProduct_MobilePhoneWithDashes",description="PBI:139723")
	public void searchOnlineMembers_MobilePhoneWithDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");
		String phone = "614-100-1003";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phone+"/"+associatedClub+"/"+trainingId)
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
						.body("Result[0].CellPhone", equalTo("6141001003"))
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
	@Test (testName="SearchOnlineMembersByProduct_MobilePhoneNoDashes",description="PBI:139723")
	public void searchOnlineMembers_MobilePhoneNoDashes() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");
		String phone = "6141001003";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phone+"/"+associatedClub+"/"+trainingId)
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
						.body("Result[0].CellPhone", equalTo("6141001003"))
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
	@Test (testName="SearchOnlineMembersByProduct_WorkPhone",description="PBI:139723")
	public void searchOnlineMembers_WorkPhone() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");
		String phone = "6143001003";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchonlinemembersbyproduct/"+member+"/"+phone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"));
	}
	@Test (testName="SearchOnlineMembersByProduct_Email",description="PBI:139723")
	public void searchOnlineMembers_Email() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		String associatedClub = prop.getProperty("associatedClub1Id");
		String trainingId = prop.getProperty("training34Id");  
		String email = "fred.auto@home.com";

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
}
