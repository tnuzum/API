package unitTests;

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
	
	static String aPIKey;
	static String companyId;
	static String clubId;
		
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="Future Appointment",description="PBI:139310")
	public void futureAppointment() {
		
		String appointmentId = prop.getProperty("futureAppointment");

			Response res =	given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
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
						.body("Result.ProductDetails", hasKey("ProductId"))
						.body("Result.ProductDetails", hasKey("ProductCategoryDescription"))
						.body("Result.ProductDetails", hasKey("ProductDescription"))
						.body("Result.ProductDetails", hasKey("ProductId"))
						.body("Result.ProductDetails", hasKey("ProductLongDescription"))
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
					Assert.assertEquals(js.getString("Result.BookedMembers[0].AppointmentOutcome"), "Future");
					Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].AttendedIndicator"), false);
					Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].CustomerCanCancel.CanCancel"), false);
					Assert.assertEquals(js.getString("Result.BookedMembers[0].CustomerCanCancel.CancellationReason"), "ProductConfiguration");		
	}
	
	@Test (testName="Attended Past Appointment",description="PBI:139310")
	public void attendedPastApointment() {
		
		String appointmentId = prop.getProperty("attendedPastApointment");

			Response res =	given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].AppointmentOutcome"), "Attended");
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].AttendedIndicator"), false);
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].CustomerCanCancel.CanCancel"), false);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].CustomerCanCancel.CancellationReason"), "TimeRestriction");
	}
	
	@Test (testName="No Show",description="PBI:139310")
	public void noShow() {
		
		String appointmentId = prop.getProperty("noShow");

			Response res =	given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].AppointmentOutcome"), "NoShow");
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].AttendedIndicator"), false);
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].CustomerCanCancel.CanCancel"), false);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].CustomerCanCancel.CancellationReason"), "TimeRestriction");
	}
	
	@Test (testName="Not Primary Appointment Member",description="PBI:139310")
	public void notPrimaryApptMember() {
		
			String appointmentId = prop.getProperty("futureAppointment");

			Response res =	given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
					Assert.assertEquals(js.getBoolean("Result.BookedMembers[1].CustomerCanCancel.CanCancel"), false);
					Assert.assertEquals(js.getString("Result.BookedMembers[1].CustomerCanCancel.CancellationReason"), "NotPrimaryAppointmentMember");		
	}
	
	@Test (testName="Canceled Appointment",description="PBI:139310")
	public void cancelledApointment() {
		
		String appointment = prop.getProperty("cancelledApointment");

			Response res =	given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.extract().response();
			
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].AppointmentOutcome"), "Cancelled");
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].AttendedIndicator"), false);
						Assert.assertEquals(js.getDouble("Result.BookedMembers[0].CancellationFee"), 2.00);
						Assert.assertEquals(js.getBoolean("Result.BookedMembers[0].CustomerCanCancel.CanCancel"), false);
						Assert.assertEquals(js.getString("Result.BookedMembers[0].CustomerCanCancel.CancellationReason"), "AppointmentCancelled");
						Assert.assertEquals(js.getBoolean("Result.Details.CanCancel.CanCancel"), false);
						Assert.assertEquals(js.getString("Result.Details.CanCancel.CancellationReason"), "AppointmentCancelled");
						Assert.assertEquals(js.getString("Result.Details.CancelDate"), "2020-03-23T09:19:37.95-04:00");
	}
	
	@Test (testName="AppointmentsNotFound",description="PBI:139310")
	public void AppointmentsNotFound() {

				String appointmentId = "999999";  

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 404 Not Found");
	}
	
	@Test (testName="AppointmentId Required",description="PBI:139310")
	public void appointmentIdRequired() {

		String appointmentId = prop.getProperty("NOTappointmentId");  

				given()
//				.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key",aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointmentId)
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Message", equalTo("The value 'null' is not valid for AppointmentId."));
	}
}
