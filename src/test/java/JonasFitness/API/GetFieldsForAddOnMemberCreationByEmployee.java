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
						
						.body("Result[11].FieldName", equalTo("WorkPhone"))
						.body("Result[11].Description", equalTo("Work Phone"))
						.body("Result[11].DisplayValue", equalTo("Work Phone"))
						.body("Result[11].Required", equalTo(false))
						.body("Result[11].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[11]", hasKey("Values"))
						.body("Result[11].MaximumLength", equalTo(0))
						.body("Result[11].DataType", equalTo("string"))
						
						.body("Result[12].FieldName", equalTo("CellPhone"))
						.body("Result[12].Description", equalTo("Mobile Phone"))
						.body("Result[12].DisplayValue", equalTo("Mobile Phone"))
						.body("Result[12].Required", equalTo(true))
						.body("Result[12].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[12]", hasKey("Values"))
						.body("Result[12].MaximumLength", equalTo(0))
						.body("Result[12].DataType", equalTo("string"))
						
						.body("Result[13].FieldName", equalTo("Email"))
						.body("Result[13].Description", equalTo("Email Address"))
						.body("Result[13].DisplayValue", equalTo("Email Address"))
						.body("Result[13].Required", equalTo(true))
						.body("Result[13].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[13]", hasKey("Values"))
						.body("Result[13].MaximumLength", equalTo(0))
						.body("Result[13].DataType", equalTo("string"))
						
						.body("Result[14].FieldName", equalTo("SendEmailStatements"))
						.body("Result[14].Description", equalTo("Pref Statement Format"))
						.body("Result[14].DisplayValue", equalTo("Pref Statement Format"))
						.body("Result[14].Required", equalTo(false))
						.body("Result[14].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[14]", hasKey("Values"))
						.body("Result[14].MaximumLength", equalTo(0))
						.body("Result[14].DataType", equalTo("string"))
						
						.body("Result[15].FieldName", equalTo("EmergencyContact"))
						.body("Result[15].Description", equalTo("Emergency Contact"))
						.body("Result[15].DisplayValue", equalTo("Emergency Contact"))
						.body("Result[15].Required", equalTo(false))
						.body("Result[15].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[15]", hasKey("Values"))
						.body("Result[15].MaximumLength", equalTo(0))
						.body("Result[15].DataType", equalTo("string"))
						
						.body("Result[16].FieldName", equalTo("EmergencyPhone"))
						.body("Result[16].Description", equalTo("Emergency Phone"))
						.body("Result[16].DisplayValue", equalTo("Emergency Phone"))
						.body("Result[16].Required", equalTo(false))
						.body("Result[16].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[16]", hasKey("Values"))
						.body("Result[16].MaximumLength", equalTo(0))
						.body("Result[16].DataType", equalTo("string"))
						
						.body("Result[17].FieldName", equalTo("DateOfBirth"))
						.body("Result[17].Description", equalTo("Birth Date"))
						.body("Result[17].DisplayValue", equalTo("Birth Date"))
						.body("Result[17].Required", equalTo(true))
						.body("Result[17].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[17]", hasKey("Values"))
						.body("Result[17].MaximumLength", equalTo(0))
						.body("Result[17].DataType", equalTo("string"))
						
						.body("Result[18].FieldName", equalTo("DriverLicNum"))
						.body("Result[18].Description", equalTo("Driver's License Number"))
						.body("Result[18].DisplayValue", equalTo("Driver's License Number"))
						.body("Result[18].Required", equalTo(false))
						.body("Result[18].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[18]", hasKey("Values"))
						.body("Result[18].MaximumLength", equalTo(0))
						.body("Result[18].DataType", equalTo("string"))
						
						.body("Result[19].FieldName", equalTo("SSNum"))
						.body("Result[19].Description", equalTo("Social Security Number"))
						.body("Result[19].DisplayValue", equalTo("Social Security Number"))
						.body("Result[19].Required", equalTo(false))
						.body("Result[19].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[19]", hasKey("Values"))
						.body("Result[19].MaximumLength", equalTo(0))
						.body("Result[19].DataType", equalTo("string"))
						
						.body("Result[20].FieldName", equalTo("Gender"))
						.body("Result[20].Description", equalTo("Gender"))
						.body("Result[20].DisplayValue", equalTo("Gender"))
						.body("Result[20].Required", equalTo(false))
						.body("Result[20].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[20]", hasKey("Values"))
						.body("Result[21].MaximumLength", equalTo(0))
						.body("Result[20].DataType", equalTo("string"))
						
						.body("Result[21].FieldName", equalTo("TrainerID"))
						.body("Result[21].Description", equalTo("Trainer"))
						.body("Result[21].DisplayValue", equalTo("Trainer"))
						.body("Result[21].Required", equalTo(false))
						.body("Result[21].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[21]", hasKey("Values"))
						.body("Result[21].MaximumLength", equalTo(0))
						.body("Result[21].DataType", equalTo("string"))
						
						.body("Result[22].FieldName", equalTo("Promotion"))
						.body("Result[22].Description", equalTo("Promotion"))
						.body("Result[22].DisplayValue", equalTo("Promotion"))
						.body("Result[22].Required", equalTo(false))
						.body("Result[22].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[22]", hasKey("Values"))
						.body("Result[22].MaximumLength", equalTo(15))
						.body("Result[22].DataType", equalTo("string"))
						
						.body("Result[23].FieldName", equalTo("SourceID"))
						.body("Result[23].Description", equalTo("Source"))
						.body("Result[23].DisplayValue", equalTo("Source"))
						.body("Result[23].Required", equalTo(false))
						.body("Result[23].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[23].Values[0].Id", equalTo(1))
						.body("Result[23].Values[0].Value", equalTo("Drive By"))
						.body("Result[23].Values[1].Id", equalTo(2))
						.body("Result[23].Values[1].Value", equalTo("Onsite Search"))
						.body("Result[23].Values[2].Id", equalTo(3))
						.body("Result[23].Values[2].Value", equalTo("Friend"))
						.body("Result[23].Values[3].Id", equalTo(4))
						.body("Result[23].Values[3].Value", equalTo("Newspaper"))
						.body("Result[23].Values[4].Id", equalTo(5))
						.body("Result[23].Values[4].Value", equalTo("Social Media"))
						.body("Result[23].Values[5].Id", equalTo(6))
						.body("Result[23].Values[5].Value", equalTo("Community Event"))
						.body("Result[23].Values[6].Id", equalTo(7))
						.body("Result[23].Values[6].Value", equalTo("Other"))
						.body("Result[23].MaximumLength", equalTo(0))
						.body("Result[23].DataType", equalTo("string"))
						
						.body("Result[24].FieldName", equalTo("Occupation"))
						.body("Result[24].Description", equalTo("Occupation"))
						.body("Result[24].DisplayValue", equalTo("Occupation of Member"))
						.body("Result[24].Required", equalTo(false))
						.body("Result[24].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[24]", hasKey("Values"))
						.body("Result[24].MaximumLength", equalTo(30))
						.body("Result[24].DataType", equalTo("string"))
						
						.body("Result[25].FieldName", equalTo("Employer"))
						.body("Result[25].Description", equalTo("Employer"))
						.body("Result[25].DisplayValue", equalTo("Employer"))
						.body("Result[25].Required", equalTo(false))
						.body("Result[25].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[25]", hasKey("Values"))
						.body("Result[25].MaximumLength", equalTo(0))
						.body("Result[25].DataType", equalTo("string"))
						
						.body("Result[26].FieldName", equalTo("IncomeChoice"))
						.body("Result[26].Description", equalTo("Income"))
						.body("Result[26].DisplayValue", equalTo("Income"))
						.body("Result[26].Required", equalTo(false))
						.body("Result[26].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[26].Values[0].Id", equalTo(1))
						.body("Result[26].Values[0].Value", equalTo(""))
						.body("Result[26].Values[1].Id", equalTo(2))
						.body("Result[26].Values[1].Value", equalTo(""))
						.body("Result[26].Values[2].Id", equalTo(3))
						.body("Result[26].Values[2].Value", equalTo(""))
						.body("Result[26].Values[3].Id", equalTo(4))
						.body("Result[26].Values[3].Value", equalTo(""))
						.body("Result[26].Values[4].Id", equalTo(5))
						.body("Result[26].Values[4].Value", equalTo(""))
						.body("Result[26].MaximumLength", equalTo(0))
						.body("Result[26].DataType", equalTo("string"))
						
						.body("Result[27].FieldName", equalTo("Login"))
						.body("Result[27].Description", equalTo("Online Log In"))
						.body("Result[27].DisplayValue", equalTo("Online Log In"))
						.body("Result[27].Required", equalTo(false))
						.body("Result[27].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[27]", hasKey("Values"))
						.body("Result[27].MaximumLength", equalTo(0))
						.body("Result[27].DataType", equalTo("string"))
						
						.body("Result[28].FieldName", equalTo("GroupID"))
						.body("Result[28].Description", equalTo("Group Name"))
						.body("Result[28].DisplayValue", equalTo("Group Name"))
						.body("Result[28].Required", equalTo(false))
						.body("Result[28].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[28].Values[0].Id", equalTo(1))
						.body("Result[28].Values[0].Value", equalTo("Silver Sneakers"))
						.body("Result[28].Values[1].Id", equalTo(2))
						.body("Result[28].Values[1].Value", equalTo("Boeing"))
						.body("Result[28].Values[2].Id", equalTo(3))
						.body("Result[28].Values[2].Value", equalTo("Microsoft"))
						.body("Result[28].Values[3].Id", equalTo(4))
						.body("Result[28].Values[3].Value", equalTo("Jeep"))
						.body("Result[28].Values[4].Id", equalTo(5))
						.body("Result[28].Values[4].Value", equalTo("Safeway"))
						.body("Result[28].Values[5].Id", equalTo(6))
						.body("Result[28].Values[5].Value", equalTo("Dodge"))
						.body("Result[28].MaximumLength", equalTo(0))
						.body("Result[28].DataType", equalTo("string"))
						
						.body("Result[29].FieldName", equalTo("InsuranceID"))
						.body("Result[29].Description", equalTo("Insurance ID"))
						.body("Result[29].DisplayValue", equalTo("Insurance ID"))
						.body("Result[29].Required", equalTo(false))
						.body("Result[29].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[29]", hasKey("Values"))
						.body("Result[29].MaximumLength", equalTo(0))
						.body("Result[29].DataType", equalTo("string"))
						
						.body("Result[30].FieldName", equalTo("InsuranceRegistrationDate"))
						.body("Result[30].Description", equalTo("Insurance Registration"))
						.body("Result[30].DisplayValue", equalTo("Insurance Registration"))
						.body("Result[30].Required", equalTo(false))
						.body("Result[30].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[30]", hasKey("Values"))
						.body("Result[30].MaximumLength", equalTo(0))
						.body("Result[30].DataType", equalTo("string"))
						
						.body("Result[31].FieldName", equalTo("UserEntry1"))
						.body("Result[31].Description", equalTo("Custom Text 1"))
						.body("Result[31].DisplayValue", equalTo("Custom Text 1"))
						.body("Result[31].Required", equalTo(false))
						.body("Result[31].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[31]", hasKey("Values"))
						.body("Result[31].MaximumLength", equalTo(40))
						.body("Result[31].DataType", equalTo("string"))
						
						.body("Result[32].FieldName", equalTo("UserEntry2"))
						.body("Result[32].Description", equalTo("Custom Text 2"))
						.body("Result[32].DisplayValue", equalTo("Custom Text 2"))
						.body("Result[32].Required", equalTo(false))
						.body("Result[32].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[32]", hasKey("Values"))
						.body("Result[32].MaximumLength", equalTo(40))
						.body("Result[32].DataType", equalTo("string"))
						
						.body("Result[33].FieldName", equalTo("UserEntry3"))
						.body("Result[33].Description", equalTo("Custom Text 3"))
						.body("Result[33].DisplayValue", equalTo("Custom Text 3"))
						.body("Result[33].Required", equalTo(false))
						.body("Result[33].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[33]", hasKey("Values"))
						.body("Result[33].MaximumLength", equalTo(40))
						.body("Result[33].DataType", equalTo("string"))
						
						.body("Result[34].FieldName", equalTo("UserEntry4"))
						.body("Result[34].Description", equalTo("Custom Text 4"))
						.body("Result[34].DisplayValue", equalTo("Custom Text 4"))
						.body("Result[34].Required", equalTo(false))
						.body("Result[34].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[34]", hasKey("Values"))
						.body("Result[34].MaximumLength", equalTo(40))
						.body("Result[34].DataType", equalTo("string"))
						
						.body("Result[35].FieldName", equalTo("UserEntry5"))
						.body("Result[35].Description", equalTo("Custom Text 5"))
						.body("Result[35].DisplayValue", equalTo("Custom Text 5"))
						.body("Result[35].Required", equalTo(false))
						.body("Result[35].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[35]", hasKey("Values"))
						.body("Result[35].MaximumLength", equalTo(40))
						.body("Result[35].DataType", equalTo("string"))
						
						.body("Result[36].FieldName", equalTo("UserEntry6"))
						.body("Result[36].Description", equalTo("Custom Text 6"))
						.body("Result[36].DisplayValue", equalTo("Custom Text 6"))
						.body("Result[36].Required", equalTo(false))
						.body("Result[36].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[36]", hasKey("Values"))
						.body("Result[36].MaximumLength", equalTo(40))
						.body("Result[36].DataType", equalTo("string"))
						
						.body("Result[37].FieldName", equalTo("UserEntry7"))
						.body("Result[37].Description", equalTo("Custom Text 7"))
						.body("Result[37].DisplayValue", equalTo("Custom Text 7"))
						.body("Result[37].Required", equalTo(false))
						.body("Result[37].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[37]", hasKey("Values"))
						.body("Result[37].MaximumLength", equalTo(40))
						.body("Result[37].DataType", equalTo("string"))
						
						.body("Result[38].FieldName", equalTo("UserEntry8"))
						.body("Result[38].Description", equalTo("Custom Text 8"))
						.body("Result[38].DisplayValue", equalTo("Custom Text 8"))
						.body("Result[38].Required", equalTo(false))
						.body("Result[38].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[38]", hasKey("Values"))
						.body("Result[38].MaximumLength", equalTo(40))
						.body("Result[38].DataType", equalTo("string"))
						
						.body("Result[39].FieldName", equalTo("UserEntry9"))
						.body("Result[39].Description", equalTo("Custom Text 9"))
						.body("Result[39].DisplayValue", equalTo("Custom Text 9"))
						.body("Result[39].Required", equalTo(false))
						.body("Result[39].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[39]", hasKey("Values"))
						.body("Result[39].MaximumLength", equalTo(40))
						.body("Result[39].DataType", equalTo("string"))
						
						.body("Result[40].FieldName", equalTo("UserEntry10"))
						.body("Result[40].Description", equalTo("Custom Text 10"))
						.body("Result[40].DisplayValue", equalTo("Custom Text 10"))
						.body("Result[40].Required", equalTo(false))
						.body("Result[40].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[40]", hasKey("Values"))
						.body("Result[40].MaximumLength", equalTo(40))
						.body("Result[40].DataType", equalTo("string"))
						
						.body("Result[41].FieldName", equalTo("UserEntry11"))
						.body("Result[41].Description", equalTo("Custom Text 11"))
						.body("Result[41].DisplayValue", equalTo("Custom Text 11"))
						.body("Result[41].Required", equalTo(false))
						.body("Result[41].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[41]", hasKey("Values"))
						.body("Result[41].MaximumLength", equalTo(40))
						.body("Result[41].DataType", equalTo("string"))
						
						.body("Result[42].FieldName", equalTo("UserEntry12"))
						.body("Result[42].Description", equalTo("Custom Text 12"))
						.body("Result[42].DisplayValue", equalTo("Custom Text 12"))
						.body("Result[42].Required", equalTo(false))
						.body("Result[42].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[42]", hasKey("Values"))
						.body("Result[42].MaximumLength", equalTo(40))
						.body("Result[42].DataType", equalTo("string"))
						
						.body("Result[43].FieldName", equalTo("UserEntry13"))
						.body("Result[43].Description", equalTo("Custom Text 13"))
						.body("Result[43].DisplayValue", equalTo("Custom Text 13"))
						.body("Result[43].Required", equalTo(false))
						.body("Result[43].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[43]", hasKey("Values"))
						.body("Result[43].MaximumLength", equalTo(40))
						.body("Result[43].DataType", equalTo("string"))
						
						.body("Result[44].FieldName", equalTo("UserEntry14"))
						.body("Result[44].Description", equalTo("Custom Text 14"))
						.body("Result[44].DisplayValue", equalTo("Custom Text 14"))
						.body("Result[44].Required", equalTo(false))
						.body("Result[44].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[44]", hasKey("Values"))
						.body("Result[44].MaximumLength", equalTo(40))
						.body("Result[44].DataType", equalTo("string"))
						
						.body("Result[45].FieldName", equalTo("UserEntry15"))
						.body("Result[45].Description", equalTo("Custom Text 15"))
						.body("Result[45].DisplayValue", equalTo("Custom Text 15"))
						.body("Result[45].Required", equalTo(false))
						.body("Result[45].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[45]", hasKey("Values"))
						.body("Result[45].MaximumLength", equalTo(40))
						.body("Result[45].DataType", equalTo("string"))
						
						.body("Result[46].FieldName", equalTo("UserEntry16"))
						.body("Result[46].Description", equalTo("Conv Membership Type"))
						.body("Result[46].DisplayValue", equalTo("Conv Membership Type"))
						.body("Result[46].Required", equalTo(false))
						.body("Result[46].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[46]", hasKey("Values"))
						.body("Result[46].MaximumLength", equalTo(40))
						.body("Result[46].DataType", equalTo("string"))
						
						.body("Result[47].FieldName", equalTo("UserDate1"))
						.body("Result[47].Description", equalTo("Custom Date 1"))
						.body("Result[47].DisplayValue", equalTo("Custom Date 1"))
						.body("Result[47].Required", equalTo(false))
						.body("Result[47].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[47]", hasKey("Values"))
						.body("Result[47].MaximumLength", equalTo(10))
						.body("Result[47].DataType", equalTo("datetime"))
						
						.body("Result[48].FieldName", equalTo("UserDate2"))
						.body("Result[48].Description", equalTo("Custom Date 2"))
						.body("Result[48].DisplayValue", equalTo("Custom Date 2"))
						.body("Result[48].Required", equalTo(false))
						.body("Result[48].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[48]", hasKey("Values"))
						.body("Result[48].MaximumLength", equalTo(10))
						.body("Result[48].DataType", equalTo("datetime"))
						
						.body("Result[49].FieldName", equalTo("UserDate3"))
						.body("Result[49].Description", equalTo("Custom Date 3"))
						.body("Result[49].DisplayValue", equalTo("Custom Date 3"))
						.body("Result[49].Required", equalTo(false))
						.body("Result[49].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[49]", hasKey("Values"))
						.body("Result[49].MaximumLength", equalTo(10))
						.body("Result[49].DataType", equalTo("datetime"))
						
						.body("Result[50].FieldName", equalTo("UserDate4"))
						.body("Result[50].Description", equalTo("Custom Date 4"))
						.body("Result[50].DisplayValue", equalTo("Custom Date 4"))
						.body("Result[50].Required", equalTo(false))
						.body("Result[50].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[50]", hasKey("Values"))
						.body("Result[50].MaximumLength", equalTo(10))
						.body("Result[50].DataType", equalTo("datetime"))
						
						.body("Result[51].FieldName", equalTo("UserAmount1"))
						.body("Result[51].Description", equalTo("Custom Amount 1"))
						.body("Result[51].DisplayValue", equalTo("Custom Amount 1"))
						.body("Result[51].Required", equalTo(false))
						.body("Result[51].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[51]", hasKey("Values"))
						.body("Result[51].MaximumLength", equalTo(10))
						.body("Result[51].DataType", equalTo("currency"))
						
						.body("Result[52].FieldName", equalTo("UserAmount2"))
						.body("Result[52].Description", equalTo("Custom Amount 2"))
						.body("Result[52].DisplayValue", equalTo("Custom Amount 2"))
						.body("Result[52].Required", equalTo(false))
						.body("Result[52].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[52]", hasKey("Values"))
						.body("Result[52].MaximumLength", equalTo(10))
						.body("Result[52].DataType", equalTo("currency"))
						
						.body("Result[53].FieldName", equalTo("UserAmount3"))
						.body("Result[53].Description", equalTo("Custom Amount 3"))
						.body("Result[53].DisplayValue", equalTo("Custom Amount 3"))
						.body("Result[53].Required", equalTo(false))
						.body("Result[53].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[53]", hasKey("Values"))
						.body("Result[53].MaximumLength", equalTo(10))
						.body("Result[53].DataType", equalTo("currency"))
						
						.body("Result[54].FieldName", equalTo("UserAmount4"))
						.body("Result[54].Description", equalTo("Custom Amount 4"))
						.body("Result[54].DisplayValue", equalTo("Custom Amount 4"))
						.body("Result[54].Required", equalTo(false))
						.body("Result[54].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[54]", hasKey("Values"))
						.body("Result[54].MaximumLength", equalTo(10))
						.body("Result[54].DataType", equalTo("currency"))
						
						.body("Result[55].FieldName", equalTo("CategoryID"))
						.body("Result[55].Description", equalTo("Membership Type"))
						.body("Result[55].DisplayValue", equalTo("Membership Type"))
						.body("Result[55].Required", equalTo(true))
						.body("Result[55].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[55].Values[0].Id", equalTo(3))
						.body("Result[55].Values[0].Value", equalTo("Athletic"))
						.body("Result[55].Values[1].Id", equalTo(4))
						.body("Result[55].Values[1].Value", equalTo("Tennis"))
						.body("Result[55].Values[2].Id", equalTo(8))
						.body("Result[55].Values[2].Value", equalTo("Employee"))
						.body("Result[55].Values[3].Id", equalTo(9))
						.body("Result[55].Values[3].Value", equalTo("Young Professional"))
						.body("Result[55].Values[4].Id", equalTo(10))
						.body("Result[55].Values[4].Value", equalTo("Health Insurance"))
						.body("Result[55].Values[5].Id", equalTo(12))
						.body("Result[55].Values[5].Value", equalTo("Outdoor Seasonal"))
						.body("Result[55].Values[6].Id", equalTo(13))
						.body("Result[55].Values[6].Value", equalTo("Athletic Elite"))
						.body("Result[55].Values[7].Id", equalTo(14))
						.body("Result[55].Values[7].Value", equalTo("Corporation"))
						.body("Result[55].Values[8].Id", equalTo(15))
						.body("Result[55].Values[8].Value", equalTo("Corporate Member"))
						.body("Result[55].Values[9].Id", equalTo(16))
						.body("Result[55].Values[9].Value", equalTo("S.W.E.A.T Member"))
						.body("Result[55].MaximumLength", equalTo(0))
						.body("Result[55].DataType", equalTo("string"))
						
						.body("Result[56].FieldName", equalTo("PreferredPhone"))
						.body("Result[56].Description", equalTo("Preferred Phone Number"))
						.body("Result[56].DisplayValue", equalTo("Preferred Phone Number"))
						.body("Result[56].Required", equalTo(false))
						.body("Result[56].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[56]", hasKey("Values"))
						.body("Result[56].MaximumLength", equalTo(0))
						.body("Result[56].DataType", equalTo("string"))
						
						.body("Result[57].FieldName", equalTo("CreditLimit"))
						.body("Result[57].Description", equalTo("Credit Limit Value"))
						.body("Result[57].DisplayValue", equalTo("Credit Limit Value"))
						.body("Result[57].Required", equalTo(false))
						.body("Result[57].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[57]", hasKey("Values"))
						.body("Result[57].MaximumLength", equalTo(0))
						.body("Result[57].DataType", equalTo("string"))
						
						.body("Result[58].FieldName", equalTo("ReferringCustomerID"))
						.body("Result[58].Description", equalTo("Referred By"))
						.body("Result[58].DisplayValue", equalTo("Referred By"))
						.body("Result[58].Required", equalTo(false))
						.body("Result[58].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[58]", hasKey("Values"))
						.body("Result[58].MaximumLength", equalTo(0))
						.body("Result[58].DataType", equalTo("string"))
						
						.body("Result[59].FieldName", equalTo("Date"))
						.body("Result[59].Description", equalTo("Referral Date"))
						.body("Result[59].DisplayValue", equalTo("Referral Date"))
						.body("Result[59].Required", equalTo(false))
						.body("Result[59].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[59]", hasKey("Values"))
						.body("Result[59].MaximumLength", equalTo(0))
						.body("Result[59].DataType", equalTo("string"))
						
						.body("Result[60].FieldName", equalTo("InterestID"))
						.body("Result[60].Description", equalTo("Interests"))
						.body("Result[60].DisplayValue", equalTo("Interests"))
						.body("Result[60].Required", equalTo(false))
						.body("Result[60].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[60].Values[0].Id", equalTo(1))
						.body("Result[60].Values[0].Value", equalTo("Build Muscle/Strength"))
						.body("Result[60].Values[1].Id", equalTo(5))
						.body("Result[60].Values[1].Value", equalTo("Group Fitness"))
						.body("Result[60].Values[2].Id", equalTo(4))
						.body("Result[60].Values[2].Value", equalTo("Maintain Health"))
						.body("Result[60].Values[3].Id", equalTo(2))
						.body("Result[60].Values[3].Value", equalTo("Sports Performance"))
						.body("Result[60].Values[4].Id", equalTo(3))
						.body("Result[60].Values[4].Value", equalTo("Weight Loss"))
						.body("Result[60].MaximumLength", equalTo(0))
						.body("Result[60].DataType", equalTo("string"))
						
						.body("Result[61].FieldName", equalTo("GeneralNotes"))
						.body("Result[61].Description", equalTo("Notes"))
						.body("Result[61].DisplayValue", equalTo("Notes"))
						.body("Result[61].Required", equalTo(false))
						.body("Result[61].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[61]", hasKey("Values"))
						.body("Result[61].MaximumLength", equalTo(0))
						.body("Result[61].DataType", equalTo("string"))
						
						.body("Result[62].FieldName", equalTo("ClubNumber"))
						.body("Result[62].Description", equalTo("Home Club"))
						.body("Result[62].DisplayValue", equalTo("Home Club"))
						.body("Result[62].Required", equalTo(false))
						.body("Result[62].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[62].Values[0].Id", equalTo(1))
						.body("Result[62].Values[0].Value", equalTo("Jonas Sports-Plex"))
						.body("Result[62].Values[1].Id", equalTo(2))
						.body("Result[62].Values[1].Value", equalTo("Studio Jonas"))
						.body("Result[62].Values[2].Id", equalTo(3))
						.body("Result[62].Values[2].Value", equalTo("Jonas Health and Wellness"))
						.body("Result[62].Values[3].Id", equalTo(4))
						.body("Result[62].Values[3].Value", equalTo("Jonas Fitness"))
						.body("Result[62].MaximumLength", equalTo(0))
						.body("Result[62].DataType", equalTo("string"))
						
						.body("Result[63].FieldName", equalTo("SalespersonID"))
						.body("Result[63].Description", equalTo("Salesperson"))
						.body("Result[63].DisplayValue", equalTo("Salesperson"))
						.body("Result[63].Required", equalTo(false))
						.body("Result[63].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[63]", hasKey("Values"))
						.body("Result[63].MaximumLength", equalTo(0))
						.body("Result[63].DataType", equalTo("string"))
						
						.body("Result[64].FieldName", equalTo("MedicalNote"))
						.body("Result[64].Description", equalTo("Medical Note"))
						.body("Result[64].DisplayValue", equalTo("Medical Note"))
						.body("Result[64].Required", equalTo(false))
						.body("Result[64].ValueMustBeInDefinedList", equalTo(true))
						.body("Result[64]", hasKey("Values"))
						.body("Result[64].MaximumLength", equalTo(60))
						.body("Result[64].DataType", equalTo("string"))
						
						.body("Result[65].FieldName", equalTo("BarcodeID"))
						.body("Result[65].Description", equalTo("Member Id"))
						.body("Result[65].DisplayValue", equalTo("Member Id"))
						.body("Result[65].Required", equalTo(true))
						.body("Result[65].ValueMustBeInDefinedList", equalTo(false))
						.body("Result[65]", hasKey("Values"))
						.body("Result[65].MaximumLength", equalTo(0))
						.body("Result[65].DataType", equalTo("string"));
	}
}