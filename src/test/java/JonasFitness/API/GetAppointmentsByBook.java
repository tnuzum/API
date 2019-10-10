package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetAppointmentsByBook extends base{

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	
	@Test 
	public void Test1() {
		String resourceId = prop.getProperty("resource1Id");
		String sDateTimeNoOffset = prop.getProperty("sDateTimeNoOffset");
		String eDateTimeNoOffset = prop.getProperty("eDateTimeNoOffset");
		
		RestAssured.useRelaxedHTTPSValidation();

		RestAssured.baseURI = prop.getProperty("baseURI"); 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/appointment/getappointmentsbybook/"+resourceId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AppointmentId"))
						.body("Result[0]", hasKey("AppointmentNotes"))
						.body("Result[0]", hasKey("CancellationDateTime"))
						.body("Result[0]", hasKey("ClubId"))
						.body("Result[0]", hasKey("ClubName"))
						.body("Result[0]", hasKey("DurationInMinutes"))
						.body("Result[0]", hasKey("ItemBarCodeId"))
						.body("Result[0]", hasKey("ItemDescription"))
						.body("Result[0]", hasKey("ItemLongDescription"))
						.body("Result[0]", hasKey("ProductCategoryDescription"))
						.body("Result[0]", hasKey("RecurringId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookDescription"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("BookName"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeDescription"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeId"))
						.body("Result[0].ScheduledAppointmentBookDTOs[0]", hasKey("ResourceTypeName"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("BarcodeId"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("CustomerId"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("DisplayName"))
						.body("Result[0].ScheduledAppointmentMemberDTOs[0]", hasKey("Outcome"))
						.body("Result[0]", hasKey("ScheduledDateTime"))
						.body("Result[0]", hasKey("ScheduledInstanceType"))
						.body("Result[0]", hasKey("StartDateTime"));
	}
}
