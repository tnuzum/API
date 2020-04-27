package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class RateLimitation extends base {
	
	/*
	 ** Run this statement to update Limit **
	USE TESTCompeteRootFuture2
	UPDATE RateLimitingCompanyRules
	SET Limit = 5
	WHERE Id = 1
	 */
	
	int customerId;
	String classId;
	String classOccurrence;
	String displayedGrandTotal;
	String cardNumber;
	String nameOnCard;
	String month;
	String year;
	String securityCode;
	String addressLine1;
	String city;
	String state;
	String postalCode;
	String enrollCustomerAsStandby;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		customerId = 248;
		classId = "alwaysAvailCl";
		classOccurrence = "2025-12-31";
		displayedGrandTotal = "10.00";
		cardNumber = prop.getProperty("MC1CardNumber");
		nameOnCard = prop.getProperty("MC1NameOnCard");
		month = prop.getProperty("MC1Month");
		year = prop.getProperty("MC1Year");
		securityCode = prop.getProperty("MC1SecurityCode");
		addressLine1 = prop.getProperty("MC1AddressLine1");
		city = "";
		state = prop.getProperty("MC1State");
		postalCode = prop.getProperty("MC1PostalCode");
		enrollCustomerAsStandby 	= "true";
	}
	
	@DataProvider(parallel = true)
	public Object[] getDataProvider(){
		
		Object[][] data = new Object[5][1];
		
				data[0][0]=prop.getProperty("availableId");
				data[1][0]=prop.getProperty("collectionsId");
				data[2][0]=prop.getProperty("noFOPId");
				data[3][0]=prop.getProperty("prospectId");
				data[4][0]=prop.getProperty("noWebId");
				return data;
	}
	
	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation", priority = 1, dataProvider="getDataProvider")
	public void rateLimitNotExceeded(String customerId) {

		given()
//				.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
				.body("{" + 
						"  \"CustomerId\": "+customerId+"," + 
						"  \"ClassId\": \""+classId+"\"," + 
						"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
						"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
						"  \"CardNumber\": \""+cardNumber+"\"," + 
						"  \"NameOnCard\": \""+nameOnCard+"\"," + 
						"  \"ExpirationDate\": {" + 
						"    \"Month\": \""+month+"\"," + 
						"    \"Year\": "+year+"" + 
						"  }," + 
						"  \"SecurityCode\": \""+securityCode+"\"," + 
						"  \"AddressLine1\": \""+addressLine1+"\"," + 
						"  \"City\": \""+city+"\"," + 
						"  \"StateProvince\": \""+state+"\"," + 
						"  \"PostalCode\": \""+postalCode+"\"," + 
						"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
						"}")
				.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard");
//				.then().log().all();
	}

	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation", priority = 2)
	public void rateLimitExceeded() {

		given()
//				.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.header("Content-Type", "application/json")
			.when()
				.body("{" + 
						"  \"CustomerId\": "+customerId+"," + 
						"  \"ClassId\": \""+classId+"\"," + 
						"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
						"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
						"  \"CardNumber\": \""+cardNumber+"\"," + 
						"  \"NameOnCard\": \""+nameOnCard+"\"," + 
						"  \"ExpirationDate\": {" + 
						"    \"Month\": \""+month+"\"," + 
						"    \"Year\": "+year+"" + 
						"  }," + 
						"  \"SecurityCode\": \""+securityCode+"\"," + 
						"  \"AddressLine1\": \""+addressLine1+"\"," + 
						"  \"City\": \""+city+"\"," + 
						"  \"StateProvince\": \""+state+"\"," + 
						"  \"PostalCode\": \""+postalCode+"\"," + 
						"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
						"}")
				.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
				.then()
//				.log().everything()
				.assertThat()
				.statusLine("HTTP/1.1 429 Too Many Requests")
				.statusCode(429);
	}
	/*
	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation", priority = 1, dataProvider="getDataProvider")
	public void rateLimitNotExceeded2(String companyId) {

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", companyId)
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getactiveclubs")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200);
	}
	
	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation", priority = 2)
	public void rateLimitExceeded() {

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/club/getactiveclubs")
					.then()
//						.log().body()
						.assertThat()
						.statusLine("HTTP/1.1 429 Too Many Requests")
						.statusCode(429);
	}
*/
}
