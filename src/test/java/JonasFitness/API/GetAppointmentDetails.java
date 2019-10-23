package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetAppointmentDetails extends base {
		
// https://compete-api-future2.test-jfisoftware.com:8252/api/v3/appointment/getappointmentdetails/16362
	
	
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI"); 
	}
	
	@Test (testName="AppointmentsFound",description="PBI:139310")
	public void AppointmentsFound() {
		
		String appointment = prop.getProperty("appointmentInFuture1Id");

				given()
//						.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/"+appointment)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.BookedMembers[0]", hasKey("AppointmentCharge"))
						.body("Result.BookedMembers[0]", hasKey("AppointmentOutcome"))
						.body("Result.BookedMembers[0]", hasKey("AttendedIndicator"))
						.body("Result.BookedMembers[0]", hasKey("BarcodeId"))
						.body("Result.BookedMembers[0]", hasKey("CancellationFee"))
						.body("Result.BookedMembers[0]", hasKey("CustomerCanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CanCancel"))
						.body("Result.BookedMembers[0].CustomerCanCancel", hasKey("CancellationReason"))
						.body("Result.BookedMembers[0]", hasKey("CustomerId"))
						.body("Result.BookedMembers[0]", hasKey("DisplayName"))
						.body("Result.BookedMembers[0]", hasKey("FirstName"))
						.body("Result.BookedMembers[0]", hasKey("LastName"))
						.body("Result.BookedMembers[0]", hasKey("NoShowFee"))
						.body("Result.BookedMembers[0]", hasKey("NoShowFeeIndicator"))
						.body("Result.BookedMembers[1]", hasKey("AppointmentCharge"))
						.body("Result.BookedMembers[1]", hasKey("AppointmentOutcome"))
						.body("Result.BookedMembers[1]", hasKey("AttendedIndicator"))
						.body("Result.BookedMembers[1]", hasKey("BarcodeId"))
						.body("Result.BookedMembers[1]", hasKey("CancellationFee"))
						.body("Result.BookedMembers[1]", hasKey("CustomerCanCancel"))
						.body("Result.BookedMembers[1].CustomerCanCancel", hasKey("CanCancel"))
						.body("Result.BookedMembers[1].CustomerCanCancel", hasKey("CancellationReason"))
						.body("Result.BookedMembers[1]", hasKey("CustomerId"))
						.body("Result.BookedMembers[1]", hasKey("DisplayName"))
						.body("Result.BookedMembers[1]", hasKey("FirstName"))
						.body("Result.BookedMembers[1]", hasKey("LastName"))
						.body("Result.BookedMembers[1]", hasKey("NoShowFee"))
						.body("Result.BookedMembers[1]", hasKey("NoShowFeeIndicator"))
						.body("Result.BookedResources[0]", hasKey("BookDescription"))
						.body("Result.BookedResources[0]", hasKey("BookId"))
						.body("Result.BookedResources[0]", hasKey("BookName"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeDescription"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeId"))
						.body("Result.BookedResources[0]", hasKey("ResourceTypeName"))
						.body("Result.BookedResources[1]", hasKey("BookDescription"))
						.body("Result.BookedResources[1]", hasKey("BookId"))
						.body("Result.BookedResources[1]", hasKey("BookName"))
						.body("Result.BookedResources[1]", hasKey("ResourceTypeDescription"))
						.body("Result.BookedResources[1]", hasKey("ResourceTypeId"))
						.body("Result.BookedResources[1]", hasKey("ResourceTypeName"))
						.body("Result.BookedResources[2]", hasKey("BookDescription"))
						.body("Result.BookedResources[2]", hasKey("BookId"))
						.body("Result.BookedResources[2]", hasKey("BookName"))
						.body("Result.BookedResources[2]", hasKey("ResourceTypeDescription"))
						.body("Result.BookedResources[2]", hasKey("ResourceTypeId"))
						.body("Result.BookedResources[2]", hasKey("ResourceTypeName"))
						.body("Result.Club", hasKey("ClubId"))
						.body("Result.Club", hasKey("ClubName"))
						
						.body("Result.Club.Details", hasKey("AppointmentDateTime"))
						.body("Result.Club.Details", hasKey("AppointmentDuration"))
						.body("Result.Club.Details", hasKey("AppointmentNotes"))
						.body("Result.Club.Details.CanCancel", hasKey("CanCancel"))
						.body("Result.Club.Details.CanCancel", hasKey("CancellationReason"))
						.body("Result.Club.Details", hasKey("CancelDate"))
						.body("Result.Club.Details", hasKey("DateAppointmentCreated"))
						.body("Result.Club.ProductDetails", hasKey("ProductBarcodeId"))
						.body("Result.Club.ProductDetails", hasKey("ProductCategoryDescription"))
						.body("Result.Club.ProductDetails", hasKey("ProductDescription"))
						.body("Result.Club.ProductDetails", hasKey("ProductId"))
						.body("Result.Club.ProductDetails", hasKey("ProductLongDescription"))

// Assert values returned are correct	
						
						.body("Result.BookedMembers[0].AppointmentCharge", equalTo(0))
						.body("Result.BookedMembers[0].AppointmentOutcome", equalTo("Future"))
						.body("Result.BookedMembers[0].AttendedIndicator", equalTo(false))
						.body("Result.BookedMembers[0].BarcodeId", equalTo("5651"))
						.body("Result.BookedMembers[0].CancellationFee", equalTo(3))
						.body("Result.BookedMembers[0].CustomerCanCancel.CanCancel", equalTo(false))
						.body("Result.BookedMembers[0].CustomerCanCancel.CancellationReason", equalTo("NotPrimaryAppointmentMember"))
						.body("Result.BookedMembers[0]CustomerId", equalTo(29970))
						.body("Result.BookedMembers[0].DisplayName", equalTo("Auto, Scott"))
						.body("Result.BookedMembers[0].FirstName", equalTo("Scott"))
						.body("Result.BookedMembers[0].LastName", equalTo("Auto"))
						.body("Result.BookedMembers[0].NoShowFee", equalTo(5))
						.body("Result.BookedMembers[0].NoShowFeeIndicator", equalTo(false))
						.body("Result.BookedMembers[1].AppointmentCharge", equalTo(150))
						.body("Result.BookedMembers[1].AppointmentOutcome", equalTo("Future"))
						.body("Result.BookedMembers[1].AttendedIndicator", equalTo(false))
						.body("Result.BookedMembers[1].BarcodeId", equalTo("5670"))
						.body("Result.BookedMembers[1].CancellationFee", equalTo(3))
						.body("Result.BookedMembers[1].CustomerCanCancel.CanCancel", equalTo(true))
						.body("Result.BookedMembers[1].CustomerCanCancel.CancellationReason", equalTo("NoCancellationFeeApplies"))
						.body("Result.BookedMembers[1]CustomerId", equalTo(29992))
						.body("Result.BookedMembers[1].DisplayName", equalTo("Auto, Paul"))
						.body("Result.BookedMembers[1].FirstName", equalTo("Paul"))
						.body("Result.BookedMembers[1].LastName", equalTo("Auto"))
						.body("Result.BookedMembers[1].NoShowFee", equalTo(5))
						.body("Result.BookedMembers[1].NoShowFeeIndicator", equalTo(false))
						.body("Result.BookedResources[0].BookDescription", equalTo("Employee, Golf Instructor, Personal Trainer"))
						.body("Result.BookedResources[0].BookId", equalTo(221))
						.body("Result.BookedResources[0].BookName", equalTo("Campbell, Samual"))
						.body("Result.BookedResources[0].ResourceTypeDescription", nullValue())
						.body("Result.BookedResources[0].ResourceTypeId", equalTo(147))
						.body("Result.BookedResources[0].ResourceTypeName", equalTo("Golf Instructors"))
						.body("Result.BookedResources[1].BookDescription", nullValue())
						.body("Result.BookedResources[1].BookId", equalTo(226))
						.body("Result.BookedResources[1].BookName", equalTo("Driving Range 2"))
						.body("Result.BookedResources[1].ResourceTypeDescription", equalTo("Used for Golf Lessons"))
						.body("Result.BookedResources[1].ResourceTypeId", equalTo(148))
						.body("Result.BookedResources[1].ResourceTypeName", equalTo("Golf Practice Areas"))
						.body("Result.BookedResources[2].BookDescription", equalTo("Rental Golf Clubs used for Golf Lessons"))
						.body("Result.BookedResources[2].BookId", equalTo(224))
						.body("Result.BookedResources[2].BookName", equalTo("Golf Clubs"))
						.body("Result.BookedResources[2].ResourceTypeDescription", equalTo("Used for Golf Lessons"))
						.body("Result.BookedResources[2].ResourceTypeId", equalTo(149))
						.body("Result.BookedResources[2].ResourceTypeName", equalTo("Golf Equipment"))
						.body("Result.Club.ClubId", equalTo(1))
						.body("Result.Club.ClubName", equalTo("Club Number One"))
						.body("Result.Club.Details.AppointmentDateTime", equalTo("2019-10-25T16:00:00-04:00"))
						.body("Result.Club.Details.AppointmentDuration", equalTo(60))
						.body("Result.Club.Details.AppointmentNotes", equalTo("MSS Appointment"))
						.body("Result.Club.Details.CanCancel.CanCancel", equalTo(true))
						.body("Result.Club.Details.CanCancel.CancellationReason", equalTo("NoCancellationFeeApplies"))
						.body("Result.Club.Details.CancelDate", nullValue())
						.body("Result.Club.Details.DateAppointmentCreated", equalTo("2019-10-18T14:21:30.303-04:00"))
						.body("Result.Club.ProductDetails.ProductBarcodeId", equalTo("st101"))
						.body("Result.Club.ProductDetails.ProductCategoryDescription", equalTo("Golf Lessons"))
						.body("Result.Club.ProductDetails.ProductDescription", equalTo("TODDGolf Swing Training"))
						.body("Result.Club.ProductDetails.ProductId", equalTo(4477))
						.body("Result.Club.ProductDetails.ProductLongDescription", nullValue())
						;			
				System.out.println("todd was here :D");
	}
	@Test (testName="AppointmentsNotFound",description="PBI:139310")
	public void AppointmentsNotFound() {

		String appointment = prop.getProperty("appointmentInFuture1Id");  

				given()
//				.log().all()
						.header("accept", prop.getProperty("accept"))
						.header("X-Api-Key", prop.getProperty("X-Api-Key"))
						.header("X-CompanyId", prop.getProperty("X-CompanyId"))
						.header("X-ClubId", prop.getProperty("X-ClubId"))
					.when()
						.get("/api/v3/appointment/getappointmentdetails/9"+appointment)// 9 is passed to make appointment id not on file
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.statusLine("HTTP/1.1 404 Not Found");

	}
}
