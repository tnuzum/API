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
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class GetAvailableClassesByMember extends base {
	
	public static Boolean onlineEnrollment = true;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Classes Found",description="PBI:144256")
	public void classesFound() { 
		
			String customerId = prop.getProperty("availableId");
			String startDateTime = ReusableDates.getCurrentDate();
//			String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();
			String endDateTime = ReusableDates.getCurrentDatePlusOneDay();

		Response res = given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getavailableclassesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
				.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
		
			JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertNotNull(js.getString("Result[0].BarcodeId"));
				Assert.assertNotNull(js.getInt("Result[0].Capacity"));
				Assert.assertNotNull(js.getString("Result[0].Category.Description"));
				Assert.assertNotNull(js.getInt("Result[0].Category.Id"));
				Assert.assertNotNull(js.getString("Result[0].ClubId"));
				Assert.assertNotNull(js.getString("Result[0].Description"));
				Assert.assertNotNull(js.getString("Result[0].Duration"));
				Assert.assertNotNull(js.getString("Result[0].EnrolledCount"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].CanEnrollUsingPunches"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].CanEnrollUsingServiceDue"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].CustomerDisplayName"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].CustomerId"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].CustomerPunchCountForPackageItem"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].EnrollmentEligibilityStatus"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentEligibilities[0].EnrollmentStatus"));
				Assert.assertNotNull(js.getDouble("Result[0].EnrollmentEligibilities[0].Price"));
				Assert.assertNotNull(js.getString("Result[0].Instructor.BarcodeId"));
				Assert.assertNotNull(js.getString("Result[0].Instructor.DisplayName"));
				Assert.assertNotNull(js.getString("Result[0].Instructor.EmployeeId"));
				Assert.assertNotNull(js.getString("Result[0].IsFull"));
				Assert.assertNotNull(js.getString("Result[0].ItemEnrollmentEligibility"));
				Assert.assertNotNull(js.getString("Result[0].ItemId"));
				Assert.assertNotNull(js.getString("Result[0].Name"));
				Assert.assertNotNull(js.getString("Result[0].Occurrence"));
				Assert.assertNotNull(js.getString("Result[0].PackagePaymentConfiguration.PackageId"));
				Assert.assertNotNull(js.getString("Result[0].PackagePaymentConfiguration.PackageName"));
				Assert.assertNotNull(js.getString("Result[0].PackagePaymentConfiguration.PunchesRequired"));
				
				Assert.assertTrue(js.getString("Result[0]").contains("SubstituteInstructor"));
				
				Assert.assertEquals(js.getString("Result[0].EnrollmentEligibilities[0].CustomerId"), customerId);
	}
	
	@Test (testName="Class Not Found",description="PBI:144256")
	public void classNotFound() { 
		
		String customerId = prop.getProperty("availableId");
		String startDateTime = "2099-01-01";
		String endDateTime = "2100-01-01";

			given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getavailableclassesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
				.then()
//					.log().body()
				.statusCode(404)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Message", equalTo("No available classes found"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:144256")
	public void customerNotFound() { 
		
		int customerId = 22300;
		String startDateTime = ReusableDates.getCurrentDate();
		String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();

			given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getavailableclassesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
				.then()
//					.log().body()
				.statusCode(404)
				.body("Message", equalTo("Customer not found"));
	}
}
