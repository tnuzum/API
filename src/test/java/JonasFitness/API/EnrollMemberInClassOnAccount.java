package JonasFitness.API;

import static io.restassured.RestAssured.given;

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
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class EnrollMemberInClassOnAccount extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Enrolled - Paid Class Already Started",description="PBI:143588")
	public void memberEnrolledPaidClassStarted(){
		
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

	@Test (testName="Member Enrolled - Paid Class Not Started",description="PBI:143588")
	public void memberEnrolledPaidClassNotStarted() {
		
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
		
				String c = prop.getProperty("collectionsId");
				int customerId = Integer.parseInt(c);
				String companyId = prop.getProperty("X-CompanyId");
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
		
				String c = prop.getProperty("standbyDId");
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
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143588")
	public void memberNotEnrolledOnStandby() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("standbyClBarcodeId");
				String classOccurrence = prop.getProperty("standbyClOccurrence");
				String displayedGrandTotal = prop.getProperty("standbyClPrice");
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
						.assertThat().statusCode(404)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Class Not Found",description="PBI:143588")
	public void classNotFound() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("NOTalwaysAvailClBarcodeId");
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
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Occurrence Not Found",description="PBI:143588")
	public void occurrenceNotFound() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence 	= "2122-12-06";
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
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Class Not Available Online",description="PBI:143588")
	public void classNotAvailableOnline() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("noWebClBarcodeId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");
				String displayedGrandTotal = prop.getProperty("noWebClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("neverAvailClBarcodeId");
				String classOccurrence = prop.getProperty("neverAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("neverAvailClPrice");
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
		
				String c = prop.getProperty("standbyAId");
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
						.body("Message", equalTo("EnrollmentNotAllowed - SchedulingConflict"));
	}
	
	@Test (testName="Enrollment Not Allowed - Terminated Member",description="PBI:143588")
	public void enrollmentNotAllowedTerminatedMember() {
		
				String c = prop.getProperty("terminatedId");
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
						.body("Message", equalTo("EnrollmentNotAllowed - MemberTerminated"));
	}
	
	@Test (testName="Enrollment Not Allowed - Collections Member",description="PBI:143588")
	public void enrollmentNotAllowedCollectionsMember() {
		
				String c = prop.getProperty("collectionsId");
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
	
	@Test (testName="Enrollment Not Allowed - Frozen Member",description="PBI:143588")
	public void enrollmentNotAllowedFrozenMember() {
		
				String c = prop.getProperty("frozenId");
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
						.body("Message", equalTo("EnrollmentNotAllowed - MemberFrozen"));
	}
	
	@Test (testName="Enrollment Not Allowed - Prospect Member",description="PBI:143588")
	public void enrollmentNotAllowedProspectMember() {
		
				String c = prop.getProperty("prospectId");
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
	
	@Test (testName="Class Ended",description="PBI:143588")
	public void classEnded() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("endedClBarcodeId");
				String classOccurrence = prop.getProperty("endedClOccurrence");
				String displayedGrandTotal = prop.getProperty("endedClPrice");
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
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("closedClBarcodeId");
				String classOccurrence 	= ReusableDates.getCurrentDatePlusOneDay();
				String displayedGrandTotal = prop.getProperty("closedClPrice");
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
		
				String c = prop.getProperty("creditLimitId");
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
}
