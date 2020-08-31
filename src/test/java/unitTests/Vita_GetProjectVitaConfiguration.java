package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class Vita_GetProjectVitaConfiguration extends base {
	
	static String aPIKey;
	static String companyId;
	static int clubId;
	static String clubName;
	static Boolean isSelected;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		String	c = prop.getProperty("X-Club1Id");
		clubId = Integer.parseInt(c);
		clubName = prop.getProperty("club1Name");
		isSelected = true;
		
	}
	
	@Test (testName="Get Project Configuration",description="PBI:164976")
	public void getProjectConfiguration() {
		
			Response res = 
				
			given()
				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key",aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.get("/api/v3/businessintelligence/getbusinessintelligenceconfiguration")
			.then()
			    .log().all()
			    .time(lessThan(60L),TimeUnit.SECONDS)
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.extract().response();
			
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertNotNull(js.getString("Result.ClubSelection[0].ClubId"));
				Assert.assertNotNull(js.getString("Result.ClubSelection[0].ClubName"));
				Assert.assertNotNull(js.getString("Result.ClubSelection[0].IsSelected"));
				Assert.assertNotNull(js.getString("Result.DataStorageTimeframe"));
				Assert.assertNotNull(js.getString("Result.DataStorageTimeframeUnits"));
				Assert.assertNotNull(js.getString("Result.IsActivated"));
	}	
}
