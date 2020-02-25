package unitTests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetCourseDetails extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Course Found",description="PBI:143545")
	public void courseFound() {
 
		String customerId = prop.getProperty("standbyAId");
		String CourseBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");

				Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetails/"+customerId+"/"+CourseBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.body("Result", hasKey("PackageName"))
						.body("Result.PunchesRemaining", not(nullValue()))
						.body("Result.PunchesRequired", not(nullValue()))
						.body("Result.RefundablePunchAmount", not(nullValue()))
						.body("Result.ServiceVisitId", not(nullValue()))
						.body("Result.StandbyCount", not(nullValue()))
						.body("Result.StandbyEnrollmentOnly", not(nullValue()))
						.body("Result.UnenrollmentOperation", not(nullValue()))
						.extract().response();
				
				JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.Price"), 100.00);
						Assert.assertEquals(js.getDouble("Result.CancellationFee"), 0.0);
						Assert.assertEquals(js.getDouble("Result.RefundableAmount"), 0.0);
						
	}
	
	@Test (testName="Course Not Found - Invalid CourseBarcodeID",description="PBI:143545")
	public void courseNotFound_InvalidCourseBarcodeID() {
 
		String customerId = prop.getProperty("standbyAId");
		String CourseBarcodeId = "invalidCourseBarcodeId";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetails/"+customerId+"/"+CourseBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
						.body("Result", not(hasKey("EndDate")))
						.body("Result", not(hasKey("Friday")))
						.body("Result", not(hasKey("Monday")))
						.body("Result", not(hasKey("Saturday")))
						.body("Result", not(hasKey("StartDate")))
						.body("Result", not(hasKey("StartTime")))
						.body("Result", not(hasKey("Sunday")))
						.body("Result", not(hasKey("Thursday")))
						.body("Result", not(hasKey("Tuesday")))
						.body("Result", not(hasKey("Wednesday")))
						.body("Result", not(hasKey("AllowEnrollment")))
						.body("Result", not(hasKey("AllowServiceDueEnrollment")))
						.body("Result", not(hasKey("CanBePaidByPackageInCart")))
						.body("Result", not(hasKey("CancellationFee")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("ChargeCancellationFee")))
						.body("Result", not(hasKey("ClassCapacity")))
						.body("Result", not(hasKey("ClubName")))
						.body("Result", not(hasKey("ClubNumber")))
						.body("Result", not(hasKey("DurationInMinutes")))
						.body("Result", not(hasKey("EnrollmentCount")))
						.body("Result", not(hasKey("EnrollmentPaymentType")))
						.body("Result", not(hasKey("InstructorBarcodeId")))
						.body("Result", not(hasKey("InstructorName")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("MemberEnrollmentStatus")))
						.body("Result", not(hasKey("PackageEnrollmentAvailable")))
						.body("Result", not(hasKey("PackageName")))
						.body("Result", not(hasKey("Price")))
						.body("Result", not(hasKey("PunchesRemaining")))
						.body("Result", not(hasKey("PunchesRequired")))
						.body("Result", not(hasKey("RefundableAmount")))
						.body("Result", not(hasKey("RefundablePunchAmount")))
						.body("Result", not(hasKey("ServiceVisitId")))
						.body("Result", not(hasKey("StandbyCount")))
						.body("Result", not(hasKey("StandbyEnrollmentOnly")))
						.body("Result", not(hasKey("UnenrollmentOperation")))
						;
	}
	
	@Test (testName="Course Not Found - Class BarcodeID Used",description="PBI:143545")
	public void courseNotFound_ClassBarcodeIDUsed() {
 
		String customerId = prop.getProperty("availableId");
		String CourseBarcodeId = prop.getProperty("alwaysAvailCl");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetails/"+customerId+"/"+CourseBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
						.body("Result", not(hasKey("EndDate")))
						.body("Result", not(hasKey("Friday")))
						.body("Result", not(hasKey("Monday")))
						.body("Result", not(hasKey("Saturday")))
						.body("Result", not(hasKey("StartDate")))
						.body("Result", not(hasKey("StartTime")))
						.body("Result", not(hasKey("Sunday")))
						.body("Result", not(hasKey("Thursday")))
						.body("Result", not(hasKey("Tuesday")))
						.body("Result", not(hasKey("Wednesday")))
						.body("Result", not(hasKey("AllowEnrollment")))
						.body("Result", not(hasKey("AllowServiceDueEnrollment")))
						.body("Result", not(hasKey("CanBePaidByPackageInCart")))
						.body("Result", not(hasKey("CancellationFee")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("ChargeCancellationFee")))
						.body("Result", not(hasKey("ClassCapacity")))
						.body("Result", not(hasKey("ClubName")))
						.body("Result", not(hasKey("ClubNumber")))
						.body("Result", not(hasKey("DurationInMinutes")))
						.body("Result", not(hasKey("EnrollmentCount")))
						.body("Result", not(hasKey("EnrollmentPaymentType")))
						.body("Result", not(hasKey("InstructorBarcodeId")))
						.body("Result", not(hasKey("InstructorName")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("MemberEnrollmentStatus")))
						.body("Result", not(hasKey("PackageEnrollmentAvailable")))
						.body("Result", not(hasKey("PackageName")))
						.body("Result", not(hasKey("Price")))
						.body("Result", not(hasKey("PunchesRemaining")))
						.body("Result", not(hasKey("PunchesRequired")))
						.body("Result", not(hasKey("RefundableAmount")))
						.body("Result", not(hasKey("RefundablePunchAmount")))
						.body("Result", not(hasKey("ServiceVisitId")))
						.body("Result", not(hasKey("StandbyCount")))
						.body("Result", not(hasKey("StandbyEnrollmentOnly")))
						.body("Result", not(hasKey("UnenrollmentOperation")))
						;
	}
	
	@Test (testName="Course Not Found - Training BarcodeID Used",description="PBI:143545")
	public void courseNotFound_TrainingBarcodeIDUsed() {
 
		String customerId = prop.getProperty("availableId");
		String CourseBarcodeId = prop.getProperty("freeT");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getcoursedetails/"+customerId+"/"+CourseBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Nothing found"))
						.body("Result", not(hasKey("EndDate")))
						.body("Result", not(hasKey("Friday")))
						.body("Result", not(hasKey("Monday")))
						.body("Result", not(hasKey("Saturday")))
						.body("Result", not(hasKey("StartDate")))
						.body("Result", not(hasKey("StartTime")))
						.body("Result", not(hasKey("Sunday")))
						.body("Result", not(hasKey("Thursday")))
						.body("Result", not(hasKey("Tuesday")))
						.body("Result", not(hasKey("Wednesday")))
						.body("Result", not(hasKey("AllowEnrollment")))
						.body("Result", not(hasKey("AllowServiceDueEnrollment")))
						.body("Result", not(hasKey("CanBePaidByPackageInCart")))
						.body("Result", not(hasKey("CancellationFee")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("ChargeCancellationFee")))
						.body("Result", not(hasKey("ClassCapacity")))
						.body("Result", not(hasKey("ClubName")))
						.body("Result", not(hasKey("ClubNumber")))
						.body("Result", not(hasKey("DurationInMinutes")))
						.body("Result", not(hasKey("EnrollmentCount")))
						.body("Result", not(hasKey("EnrollmentPaymentType")))
						.body("Result", not(hasKey("InstructorBarcodeId")))
						.body("Result", not(hasKey("InstructorName")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("MemberEnrollmentStatus")))
						.body("Result", not(hasKey("PackageEnrollmentAvailable")))
						.body("Result", not(hasKey("PackageName")))
						.body("Result", not(hasKey("Price")))
						.body("Result", not(hasKey("PunchesRemaining")))
						.body("Result", not(hasKey("PunchesRequired")))
						.body("Result", not(hasKey("RefundableAmount")))
						.body("Result", not(hasKey("RefundablePunchAmount")))
						.body("Result", not(hasKey("ServiceVisitId")))
						.body("Result", not(hasKey("StandbyCount")))
						.body("Result", not(hasKey("StandbyEnrollmentOnly")))
						.body("Result", not(hasKey("UnenrollmentOperation")))
						;
	}
	
	@Test (testName="InvalidCustomerId", description="PBI:143545")
	public void invalidCustomerId() {
	
		int customerId = 22300;
		String CourseBarcodeId = prop.getProperty("alwaysAvailCl");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getcoursedetails/"+customerId+"/"+CourseBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"))
						.body("Result", not(hasKey("EndDate")))
						.body("Result", not(hasKey("Friday")))
						.body("Result", not(hasKey("Monday")))
						.body("Result", not(hasKey("Saturday")))
						.body("Result", not(hasKey("StartDate")))
						.body("Result", not(hasKey("StartTime")))
						.body("Result", not(hasKey("Sunday")))
						.body("Result", not(hasKey("Thursday")))
						.body("Result", not(hasKey("Tuesday")))
						.body("Result", not(hasKey("Wednesday")))
						.body("Result", not(hasKey("AllowEnrollment")))
						.body("Result", not(hasKey("AllowServiceDueEnrollment")))
						.body("Result", not(hasKey("CanBePaidByPackageInCart")))
						.body("Result", not(hasKey("CancellationFee")))
						.body("Result", not(hasKey("CategoryDescription")))
						.body("Result", not(hasKey("ChargeCancellationFee")))
						.body("Result", not(hasKey("ClassCapacity")))
						.body("Result", not(hasKey("ClubName")))
						.body("Result", not(hasKey("ClubNumber")))
						.body("Result", not(hasKey("DurationInMinutes")))
						.body("Result", not(hasKey("EnrollmentCount")))
						.body("Result", not(hasKey("EnrollmentPaymentType")))
						.body("Result", not(hasKey("InstructorBarcodeId")))
						.body("Result", not(hasKey("InstructorName")))
						.body("Result", not(hasKey("ItemBarcodeId")))
						.body("Result", not(hasKey("ItemDescription")))
						.body("Result", not(hasKey("ItemId")))
						.body("Result", not(hasKey("LongDescription")))
						.body("Result", not(hasKey("MemberEnrollmentStatus")))
						.body("Result", not(hasKey("PackageEnrollmentAvailable")))
						.body("Result", not(hasKey("PackageName")))
						.body("Result", not(hasKey("Price")))
						.body("Result", not(hasKey("PunchesRemaining")))
						.body("Result", not(hasKey("PunchesRequired")))
						.body("Result", not(hasKey("RefundableAmount")))
						.body("Result", not(hasKey("RefundablePunchAmount")))
						.body("Result", not(hasKey("ServiceVisitId")))
						.body("Result", not(hasKey("StandbyCount")))
						.body("Result", not(hasKey("StandbyEnrollmentOnly")))
						.body("Result", not(hasKey("UnenrollmentOperation")))
						;
	}
}