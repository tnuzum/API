package unitTests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.ChangeRequestPL;
import resources.ReusableMethods;
import resources.base;
import resources.myGets;

public class UpdatePersonalInformation extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	static String customerId;
	static String familyMemberCustomerIds;
	static String submissionReason;
	static String submissionReasonDetail;
	static String signature;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		customerId = prop.getProperty("changeMemberId");
		familyMemberCustomerIds = "";
		submissionReason = prop.getProperty("submissionReason");
		submissionReasonDetail = "Test Submission Reason Details";
		signature = "null";
	}
	
	@Test (priority=1,testName="Update Address 1",description="PBI:149847")
	public void updateAddress1() {
		
		String fieldName = "Address1";
		String newValue = "1100 1st St.";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.AddressLine1"), newValue);
				
//				System.out.println("NewValue: "+newValue);
//				System.out.println("ReturnedValue: "+js2.getString("Result.Address.AddressLine1"));
	}
	
	@Test (priority=1,testName="Update Address 2",description="PBI:149847")
	public void updateAddress2() {
		
		String fieldName = "Address2";
		String newValue = "Apt. B";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.AddressLine2"), newValue);
	}
	
	@Test (priority=1,testName="Remove Address 2",description="PBI:149847")
	public void removeAddress2() {
		
		String fieldName = "Address2";
		String newValue = "";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.AddressLine2"), newValue);
	}
	
	@Test (priority=1,testName="Update City",description="PBI:149847")
	public void updateCity() {
		
		String fieldName = "City";
		String newValue = "Worthington";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.City"), newValue);
	}
	
	@Test (priority=1,testName="Update Postalcode",description="PBI:149847")
	public void updatePostalcode() {
		
		String fieldName = "ZipCode";
		String newValue = "43015";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.PostalCode"), newValue);
	}
	
	@Test (priority=1,testName="Update StateProvince",description="PBI:149847")
	public void updateStateProvince() {
		
		String fieldName = "State";
		String newValue = "OH";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.StateProvince"), newValue);
	}
	
	@Test (priority=1,testName="Update Date Of Birth",description="PBI:149847", enabled = true)
	public void updateDateOfBirth() {
		
		String fieldName = "DateOfBirth";
		String newValue = "01-Jan-1980";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				/*
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.DateOfBirth"), newValue);
				 !! disabled temporarily because the date format isn't the same in the GetMember
				 */
	}
	
	@Test (priority=1,testName="Update BarcodeId",description="PBI:149847", enabled = true)
	public void updateBarcodeId() {
		
		String fieldName = "BarcodeId";
		String newValue = "1141121";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.MemberID"), newValue);
	}
	
	@Test (priority=1,testName="Update Display Name",description="PBI:149847", enabled = true)
	public void updateDisplayName() {
		
		String fieldName = "DisplayName";
		String newValue = "Change Name";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Name.DisplayName"), newValue);
	}
	
	@Test (priority=1,testName="Update First Name",description="PBI:149847", enabled = true)
	public void updateFirstName() {
		
		String fieldName = "FirstName";
		String newValue = "Change";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Name.FirstName"), newValue);
	}
	
	@Test (priority=1,testName="Update Last Name",description="PBI:149847", enabled = true)
	public void updateLastName() {
		
		String fieldName = "LastName";
		String newValue = "Name";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Name.LastName"), newValue);
	}
	
	@Test (priority=1,testName="Update Middle Initial",description="PBI:149847", enabled = true)
	public void updateMiddleInitial() {
		
		String fieldName = "MiddleInitial";
		String newValue = "D";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Name.MiddleInitial"), newValue);
	}
	
	@Test (priority=1,testName="Update Preferred Name",description="PBI:149847", enabled = true)
	public void updatePreferredName() {
		
		String fieldName = "PreferredName";
		String newValue = "Changey";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Name.PreferredName"), newValue);
	}
	
	@Test (priority=1,testName="Update Drivers License Number",description="PBI:149847", enabled = true)
	public void updateDriversLicenseNumber() {
		
		String fieldName = "DriversLicenseNumber";
		String newValue = "ABC123456789";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.DriversLicenseNumber"), newValue);
	}
	
	@Test (priority=1,testName="Update Email Address",description="PBI:149847", enabled = true)
	public void updateEmailAddress() {
		
		String fieldName = "EmailAddress";
		String newValue = "test-compete@jonasfitness.com";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.EmailAddress"), newValue);
	}
	
	@Test (priority=1,testName="Update Email Contact Consent True",description="PBI:149847", enabled = true)
	public void updateEmailContactConsentTrue() {
		
		String fieldName = "EmailContactConsent";
		String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.EmailContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Email Contact Consent False",description="PBI:149847", enabled = true)
	public void updateEmailContactConsentFalse() {
		
		String fieldName = "EmailContactConsent";
		String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.EmailContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Emergency Contact Name",description="PBI:149847", enabled = true)
	public void updateEmergencyContactName() {
		
		String fieldName = "EmergencyContactName";
		String newValue = "Spouse Name";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.EmergencyContactName"), newValue);
	}
	
	@Test (priority=1,testName="Update Emergency Contact Phone Number",description="PBI:149847", enabled = true)
	public void updateEmergencyContactPhoneNumber() {
		
		String fieldName = "EmergencyContactPhoneNumber";
		String newValue = "6141001234";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.EmergencyContactPhoneNumber.Number"), newValue);
	}
		
	@Test (priority=1,testName="Update Home Phone Contact Consent False",description="PBI:149847", enabled = true)
	public void updateHomePhoneContactConsentFalse() {
		
		String fieldName = "HomePhoneContactConsent";
		String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.HomePhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Home Phone Contact Consent False",description="PBI:149847", enabled = true)
	public void updateHomePhoneContactConsentTrue() {
		
		String fieldName = "HomePhoneContactConsent";
		String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.HomePhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Home Phone Number",description="PBI:149847", enabled = true)
	public void updateHomePhoneNumber() {
		
		String fieldName = "HomePhone";
		String newValue = "6142001234";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.HomePhoneNumber.Number"), newValue);
	}
	
	@Test (priority=1,testName="Update Mobile Phone Contact Consent False",description="PBI:149847", enabled = true)
	public void updateMobilePhoneContactConsentFalse() {
		
		String fieldName = "MobilePhoneContactConsent";
		String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.MobilePhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Mobile Phone Contact Consent False",description="PBI:149847", enabled = true)
	public void updateMobilePhoneContactConsentTrue() {
		
		String fieldName = "MobilePhoneContactConsent";
		String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.MobilePhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Mobile Phone Number",description="PBI:149847", enabled = true)
	public void updateMobilePhoneNumber() {
		
		String fieldName = "MobilePhone";
		String newValue = "6142001234";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.MobilePhoneNumber.Number"), newValue);
	}
	
	@Test (priority=1,testName="Update Work Phone Contact Consent False",description="PBI:149847", enabled = true)
	public void updateWorkPhoneContactConsentFalse() {
		
		String fieldName = "WorkPhoneContactConsent";
		String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.WorkPhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Work Phone Contact Consent True",description="PBI:149847", enabled = true)
	public void updateWorkPhoneContactConsentTrue() {
		
		String fieldName = "WorkPhoneContactConsent";
		String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.WorkPhoneContactConsent"), newValue);
	}
	
	@Test (priority=1,testName="Update Work Phone Number",description="PBI:149847", enabled = true)
	public void updateWorkPhoneNumber() {
		
				String fieldName = "WorkPhone";
				String newValue = "61430001000";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.WorkPhoneNumber.Number"), newValue);
	}
	
	@Test (priority=1,testName="Update Work Phone Extension",description="PBI:149847", enabled = true)
	public void updateWorkPhoneExtension() {
		
				String fieldName = "WorkPhoneExtension";
				String newValue = "111  ";	// 2 spaces needed after extension because field return for validation has 2 spaces at end of number
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.WorkPhoneNumber.Extension"), newValue);
	}
	
	@Test (priority=1,testName="Update Preferred Phone Home",description="PBI:149847", enabled = true)
	public void updatePreferredPhoneHome() {
		
				String fieldName = "PreferredPhone";
				String newValue = "Home";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.PreferredPhoneType"), newValue);
	}
	
	@Test (priority=1,testName="Update Preferred Phone Mobile",description="PBI:149847", enabled = true)
	public void updatePreferredPhoneMobile() {
		
				String fieldName = "PreferredPhone";
				String newValue = "Mobile";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.PreferredPhoneType"), newValue);
	}
		
	@Test (priority=1,testName="Update Restrict Member From Multi-Member Search to True",description="PBI:149847", enabled = true)
	public void updateRestrictMemberFromMultiMemberSearchTrue() {
		
				String fieldName = "RestrictMemberFromSearch";
				String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.RestrictMemberFromSearch"), newValue);
	}
	
	@Test (priority=1,testName="Update Restrict Member From Multi-Member Search to False",description="PBI:149847", enabled = true)
	public void updateRestrictMemberFromMultiMemberSearchFalse() {
		
				String fieldName = "RestrictMemberFromSearch";
				String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.RestrictMemberFromSearch"), newValue);
	}
	
	@Test (priority=1,testName="Update AllowOnlineSearch to True",description="PBI:149847", enabled = true)
	public void updateAllowOnlineSearchTrue() {
		
				String fieldName = "AllowOnlineSearch";
				String newValue = "true";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
				// Can't validate now because this field is not returned in the GetMember call
				
//		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
//		
//				JsonPath js2 = ReusableMethods.rawToJson(res2);
//				
//				Assert.assertEquals(js2.getString("Result.RestrictMemberFromSearch"), newValue);
	}
	
	@Test (priority=1,testName="Update AllowOnlineSearch to False",description="PBI:149847", enabled = true)
	public void updateAllowOnlineSearchFalse() {
		
				String fieldName = "AllowOnlineSearch";
				String newValue = "false";
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
				// Can't validate now because this field is not returned in the GetMember call
				
//		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
//		
//				JsonPath js2 = ReusableMethods.rawToJson(res2);
//				
//				Assert.assertEquals(js2.getString("Result.RestrictMemberFromSearch"), newValue);
	}
	
	@Test (priority=1,testName="Update Address 1 Family",description="PBI:149847")
	public void updateAddress1Family() {
		
		String fieldName = "Address1";
		String newValue = "1201 1st St.";
		String familyMemberCustomerIds = prop.getProperty("familyMemberCustomerId");
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				Assert.assertTrue(res.getTime() >= 60L);
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly for Primary member
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				Assert.assertEquals(js2.getString("Result.Address.AddressLine1"), newValue);
				
				// ** Validate field was updated correctly for Family member
		Response res3	=	myGets.getMember(aPIKey, companyId, clubId, familyMemberCustomerIds);
				
				JsonPath js3 = ReusableMethods.rawToJson(res3);
				Assert.assertEquals(js3.getString("Result.Address.AddressLine1"), newValue);
	}
	
	@Test (priority=1,testName="Update Address 2 Family",description="PBI:149847")
	public void updateAddress2Family() {
		
		String fieldName = "Address2";
		String newValue = "Apt. B";
		String familyMemberCustomerIds = prop.getProperty("familyMemberCustomerId");
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.AddressLine2"), newValue);
				
				// ** Validate field was updated correctly for Family member
		Response res3	=	myGets.getMember(aPIKey, companyId, clubId, familyMemberCustomerIds);
				
				JsonPath js3 = ReusableMethods.rawToJson(res3);
				Assert.assertEquals(js3.getString("Result.Address.AddressLine2"), newValue);
	}
	
	@Test (priority=1,testName="Update City Family",description="PBI:149847")
	public void updateCityFamily() {
		
		String fieldName = "City";
		String newValue = "Worthington";
		String familyMemberCustomerIds = prop.getProperty("familyMemberCustomerId");
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.City"), newValue);
				
				// ** Validate field was updated correctly for Family member
		Response res3	=	myGets.getMember(aPIKey, companyId, clubId, familyMemberCustomerIds);
				
				JsonPath js3 = ReusableMethods.rawToJson(res3);
				Assert.assertEquals(js3.getString("Result.Address.City"), newValue);
	}
	
	@Test (priority=1,testName="Update Postalcode Family",description="PBI:149847")
	public void updatePostalcodeFamily() {
		
				String fieldName = "ZipCode";
				String newValue = "43015";
				String familyMemberCustomerIds = prop.getProperty("familyMemberCustomerId");
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.PostalCode"), newValue);
				
				// ** Validate field was updated correctly for Family member
		Response res3	=	myGets.getMember(aPIKey, companyId, clubId, familyMemberCustomerIds);
				
				JsonPath js3 = ReusableMethods.rawToJson(res3);
				Assert.assertEquals(js3.getString("Result.Address.PostalCode"), newValue);
	}
	
	@Test (priority=1,testName="Update StateProvince Family",description="PBI:149847")
	public void updateStateProvinceFamily() {
		
				String fieldName = "State";
				String newValue = "OH";
				String familyMemberCustomerIds = prop.getProperty("familyMemberCustomerId");
		
		Response res = 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(200)			
				.body("Status", equalTo(200))
				.extract().response();	
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(res.getTime() >= 60L);
				
				Assert.assertTrue(js.getInt("Result.AutoApprovedConfirmationNumber") > 0);
				Assert.assertEquals(js.getString("Result.ErrorMessages"),"None");
				Assert.assertNull(js.getString("Result.PendingConfirmationNumber"));
				
				// ** Validate field was updated correctly
		Response res2	=	myGets.getMember(aPIKey, companyId, clubId, customerId);
		
				JsonPath js2 = ReusableMethods.rawToJson(res2);
				
				Assert.assertEquals(js2.getString("Result.Address.StateProvince"), newValue);
				
				// ** Validate field was updated correctly for Family member
		Response res3	=	myGets.getMember(aPIKey, companyId, clubId, familyMemberCustomerIds);
				
				JsonPath js3 = ReusableMethods.rawToJson(res3);
				Assert.assertEquals(js3.getString("Result.Address.StateProvince"), newValue);
	}
	
	@Test (priority=1,testName="CustomerId Required",description="PBI:149847")
	public void customerIdRequired() {
		
		String fieldName = "Address1";
		String newValue = "1100 1st St.";
		String customerId = prop.getProperty("NOF"); 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(400)	
				.statusLine("HTTP/1.1 400 Bad Request");
	}
	
	@Test (priority=1,testName="Field Name Required",description="PBI:149847")
	public void fieldNameRequired() {
		
		String fieldName = prop.getProperty("NOF");
		String newValue = "1100 1st St."; 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
//				.log().all()
				.assertThat().statusCode(400)	
				.statusLine("HTTP/1.1 400 Bad Request");
	}
	
	@Test (priority=1,testName="First Name Required",description="PBI:149847", enabled = false)
	public void firstNameRequired() {
		
		String fieldName =  "FirstName";
		String newValue = ""; 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
				.log().all()
				.assertThat().statusCode(400)	
				.statusLine("HTTP/1.1 400 Bad Request");
	}
	
	@Test (priority=1,testName="Last Name Required",description="PBI:149847", enabled = false)
	public void LastNameRequired() {
		
		String fieldName =  "LastName";
		String newValue = ""; 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
				.log().all()
				.assertThat().statusCode(400)	
				.statusLine("HTTP/1.1 400 Bad Request");
	}
	
	@Test (priority=1,testName="BarcodeId Required",description="PBI:149847", enabled = false)
	public void BarcodeIdRequired() {
		
		String fieldName =  "BarcodeId";
		String newValue = ""; 

			given()
//				.log().all()
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(ChangeRequestPL.updatePersonalInformation(familyMemberCustomerIds, customerId, fieldName, newValue, submissionReason, submissionReasonDetail, signature))
				.post("/api/v3/changerequest/updatepersonalinformation").
			then()
				.log().all()
				.assertThat().statusCode(400)	
				.statusLine("HTTP/1.1 400 Bad Request");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
