package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
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

public class EnrollMemberInCourseWithCardOnFile extends base {
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test (testName="Member Enrolled - Paid Course",description="PBI:146578")
	public void memberEnrolled_PaidCourse() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedGrandTotal 		= "100.00";
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

			Response res =	given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
						.body("{" + 
								"  \"CustomerId\": "+customerId+"," + 
								"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
								"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
								"  \"AccountId\": \""+accountId+"\"," + 
								"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
								"}")
						.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
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
						.time(lessThan(5L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);	
	}
	
	@Test (testName="Member Enrolled - Free Course",description="PBI:146578")
	public void memberEnrolled_FreeCourse() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String courseBarcodeId 			= "freeCo";
				String displayedGrandTotal 		= "0.00";
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}

	@Test (testName="Member Enrolled On Standby",description="PBI:146578")
	public void memberEnrolledOnStandby() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String courseBarcodeId 			= "standbyCo";
				String displayedGrandTotal 		= "1500.00";
				int accountId					= 1;
				String enrollCustomerAsStandby 	= "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.header("Content-Type", "application/json")
					.when()
					.body("{" + 
							"  \"CustomerId\": "+customerId+"," + 
							"  \"CourseBarcodeId\": \""+courseBarcodeId+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
			
	} 
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:146578")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 					= 247;
				String courseBarcodeId 			= "standbyCo";
				String displayedGrandTotal 		= "1500.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}

	@Test (testName="Member Already Enrolled",description="PBI:146578")
	public void memberAlreadyEnrolled() {
		
				int customerId 					= 241;
				String courseBarcodeId 			= "standbyCo";
				String displayedGrandTotal 		= "1500.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:146578")
	public void memberAlreadyOnStandby() {
		
				int customerId 					= 242;
				String courseBarcodeId 			= "standbyCo";
				String displayedGrandTotal 		= "1500.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Course Not Available Online",description="PBI:146578")
	public void courseNotAvailableOnline() {
		
				int customerId 					= 248;
				String courseBarcodeId 			= "noWebCo";
				String displayedGrandTotal 		= "1500.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat()
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"))
						.statusCode(400);
	}
	
	@Test (testName="Course Ended",description="PBI:146578")
	public void courseEnded() {
		
				int customerId 					= 248;
				String courseBarcodeId 			= "endedCo";
				String displayedGrandTotal 		= "10.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:146578")
	public void customerNotFound() {
		
				int customerId 					= 248000;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedGrandTotal 		= "100.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Course Not Found",description="PBI:146578")
	public void courseNotFound() {
		
				int customerId 					= 248;
				String courseBarcodeId 			= "NOTalwaysAvailCo";
				String displayedGrandTotal 		= "100.00";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:146578")
	public void productPriceChanged() {
		
				int customerId 					= 248;
				String courseBarcodeId 			= "alwaysAvailCo";
				String displayedGrandTotal 		= "10.01";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:146578")
	public void schedulingConflict() {
		
				int customerId 					= 246;
				String courseBarcodeId 			= "standbyCo";
				String displayedGrandTotal 		= "1500.0";
				int accountId					= 1;
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
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberincoursewithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
}
