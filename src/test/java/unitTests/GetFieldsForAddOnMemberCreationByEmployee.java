package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import resources.base;

public class GetFieldsForAddOnMemberCreationByEmployee extends base {

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (priority=1, testName="Test 1",description="PBI:147958")
	public void fieldsFound() { 

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/member/getfieldsforaddonmembercreationbyemployee")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						
						.body("Result.FieldName", anyOf(hasItem("Title")))
						.body("Result.Description", anyOf(hasItem("Title")))
						.body("Result.DisplayValue", anyOf(hasItem("Title")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("string")))

						.body("Result.FieldName", anyOf(hasItem("FirstName")))
						.body("Result.Description", anyOf(hasItem("First Name")))
						.body("Result.DisplayValue", anyOf(hasItem("First Name")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("MI")))
						.body("Result.Description", anyOf(hasItem("M.I.")))
						.body("Result.DisplayValue", anyOf(hasItem("M.I.")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("LastName")))
						.body("Result.Description", anyOf(hasItem("Last Name")))
						.body("Result.DisplayValue", anyOf(hasItem("Last Name")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Preferred")))
						.body("Result.Description", anyOf(hasItem("Preferred Name")))
						.body("Result.DisplayValue", anyOf(hasItem("Preferred Name")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Address1")))
						.body("Result.Description", anyOf(hasItem("Address 1")))
						.body("Result.DisplayValue", anyOf(hasItem("Address 1")))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Address2")))
						.body("Result.Description", anyOf(hasItem("Address 2")))
						.body("Result.DisplayValue", anyOf(hasItem("Address 2")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("City")))
						.body("Result.Description", anyOf(hasItem("City")))
						.body("Result.DisplayValue", anyOf(hasItem("City")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("State")))
						.body("Result.Description", anyOf(hasItem("State")))
						.body("Result.DisplayValue", anyOf(hasItem("State")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Zipcode")))
						.body("Result.Description", anyOf(hasItem("Zip Code")))
						.body("Result.DisplayValue", anyOf(hasItem("Zip Code")))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("HomePhone")))
						.body("Result.Description", anyOf(hasItem("Home Phone")))
						.body("Result.DisplayValue", anyOf(hasItem("Home Phone")))
						.body("Result.Required", anyOf(hasItem(false))) //TN 07.08.21 changed from false to match updated value seen in back office
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))

						.body("Result.FieldName", anyOf(hasItem("WorkPhone")))
						.body("Result.Description", anyOf(hasItem("Work Phone")))
						.body("Result.DisplayValue", anyOf(hasItem("Work Phone")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("CellPhone")))
						.body("Result.Description", anyOf(hasItem("Mobile Phone")))
						.body("Result.DisplayValue", anyOf(hasItem("Mobile Phone")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Email")))
						.body("Result.Description", anyOf(hasItem("Email Address")))
						.body("Result.DisplayValue", anyOf(hasItem("Email Address")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("SendEmailStatements")))
						.body("Result.Description", anyOf(hasItem("Pref Statement Format")))
						.body("Result.DisplayValue", anyOf(hasItem("Pref Statement Format")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("EmergencyContact")))
						.body("Result.Description", anyOf(hasItem("Emergency Contact")))
						.body("Result.DisplayValue", anyOf(hasItem("Emergency Contact")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("EmergencyPhone")))
						.body("Result.Description", anyOf(hasItem("Emergency Phone")))
						.body("Result.DisplayValue", anyOf(hasItem("Emergency Phone")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("DateOfBirth")))
						.body("Result.Description", anyOf(hasItem("Birth Date")))
						.body("Result.DisplayValue", anyOf(hasItem("Birth Date")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("DriverLicNum")))
						.body("Result.Description", anyOf(hasItem("Driver's License Number")))
						.body("Result.DisplayValue", anyOf(hasItem("Driver's License Number")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("SSNum")))
						.body("Result.Description", anyOf(hasItem("Social Security Number")))
						.body("Result.DisplayValue", anyOf(hasItem("Social Security Number")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Gender")))
						.body("Result.Description", anyOf(hasItem("Gender")))
						.body("Result.DisplayValue", anyOf(hasItem("Gender")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))

						.body("Result.FieldName", anyOf(hasItem("TrainerID")))
						.body("Result.Description", anyOf(hasItem("Trainer")))
						.body("Result.DisplayValue", anyOf(hasItem("Trainer")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Promotion")))
						.body("Result.Description", anyOf(hasItem("Promotion")))
						.body("Result.DisplayValue", anyOf(hasItem("Promotion")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(15)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("SourceID")))
						.body("Result.Description", anyOf(hasItem("Source")))
						.body("Result.DisplayValue", anyOf(hasItem("Source")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Occupation")))
						.body("Result.Description", anyOf(hasItem("Occupation")))
						.body("Result.DisplayValue", anyOf(hasItem("Occupation of Member")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(30)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Employer")))
						.body("Result.Description", anyOf(hasItem("Employer")))
						.body("Result.DisplayValue", anyOf(hasItem("Employer")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("IncomeChoice")))
						.body("Result.Description", anyOf(hasItem("Income")))
						.body("Result.DisplayValue", anyOf(hasItem("Income")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Login")))
						.body("Result.Description", anyOf(hasItem("Online Log In")))
						.body("Result.DisplayValue", anyOf(hasItem("Online Log In")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("GroupID")))
						.body("Result.Description", anyOf(hasItem("Group Name")))
						.body("Result.DisplayValue", anyOf(hasItem("Group Name")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("InsuranceID")))
						.body("Result.Description", anyOf(hasItem("Insurance ID")))
						.body("Result.DisplayValue", anyOf(hasItem("Insurance ID")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("InsuranceRegistrationDate")))
						.body("Result.Description", anyOf(hasItem("Insurance Registration")))
						.body("Result.DisplayValue", anyOf(hasItem("Insurance Registration")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry1")))
						.body("Result.Description", anyOf(hasItem("Custom Text 1")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 1")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry2")))
						.body("Result.Description", anyOf(hasItem("Custom Text 2")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 2")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry3")))
						.body("Result.Description", anyOf(hasItem("Custom Text 3")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 3")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry4")))
						.body("Result.Description", anyOf(hasItem("Custom Text 4")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 4")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry5")))
						.body("Result.Description", anyOf(hasItem("Custom Text 5")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 5")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry6")))
						.body("Result.Description", anyOf(hasItem("Custom Text 6")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 6")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry7")))
						.body("Result.Description", anyOf(hasItem("Custom Text 7")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 7")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry8")))
						.body("Result.Description", anyOf(hasItem("Custom Text 8")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 8")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry9")))
						.body("Result.Description", anyOf(hasItem("Custom Text 9")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 9")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry10")))
						.body("Result.Description", anyOf(hasItem("Custom Text 10")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 10")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))

						.body("Result.FieldName", anyOf(hasItem("UserEntry11")))
						.body("Result.Description", anyOf(hasItem("Custom Text 11")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 11")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry12")))
						.body("Result.Description", anyOf(hasItem("Custom Text 12")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 12")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry13")))
						.body("Result.Description", anyOf(hasItem("Custom Text 13")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 13")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry14")))
						.body("Result.Description", anyOf(hasItem("Custom Text 14")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 14")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry15")))
						.body("Result.Description", anyOf(hasItem("Custom Text 15")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Text 15")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserEntry16")))
						.body("Result.Description", anyOf(hasItem("Conv Membership Type")))
						.body("Result.DisplayValue", anyOf(hasItem("Conv Membership Type")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(40)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("UserDate1")))
						.body("Result.Description", anyOf(hasItem("Custom Date 1")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Date 1")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("datetime")))
						
						.body("Result.FieldName", anyOf(hasItem("UserDate2")))
						.body("Result.Description", anyOf(hasItem("Custom Date 2")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Date 2")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("datetime")))
						
						.body("Result.FieldName", anyOf(hasItem("UserDate3")))
						.body("Result.Description", anyOf(hasItem("Custom Date 3")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Date 3")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("datetime")))
						
						.body("Result.FieldName", anyOf(hasItem("UserDate4")))
						.body("Result.Description", anyOf(hasItem("Custom Date 4")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Date 4")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("datetime")))

						.body("Result.FieldName", anyOf(hasItem("UserAmount1")))
						.body("Result.Description", anyOf(hasItem("Custom Amount 1")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Amount 1")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("currency")))
						
						.body("Result.FieldName", anyOf(hasItem("UserAmount2")))
						.body("Result.Description", anyOf(hasItem("Custom Amount 2")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Amount 2")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("currency")))
						
						.body("Result.FieldName", anyOf(hasItem("UserAmount3")))
						.body("Result.Description", anyOf(hasItem("Custom Amount 3")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Amount 3")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("currency")))
						
						.body("Result.FieldName", anyOf(hasItem("UserAmount4")))
						.body("Result.Description", anyOf(hasItem("Custom Amount 4")))
						.body("Result.DisplayValue", anyOf(hasItem("Custom Amount 4")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(10)))
						.body("Result.DataType", anyOf(hasItem("currency")))
						
						.body("Result.FieldName", anyOf(hasItem("CategoryID")))
						.body("Result.Description", anyOf(hasItem("Membership Type")))
						.body("Result.DisplayValue", anyOf(hasItem("Membership Type")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("PreferredPhone")))
						.body("Result.Description", anyOf(hasItem("Preferred Phone Number")))
						.body("Result.DisplayValue", anyOf(hasItem("Preferred Phone Number")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("CreditLimit")))
						.body("Result.Description", anyOf(hasItem("Credit Limit Value")))
						.body("Result.DisplayValue", anyOf(hasItem("Credit Limit Value")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("ReferringCustomerID")))
						.body("Result.Description", anyOf(hasItem("Referred By")))
						.body("Result.DisplayValue", anyOf(hasItem("Referred By")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("Date")))
						.body("Result.Description", anyOf(hasItem("Referral Date")))
						.body("Result.DisplayValue", anyOf(hasItem("Referral Date")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("InterestID")))
						.body("Result.Description", anyOf(hasItem("Interests")))
						.body("Result.DisplayValue", anyOf(hasItem("Interests")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))

						.body("Result.FieldName", anyOf(hasItem("GeneralNotes")))
						.body("Result.Description", anyOf(hasItem("Notes")))
						.body("Result.DisplayValue", anyOf(hasItem("Notes")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("ClubNumber")))
						.body("Result.Description", anyOf(hasItem("Home Club")))
						.body("Result.DisplayValue", anyOf(hasItem("Home Club")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("SalespersonID")))
						.body("Result.Description", anyOf(hasItem("Salesperson")))
						.body("Result.DisplayValue", anyOf(hasItem("Salesperson")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("MedicalNote")))
						.body("Result.Description", anyOf(hasItem("Medical Note")))
						.body("Result.DisplayValue", anyOf(hasItem("Medical Note")))
						.body("Result.Required", anyOf(hasItem(false)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(true)))
						.body("Result.MaximumLength", anyOf(hasItem(60)))
						.body("Result.DataType", anyOf(hasItem("string")))
						
						.body("Result.FieldName", anyOf(hasItem("BarcodeID")))
						.body("Result.Description", anyOf(hasItem("Member Id")))
						.body("Result.DisplayValue", anyOf(hasItem("Member Id")))
						.body("Result.Required", anyOf(hasItem(true)))
						.body("Result.ValueMustBeInDefinedList", anyOf(hasItem(false)))
						.body("Result.MaximumLength", anyOf(hasItem(0)))
						.body("Result.DataType", anyOf(hasItem("string")))
						;
	} 
}
