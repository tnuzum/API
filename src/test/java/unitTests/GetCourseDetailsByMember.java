package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetCourseDetailsByMember extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Course Found",description="PBI:143545")
	public void courseFound() {
 
		String customerId = prop.getProperty("availableId");
		String courseId = prop.getProperty("alwaysAvailCoId");

				Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
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
						Assert.assertNotNull(js.getString("Result.DaysOfWeek"));
						Assert.assertNotNull(js.getString("Result.Description"));
						Assert.assertNotNull(js.getString("Result.Duration"));
						Assert.assertNotNull(js.getString("Result.EndDate"));
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
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PackageId"));
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PackageName"));
						Assert.assertNotNull(js.getString("Result.PackagePaymentConfiguration.PunchesRequired"));
						Assert.assertNotNull(js.getString("Result.StartDate"));
						Assert.assertNotNull(js.getString("Result.StartTime"));
						Assert.assertEquals(js.getString("Result.ItemId"), courseId);				
	}
	
	@Test (testName="Course Found - Online Sale Not Allowed",description="PBI:143544")
	public void courseFoundOnlineSaleNotAllowed() {

				String customerId = prop.getProperty("availableId");
				String courseId = prop.getProperty("noWebClId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Course Not Found - Invalid CourseID",description="PBI:143545")
	public void courseNotFound_InvalidCourseID() {
 
		String customerId = prop.getProperty("standbyAId");
		String courseId = "99999";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
						.then()
//						.log().body()
						.assertThat()
						.statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Course not found"));
	}
	
	/*
	 * disabled for research; now returning results for class details too
	@Test (testName="Course Not Found - Class ID Used",description="PBI:143545")
	public void courseNotFound_ClassIDUsed() {
 
		String customerId = prop.getProperty("availableId");
		String courseId = prop.getProperty("alwaysAvailClId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS);
	}
	*/
	
	@Test (testName="Course Not Found - Training ID Used",description="PBI:143545")
	public void courseNotFound_TrainingIDUsed() {
 
		String customerId = prop.getProperty("availableId");
		String courseId = prop.getProperty("freeTId");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Course not found"));
	}
	
	
	@Test (testName="InvalidCustomerId", description="PBI:143545")
	public void invalidCustomerId() {
	
		int customerId = 99999;
		String courseId = prop.getProperty("alwaysAvailClId");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getcoursedetailsbymember/"+customerId+"/"+courseId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"));;
	}
}
