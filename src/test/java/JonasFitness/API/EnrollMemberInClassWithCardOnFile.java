package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class EnrollMemberInClassWithCardOnFile extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}

	@Test (testName="Member Enrolled - Paid Class",description="PBI:146577")
	public void memberEnrolled_PaidClass() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
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
//						.time(lessThan(5L),TimeUnit.SECONDS)
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("freeClBarcodeId");
				String classOccurrence = prop.getProperty("freeClOccurrence");
				String displayedGrandTotal = prop.getProperty("freeClPrice");
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
	
	@Test (testName="Member Enrolled - Free Class - Collections Member",description="PBI:146577")
	public void memberEnrolled_FreeClassCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("freeClBarcodeId");
				String classOccurrence = prop.getProperty("freeClOccurrence");
				String displayedGrandTotal = prop.getProperty("freeClPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
				int accountId = 1;
				String enrollCustomerAsStandby = "true";

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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
		
				String c = prop.getProperty("standbyCId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
		
				String c = prop.getProperty("standbyDId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("noWebClBarcodeId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				String displayedGrandTotal = prop.getProperty("noWebClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("endedClBarcodeId");
				String classOccurrence = prop.getProperty("endedClOccurrence");
				String displayedGrandTotal = prop.getProperty("endedClPrice");
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
		
				int customerId 			= 245000;
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("NOTalwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence 	= "2122-12-06";
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
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
		
				String c = prop.getProperty("standbyAId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
