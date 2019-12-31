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

						.body("Result[1].FieldName", equalTo("FirstName"))
						.body("Result[1].Description", equalTo("First Name"))
						.body("Result[1].DisplayValue", equalTo("First Name"))
						.body("Result[1].Required", equalTo(true))
						.body("Result[1].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[1]", hasKey("Values"))
						.body("Result[1].MaximumLength", equalTo(0))
						.body("Result[1].DataType", equalTo("string"))
						
						.body("Result[2].FieldName", equalTo("MI"))
						.body("Result[2].Description", equalTo("M.I."))
						.body("Result[2].DisplayValue", equalTo("M.I."))
						.body("Result[2].Required", equalTo(false))
						.body("Result[2].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[2]", hasKey("Values"))
						.body("Result[2].MaximumLength", equalTo(0))
						.body("Result[2].DataType", equalTo("string"))
						
						.body("Result[3].FieldName", equalTo("LastName"))
						.body("Result[3].Description", equalTo("Last Name"))
						.body("Result[3].DisplayValue", equalTo("Last Name"))
						.body("Result[3].Required", equalTo(true))
						.body("Result[3].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[3]", hasKey("Values"))
						.body("Result[3].MaximumLength", equalTo(0))
						.body("Result[3].DataType", equalTo("string"))
						
						.body("Result[4].FieldName", equalTo("Preferred"))
						.body("Result[4].Description", equalTo("Preferred Name"))
						.body("Result[4].DisplayValue", equalTo("Preferred Name"))
						.body("Result[4].Required", equalTo(false))
						.body("Result[4].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[4]", hasKey("Values"))
						.body("Result[4].MaximumLength", equalTo(0))
						.body("Result[4].DataType", equalTo("string"))
						
						.body("Result[5].FieldName", equalTo("Address1"))
						.body("Result[5].Description", equalTo("Address 1"))
						.body("Result[5].DisplayValue", equalTo("Address 1"))
						.body("Result[5].Required", equalTo(true))
						.body("Result[5].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[5]", hasKey("Values"))
						.body("Result[5].MaximumLength", equalTo(0))
						.body("Result[5].DataType", equalTo("string"))
						
						.body("Result[6].FieldName", equalTo("Address2"))
						.body("Result[6].Description", equalTo("Address 2"))
						.body("Result[6].DisplayValue", equalTo("Address 2"))
						.body("Result[6].Required", equalTo(false))
						.body("Result[6].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[6]", hasKey("Values"))
						.body("Result[6].MaximumLength", equalTo(0))
						.body("Result[6].DataType", equalTo("string"))
						
						.body("Result[7].FieldName", equalTo("City"))
						.body("Result[7].Description", equalTo("City"))
						.body("Result[7].DisplayValue", equalTo("City"))
						.body("Result[7].Required", equalTo(true))
						.body("Result[7].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[7]", hasKey("Values"))
						.body("Result[7].MaximumLength", equalTo(0))
						.body("Result[7].DataType", equalTo("string"))
						
						.body("Result[8].FieldName", equalTo("State"))
						.body("Result[8].Description", equalTo("State"))
						.body("Result[8].DisplayValue", equalTo("State"))
						.body("Result[8].Required", equalTo(true))
						.body("Result[8].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[8]", hasKey("Values"))
						.body("Result[8].MaximumLength", equalTo(0))
						.body("Result[8].DataType", equalTo("string"))
						
						.body("Result[9].FieldName", equalTo("Zipcode"))
						.body("Result[9].Description", equalTo("Zip Code"))
						.body("Result[9].DisplayValue", equalTo("Zip Code"))
						.body("Result[9].Required", equalTo(true))
						.body("Result[9].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[9]", hasKey("Values"))
						.body("Result[9].MaximumLength", equalTo(0))
						.body("Result[9].DataType", equalTo("string"))
						
						.body("Result[10].FieldName", equalTo("HomePhone"))
						.body("Result[10].Description", equalTo("Home Phone"))
						.body("Result[10].DisplayValue", equalTo("Home Phone"))
						.body("Result[10].Required", equalTo(false))
						.body("Result[10].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[10]", hasKey("Values"))
						.body("Result[10].MaximumLength", equalTo(0))
						.body("Result[10].DataType", equalTo("string"))
						
						;
	}
}
