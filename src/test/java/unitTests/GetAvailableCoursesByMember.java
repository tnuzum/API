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
//				.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/classcourse/getavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime+"/"+onlineEnrollment)
				.then()
//				.log().body()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Result[0]").contains("PackageName"));
				
				Assert.assertNotNull(js.getString("Result[0].EndDate"));
				Assert.assertNotNull(js.getString("Result[0].Friday"));
				Assert.assertNotNull(js.getString("Result[0].Monday"));
				Assert.assertNotNull(js.getString("Result[0].Saturday"));
				Assert.assertNotNull(js.getString("Result[0].StartDate"));
				Assert.assertNotNull(js.getString("Result[0].StartTime"));
				Assert.assertNotNull(js.getString("Result[0].Sunday"));
				Assert.assertNotNull(js.getString("Result[0].Thursday"));
				Assert.assertNotNull(js.getString("Result[0].Tuesday"));
				Assert.assertNotNull(js.getString("Result[0].Wednesday"));
				Assert.assertNotNull(js.getString("Result[0].AllowEnrollment"));
				Assert.assertNotNull(js.getString("Result[0].AllowServiceDueEnrollment"));
				Assert.assertNotNull(js.getString("Result[0].CanBePaidByPackageInCart"));
				Assert.assertNotNull(js.getString("Result[0].CancellationFee"));
				Assert.assertNotNull(js.getString("Result[0].CategoryDescription"));
				Assert.assertNotNull(js.getString("Result[0].ChargeCancellationFee"));
				Assert.assertNotNull(js.getString("Result[0].ClassCapacity"));
				Assert.assertNotNull(js.getString("Result[0].ClubName"));
				Assert.assertNotNull(js.getString("Result[0].ClubNumber"));
				Assert.assertNotNull(js.getString("Result[0].DurationInMinutes"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentCount"));
				Assert.assertNotNull(js.getString("Result[0].EnrollmentPaymentType"));
				Assert.assertNotNull(js.getString("Result[0].InstructorBarcodeId"));
				Assert.assertNotNull(js.getString("Result[0].InstructorName"));
				Assert.assertNotNull(js.getString("Result[0].ItemBarcodeId"));
				Assert.assertNotNull(js.getString("Result[0].ItemDescription"));
				Assert.assertNotNull(js.getString("Result[0].ItemId"));
				Assert.assertNotNull(js.getString("Result[0].LongDescription"));
				Assert.assertNotNull(js.getString("Result[0].MemberEnrollmentStatus"));
				Assert.assertNotNull(js.getString("Result[0].PackageEnrollmentAvailable"));
				Assert.assertNotNull(js.getString("Result[0].Price"));
				Assert.assertNotNull(js.getString("Result[0].PunchesRemaining"));
				Assert.assertNotNull(js.getString("Result[0].PunchesRequired"));
				Assert.assertNotNull(js.getString("Result[0].RefundableAmount"));
				Assert.assertNotNull(js.getString("Result[0].RefundablePunchAmount"));
				Assert.assertNotNull(js.getString("Result[0].ServiceVisitId"));
				Assert.assertNotNull(js.getString("Result[0].StandbyCount"));
				Assert.assertNotNull(js.getString("Result[0].StandbyEnrollmentOnly"));
				Assert.assertNotNull(js.getString("Result[0].UnenrollmentOperation"));
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
