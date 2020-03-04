package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetClassDetailsByMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Class Found - Online Sale Allowed",description="PBI:143544")
	public void classFoundOnlineSaleAllowed() {
 
				String customerId = prop.getProperty("availableId");
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

			Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
 						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
					
						Assert.assertNotNull(js.getString("Result.BarcodeId"));
						Assert.assertNotNull(js.getInt("Result.Capacity"));
						Assert.assertNotNull(js.getString("Result.Category.Description"));
						Assert.assertNotNull(js.getInt("Result.Category.Id"));
						Assert.assertNotNull(js.getString("Result.ClubId"));
						Assert.assertNotNull(js.getString("Result.Description"));
						Assert.assertNotNull(js.getString("Result.Duration"));
						Assert.assertNotNull(js.getString("Result.EnrolledCount"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].CanEnrollUsingPunches"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].CanEnrollUsingServiceDue"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].CustomerDisplayName"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].CustomerId"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].CustomerPunchCountForPackageItem"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].EnrollmentEligibilityStatus"));
						Assert.assertNotNull(js.getString("Result.EnrollmentEligibilities[0].EnrollmentStatus"));
						Assert.assertNotNull(js.getDouble("Result.EnrollmentEligibilities[0].Price"));
						Assert.assertNotNull(js.getString("Result.Instructor.BarcodeId"));
						Assert.assertNotNull(js.getString("Result.Instructor.DisplayName"));
						Assert.assertNotNull(js.getString("Result.Instructor.EmployeeId"));
						Assert.assertNotNull(js.getString("Result.IsFull"));
						Assert.assertNotNull(js.getString("Result.ItemEnrollmentEligibility"));
						Assert.assertNotNull(js.getString("Result.ItemId"));
						Assert.assertNotNull(js.getString("Result.Name"));
						Assert.assertNotNull(js.getString("Result.Occurrence"));
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PackageId"));
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PackageName"));
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PunchesRequired"));
						Assert.assertEquals(js.getString("Result.ItemId"), classId);
	}

	@Test (testName="Class Found - Online Sale Not Allowed",description="PBI:143544")
	
	/*
	 * disabled due to bug# 160419; if I change item to allow web sales the call is successful
	 */ 
	public void classFoundOnlineSaleNotAllowed() {

				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("noWebClId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Class Not Found - Invalid ClassID",description="PBI:143544")
	public void classNotFound_InvalidClassID() {
 
				String customerId = prop.getProperty("availableId");
				String classId = "99999";
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)						
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"));
	}
	
	/*
	 * disabled for research; now returning results for course details too
	@Test (testName="Class Not Found - Course ID Used",description="PBI:143544")
	public void classNotFound_CourseIDUsed() {
 
			String customerId = prop.getProperty("availableId");
			String classId = prop.getProperty("alwaysAvailCoId");
			String classOccurrence = prop.getProperty("alwaysAvailCoOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
						.log().body()
						.assertThat()
						.body("Message", equalTo("Class not found"))						
						.statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
		 */
	
	@Test (testName="Class Not Found - Training ID Used",description="PBI:143544")
	public void classNotFound_TrainingIDUsed() {
 
			String customerId = prop.getProperty("availableId");
			String classId = prop.getProperty("freeTId");
			String classOccurrence = prop.getProperty("freeTOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
						;
	}
	
	@Test (testName="InvalidCustomerId", description="PBI:143544")
	public void invalidCustomerId() {
	
		int customerId = 99999;
		String classId = prop.getProperty("alwaysAvailClId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetailsbymember/"+customerId+"/"+classOccurrence+"/"+classId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"));
	}
	
}
