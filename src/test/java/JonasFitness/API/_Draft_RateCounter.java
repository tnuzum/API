package JonasFitness.API;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
//import resources.ReusableMethods;
import resources.base;

public class _Draft_RateCounter extends base {
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation")
	/*public void rateCounterLimitation() {
		//** This is not working because it doesn't always send 3 requests within 1 second
		String member = prop.getProperty("availableId");
				for (int i=1; i<5; i++) {
					given()

						.header("accept", prop.getProperty("accept"))
//						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-Api-Key", "")
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
						.header("Content-Type", "application/json")
					.when()
						.get("/api/v3/member/getmember/"+member);
//						.then()
//						.log().body();
//						.assertThat().header("X-Rate-Limit-Limit", "1s");	
				}
						
	} */
	public void missingCity() {
		
		int customerId 					= 248;
		String classBarcodeId 			= "alwaysAvailCl";
		String classOccurrence 			= "2025-12-31";
		String displayedGrandTotal 		= "10.00";
		String cardNumber = prop.getProperty("CC1CardNumber");
		String nameOnCard = prop.getProperty("CC1NameOnCard");
		String month = prop.getProperty("CC1Month");
		String year = prop.getProperty("CC1Year");
		String securityCode = prop.getProperty("CC1SecurityCode");
		String addressLine1 = prop.getProperty("CC1AddressLine1");
		String city						= "";
		String state = prop.getProperty("CC1State");
		String postalCode = prop.getProperty("CC1PostalCode");
		String enrollCustomerAsStandby 	= "true";
		
		for (int i=1; i<20; i++) {

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
						"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
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
//				.then()
////				.log().body()
//				.assertThat().statusCode(400)
//				.body("Message", equalTo("The City field is required."));
		given()
//		.log().all()
.header("accept", prop.getProperty("accept"))
.header("X-Api-Key", prop.getProperty("X-Api-Key"))
.header("X-CompanyId", prop.getProperty("X-CompanyId"))
.header("X-ClubId", prop.getProperty("X-Club1Id"))
.header("Content-Type", "application/json")
	.when()
		.body("{" + 
				"  \"CustomerId\": "+customerId+"," + 
				"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
				"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
				"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
				"  \"CardNumber\": \""+cardNumber+"\"," + 
				"  \"NameOnCard\": \""+nameOnCard+"\"," + 
				"  \"ExpirationDate\": {" + 
				"    \"Month\": \""+month+"\"," + 
				"    \"Year\": "+year+"" + 
				"  }," + 
				"  \"SecurityCode\": \"\"," + 
				"  \"AddressLine1\": \""+addressLine1+"\"," + 
				"  \"City\": \"Columbus\"," + 
				"  \"StateProvince\": \""+state+"\"," + 
				"  \"PostalCode\": \""+postalCode+"\"," + 
				"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
				"}")
		.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard");
		}
}
}
