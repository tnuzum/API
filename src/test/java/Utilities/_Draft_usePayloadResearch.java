package Utilities;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Payload;
import resources.ReusableMethods;
import resources.base;

public class _Draft_usePayloadResearch extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="ValidInput",description="PBI:TBD")
	public void ValidInput() { 
		
		String companyId = prop.getProperty("X-CompanyId");
		String clubId = prop.getProperty("X-Club1Id");
		String customerId = prop.getProperty("availableId");
		String classBarcodeId = prop.getProperty("alwaysAvailClBarcodeId");
		String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
		String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");

//		Response r = Payload.verifyClassEnrollmentCapability(companyId, clubId, customerId, classBarcodeId, classOccurrence, displayedGrandTotal);
//		JsonPath js = ReusableMethods.rawToJson(r);
//		Assert.assertEquals(200, r.getStatusCode());
//		Assert.assertEquals("true", js.getString("AllowedToEnroll"));

		JsonPath js = Payload.verifyClassEnrollmentCapability2(companyId, clubId, customerId, classBarcodeId, classOccurrence, displayedGrandTotal);
		Assert.assertEquals("true", js.getString("AllowedToEnroll"));

	}
}
