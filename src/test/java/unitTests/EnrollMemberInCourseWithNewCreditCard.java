package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class EnrollMemberInCourseWithNewCreditCard extends base {
	
	public static Boolean onlineEnrollment = true;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	 
	@Test (testName="Member Enrolled - Paid Course",description="PBI:146580")
	public void memberEnrolledPaidCourse() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
				String enrollCustomerAsStandby 	= "true";
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()))
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
//							ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:146580")
	public void memberEnrolledFreeCourse() {
		
				String companyId = prop.getProperty("X-CompanyId");
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("freeCoId");
				String displayedGrandTotal = prop.getProperty("freeCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
				String enrollCustomerAsStandby 	= "true";
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+  
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
	@Test (testName="Member Enrolled - Free Course - Collections Member",description="PBI:146580")
	public void memberEnrolledFreeCourseCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseId = prop.getProperty("freeCoId");
				String displayedGrandTotal = prop.getProperty("freeCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
				String enrollCustomerAsStandby 	= "true";
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:146580")
	public void memberEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String courseId = prop.getProperty("standbyCoId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
				String enrollCustomerAsStandby 	= "true";
				
				if (ReusableMethods.isEnrolled(customerId) == false) {

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+  
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
						.body("Result.PreferredName", not(nullValue()))
						.extract().response();
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						
						if (res.statusCode() == 200) {
							ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
							ReusableMethods.deleteInvoice(companyId, enrollmentId, invoiceId, customerId);
						}
				}
				else{
					Assert.assertTrue(false); //failing test because isEnrolled() == true
				}
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:146580")
	public void memberNotEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("standbyCoId");
				String displayedGrandTotal = prop.getProperty("standbyCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
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
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Card Expired",description="PBI:146580")
	public void cardExpired() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				int year = 2019;
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
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
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Card Number Invalid",description="PBI:146580")
	public void cardNumberInvalid() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				String cardNumber = "5454545454545400";
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
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
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+  
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Card Number Length Incorrect",description="PBI:146580")
	public void cardNumberLengthIncorrect() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String courseId = prop.getProperty("alwaysAvailCoId");
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				String cardNumber = "54545454545454540000";
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
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
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+ 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Course Not Found",description="PBI:146580", enabled = true)
	public void courseNotFound() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				int courseId = 99999;
				String displayedGrandTotal = prop.getProperty("alwaysAvailCoPrice");
				String cardNumber = prop.getProperty("CC1CardNumber");
				String nameOnCard = prop.getProperty("CC1NameOnCard");
				String month = prop.getProperty("CC1Month");
				String year = prop.getProperty("CC1Year");
				String securityCode = prop.getProperty("CC1SecurityCode");
				String addressLine1 = prop.getProperty("CC1AddressLine1");
				String city = prop.getProperty("CC1City");
				String state = prop.getProperty("CC1State");
				String postalCode = prop.getProperty("CC1PostalCode");
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
								"  \"CourseId\": \""+courseId+"\"," + 
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
								"  \"EnrollCustomerAsStandBy\": \""+enrollCustomerAsStandby+"\"," +
								"  \"OnlineEnrollment\": \""+onlineEnrollment+"\""+  
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	

	
}
