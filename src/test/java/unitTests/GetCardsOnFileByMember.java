package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetCardsOnFileByMember extends base {
	
	static String aPIKey;
	static String companyId;
	static String clubId;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
	}
	
	@Test (testName="SingleAgreementWithCard", description="PBI:143543")
	public void SingleAgreementWithCard() {
		
				String member = prop.getProperty("availableId");

				given()
//					.log().all()
					.header("accept", "application/json")
					.header("X-Api-Key", aPIKey)
					.header("X-CompanyId", companyId)
					.header("X-ClubId", clubId)
				.when()
					.get("/api/v3/member/getCardsOnFileByMember/"+member)
				.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AccountId"))
						.body("Result[0].Address", hasKey("AddressLine1"))
						.body("Result[0].Address", hasKey("AddressLine2"))
						.body("Result[0].Address", hasKey("City"))
						.body("Result[0].Address", hasKey("Country"))
						.body("Result[0].Address", hasKey("PostalCode"))
						.body("Result[0].Address", hasKey("StateProvince"))
						.body("Result[0].Agreements[0]", hasKey("AgreementNumber"))
						.body("Result[0].Agreements[0]", hasKey("Description"))
						.body("Result[0].Agreements[0]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[0]", hasKey("CardType"))
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsHouseAccount"))
						.body("Result[0]", hasKey("IsSameAsMemberAddress"))
						.body("Result[0]", hasKey("NameOnCard"))
						.body("Result[0]", hasKey("TruncatedAccountNumber"))
						.body("Result[0]", not(hasKey("UseInPOS")))
						.body("Result[0]", not(hasKey("BankAccountType")))
						.body("Result[0]", not(hasKey("BankName")))
						.body("Result[0]", not(hasKey("BillingName")))
						.body("Result[0]", not(hasKey("IsBusiness")))
						.body("Result[0]", not(hasKey("RoutingNumber")));
	}
	
	@Test (testName="MultipleAgreementsWithMultipleCards", description="PBI:143543")
	public void MultipleAgreementsWithMultipleCards() {
		
						String member = prop.getProperty("MultipleAgreementsWithMultipleCardsId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AccountId"))
						.body("Result[0].Address", hasKey("AddressLine1"))
						.body("Result[0].Address", hasKey("AddressLine2"))
						.body("Result[0].Address", hasKey("City"))
						.body("Result[0].Address", hasKey("Country"))
						.body("Result[0].Address", hasKey("PostalCode"))
						.body("Result[0].Address", hasKey("StateProvince"))
						.body("Result[0].Agreements[0]", hasKey("AgreementNumber"))
						.body("Result[0].Agreements[0]", hasKey("Description"))
						.body("Result[0].Agreements[0]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[0]", hasKey("CardType"))
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsHouseAccount"))
						.body("Result[0]", hasKey("IsSameAsMemberAddress"))
						.body("Result[0]", hasKey("NameOnCard"))
						.body("Result[0]", hasKey("TruncatedAccountNumber"))
						.body("Result[1]", hasKey("AccountId"))
						.body("Result[1].Address", hasKey("AddressLine1"))
						.body("Result[1].Address", hasKey("AddressLine2"))
						.body("Result[1].Address", hasKey("City"))
						.body("Result[1].Address", hasKey("Country"))
						.body("Result[1].Address", hasKey("PostalCode"))
						.body("Result[1].Address", hasKey("StateProvince"))
						.body("Result[1].Agreements[0]", hasKey("AgreementNumber"))
						.body("Result[1].Agreements[0]", hasKey("Description"))
						.body("Result[1].Agreements[0]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[1]", hasKey("CardType"))
						.body("Result[1]", hasKey("ExpirationDate"))
						.body("Result[1]", hasKey("Id"))
						.body("Result[1]", hasKey("IsHouseAccount"))
						.body("Result[1]", hasKey("IsSameAsMemberAddress"))
						.body("Result[1]", hasKey("NameOnCard"))
						.body("Result[1]", hasKey("TruncatedAccountNumber"))
						.body("Result[0]", not(hasKey("UseInPOS")))
						.body("Result[0]", not(hasKey("BankAccountType")))
						.body("Result[0]", not(hasKey("BankName")))
						.body("Result[0]", not(hasKey("BillingName")))
						.body("Result[0]", not(hasKey("IsBusiness")))
						.body("Result[0]", not(hasKey("RoutingNumber")));
	}
	
	@Test (testName="MultipleAgreementsWithSingleCard", description="PBI:143543")
	public void MultipleAgreementsWithSingleCard() {
		
		String member = prop.getProperty("MultipleAgreementsWithSingleCardId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AccountId"))
						.body("Result[0].Address", hasKey("AddressLine1"))
						.body("Result[0].Address", hasKey("AddressLine2"))
						.body("Result[0].Address", hasKey("City"))
						.body("Result[0].Address", hasKey("Country"))
						.body("Result[0].Address", hasKey("PostalCode"))
						.body("Result[0].Address", hasKey("StateProvince"))
						.body("Result[0].Agreements[0]", hasKey("AgreementNumber"))
						.body("Result[0].Agreements[0]", hasKey("Description"))
						.body("Result[0].Agreements[0]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[0].Agreements[1]", hasKey("AgreementNumber"))
						.body("Result[0].Agreements[1]", hasKey("Description"))
						.body("Result[0].Agreements[1]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[0]", hasKey("CardType"))
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsHouseAccount"))
						.body("Result[0]", hasKey("IsSameAsMemberAddress"))
						.body("Result[0]", hasKey("NameOnCard"))
						.body("Result[0]", hasKey("TruncatedAccountNumber"));
	}
	
	@Test (testName="SingleAgreementMultipleCardsOnFile", description="PBI:143543")//Multiple Cards on File with 1 Associated with Agreement
	public void SingleAgreementMultipleCardsOnFile() {
		
				String member = prop.getProperty("SingleAgreementMultipleCardsOnFileId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member) // Scott Auto
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AccountId"))
						.body("Result[0].Address", hasKey("AddressLine1"))
						.body("Result[0].Address", hasKey("AddressLine2"))
						.body("Result[0].Address", hasKey("City"))
						.body("Result[0].Address", hasKey("Country"))
						.body("Result[0].Address", hasKey("PostalCode"))
						.body("Result[0].Address", hasKey("StateProvince"))
						.body("Result[0].Agreements[0]", hasKey("AgreementNumber"))
						.body("Result[0].Agreements[0]", hasKey("Description"))
						.body("Result[0].Agreements[0]", hasKey("IsAccountOwnerPrimaryMember"))
						.body("Result[0]", hasKey("CardType"))
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsHouseAccount"))
						.body("Result[0]", hasKey("IsSameAsMemberAddress"))
						.body("Result[0]", hasKey("NameOnCard"))
						.body("Result[0]", hasKey("TruncatedAccountNumber"))
						.body("Result[1]", hasKey("AccountId"))
						.body("Result[1].Address", hasKey("AddressLine1"))
						.body("Result[1].Address", hasKey("AddressLine2"))
						.body("Result[1].Address", hasKey("City"))
						.body("Result[1].Address", hasKey("Country"))
						.body("Result[1].Address", hasKey("PostalCode"))
						.body("Result[1].Address", hasKey("StateProvince"))
						.body("Result[1].Agreements[0]", not(hasKey("AgreementNumber")))
						.body("Result[1].Agreements[0]", not(hasKey("Description")))
						.body("Result[1].Agreements[0]", not(hasKey("IsAccountOwnerPrimaryMember")))
						.body("Result[1]", hasKey("CardType"))
						.body("Result[1]", hasKey("ExpirationDate"))
						.body("Result[1]", hasKey("Id"))
						.body("Result[1]", hasKey("IsHouseAccount"))
						.body("Result[1]", hasKey("IsSameAsMemberAddress"))
						.body("Result[1]", hasKey("NameOnCard"))
						.body("Result[1]", hasKey("TruncatedAccountNumber"))
						.body("Result[0]", not(hasKey("UseInPOS")))
						.body("Result[0]", not(hasKey("BankAccountType")))
						.body("Result[0]", not(hasKey("BankName")))
						.body("Result[0]", not(hasKey("BillingName")))
						.body("Result[0]", not(hasKey("IsBusiness")))
						.body("Result[0]", not(hasKey("RoutingNumber")));
	}
	
	@Test (testName="CardWithoutAgreement", description="PBI:143543")//No Agreements with Card on File
	public void CardWithoutAgreement() {
		
		String member = prop.getProperty("CardWithoutAgreementId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0]", hasKey("AccountId"))
						.body("Result[0].AccountId", equalTo("1"))
						.body("Result[0].Address", hasKey("AddressLine1"))
						.body("Result[0].Address", hasKey("AddressLine2"))
						.body("Result[0].Address", hasKey("City"))
						.body("Result[0].Address", hasKey("Country"))
						.body("Result[0].Address", hasKey("PostalCode"))
						.body("Result[0].Address", hasKey("StateProvince"))
						.body("Result[0].Agreements[0]", not(hasKey("AgreementNumber")))
						.body("Result[0].Agreements[0]", not(hasKey("Description")))
						.body("Result[0].Agreements[0]", not(hasKey("IsAccountOwnerPrimaryMember")))
						.body("Result[0]", hasKey("CardType"))
						.body("Result[0]", hasKey("ExpirationDate"))
						.body("Result[0]", hasKey("Id"))
						.body("Result[0]", hasKey("IsHouseAccount"))
						.body("Result[0].IsHouseAccount", equalTo(false))
						.body("Result[0]", hasKey("IsSameAsMemberAddress"))
						.body("Result[0]", hasKey("NameOnCard"))
						.body("Result[0]", hasKey("TruncatedAccountNumber"))
						.body("Result[0]", not(hasKey("UseInPOS")))
						.body("Result[0]", not(hasKey("BankAccountType")))
						.body("Result[0]", not(hasKey("BankName")))
						.body("Result[0]", not(hasKey("BillingName")))
						.body("Result[0]", not(hasKey("IsBusiness")))
						.body("Result[0]", not(hasKey("RoutingNumber")));
	}
	
	@Test (testName="noCardOnFile", description="PBI:143543")
	public void NoCardOnFile() {
		
				String member = prop.getProperty("noFOPId");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", aPIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.then()
//						.log().all()
						.assertThat()
						.statusCode(404)
						.statusLine("HTTP/1.1 404 Not Found")
						.body("Message", equalTo("No credit cards found"))
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result", not(hasKey("AccountId")))
						.body("Result", not(hasKey("Address")))
						.body("Result", not(hasKey("Agreements")))
						.body("Result", not(hasKey("CardType")))
						.body("Result", not(hasKey("ExpirationDate")))
						.body("Result", not(hasKey("Id")))
						.body("Result", not(hasKey("IsHouseAccount")))
						.body("Result", not(hasKey("IsSameAsMemberAddress")))
						.body("Result", not(hasKey("NameOnCard")))
						.body("Result", not(hasKey("TruncatedAccountNumber")))
						.body("Result", not(hasKey("UseInPOS")))
						.body("Result", not(hasKey("BankAccountType")))
						.body("Result", not(hasKey("BankName")))
						.body("Result", not(hasKey("BillingName")))
						.body("Result", not(hasKey("IsBusiness")))
						.body("Result", not(hasKey("RoutingNumber")));
	}

}
