package utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;



public class ModifyCardOnFileByMember extends base {
	
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
	
	@Test (testName="Modify Card")
	public void modifyCard() {
		
		String accountId = "1";
		String customerId = "248";
		String cardNumber = "5499740000000057";
		
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body("{\r\n" + 
						"  \"CustomerId\": \""+customerId+"\",\r\n" + 
						"  \"AccountId\": \""+accountId+"\",\r\n" + 
						"  \"CardNumber\": \""+cardNumber+"\",\r\n" + 
						"  }\r\n" +  
						"}")
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
				.log().all();
			
	}
}