package utilities;

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
		 
		 static String customerId;
		 static String employeeBarcodeId;
		 static Double amount;
		 static String effectiveDate;
		 static String paymentDescription;
		 static String paymentCategory;
	
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

	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		
		customerId = prop.getProperty("takePayId");
		employeeBarcodeId = prop.getProperty("activeEmployeeBarcodeId");
		amount = 1.00;
		effectiveDate = ReusableDates.getCurrentDatePlusOneDay();
		paymentDescription = "Credit Card Payment Received";
		paymentCategory = prop.getProperty("paymentCategory1");
		
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
	
	@Test (testName="Take MasterCard Payment No Invoice")
	public void takeMasterCardPaymentNoInvoice() {

			given()
				.log().all()
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
}
