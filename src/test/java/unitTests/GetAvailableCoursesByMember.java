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

public class GetAvailableCoursesByMember extends base {
	
	public static Boolean onlineEnrollment = true;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Courses Found",description="PBI:144256")
	public void coursesFound() { 
		
				String customerId = prop.getProperty("availableId");
				String startDateTime = ReusableDates.getCurrentDate();
				String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();

		Response res = given()
//		.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
	.when()
		.get("/api/v3/classcourse/getavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
		.then()
//		.log().body()
		.assertThat().statusCode(200)
		.time(lessThan(60L),TimeUnit.SECONDS)
//		.body("Result.EndDate", not(nullValue()))
//		.body("Result.Friday", not(nullValue()))
//		.body("Result.Monday", not(nullValue()))
//		.body("Result.Saturday", not(nullValue()))
//		.body("Result.StartDate", not(nullValue()))
//		.body("Result.StartTime", not(nullValue()))
//		.body("Result.Sunday", not(nullValue()))
//		.body("Result.Thursday", not(nullValue()))
//		.body("Result.Tuesday", not(nullValue()))
//		.body("Result.Wednesday", not(nullValue()))
//		.body("Result.AllowEnrollment", not(nullValue()))
//		.body("Result.AllowServiceDueEnrollment", not(nullValue()))
//		.body("Result.CanBePaidByPackageInCart", not(nullValue()))
//		.body("Result.CancellationFee", not(nullValue()))
//		.body("Result.CategoryDescription", not(nullValue()))
//		.body("Result.ChargeCancellationFee", not(nullValue()))
//		.body("Result.ClassCapacity", not(nullValue()))
//		.body("Result.ClubName", not(nullValue()))
//		.body("Result.ClubNumber", not(nullValue()))
//		.body("Result.DurationInMinutes", not(nullValue()))
//		.body("Result.EnrollmentCount", not(nullValue()))
//		.body("Result.EnrollmentPaymentType", not(nullValue()))
//		.body("Result.InstructorId", not(nullValue()))
//		.body("Result.InstructorName", not(nullValue()))
//		.body("Result.ItemBarcodeId", not(nullValue()))
//		.body("Result.ItemDescription", not(nullValue()))
//		.body("Result.ItemId", not(nullValue()))
//		.body("Result.LongDescription", not(nullValue()))
//		.body("Result.MemberEnrollmentStatus", not(nullValue()))
//		.body("Result.PackageEnrollmentAvailable", not(nullValue()))
//		.body("Result.PackageName", not(nullValue()))
//		.body("Result.Price", not(nullValue()))
//		.body("Result.PunchesRemaining", not(nullValue()))
//		.body("Result.PunchesRequired", not(nullValue()))
//		.body("Result.RefundableAmount", not(nullValue()))
//		.body("Result.RefundablePunchAmount", not(nullValue()))
//		.body("Result.ServiceVisitId", not(nullValue()))
//		.body("Result.StandbyCount", not(nullValue()))
//		.body("Result.StandbyEnrollmentOnly", not(nullValue()))
//		.body("Result.UnenrollmentOperation", not(nullValue()))
		.extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		
		Assert.assertNotNull(js.getString("Result.EndDate"));
		Assert.assertNotNull(js.getString("Result.Friday"));
		Assert.assertNotNull(js.getString("Result.Monday"));
		Assert.assertNotNull(js.getString("Result.Saturday"));
		Assert.assertNotNull(js.getString("Result.StartDate"));
		Assert.assertNotNull(js.getString("Result.StartTime"));
		Assert.assertNotNull(js.getString("Result.Sunday"));
		Assert.assertNotNull(js.getString("Result.Thursday"));
		Assert.assertNotNull(js.getString("Result.Tuesday"));
		Assert.assertNotNull(js.getString("Result.Wednesday"));
		Assert.assertNotNull(js.getString("Result.AllowEnrollment"));
		Assert.assertNotNull(js.getString("Result.AllowServiceDueEnrollment"));
		Assert.assertNotNull(js.getString("Result.CanBePaidByPackageInCart"));
		Assert.assertNotNull(js.getString("Result.CancellationFee"));
		Assert.assertNotNull(js.getString("Result.CategoryDescription"));
		Assert.assertNotNull(js.getString("Result.ChargeCancellationFee"));
		Assert.assertNotNull(js.getString("Result.ClassCapacity"));
		Assert.assertNotNull(js.getString("Result.ClubName"));
		Assert.assertNotNull(js.getString("Result.ClubNumber"));
		Assert.assertNotNull(js.getString("Result.DurationInMinutes"));
		Assert.assertNotNull(js.getString("Result.EnrollmentCount"));
		Assert.assertNotNull(js.getString("Result.EnrollmentPaymentType"));
		Assert.assertNotNull(js.getString("Result.InstructorId"));
		Assert.assertNotNull(js.getString("Result.InstructorName"));
		Assert.assertNotNull(js.getString("Result.ItemBarcodeId"));
		Assert.assertNotNull(js.getString("Result.ItemDescription"));
		Assert.assertNotNull(js.getString("Result.ItemId"));
		Assert.assertNotNull(js.getString("Result.LongDescription"));
		Assert.assertNotNull(js.getString("Result.MemberEnrollmentStatus"));
		Assert.assertNotNull(js.getString("Result.PackageEnrollmentAvailable"));
		Assert.assertNotNull(js.getString("Result.PackageName"));
		Assert.assertNotNull(js.getString("Result.Price"));
		Assert.assertNotNull(js.getString("Result.PunchesRemaining"));
		Assert.assertNotNull(js.getString("Result.PunchesRequired"));
		Assert.assertNotNull(js.getString("Result.RefundableAmount"));
		Assert.assertNotNull(js.getString("Result.RefundablePunchAmount"));
		Assert.assertNotNull(js.getString("Result.ServiceVisitId"));
		Assert.assertNotNull(js.getString("Result.StandbyCount"));
		Assert.assertNotNull(js.getString("Result.StandbyEnrollmentOnly"));
		Assert.assertNotNull(js.getString("Result.UnenrollmentOperation"));
	}
	
	@Test (testName="Course Not Found",description="PBI:144256")
	public void courseNotFound() { 
		
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
					.get("/api/v3/classcourse/getavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("No available courses found"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:144256")
	public void customerNotFound() { 
		
		int customerId = 99999;
		String startDateTime = ReusableDates.getCurrentDate();
		String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"));
	}
}
