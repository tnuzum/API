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

public class EnrollMemberInClassWithCardOnFile extends base {
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test (testName="Member Enrolled - Paid Class",description="PBI:146577")
	public void memberEnrolled_PaidClass() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String classBarcodeId 			= "alwaysAvailCl";
				String classOccurrence 			= "2025-12-31";
				String displayedGrandTotal 		= "10.00";
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
								"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
								"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
								"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
								"  \"AccountId\": \""+accountId+"\"," + 
								"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
								"}")
						.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.Enrolled", equalTo(true))
						.body("Result.EnrollmentStatus", equalTo("Enrolled"))
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
	
	@Test (testName="Member Enrolled - Free Class",description="PBI:146577")
	public void memberEnrolled_FreeClass() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String classBarcodeId 			= "freeCl";
				String classOccurrence 			= "2025-12-31";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
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
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId)
						;
	
			}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:146577")
	public void memberEnrolledOnStandby() {
		
				String companyId				= prop.getProperty("X-CompanyId");
				int customerId 					= 248;
				String classBarcodeId 			= "standbyCl";
				String classOccurrence 			= "2023-01-02";
				String displayedGrandTotal 		= "150.00";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
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
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:146577")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 					= 247;
				String classBarcodeId 			= "standbyCl";
				String classOccurrence 			= "2023-01-02";
				String displayedGrandTotal 		= "150.00";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}

	@Test (testName="Member Already Enrolled",description="PBI:146577")
	public void memberAlreadyEnrolled() {
		
				int customerId 					= 245;
				String classBarcodeId 			= "standbyCl";
				String classOccurrence 			= "2023-01-02";
				String displayedGrandTotal 		= "150.00";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:146577")
	public void memberAlreadyOnStandby() {
		
				int customerId 					= 246;
				String classBarcodeId 			= "standbyCl";
				String classOccurrence 			= "2023-01-02";
				String displayedGrandTotal 		= "150.00";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Class Not Available Online",description="PBI:146577")
	public void classNotAvailableOnline() {
		
				int customerId 					= 248;
				String classBarcodeId 			= "noWebCl";
				String classOccurrence 			= "2025-01-01";
				String displayedGrandTotal 		= "150.00";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	}
	
	@Test (testName="Class Ended",description="PBI:146577")
	public void classEnded() {
		
				int customerId 					= 248;
				String classBarcodeId 			= "endedCl";
				String classOccurrence 			= "2019-12-13";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:146577")
	public void customerNotFound() {
		
				int customerId 					= 248000;
				String classBarcodeId 			= "alwaysAvailCl";
				String classOccurrence 			= "2025-01-01";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Class Not Found",description="PBI:146577")
	public void classNotFound() {
		
				int customerId 					= 248;
				String classBarcodeId 			= "NOTalwaysAvailCl";
				String classOccurrence 			= "2025-01-01";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Class Occurrence Not Found",description="PBI:146577")
	public void classOccurrenceNotFound() {
		
				int customerId 					= 248;
				String classBarcodeId 			= "alwaysAvailCl";
				String classOccurrence 			= "2225-01-01";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:146577")
	public void productPriceChanged() {
		
				int customerId 					= 248;
				String classBarcodeId 			= "alwaysAvailCl";
				String classOccurrence 			= "2025-01-01";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:146578")
	public void schedulingConflict() {
		
				int customerId 					= 242;
				String classBarcodeId 			= "standbyCl";
				String classOccurrence 			= "2023-01-02";
				String displayedGrandTotal 		= "150.0";
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
							"  \"ClassBarcodeId\": \""+classBarcodeId+"\"," + 
							"  \"ClassOccurrence\": \""+classOccurrence+"\"," + 
							"  \"DisplayedGrandTotal\": "+displayedGrandTotal+"," + 
							"  \"AccountId\": \""+accountId+"\"," + 
							"  \"EnrollCustomerAsStandBy\": "+enrollCustomerAsStandby+"" + 
							"}")
					.post("/api/v3/classcourse/enrollmemberinclasswithcardonfile")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
}
