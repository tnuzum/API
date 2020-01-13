package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetClassCoursePricing extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Item Found - Single Tax",description="PBI:155543")
	public void itemFound_SingleTax() { 
		
		int customerId = 248;
		int itemId = 250;

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.GrandTotal", not(nullValue()))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.PriceDetails[0].Price", not(nullValue()))
						.body("Result.SubTotal", not(nullValue()))
						.body("Result.Tax", not(nullValue()))
						.body("Result.TaxDetails[0].TaxAmount", not(nullValue()))
						.body("Result.TaxDetails[0].TaxItemId", not(nullValue()));
	}
	
	@Test (testName="Item Found - Multiple Taxes",description="PBI:155543")
	public void itemFound_MultipleTaxes() { 
		
		int customerId = 248;
		int itemId = 254;

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.GrandTotal", not(nullValue()))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.PriceDetails[0].Price", not(nullValue()))
						.body("Result.SubTotal", not(nullValue()))
						.body("Result.Tax", not(nullValue()))
						.body("Result.TaxDetails[0].TaxAmount", not(nullValue()))
						.body("Result.TaxDetails[0].TaxItemId", not(nullValue()))
						.body("Result.TaxDetails[1].TaxAmount", not(nullValue()))
						.body("Result.TaxDetails[1].TaxItemId", not(nullValue()));
						/*
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.GrandTotal", equalTo("11.3"))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(true))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.PriceDetails[0].Price", equalTo(10))
						.body("Result.SubTotal", equalTo(10))
						.body("Result.Tax", equalTo(1.3))
						.body("Result.TaxDetails[0].TaxAmount", equalTo(0.25))
						.body("Result.TaxDetails[0].TaxItemId", equalTo(4))
						.body("Result.TaxDetails[1].TaxAmount", equalTo(0.3))
						.body("Result.TaxDetails[1].TaxItemId", equalTo(5))
						.body("Result.TaxDetails[2].TaxAmount", equalTo(0.4))
						.body("Result.TaxDetails[2].TaxItemId", equalTo(6))
						.body("Result.TaxDetails[3].TaxAmount", equalTo(0.35))
						.body("Result.TaxDetails[3].TaxItemId", equalTo(7)) */
	}
	
	@Test (testName="Item Found - No Tax",description="PBI:155543")
	public void itemFound_NoTax() { 
		
		int customerId = 248;
		int itemId = 224;

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.GrandTotal", not(nullValue()))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(false))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.PriceDetails[0].Price", not(nullValue()))
						.body("Result.SubTotal", not(nullValue()))
						.body("Result.Tax", equalTo(0.0));
	}
	
	@Test (testName="Item Found - Free Item",description="PBI:155543")
	public void itemFound_FreeItem() { 
		
		int customerId = 248;
		int itemId = 246;

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclasscoursepricing/"+customerId+"/"+itemId)
						.then()
						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						.body("Result.CanPlaceOnAccount", equalTo(true))
						.body("Result.GrandTotal", is(0.0))
						.body("Result.PriceDetails[0].CorrelationId", not(nullValue()))
						.body("Result.PriceDetails[0].CustomerId", equalTo(customerId))
						.body("Result.PriceDetails[0].IsTaxed", equalTo(false))
						.body("Result.PriceDetails[0].ItemId", equalTo(itemId))
						.body("Result.PriceDetails[0].Price", is(0.0))
						.body("Result.SubTotal", not(nullValue()))
						.body("Result.Tax", is(0.0));
	}
}
