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
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class EnrollMemberInClassOnAccount extends base {
	
	@BeforeTest
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Enrolled - Class Already Started",description="PBI:143588")
	public void memberEnrolledClassStarted(){
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String enrollCustomerAsStandby = "true";

			Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
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

		@Test (testName="Member Enrolled - Class Already Not Started",description="PBI:143588")
	public void memberEnrolledClassNotStarted() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("notStartedClBarcodeId");
				String classOccurrence = prop.getProperty("notStartedClOccurrence");
				String displayedGrandTotal = prop.getProperty("notStartedClPrice");
				String enrollCustomerAsStandby = "true";

			Response res = given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
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
					ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:143588")
	public void memberEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
				String enrollCustomerAsStandby = "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
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
	
	@Test (testName="Member Enrolled - Free Class",description="PBI:143588")
	public void memberEnrolledFreeClass() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
//				String classBarcodeId = "freeCl";
//				String classOccurrence = "2025-12-31";
//				String displayedGrandTotal = "0.00";
				String classBarcodeId = prop.getProperty("freeClBarcodeId");
				String classOccurrence = prop.getProperty("freeClOccurrence");
				String displayedGrandTotal = prop.getProperty("freeClPrice");
				String enrollCustomerAsStandby = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled - Free Class - Collections Member",description="PBI:143588")
	public void memberEnrolledFreeClassCollectionsMember() {
		
				int customerId = 227;
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = "freeCl";
				String classOccurrence = "2025-12-31";
				String displayedGrandTotal = "0.00";
				String enrollCustomerAsStandby = "true";

			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
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
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="No FOP - Account Problem",description="PBI:143588") // failed to create invoice because member's billing info not setup
	public void noFOP_AccountProblem() {
		
				String c = prop.getProperty("noFOPId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:143588")
	public void memberAlreadyEnrolled() {
		
				String c = prop.getProperty("standbyCId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:143588")
	public void productPriceChanged() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = "10.01";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	
	@Test (testName="Member Already On Standby",description="PBI:143588")
	public void memberAlreadyOnStandby() {
		
				int customerId 			= 246;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143588")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:143588")
	public void customerNotFound() {
		
				int customerId 			= 245000;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Class Not Found",description="PBI:143588")
	public void classNotFound() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2122-12-06";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:143588")
	public void enrollmentNotAllowed_Item() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "noWebCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - EnrollmentNotAllowed"));
	}
	
	@Test (testName="Enrollment Not Open",description="PBI:143588")
	public void enrollmentNotOpen() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "neverAvailCl";
				String classOccurrence 	= "2119-12-25";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Scheduling Conflict",description="PBI:143588")
	public void schedulingConflict() {
		
				int customerId 			= 241;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:143588")
	public void enrollmentNotAllowedTerminatedMember() {
		
				int customerId 			= 249;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - MemberTerminated"));
	}
	
	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:143588")
	public void enrollmentNotAllowedCollectionsMember() {
		
				int customerId 			= 227;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:143588")
	public void enrollmentNotAllowedFrozenMember() {
		
				int customerId 			= 250;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - MemberFrozen"));
	}
	
	@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:143588")
	public void enrollmentNotAllowedProspectMember() {
		
				int customerId 			= 228;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Account Problem"));
	}
	
	@Test (testName="Class Ended",description="PBI:143588")
	public void classEnded() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "endedCl";
				String classOccurrence 	= "2019-12-13";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemHasEnded"));
	}
	
	@Test (testName="Class Enrollment Closed",description="PBI:143588")
	public void classEnrollmentClosed() {
		
				int customerId 			= 248;
				String classBarcodeId 	= "closedCl";
				String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay();
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
	
	@Test (testName="Credit Limited Exceeded",description="PBI:143588")
	public void creditLimitedExceeded() {
		
				int customerId 			= 251;
				String classBarcodeId 	= "alwaysAvailCl";
				String classOccurrence 	= "2025-12-31";
				String displayedGrandTotal	= "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedGrandTotal+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Account Problem"));
	}
}
