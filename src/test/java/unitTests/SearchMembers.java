package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class SearchMembers extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="SearchMembers_LastName",description="PBI:124130")
	public void searchMembers_LastName() {
		
				String lName = prop.getProperty("memberSearchLName");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("Name", lName)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"))
					    .body("Result[0].Address.AddressLine1", not(nullValue()))
					    .body("Result[0].Address.City", not(nullValue()))
					    .body("Result[0].Address.Country", nullValue())
					    .body("Result[0].Address.PostalCode", not(nullValue()))
					    .body("Result[0].Address.StateProvince", not(nullValue()))
					    .body("Result[0].BarcodeId", not(nullValue()))
					    .body("Result[0].EmailAddress", not(nullValue()))
					    .body("Result[0].HomePhone.Number", not(nullValue()))
					    .body("Result[0].HomePhone.PhoneType", not(nullValue()))
					    .body("Result[0].Id", not(nullValue()))
					    .body("Result[0].Name.DisplayName", not(nullValue()))
					    .body("Result[0].Name.FirstName", not(nullValue()))
					    .body("Result[0].Name.LastName", not(nullValue()))
					    .body("Result[0].PreferredPhone", not(nullValue()));
	}
	
	@Test (testName="SearchMembers_FirstName",description="PBI:124130")
	public void searchMembers_FirstName() { 
		
				String fName = prop.getProperty("memberSearchFName");

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("Name", fName)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_LastFirstName",description="PBI:124130")
	public void searchMembers_LastFirstName() {  
		
		String fName = prop.getProperty("memberSearchFName");
		String lName = prop.getProperty("memberSearchLName");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("Name",lName+","+fName)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_HomePhoneDashes", description="PBI:124130")
	public void searchMembers_HomePhoneDashes() {  
		
				String hPhoneD = prop.getProperty("memberSearchHPhoneD");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",hPhoneD)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_HomePhoneNoDashes", description="PBI:124130")
	public void searchMembers_HomePhoneNoDashes() {   
		
		String hPhone = prop.getProperty("memberSearchHPhone");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",hPhone)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_MobilePhoneDashes", description="PBI:124130")
	public void searchMembers_MobilePhoneDashes() { 
		
			String mPhoneD = prop.getProperty("memberSearchMPhoneD");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",mPhoneD)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_MobilePhoneNoDashes", description="PBI:124130")
	public void searchMembers_MobilePhoneNoDashes() { 
		
		String mPhone = prop.getProperty("memberSearchMPhone");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",mPhone)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_WorkPhoneDashes",description="PBI:124130")
	public void searchMembers_WorkPhoneDashes() {  
		
		String wPhoneD = prop.getProperty("memberSearchWPhoneD");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",wPhoneD)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_WorkPhoneNoDashes",description="PBI:124130")
	public void searchMembers_WorkPhoneNoDashes() {  
		
		String wPhone = prop.getProperty("memberSearchWPhone");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("PhoneNumber",wPhone)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
	
	@Test (testName="SearchMembers_Email",description="PBI:124130")
	public void searchMembers_Email() {
		
		String email = prop.getProperty("memberSearcheMail");

				given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.queryParam("Email",email)
					.when()
						.get("/api/v3/member/searchmembers")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("Address"))
					    .body("Result[0].Address", hasKey("AddressLine1"))
					    .body("Result[0].Address", hasKey("AddressLine2"))
					    .body("Result[0].Address", hasKey("City"))
					    .body("Result[0].Address", hasKey("Country"))
					    .body("Result[0].Address", hasKey("PostalCode"))
					    .body("Result[0].Address", hasKey("StateProvince"))
					    .body("Result[0]", hasKey("BarcodeId"))
					    .body("Result[0]", hasKey("EmailAddress"))
					    .body("Result[0].HomePhone", hasKey("Extension"))
					    .body("Result[0].HomePhone", hasKey("Number"))
					    .body("Result[0].HomePhone", hasKey("PhoneType"))
					    .body("Result[0]", hasKey("Id"))
					    .body("Result[0].MobilePhone", hasKey("Extension"))
					    .body("Result[0].MobilePhone", hasKey("Number"))
					    .body("Result[0].MobilePhone", hasKey("PhoneType"))
					    .body("Result[0].Name", hasKey("DisplayName"))
					    .body("Result[0].Name", hasKey("FirstName"))
					    .body("Result[0].Name", hasKey("LastName"))
					    .body("Result[0].Name", hasKey("MiddleInitial"))
					    .body("Result[0].Name", hasKey("PreferredName"))
					    .body("Result[0]", hasKey("PreferredPhone"))
					    .body("Result[0].WorkPhone", hasKey("Extension"))
					    .body("Result[0].WorkPhone", hasKey("Number"))
					    .body("Result[0].WorkPhone", hasKey("PhoneType"));
	}
}