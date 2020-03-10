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
				String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();

		Response res = given()
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getavailableclassesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
				.then()
				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
//				.body("Result.StartDateTime", not(empty()))
//				.body("Result.SubstituteInstructorName", not(empty()))
//				.body("Result.SubstituteInstructorId", not(empty()))
//				.body("Result.ItemDescription", not(empty()))
//				.body("Result.ItemId", not(empty()))
//				.body("Result.ItemId", not(empty()))
//				.body("Result.LongDescription", not(empty()))
//				.body("Result.DurationInMinutes", not(empty()))
//				.body("Result.InstructorName", not(empty()))
//				.body("Result.InstructorId", not(empty()))
//				.body("Result.ClubName", not(empty()))
//				.body("Result.ClubNumber", not(empty()))
//				.body("Result.CategoryDescription", not(empty()))
//				.body("Result.EnrollmentCount", not(empty()))
//				.body("Result.StandbyCount", not(empty()))
//				.body("Result.MemberEnrollmentStatus", not(empty()))
//				.body("Result.ClassCapacity", not(empty()))
//				.body("Result.StandbyEnrollmentOnly", not(empty()))
//				.body("Result.Price", not(empty()))
//				.body("Result.AllowEnrollment", not(empty()))
//				.body("Result.AllowServiceDueEnrollment", not(empty()))
//				.body("Result.PackageEnrollmentAvailable", not(empty()))
//				.body("Result.ServiceVisitId", not(empty()))
//				.body("Result.PunchesRequired", not(empty()))
//				.body("Result.PunchesRemaining", not(empty()))
//				.body("Result.PackageName", not(empty()))
				.extract().response();
		
			JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertNotNull(js.getString("Result.StartDateTime"));
				Assert.assertNotNull(js.getString("Result.SubstituteInstructorName"));
				Assert.assertNotNull(js.getString("Result.SubstituteInstructorId"));
				Assert.assertNotNull(js.getString("Result.ItemDescription"));
				Assert.assertNotNull(js.getString("Result.ItemId"));
				Assert.assertNotNull(js.getString("Result.LongDescription"));
				Assert.assertNotNull(js.getString("Result.DurationInMinutes"));
				Assert.assertNotNull(js.getString("Result.InstructorName"));
				Assert.assertNotNull(js.getString("Result.InstructorId"));
				Assert.assertNotNull(js.getString("Result.ClubName"));
				Assert.assertNotNull(js.getString("Result.ClubNumber"));
				Assert.assertNotNull(js.getString("Result.CategoryDescription"));
				Assert.assertNotNull(js.getString("Result.EnrollmentCount"));
				Assert.assertNotNull(js.getString("Result.StandbyCount"));
				Assert.assertNotNull(js.getString("Result.MemberEnrollmentStatus"));
				Assert.assertNotNull(js.getString("Result.ClassCapacity"));
				Assert.assertNotNull(js.getString("Result.StandbyEnrollmentOnly"));
				Assert.assertNotNull(js.getString("Result.Price"));
				Assert.assertNotNull(js.getString("Result.AllowEnrollment"));
				Assert.assertNotNull(js.getString("Result.AllowServiceDueEnrollment"));
				Assert.assertNotNull(js.getString("Result.PackageEnrollmentAvailable"));
				Assert.assertNotNull(js.getString("Result.ServiceVisitId"));
				Assert.assertNotNull(js.getString("Result.PunchesRequired"));
				Assert.assertNotNull(js.getString("Result.PunchesRemaining"));
				Assert.assertNotNull(js.getString("Result.PackageName"));
	}
	
	@Test (testName="Class Not Found",description="PBI:144256")
	public void classNotFound() { 
		
		String customerId = prop.getProperty("availableId");
		String startDateTime = "2099-01-01";
		String endDateTime = "2100-01-01";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getavailableclassesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
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
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"));
	}
}
