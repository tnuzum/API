package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.BusinessIntelligencePL;
import resources.ReusableMethods;
import resources.base;
import resources.myGets;

public class Vita_SetProjectVitaConfiguration extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	static String clubName;
	static String additionalClub1Id;
	static String bICompanyId = prop.getProperty("X-CompanyId");
	static String timeFrame;
	static String timeFrameUnits;
	static String isActivated;
	static String clubIsSelected;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		clubName = prop.getProperty("club1Name");
		additionalClub1Id = prop.getProperty("X-Club2Id");
		bICompanyId = prop.getProperty("X-CompanyId");
		timeFrame = "1";
		timeFrameUnits = "1";
		isActivated = "true";
		clubIsSelected = "true";
		
	}
	
	@Test (testName="Set All Parameters Test 1",description="PBI:164440")
	public void setAllParametersTest1() {
		
				String clubIsSelected = "false";
				String bICompanyId = prop.getProperty("X-CompanyId");
				String timeFrame = "5";
				String timeFrameUnits = "5";
				String isActivated = "false";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_AllParameters(companyId, clubId, clubName, clubIsSelected, bICompanyId, timeFrame, timeFrameUnits, isActivated))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubId"), clubId);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubName"), clubName);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].IsSelected"), clubIsSelected);
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframe"), timeFrame);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframeUnits"), timeFrameUnits);
				Assert.assertEquals(js.getString("Result.IsActivated"), isActivated);	
	}	
	
	@Test (testName="Set All Parameters Test 2",description="PBI:164440")
	public void setAllParametersTest2() {
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_AllParameters(companyId, clubId, clubName, clubIsSelected, bICompanyId, timeFrame, timeFrameUnits, isActivated))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubId"), clubId);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubName"), clubName);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].IsSelected"), clubIsSelected);
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframe"), timeFrame);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframeUnits"), timeFrameUnits);
				Assert.assertEquals(js.getString("Result.IsActivated"), isActivated);
	}	
	
	@Test (testName="Set All Parameters Multiple Clubs",description="PBI:164440")
	public void setAllParametersMultipleClubs() {
		
				String club2Id = prop.getProperty("club2Id");
				String club2Name = prop.getProperty("club2Name");
				String club2IsSelected = "true";
				String clubIsSelected = "false";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_MultipleClubs(companyId, clubId, clubName, clubIsSelected, club2Id, club2Name, club2IsSelected, bICompanyId, timeFrame, timeFrameUnits, isActivated))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubId"), clubId);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].ClubName"), clubName);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].IsSelected"), clubIsSelected);
				Assert.assertEquals(js.getString("Result.ClubSelection[1].ClubId"), club2Id);
				Assert.assertEquals(js.getString("Result.ClubSelection[1].ClubName"), club2Name);
				Assert.assertEquals(js.getString("Result.ClubSelection[1].IsSelected"), club2IsSelected);
	}	
	
	@Test (testName="Company Is Activated",description="PBI:164440")
	public void companyIsActivated() {
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_IsActivated(companyId, isActivated))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.IsActivated"), isActivated);
	}	
	
	@Test (testName="Company Is Deactivated",description="PBI:164440")
	public void companyIsDeactivated() {
		
				String isActivated = "false";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_IsActivated(companyId, isActivated))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(js.getBoolean("Result.ClubSelection[0].IsSelected"), false); //this is set to false when company is deactivated
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.IsActivated"), isActivated);
	}	
	
	@Test (testName="Set Time Frame",description="PBI:164440")
	public void setTimeFrame() {
		
				String timeFrame = "7";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_TimeFrame(companyId, timeFrame))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframe"), timeFrame);
	}	
	
	@Test (testName="Set Time Frame Units",description="PBI:164440")
	public void setTimeFrameUnits() {
		
				String timeFrameUnits = "2";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_TimeFrameUnits(companyId, timeFrameUnits))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.DataStorageTimeframeUnits"), timeFrameUnits);
	}	
	
	@Test (testName="Club Is Not Selected",description="PBI:164440")
	public void clubIsNotSelected() {
		
				String clubIsSelected = "false";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_ClubIsSelected(companyId, clubId, clubIsSelected))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].IsSelected"), clubIsSelected);
	}	
	
	@Test (testName="Club Is Selected",description="PBI:164440")
	public void clubIsSelected() {
		
				String clubIsSelected = "true";
				
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(BusinessIntelligencePL.SetBusinessIntelligenceConfiguration_ClubIsSelected(companyId, clubId, clubIsSelected))
				.post("api/v3/businessintelligence/setbusinessintelligenceconfiguration")
			.then()
//			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("Status", equalTo(200));

				// ** Validate field was updated correctly
				
		Response res	=	myGets.getProjectConfiguration(aPIKey, companyId, clubId);
	
				JsonPath js = ReusableMethods.rawToJson(res);
			
				Assert.assertEquals(js.getString("Result.CompanyId"), companyId);
				Assert.assertEquals(js.getString("Result.ClubSelection[0].IsSelected"), clubIsSelected);
	}	

	
	
}
