package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import java.util.concurrent.TimeUnit;
import io.restassured.RestAssured;
import payloads.FinancialPL;
import resources.ReusableDates;
import resources.base;

public class TakePaymentWithNewCreditCard extends base {
	
		 static String aPIKey;
		 static String companyId;
		 static String clubId;
	
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

				String customerId = "235";	//String customerId = prop.getProperty("availableId");
				Double amount = 5.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				int invoiceId = 45909;	//int invoiceId = 44362;
				String cardNumber = "4111111111111111";

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
				.statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.body("Status", equalTo(200))
				.body("ReceiptNumber", not(nullValue()));
	}
	
	@Test (testName="Take MasterCard Payment No Invoice",description="PBI:150194")
	public void takeMasterCardPaymentNoInvoice() {

				String customerId = "235";	//String customerId = prop.getProperty("noFOPId");
				Double amount = 15.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card Payment Received";
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
//				.log().all()
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
				.statusCode(500)
				.time(lessThan(60L),TimeUnit.SECONDS);
	}
	
	@Test (testName="Payment Category Not Found",description="PBI:150194", enabled = false)
	public void paymentCategoryNotFound() {
		
		// currently this field is not required

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = "";

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
//				.log().all()
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
				String expirationDate = "2020-01-01T00:00:00Z";

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
				.body("Status", equalTo(409))
				.body("Message", equalTo("Credit Card Processing Failed"));
	}
	
	@Test (testName="Card Number Invalid",description="PBI:150194", enabled = true)
	public void cardNumberInvalid() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String cardNumber = "545454545454545400";

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
				String cardNumber = "";

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
	public void nameOnCardRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
//				String nameOnCard = prop.getProperty("NOT REAL NAME"); this passes a null value but the call is successful
				String nameOnCard = "";

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
				.body("Message", equalTo("The NameOnCard field is required."));
	}
	
	@Test (testName="Security Code Required",description="PBI:150194", enabled = true)
	public void securityCodeRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String securityCode = prop.getProperty("NOT REAL SEC CODE");

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
				.body("Message", equalTo("The SecurityCode field is required."));
	}

	@Test (testName="Address Line1 Required",description="PBI:150194", enabled = true)
	public void addressLine1Required() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				//String addressLine1 = prop.getProperty("NOT REAL ADDRESS");
				String addressLine1 = "";

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
				.body("Message", equalTo("The AddressLine1 field is required."));
	}
	
	@Test (testName="City Required",description="PBI:150194", enabled = true)
	public void cityRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String city = "";

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
				.body("Message", equalTo("The City field is required."));
	}
	
	@Test (testName="StateProvince Required",description="PBI:150194", enabled = true)
	public void stateProvinceRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				//String stateProvince = prop.getProperty("NOT REAL STATE");
				String stateProvince = "";

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
				.body("Message", equalTo("The StateProvince field is required."));
	}
	
	@Test (testName="Postal Code Required",description="PBI:150194", enabled = true)
	public void postalCodeRequired() {

				String customerId = prop.getProperty("noFOPId");
				Double amount = 1.00;
				String effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
				String paymentDescription = "Credit Card payment received";
				String paymentCategory = prop.getProperty("paymentCategory1");
				String postalCode = prop.getProperty("NOT REAL STATE");

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
				.body("Message", equalTo("The PostalCode field is required."));
	}
}
