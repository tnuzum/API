package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.FinancialPL;
import resources.ReusableDates;
import resources.ReusableMethods;
import resources.base;

public class TakePaymentWithNewCreditCard extends base {
	
		 String aPIKey;
		 String companyId;
		 String clubId;
	
		 static String cardNumber;
		 static String nameOnCard;
		 static String expirationDate;
		 static String securityCode;
		 static String addressLine1;
		 static String addressLine2;
		 static String city;
		 static String stateProvince;
		 static String postalCode;
		 static String country;
		 static String employeeBarcodeId;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		employeeBarcodeId = prop.getProperty("activeEmployeeBarcodeId");
		
		cardNumber = prop.getProperty("MC1CardNumber");
		nameOnCard = prop.getProperty("MC1NameOnCard");
		expirationDate = ReusableDates.getCurrentDatePlusOneYear();
		securityCode = prop.getProperty("MC1SecurityCode");
		addressLine1 = prop.getProperty("MC1AddressLine1");
		addressLine2 = prop.getProperty("MC1AddressLine2");
		city = prop.getProperty("MC1City");
		stateProvince = prop.getProperty("MC1State");
		postalCode = prop.getProperty("MC1PostalCode");
	}

	@Test (testName="Take MasterCard Payment On Invoice",description="PBI:150194")
	public void takeMasterCardPaymentOnInvoice() {

				String customerId = prop.getProperty("availableId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				int invoiceId = 44362;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_AllParameters(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, addressLine2, city, stateProvince, postalCode, country, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory,invoiceId))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("ReceiptNumber", not(nullValue()));
	}
	
	@Test (testName="Take MasterCard Payment No Invoice",description="PBI:150194")
	public void takeMasterCardPaymentNoInvoice() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("ReceiptNumber", not(nullValue()));
	}
	
	@Test (testName="Invoice Not Found",description="PBI:150194")
	public void invoiceNotFound() {

				String customerId = prop.getProperty("availableId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				int invoiceId = 999999;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
			.body(FinancialPL.takePaymentWithNewCreditCard_AllParameters(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, addressLine2, city, stateProvince, postalCode, country, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory,invoiceId))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
//				.log().all()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Member Not Found",description="PBI:150194")
	public void memberNotFound() {

				String customerId = "99999";
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				int invoiceId = 44362;

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory)).body(FinancialPL.takePaymentWithNewCreditCard_AllParameters(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, addressLine2, city, stateProvince, postalCode, country, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory,invoiceId))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
//				.log().all()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Payment Category Not Found",description="PBI:150194", enabled = false)
	public void paymentCategoryNotFound() {
		
		// currently accepts invalid Payment Category

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = "NOT PAYMENT CATEGORY";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Effective Date In Past",description="PBI:150194")
	public void effectiveDateInPast() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDateMinusOneYear();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("ReceiptNumber", not(nullValue()));
	}
	
	@Test (testName="Card Expired",description="PBI:150194", enabled = true)
	public void cardExpired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				expirationDate = "2020-01-01T00:00:00Z";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(409)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(409))
				.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Card Number Invalid",description="PBI:150194", enabled = false)
	public void cardNumberInvalid() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				cardNumber = "545454545454545400";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
				.log().all()
				.statusCode(409)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(409))
				.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Card Number Required",description="PBI:150194", enabled = true)
	public void cardNumberRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String cardNumber = prop.getProperty("NOT REAL CARD");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
//				.log().all()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("The CardNumber field is required."));
	}
	
	@Test (testName="Name On Card Required",description="PBI:150194", enabled = true)
	public void cardNumberRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String cardNumber = prop.getProperty("NOT REAL CARD");

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
			.when()
				.body(FinancialPL.takePaymentWithNewCreditCard_RequiredParametersOnly(cardNumber, nameOnCard, expirationDate, securityCode, addressLine1, city, stateProvince, postalCode, customerId, employeeBarcodeId, clubId, amount, effectiveDate, paymentDescription, paymentCategory))
				.post("/api/v3/financial/takepaymentwithnewcreditcardformember")
			.then()
				.assertThat()
//				.log().all()
				.statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(400))
				.body("Message", equalTo("The CardNumber field is required."));
	}
	
	
	/*
	@Test (testName="Missing Member Name On Card",description="PBI:146579")
	public void missingMemberNameOnCard() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String cardNumber = prop.getProperty("MC1CardNumber");
				String nameOnCard = "";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The NameOnCard field is required."));
	}
	
	
	
	@Test (testName="Missing Card Number",description="PBI:146579")
	public void missingCardNumber() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String cardNumber = "";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The CardNumber field is required."));
	}
	
	@Test (testName="Missing Security Code",description="PBI:146579")
	public void missingSecurityCode() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String securityCode = "";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The SecurityCode field is required."));
	}
	
	@Test (testName="Missing Address Line1",description="PBI:146579")
	public void missingAddressLine1() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String addressLine1	= "";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The AddressLine1 field is required."));
	}
	
	@Test (testName="Missing City",description="PBI:146579")
	public void missingCity() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String city = "";
				
				ReusableMethods.myWait(200);

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The City field is required."));
	}
	
	@Test (testName="Missing StateProvince",description="PBI:146579")
	public void missingStateProvince() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String state = "";

				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The StateProvince field is required."));
	}
	
	@Test (testName="Missing Postal Code",description="PBI:146579")
	public void missingPostalCode() {
		
				String c = prop.getProperty("availableId");
				int customerId = Integer.parseInt(c);
				String classId = prop.getProperty("alwaysAvailClId");
				String classOccurrence = prop.getProperty("alwaysAvailClOccurrence");
				String displayedGrandTotal = prop.getProperty("alwaysAvailClPrice");
				String postalCode = "";
				
				ReusableMethods.myWait(200);
				
				given()
//						.log().all()
				.header("accept", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
				.header("Content-Type", "application/json")
					.when()
					.body(ClassCoursePL.EnrollMemberInClassWithNewCreditCard(customerId,classId,classOccurrence,displayedGrandTotal,cardNumber,nameOnCard,month,year,securityCode,addressLine1,city,state,postalCode,enrollCustomerAsStandby,onlineEnrollment))
						.post("/api/v3/classcourse/enrollmemberinclasswithnewcreditcard")
						.then()
//						.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("The PostalCode field is required."));
	}
	*/
}
