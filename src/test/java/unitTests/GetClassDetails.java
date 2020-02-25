package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetClassDetails extends base{

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Class Found - Online Sale Allowed",description="PBI:143544")
	public void classFoundOnlineSaleAllowed() {
 
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

			Response res = given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.StartDateTime", not(nullValue()))
						.body("Result.SubstituteInstructorName", not(empty()))
						.body("Result.SubstituteInstructorBarcodeId", not(empty()))
						.body("Result.ItemDescription", not(empty()))
						.body("Result.ItemBarcodeId", not(nullValue()))
						.body("Result.ItemId", not(nullValue()))
						.body("Result.LongDescription", not(nullValue()))
						.body("Result.DurationInMinutes", not(nullValue()))
						.body("Result", hasKey("InstructorName"))
						.body("Result", hasKey("InstructorBarcodeId"))
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
						.extract().response();
				
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.Price"), 10.00);
						Assert.assertEquals(js.getDouble("Result.CancellationFee"), 0.0);
						Assert.assertEquals(js.getDouble("Result.RefundableAmount"), 0.0);
	}
	
	@Test (testName="Class Found - Online Sale Not Allowed",description="PBI:143544")
	public void classFoundOnlineSaleNotAllowed() {
			// this call returns all class, so this test returns the same response as the first test
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("noWebClBarcodeId");
				String classOccurrence = prop.getProperty("noWebClOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						;
	}
	
	@Test (testName="Class Not Found - Invalid ClassBarcodeID",description="PBI:143544")
	public void classNotFound_InvalidClassBarcodeID() {
 
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classBarcodeId = prop.getProperty("NOTalwaysAvailClBarcodeId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
	
	@Test (testName="Class Not Found - Course BarcodeID Used",description="PBI:143544")
	public void classNotFound_CourseBarcodeIDUsed() {
 
		String c = prop.getProperty("availableId");
		int customerId = Integer.parseInt(c);
		String classBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
		String classOccurrence = prop.getProperty("alwaysAvailCoOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
						;
	}
	
	@Test (testName="Class Not Found - Training BarcodeID Used",description="PBI:143544")
	public void classNotFound_TrainingBarcodeIDUsed() {
 
//		int customerId = 223;
//		String ClassBarcodeId = "BCA";
//		String ClassDateTime 	= "2025-12-03";
		String c = prop.getProperty("availableId");
		int customerId = Integer.parseInt(c);
		String classBarcodeId = prop.getProperty("freeTId");
		String classOccurrence = prop.getProperty("freeTOccurrence");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Class not found"))
						;
	}
	
	@Test (testName="InvalidCustomerId", description="PBI:143544")
	public void invalidCustomerId() {
	
		int customerId = 22300;
//		String ClassBarcodeId = "PBoot430";
//		String ClassDateTime 	= "2025-12-03";
		String classBarcodeId = prop.getProperty("alwaysAvailCoBarcodeId");
		String classOccurrence = prop.getProperty("alwaysAvailCoOccurrence");

				given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
					.get("/api/v3/classcourse/getclassdetails/"+customerId+"/"+classOccurrence+"/"+classBarcodeId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("Customer not found"))
						;
	}
	
}