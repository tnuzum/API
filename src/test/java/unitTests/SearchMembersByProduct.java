package unitTests;

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
	
	@Test (testName="FirstName",description="PBI:139726")
	public void FirstName() {
		
				String associatedClub = prop.getProperty("club1Id");
				String trainingId = prop.getProperty("selectableResourceTrainingId");
				String fName = prop.getProperty("memberSearchFName");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+fName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]",  hasKey("AddressLine1"))
						.body("Result[0]",  hasKey("AddressLine2"))
						.body("Result[0]",  hasKey("Id"))
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
					    .body("Result[0].Id", not(nullValue()))
					    .body("Result[0].EmailAddress", not(nullValue()))
					    .body("Result[0].HomePhone", not(nullValue()))
					    .body("Result[0].Id", not(nullValue()))
					    .body("Result[0].DisplayName", not(nullValue()))
					    .body("Result[0].FirstName", not(nullValue()))
					    .body("Result[0].LastName", not(nullValue()))
					    .body("Result[0].PreferredPhoneType", not(nullValue()));
	}
	
	@Test (testName="LastName",description="PBI:139726")
	public void LastName() {
		
		String associatedClub = prop.getProperty("club1Id");
		String trainingId = prop.getProperty("selectableResourceTrainingId");
		String lName = prop.getProperty("memberSearchLName");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+lName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo(lName));

	}
	
	@Test (testName="LastFirstName",description="PBI:139726")
	public void LastFirstName() {
		
		String associatedClub = prop.getProperty("club1Id");
		String trainingId = prop.getProperty("selectableResourceTrainingId"); 
		String fName = prop.getProperty("memberSearchFName");
		String lName = prop.getProperty("memberSearchLName");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+lName+" "+fName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo(lName));
	}
	
	@Test (testName="FirstLastName",description="PBI:139726")
	public void FirstLastName() {
		
		String associatedClub = prop.getProperty("club1Id");
		String trainingId = prop.getProperty("selectableResourceTrainingId"); 
		String fName = prop.getProperty("memberSearchFName");
		String lName = prop.getProperty("memberSearchLName");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+fName+" "+lName+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].LastName", equalTo(lName));
	}
	
	@Test (testName="HomePhoneWithDashes",description="PBI:139726")
	public void HomePhoneWithDashes() {
		
				String associatedClub = prop.getProperty("club1Id");
				String trainingId = prop.getProperty("selectableResourceTrainingId"); 
				String hPhoneD = prop.getProperty("memberSearchHPhoneD");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhoneD+"/"+associatedClub+"/"+trainingId)
						.then()
//				     	.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo("6142001000"));
	}
	
	@Test (testName="HomePhoneWithoutDashes",description="PBI:139726")
	public void HomePhoneWithoutDashes() {
		
				String associatedClub = prop.getProperty("club1Id");
				String trainingId = prop.getProperty("selectableResourceTrainingId"); 
				String hPhone = prop.getProperty("memberSearchHPhone");
		
				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+hPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].HomePhone", equalTo(hPhone));
	}
	
	@Test (testName="MobilePhoneWithoutDashes",description="PBI:139726")
	public void MobilePhoneWithoutDashes() {
		
				String associatedClub = prop.getProperty("club1Id");
				String trainingId = prop.getProperty("selectableResourceTrainingId"); 
				String mPhone = prop.getProperty("memberSearchMPhone");
		
				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+mPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].CellPhone", equalTo(mPhone));
	}
	
	@Test (testName="MobilePhoneWithDashes",description="PBI:139726")
	public void MobilePhoneWithDashes() {
		
		String associatedClub = prop.getProperty("club1Id");
		String trainingId = prop.getProperty("selectableResourceTrainingId"); 
		String mPhone = prop.getProperty("memberSearchMPhoneD");
		
				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+mPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].CellPhone", equalTo("6141001000"));
	}
	
	@Test (testName="WorkPhoneWithDashes",description="PBI:139726")
	public void WorkPhoneWithDashes() {
		/*
		 * Work phone not allowed as search criteria
		 */
				String associatedClub = prop.getProperty("club1Id");
				String trainingId = prop.getProperty("selectableResourceTrainingId"); 
				String wPhone = prop.getProperty("memberSearchWPhoneD");
		
				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+wPhone+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						;
	}
	
	@Test (testName="Email",description="PBI:139726")
	public void Email() {
		
		String associatedClub = prop.getProperty("club1Id");
		String trainingId = prop.getProperty("selectableResourceTrainingId");
		String email = prop.getProperty("memberSearcheMail");

				given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/searchmembersbyproduct/"+email+"/"+associatedClub+"/"+trainingId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].EmailAddress", equalTo(email));

	}
}
