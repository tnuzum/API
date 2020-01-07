package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class EnrollMemberInCourseWithNewCreditCard extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	/* // !!! Disabled until an unenroll is created
	 *
	@Test (testName="Member Enrolled",description="PBI:146580")
	public void memberEnrolled() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedCoursePrice 	= "100.00";
				String cardNumber				= "5454545454545454";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						;
	}
		@Test (testName="Member Enrolled On Standby",description="PBI:146580")
	public void memberEnrolledOnStandby() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "standbyCo";
				String displayedCoursePrice 	= "1500.00";
				String cardNumber				= "5454545454545454";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.Enrolled", equalTo(false))
						.body("Result.EnrollmentStatus", equalTo("StandBy"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	} */
	@Test (testName="Member Not Enrolled On Standby",description="PBI:146580")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "standbyCo";
				String displayedCoursePrice 	= "1500.00";
				String cardNumber				= "5454545454545454";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Card Expired",description="PBI:146580")
	public void cardExpired() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedCoursePrice 	= "100.00";
				String cardNumber				= "5454545454545454";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2019;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Payment Failed"));
	}
	
	@Test (testName="Card Number Invalid",description="PBI:146580")
	public void cardNumberInvalid() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedCoursePrice 	= "100.00";
				String cardNumber				= "5454545454545400";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Payment Failed"));
	}
	
	@Test (testName="Card Number Length Incorrect",description="PBI:146580")
	public void cardNumberLengthIncorrect() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedCoursePrice 	= "100.00";
				String cardNumber				= "54545454545454540000";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Payment Failed"));
	}
	
	@Test (testName="Course Item Not Found",description="PBI:146580")
	public void courseItemNotFound() {
		
				int customerId 					= 237;
				String courseBarcodeId 			= "NOTalwaysAvailCo";
				String displayedCoursePrice 	= "100.00";
				String cardNumber				= "5454545454545454";
				String nameOnCard				= "JIM MANNY";
				String month					= "12";
				int year						= 2025;
				String securityCode				= "007";
				String addressLine1				= "210 Northwoods Blvd";
				String city						= "Delaware";
				String state					= "OH";
				String postalCode				= "43015";
				String enrollCustomerAsStandby 	= "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedCoursePrice\": "+displayedCoursePrice+"," + 
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
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	}
	

	
}
