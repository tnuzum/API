package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.CustomerInfoPL;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class CreateMemberNotes extends base{
	
		static String aPIKey;
		static String companyId;
		static String actionId;
		static String alertOnCheckIn;
		static String clubId;
		static String customerId;
		static String doNotDisplayInFrontDesk;
		static String employeeId;
		static String note;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		 actionId = "1";
		 alertOnCheckIn ="false";
		 clubId = prop.getProperty("club1Id");
		 customerId = prop.getProperty("changeMemberId");
		 doNotDisplayInFrontDesk = "false";
		 employeeId = prop.getProperty("activeEmployeeId");
		 note = "TN This is a test note created on "+ReusableDates.getCurrentDate();
	}
	
	@Test  (testName="Member Notes Created", description="PBI:165463")
	public void memberNotesCreated() {
									
			Response res = 

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(200)
						.body("Status", equalTo(200))
						.extract().response();
			
						JsonPath js = ReusableMethods.rawToJson(res);		
						
						Assert.assertTrue(res.getTime() >= 60L);
						Assert.assertEquals(js.getInt("Status"), 200);
						Assert.assertTrue(js.getInt("NoteId") > 1);
	}
	
	@Test  (testName="Note Alert On Checkin", description="PBI:165463")
	public void noteAlertOnCheckin() {
		
					String alertOnCheckIn = "true";
							
			Response res = 

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(200)
						.body("Status", equalTo(200))
						.extract().response();
			
						JsonPath js = ReusableMethods.rawToJson(res);		
						
						Assert.assertTrue(res.getTime() >= 60L);
						Assert.assertEquals(js.getInt("Status"), 200);
						Assert.assertTrue(js.getInt("NoteId") > 1);
	}
	
	@Test  (testName="Note Do Not Display In Front Desk", description="PBI:165463")
	public void doNotDisplayInFrontDesk() {
		
					String doNotDisplayInFrontDesk = "true";
							
			Response res = 

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(200)
						.body("Status", equalTo(200))
						.extract().response();
			
						JsonPath js = ReusableMethods.rawToJson(res);		
						
						Assert.assertTrue(res.getTime() >= 60L);
						Assert.assertEquals(js.getInt("Status"), 200);
						Assert.assertTrue(js.getInt("NoteId") > 1);
	}
	
	@Test  (testName="Action Not Found", description="PBI:165463", enabled = true)
	public void actionNotFound() {
		
						String actionId = "99999";		

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(500);
	}
	
	@Test  (testName="Action Null", description="PBI:165463", enabled = true)
	public void actionNull() {
		
						String actionId = prop.getProperty("NOF");
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(400);
	}
	
	@Test  (testName="Alert On CheckIn Null", description="PBI:165463", enabled = true)
	public void alertOnCheckInNull() {
		
	// ** this parameter defaults to FALSE when null value is passed **
		
						String alertOnCheckIn = prop.getProperty("NOF");
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(200);
	}
	
	@Test  (testName="Customer Null", description="PBI:165463", enabled = true)
	public void customerNull() {
				
						String customerId = prop.getProperty("NOF");
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(400);
	}
	
	@Test  (testName="Customer Not Found", description="PBI:165463", enabled = true)
	public void customerNotFound() {
				
						String customerId = "99999";
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(500);
	}
	
	@Test  (testName="Do Not Display In Front Desk Null", description="PBI:165463", enabled = true)
	public void doNotDisplayInFrontDeskNull() {
		
		// ** this parameter defaults to FALSE when null value is passed **
				
						String doNotDisplayInFrontDesk = prop.getProperty("NOF");
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(200);
	}
	
	@Test  (testName="Employee Not Found", description="PBI:165463", enabled = true)
	public void employeeNotFound() {
		
						String employeeId = "99999";
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(500);
	}
	
	@Test  (testName="Employee Null", description="PBI:165463", enabled = true)
	public void employeeNull() {
		
						String employeeId = prop.getProperty("NOF");
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(400);
	}
	
	@Test  (testName="Note Empty", description="PBI:165463", enabled = true)
	public void noteEmpty() {
		
						String note ="";
							
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("Content-Type", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.body(CustomerInfoPL.CreateMemberNotes(customerId, clubId, employeeId, actionId, note, alertOnCheckIn, doNotDisplayInFrontDesk))
						.post("/api/v3/member/createmembernotes")
					.then()
//						.log().all()
						.assertThat()
						.statusCode(400)
						.body("Message", equalTo("The Note field is required."));
	}
	
	
	
	
	
	
}
