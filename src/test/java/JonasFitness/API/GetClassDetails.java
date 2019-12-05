package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import io.restassured.RestAssured;
import resources.base;

public class GetClassDetails extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	@Test (testName="Class Found - Online Sale Allowed",description="PBI:143544")
	public void classFoundOnlineSaleAllowed() {
 
		int customerId 			= 231;
		String ClassBarcodeId 	= "BARREF5p";
//		String ClassDateTime 	= "2019-11-28T17:00:00-05:00";
		String ClassDateTime 	= "2019-11-28";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.StartDateTime", not(nullValue()))
						.body("Result.SubstituteInstructorName", not(empty()))
						.body("Result.SubstituteInstructorBarcodeId", not(empty()))
						.body("Result.ItemDescription", not(empty()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.LongDescription", not(nullValue()))
						.body("Result.DurationInMinutes", not(nullValue()))
						.body("Result.InstructorName", not(nullValue()))
						.body("Result.InstructorBarcodeId", not(nullValue()))
						.body("Result.ClubName", not(nullValue()))
						.body("Result.ClubNumber", not(nullValue()))
						.body("Result.CategoryDescription", not(nullValue()))
						.body("Result.EnrollmentCount", not(nullValue()))
						.body("Result.StandbyCount", not(nullValue()))
						.body("Result.MemberEnrollmentStatus", not(nullValue()))
						.body("Result.ClassCapacity", not(nullValue()))
						.body("Result.StandbyEnrollmentOnly", not(nullValue()))
						.body("Result.Price", not(nullValue()))
						.body("Result.AllowEnrollment", not(nullValue()))
						.body("Result.AllowServiceDueEnrollment", not(nullValue()))
						.body("Result.PackageEnrollmentAvailable", not(nullValue()))
						.body("Result.ServiceVisitId", not(nullValue()))
						.body("Result.PunchesRequired", not(nullValue()))
						.body("Result.PunchesRemaining", not(nullValue()))
						.body("Result.PackageName", not(empty()))
						.body("Result.EnrollmentPaymentType", not(nullValue()))
						.body("Result.UnenrollmentOperation", not(nullValue()))
						.body("Result.CancellationFee", not(nullValue()))
						.body("Result.RefundableAmount", not(nullValue()))
						.body("Result.RefundablePunchAmount", not(nullValue()))
						.body("Result.ChargeCancellationFee", not(empty()))
						.body("Result.UnenrollmentAllowed", not(nullValue()))
						.body("Result.RefundToPunchAndCredit", not(nullValue()))
						.body("Result.RefundAllowed", not(nullValue()))
						.body("Result.RefundToPunchcard", not(nullValue()))
						.body("Result.RefundToCreditCard", not(nullValue()))
						.body("Result.RefundToOnAccount", not(nullValue()))
						.body("Result.EnrollmentPaidWithMoney", not(nullValue()))
						.body("Result.UnenrollmentWithoutRefund", not(nullValue()))
						.body("Result.ShowRefundMoneyAmount", not(nullValue()))
						.body("Result.ShowFormOfPayment", not(nullValue()))
						.body("Result.ShowRefundPackageVisits", not(nullValue()))
						;
	}
	@Test (testName="Class Found - Online Sale Not Allowed",description="PBI:143544")
	public void classFoundOnlineSaleNotAllowed() {
 
		int customerId 			= 231;
		String ClassBarcodeId 	= "BalanceItem";
//		String ClassDateTime 	= "2019-12-03T17:30:00-05:00";
		String ClassDateTime 	= "2019-12-03";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						;
	}
	@Test (testName="Class Not Found - Invalid ClassBarcodeID",description="PBI:143544")
	public void classNotFound_InvalidClassBarcodeID() {
 
		int customerId 			= 231;
		String ClassBarcodeId 	= "invalidClassBarcodeId";
//		String ClassDateTime 	= "2019-12-03T17:30:00-05:00";
		String ClassDateTime 	= "2025-12-03";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
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
	@Test (testName="Class Not Found - Class BarcodeID Used",description="PBI:143544")
	public void classNotFound_ClassBarcodeIDUsed() {
 
		int customerId = 223;
		String ClassBarcodeId = "Balance44";
		String ClassDateTime 	= "2025-12-03";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
						;
	}
	@Test (testName="Class Not Found - Training BarcodeID Used",description="PBI:143544")
	public void classNotFound_TrainingBarcodeIDUsed() {
 
		int customerId = 223;
		String ClassBarcodeId = "BCA";
		String ClassDateTime 	= "2025-12-03";

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
						;
	}
	@Test (testName="InvalidCustomerId", description="PBI:143544")
	public void invalidCustomerId() {
	
		int customerId = 22300;
		String ClassBarcodeId = "PBoot430";
		String ClassDateTime 	= "2025-12-03";

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+ClassDateTime+"/"+ClassBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"))
						;
	}
	
}
