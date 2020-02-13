package JonasFitness.API;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetAppointmentDetails extends base {
		
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	@Test (testName="AppointmentsFound",description="PBI:139310")
	public void AppointmentsFound() {
		
		int appointment = 7463;

			Response res =	given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result.BookedMembers[0]", hasKey("AttendedIndicator"))
						.body("Result.BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result.BookedMembers[0]", hasKey("CustomerCanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CancellationReason"))
						.body("Result.BookedMembers[0]", hasKey("CustomerId"))
						.body("Result.BookedMembers[0]", hasKey("DisplayName"))
						.body("Result.BookedMembers[0]", hasKey("FirstName"))
						.body("Result.BookedMembers[0]", hasKey("LastName"))
						.body("Result.BookedMembers[0]", hasKey("NoShowFeeIndicator"))
						.body("Result.BookedMembers[1]", hasKey("AppointmentOutcome"))
						.body("Result.BookedMembers[1]", hasKey("AttendedIndicator"))
						.body("Result.BookedMembers[1]", hasKey("BarcodeId"))
						.body("Result.BookedMembers[1]", hasKey("CustomerCanCancel"))
						.body("Result.BookedMembers[1].CustomerCanCancel", hasKey("CanCancel"))
						.body("Result.BookedMembers[1].CustomerCanCancel", hasKey("CancellationReason"))
						.body("Result.BookedMembers[1]", hasKey("CustomerId"))
						.body("Result.BookedMembers[1]", hasKey("DisplayName"))
						.body("Result.BookedMembers[1]", hasKey("FirstName"))
						.body("Result.BookedMembers[1]", hasKey("LastName"))
						.body("Result.BookedMembers[1]", hasKey("NoShowFeeIndicator"))
						.body("Result.BookedResources[0]", hasKey("BookDescription"))
						.body("Result.BookedResources[0]", hasKey("BookId"))
						.body("Result.BookedResources[0]", hasKey("BookName"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeDescription"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeId"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeName"))
						.body("Result.Club", hasKey("ClubId"))
						.body("Result.Club", hasKey("ClubName"))
						.body("Result.Details", hasKey("AppointmentDateTime"))
						.body("Result.Details", hasKey("AppointmentDuration"))
						.body("Result.Details", hasKey("AppointmentNotes"))
						.body("Result.Details.CanCancel", hasKey("CanCancel"))
						.body("Result.Details.CanCancel", hasKey("CancellationReason"))
						.body("Result.Details", hasKey("CancelDate"))
						.body("Result.Details", hasKey("DateAppointmentCreated"))
						.body("Result.ProductDetails", hasKey("ProductBarcodeId"))
						.body("Result.ProductDetails", hasKey("ProductCategoryDescription"))
						.body("Result.ProductDetails", hasKey("ProductDescription"))
						.body("Result.ProductDetails", hasKey("ProductId"))
						.body("Result.ProductDetails", hasKey("ProductLongDescription"))
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[0].AppointmentCharge"), 60.00);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[0].CancellationFee"), 0.00);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[0].NoShowFee"), -1.00);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[1].AppointmentCharge"), 0.00);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[1].CancellationFee"), 0.00);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[1].NoShowFee"), -1.00);		
	}
	@Test (testName="AppointmentsNotFound",description="PBI:139310")
	public void AppointmentsNotFound() {

		String appointment = prop.getProperty("appointmentInFuture1Id");  

				given()
//				.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/9"+appointment)// 9 is passed to make appointment id not on file
						.then()
//						.log().body()
						.assertThat().statusCode(404)
//						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 404 Not Found");

	}
}
