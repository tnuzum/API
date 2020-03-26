package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.ReusableMethods;
import resources.base;

public class SearchMembers extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="SearchMembers_LastName",description="PBI:124130")
	public void searchMembers_LastName() {
		
				ReusableMethods.myWait(500);
		
				String lName = prop.getProperty("memberSearchLName");

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
					    .body("Result[0].Id", not(nullValue()))
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
		
				ReusableMethods.myWait(500);
		
				String fName = prop.getProperty("memberSearchFName");

				given()

				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
		ReusableMethods.myWait(500);
		
		String fName = prop.getProperty("memberSearchFName");
		String lName = prop.getProperty("memberSearchLName");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
				
				ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
		ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
			
			ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
				ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
				ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
				ReusableMethods.myWait(500);

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
		
		ReusableMethods.myWait(500);
		
		String email = prop.getProperty("memberSearcheMail");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
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
					    .body("Result[0]", hasKey("Id"))
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
	
	@Test (testName="No Optional Parameters",description="PBI:124130")
	public void noOptionalParameters() {
		
				ReusableMethods.myWait(500);

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key",aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/member/searchmembers")
				.then()
//					.log().body()
					.assertThat().statusCode(200)
					.time(lessThan(60L),TimeUnit.SECONDS);
	}

	
	
}
