package sprintReview;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class EnrollMemberInClassWithPunchcard extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Enrolled - Paid Class Already Started",description="PBI:147808")
	public void memberEnrolledPaidClassStarted() {
		
				int customerId = 248;
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = "alwaysAvailCl";
				String classOccurrence = "2025-12-31";
				String enrollCustomerAsStandby = "true";

			Response res =	given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
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
//				ReusableMethods.myWait();
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
//						ReusableMethods.myWait();
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
		@Test (testName="Member Enrolled - Paid Class Not Started",description="PBI:147808")
	public void memberEnrolledPaidClassNotStarted() {
		
				int customerId = 248;
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId = "notStartedCl";
				String classOccurrence = "2119-12-25";
				String enrollCustomerAsStandby = "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
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
//				ReusableMethods.myWait();
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
//						ReusableMethods.myWait();
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);		
	}
		
	@Test (testName="Member Enrolled On Standby",description="PBI:147808")
	public void memberEnrolledOnStandby() {
		
				int customerId 			= 248;
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId 	= "standbyCl";
				String classOccurrence 	= "2025-12-31";
				String enrollCustomerAsStandby = "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
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
//				ReusableMethods.myWait();
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
//						ReusableMethods.myWait();
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Member Enrolled - Free Class",description="PBI:147808")
	public void memberEnrolledFreeClass() {
		
				int customerId 			= 248;
				String companyId = prop.getProperty("X-CompanyId");
				String classBarcodeId 	= "freeCl";
				String classOccurrence 	= "2025-12-31";
				String enrollCustomerAsStandby = "true";

			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
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
//				ReusableMethods.myWait();
				JsonPath js = ReusableMethods.rawToJson(res);
						int enrollmentId = js.getInt("Result.EnrollmentId");
						int invoiceId = js.getInt("Result.InvoiceId");
//						ReusableMethods.myWait();
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
	}
	
	@Test (testName="Not Enough Punches",description="PBI:147808")
	public void notEnoughPunches() {
		
				int customerId = 247;
				String classBarcodeId = "notStartedCl";
				String classOccurrence 	= "2119-12-25";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
//						.body("Message", equalTo("NotEnoughPunches"));
						.body("Message", equalTo("Class or customer configuration does not allow punchcard enrollment"));
		}
	
	@Test (testName="Punchcard Not Allowed",description="PBI:147808")
	public void punchcardNotAllowed() {
		
				int customerId = 247;
				String classBarcodeId = "noPunchCl";
				String classOccurrence 	= "2025-12-31";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Class or customer configuration does not allow punchcard enrollment"));
		}
	
	@Test (testName="Class Full - Standby Not Allowed",description="PBI:147808")
	public void standbyNotAllowed() {
		
				int customerId = 247;
				String classBarcodeId = "standbyCl";
				String classOccurrence 	= "2023-01-02";
				String enrollCustomerAsStandby = "false";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
//						.body("Message", equalTo("Full"));
						.body("Message", equalTo("Class or customer configuration does not allow punchcard enrollment"));
		}
	
	@Test (testName="Class Not Found",description="PBI:147808")
	public void classNotFound() {
		
				int customerId = 247;
				String classBarcodeId = "NOTstandbyCl";
				String classOccurrence 	= "2023-01-02";
				String enrollCustomerAsStandby = "true";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/enrollmemberinclasswithpunchcard/"+customerId+"/"+classBarcodeId+"/"+classOccurrence+"/"+enrollCustomerAsStandby+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("ItemNotFound"));
		}
	} 
