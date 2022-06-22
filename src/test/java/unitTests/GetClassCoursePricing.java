package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetClassCoursePricing extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;
	String valueAssertions;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		valueAssertions = prop.getProperty("valueAssertions");
	}
	
	@Test (testName="Item Found - Single Tax",description="PBI:155543")
	public void itemFound_SingleTax() { 
		
		String c = prop.getProperty("availableId");
		int customerId = Integer.parseInt(c);
		String i = prop.getProperty("taxSingleClId");
		int itemId = Integer.parseInt(i);

		Response res = 
		
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.TaxDetails[0].TaxItemId", equalTo(4))
						.extract().response();
		
					if (valueAssertions.equals("true")) {
						JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.GrandTotal"), 10.25);
						Assert.assertEquals(js.getDouble("Result.PriceDetails[0].Price"), 10.0);
						Assert.assertEquals(js.getDouble("Result.SubTotal"), 10.0);
						Assert.assertEquals(js.getDouble("Result.Tax"), 0.25);
						Assert.assertEquals(js.getDouble("Result.TaxDetails[0].TaxAmount"), 0.25);
					}
	}
	
	@Test (testName="Item Found - Multiple Taxes",description="PBI:155543")
	public void itemFound_MultipleTaxes() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String i = prop.getProperty("taxMultipleClId");
				int itemId = Integer.parseInt(i);

		Response res =
				
					given()
//						.log().all()				
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.TaxDetails[0].TaxItemId", equalTo(4))
						.body("Result.TaxDetails[1].TaxItemId", equalTo(5))
						.body("Result.TaxDetails[2].TaxItemId", equalTo(6))
						.body("Result.TaxDetails[3].TaxItemId", equalTo(7))
						.extract().response();
					if (valueAssertions.equals("true")) {
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.GrandTotal"), 12.24);
						Assert.assertEquals(js.getDouble("Result.PriceDetails[0].Price"), 10.0);
						Assert.assertEquals(js.getDouble("Result.SubTotal"), 10.0);
						Assert.assertEquals(js.getDouble("Result.Tax"), 2.24);
						Assert.assertEquals(js.getDouble("Result.TaxDetails[0].TaxAmount"), 0.59);
						Assert.assertEquals(js.getDouble("Result.TaxDetails[1].TaxAmount"), 0.3);						
						Assert.assertEquals(js.getDouble("Result.TaxDetails[2].TaxAmount"), 1.0);						
						Assert.assertEquals(js.getDouble("Result.TaxDetails[3].TaxAmount"), 0.35);	
					}
}
	
	@Test (testName="Item Found - Club 2",description="PBI:155543")
	public void itemFound_Club2() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String i = prop.getProperty("taxSingleClId");
				int itemId = Integer.parseInt(i);

		Response res = 
					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", 2)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
					.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.TaxDetails[0].TaxItemId", equalTo(4))
						.extract().response();
		
					if (valueAssertions.equals("true")) {
						JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.GrandTotal"), 10.39);
						Assert.assertEquals(js.getDouble("Result.PriceDetails[0].Price"), 10.0);
						Assert.assertEquals(js.getDouble("Result.SubTotal"), 10.0);
						Assert.assertEquals(js.getDouble("Result.Tax"), 0.29);
						Assert.assertEquals(js.getDouble("Result.TaxDetails[0].TaxAmount"), 0.29);
					}
	}
	
	@Test (testName="Item Found - No Tax",description="PBI:155543")
	public void itemFound_NoTax() { 
		
		String c = prop.getProperty("availableId");
		int customerId = Integer.parseInt(c);
		String i = prop.getProperty("alwaysAvailClId");
		int itemId = Integer.parseInt(i);
//		int itemId = 224;

			Response res = given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(false))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.extract().response();
			
			if (valueAssertions.equals("true")) {
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.GrandTotal"), 10.0);
						Assert.assertEquals(js.getDouble("Result.PriceDetails[0].Price"), 10.0);
						Assert.assertEquals(js.getDouble("Result.SubTotal"), 10.0);
						Assert.assertEquals(js.getDouble("Result.Tax"), 0.0);
			}
	}
	
	@Test (testName="Item Found - Free Item",description="PBI:155543")
	public void itemFound_FreeItem() { 
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String i = prop.getProperty("freeClId");
				int itemId = Integer.parseInt(i);

		Response res =	given()
						
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(false))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.extract().response();
		
		if (valueAssertions.equals("true")) {
					JsonPath js = ReusableMethods.rawToJson(res);
						Assert.assertEquals(js.getDouble("Result.GrandTotal"), 0.0);
						Assert.assertEquals(js.getDouble("Result.PriceDetails[0].Price"), 0.0);
						Assert.assertEquals(js.getDouble("Result.SubTotal"), 0.0);
						Assert.assertEquals(js.getDouble("Result.Tax"), 0.0);
		}
	}
	
	@Test (testName="Item Not Found",description="PBI:155543")
	public void itemNotFound() { 
		
		String c = prop.getProperty("availableId");
		int customerId = Integer.parseInt(c);
		int itemId = 99999;

					given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
					.then()
//						.log().all()
//						.assertThat().statusCode(404)
//						.body("Message", equalTo("Item not found"))
						.assertThat().statusCode(500);
	}
	
	@Test (testName="Customer Not Found",description="PBI:155543")
	public void customerNotFound() { 
		
		int customerId = 248000;
		String i = prop.getProperty("alwaysAvailClId");
		int itemId = Integer.parseInt(i);

				given()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("Customer not found"))
						;
	}
}
