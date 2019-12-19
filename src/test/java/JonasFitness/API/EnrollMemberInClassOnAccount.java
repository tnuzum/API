package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

/*
DONE - No FOP - Account Problem - failed to create invoice because billing info not setup
DONE / ON HOLD - Member enrolled - awaiting an unenroll call to link to this enroll call
DONE - Member already enrolled
DONE - Product price changed
DONE / On HOLD - Member enrolled standby - awaiting an unenroll call to link to this enroll call
DONE - Member already on standby
DONE - Member NOT enrolled standby
DONE - Customer not found
DONE - Class not found
DONE - Item does not allow enrollment
DONE - Member not enrolled - enrollment not open
 */

public class EnrollMemberInClassOnAccount extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	/* !!! Disabled until an unenroll is created
	@Test (testName="Member Enrolled",description="PBI:143588")
	public void memberEnrolled() {
		
				int customerId = 229;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2019-12-25";
				String displayedClassPrice = "10.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
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
						.body("Result.PreferredName", not(nullValue()));
	}
	
	@Test (testName="Member Enrolled On Standby",description="PBI:143588")
	public void memberEnrolledOnStandby() {
		
				int customerId 			= 223;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2022-12-06";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.Enrolled", equalTo(false))
						.body("Result.EnrollmentStatus", equalTo("StandBy"))
						.body("Result.CustomerId", equalTo(customerId))
						.body("Result.FirstName", not(nullValue()))
						.body("Result.LastName", not(nullValue()))
						.body("Result", hasKey("MiddleInitial"))
						.body("Result.DisplayName", not(nullValue()))
						.body("Result.PreferredName", not(nullValue()));
	}*/
	
	@Test (testName="No FOP - Account Problem",description="PBI:143588") // failed to create invoice because member's billing info not setup
	public void noFOP_AccountProblem() {
		
				int customerId = 247;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2019-12-25";
				String displayedClassPrice = "10.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("AccountProblem"));
	}
	
	@Test (testName="Member Already Enrolled",description="PBI:143588")
	public void memberAlreadyEnrolled() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2023-01-02";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyEnrolled"));
	}
	
	@Test (testName="Product Price Changed",description="PBI:143588")
	public void productPriceChanged() {
		
				int customerId = 247;
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2019-12-25";
				String displayedClassPrice = "10.01";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ProductPriceChanged"));
	}
	@Test (testName="Member Already On Standby",description="PBI:143588")
	public void memberAlreadyOnStandby() {
		
				int customerId 			= 246;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2023-01-02";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerAlreadyOnStandby"));
	}
	
	@Test (testName="Member Not Enrolled On Standby",description="PBI:143588")
	public void memberNotEnrolledOnStandby() {
		
				int customerId 			= 247;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2022-12-06";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Full"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:143588")
	public void customerNotFound() {
		
				int customerId 			= 245000;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2022-12-06";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("CustomerNotFound"));
	}
	
	@Test (testName="Class Not Found",description="PBI:143588")
	public void classNotFound() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2122-12-06";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("ItemNotFound"));
	}
	
	@Test (testName="Enrollment Not Allowed - Item",description="PBI:143588")
	public void enrollmentNotAllowed_Item() {
		
				int customerId 			= 245;
				String classBarcodeId 	= "noWebCl";
				String classOccurrence 	= "2024-12-25";
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
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
				String displayedClassPrice	= "150.00";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclassonaccount/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+displayedClassPrice+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("EnrollmentNotAllowed - ItemRestrictions"));
	}
}
