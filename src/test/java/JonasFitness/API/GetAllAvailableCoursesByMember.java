package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


import io.restassured.RestAssured;
import resources.ReusableDates;
import resources.base;

public class GetAllAvailableCoursesByMember extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Course Details Returned",description="PBI:146572")
	public void courseDetailsReturned() { 
		
		String customerId = prop.getProperty("availableId");
		String startDateTime = ReusableDates.getCurrentDate();
		String endDateTime = ReusableDates.getCurrentDatePlusOneWeek();

		given()
//		.log().all()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
		.when()
		.get("/api/v3/classcourse/getallavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime)
		.then()
//		.log().body()
		.assertThat().statusCode(200)
		.time(lessThan(60L),TimeUnit.SECONDS)
		.body("Result.ItemBarcodeId", anyOf(hasItem(prop.getProperty("alwaysAvailCoBarcodeId"))))// Item is set to Allow Online Sales
		.body("Result.ItemBarcodeId", anyOf(hasItem(prop.getProperty("noWebCoBarcodeId"))))// Item is set to NOT Allow Online Sales
		.body("Result.EndDate", not(nullValue()))
		.body("Result.Friday", not(nullValue()))
		.body("Result.Monday", not(nullValue()))
		.body("Result.Saturday", not(nullValue()))
		.body("Result.StartDate", not(nullValue()))
		.body("Result.StartTime", not(nullValue()))
		.body("Result.Sunday", not(nullValue()))
		.body("Result.Thursday", not(nullValue()))
		.body("Result.Tuesday", not(nullValue()))
		.body("Result.Wednesday", not(nullValue()))
		.body("Result.AllowEnrollment", not(nullValue()))
		.body("Result.AllowServiceDueEnrollment", not(nullValue()))
		.body("Result.CanBePaidByPackageInCart", not(nullValue()))
		.body("Result.CancellationFee", not(nullValue()))
		.body("Result.CategoryDescription", not(nullValue()))
		.body("Result.ChargeCancellationFee", not(nullValue()))
		.body("Result.ClassCapacity", not(nullValue()))
		.body("Result.ClubName", not(nullValue()))
		.body("Result.ClubNumber", not(nullValue()))
		.body("Result.DurationInMinutes", not(nullValue()))
		.body("Result.EnrollmentCount", not(nullValue()))
		.body("Result.EnrollmentPaymentType", not(nullValue()))
		.body("Result.InstructorBarcodeId", not(nullValue()))
		.body("Result.InstructorName", not(nullValue()))
		.body("Result.ItemBarcodeId", not(nullValue()))
		.body("Result.ItemDescription", not(nullValue()))
		.body("Result.ItemId", not(nullValue()))
		.body("Result.LongDescription", not(nullValue()))
		.body("Result.MemberEnrollmentStatus", not(nullValue()))
		.body("Result.PackageEnrollmentAvailable", not(nullValue()))
		.body("Result.PackageName", not(nullValue()))
		.body("Result.Price", not(nullValue()))
		.body("Result.PunchesRemaining", not(nullValue()))
		.body("Result.PunchesRequired", not(nullValue()))
		.body("Result.RefundableAmount", not(nullValue()))
		.body("Result.RefundablePunchAmount", not(nullValue()))
		.body("Result.ServiceVisitId", not(nullValue()))
		.body("Result.StandbyCount", not(nullValue()))
		.body("Result.StandbyEnrollmentOnly", not(nullValue()))
		.body("Result.UnenrollmentOperation", not(nullValue()));
	}
	
	@Test (testName="Courses Not Found",description="PBI:146572")
	public void coursesNotFound() { 
		
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
						.get("/api/v3/classcourse/getallavailablecoursesbymember/"+customerId+"/"+startDateTime+"/"+endDateTime)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("No available courses found"));
	}
	
	@Test (testName="Customer Not Found",description="PBI:146572")
	public void customerNotFound() { 
		
		int CustomerId = 22300;
		String StartDateTime = ReusableDates.getCurrentDate();
		String EndDateTime = ReusableDates.getCurrentDatePlusOneWeek();

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getallavailablecoursesbymember/"+CustomerId+"/"+StartDateTime+"/"+EndDateTime)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"))
						;

	}
}
