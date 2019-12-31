package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetFieldsForAddOnMemberCreationByEmployee extends base {
	
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Fields Found",description="PBI:147958")
	public void fieldsFound() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getfieldsforaddonmembercreationbyemployee")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
						
						.body("Result[0].FieldName", equalTo("Title"))
						.body("Result[0].Description", equalTo("Title"))
						.body("Result[0].DisplayValue", equalTo("Title"))
						.body("Result[0].Required", equalTo(false))
						.body("Result[0].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[0]", hasKey("Values"))
						.body("Result[0].MaximumLength", equalTo(10))
						.body("Result[0].DataType", equalTo("string"))
						
						;
	}
}
