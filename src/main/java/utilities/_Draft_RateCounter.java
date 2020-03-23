package utilities;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
//import resources.ReusableMethods;
import resources.base;

public class _Draft_RateCounter extends base {
	
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
		cardNumber = prop.getProperty("CC1CardNumber");
		nameOnCard = prop.getProperty("CC1NameOnCard");
		month = prop.getProperty("CC1Month");
		year = prop.getProperty("CC1Year");
		securityCode = prop.getProperty("CC1SecurityCode");
		addressLine1 = prop.getProperty("CC1AddressLine1");
		city = "";
		state = prop.getProperty("CC1State");
		postalCode = prop.getProperty("CC1PostalCode");
		enrollCustomerAsStandby 	= "true";
	}
	
	@Test (testName="Rate-counter Limitation", description="Rate-counter Limitation")
	public void missingCity() {

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
	}
	
	@Test (testName="Rate-counter Limitation2", description="Rate-counter Limitation")
	public void missingCity2() {

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
	}
	
	@Test (testName="Rate-counter Limitation2", description="Rate-counter Limitation")
	public void missingCity3() {

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
	}
	
	@Test (testName="Rate-counter Limitation2", description="Rate-counter Limitation")
	public void missingCity4() {

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
	}
	
	@Test (testName="Rate-counter Limitation2", description="Rate-counter Limitation")
	public void missingCity5() {

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
	}
	
	@Test (testName="Rate-counter Limitation2", description="Rate-counter Limitation")
	public void missingCity6() {

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
	}
}
