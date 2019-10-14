package JonasFitness.API;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetCardsOnFileByMember extends base {

	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
	}
	@Test (priority=1, description="1 Agreement with 1 Credit Card only")
	public void PBI146302_Test1() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
//						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.get("/api/v3/member/getCardsOnFileByMember/30020")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
	@Test (priority=1, description="Multiple Agreements with Card on File")
	public void PBI146302_Test2() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
//						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.get("/api/v3/member/getCardsOnFileByMember/30021")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
	@Test (priority=1, description="Multiple Cards on File with 1 Associated with Agreement")
	public void PBI146302_Test3() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
//						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.get("/api/v3/member/getCardsOnFileByMember/30018") // Thomas Manny
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
	@Test (priority=1, description="No Agreements with Card on File")
	public void PBI146302_Test4() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
						.get("/api/v3/member/getCardsOnFileByMember/"+member)// Robert Auto ID 29947
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(5L),TimeUnit.SECONDS)
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
	@Test (priority=1, description="No Card On File")
	public void PBI146302_Test5() {
		
		String member = prop.getProperty("activeMember1_CustomerId");
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", "B50A8F2BF7315812CF2A21690A7FF5FDA33A156C")
						.header("X-CompanyId", "101")
						.header("X-ClubId", "1")
					.when()
//						.get("/api/v3/member/getCardsOnFileByMember/"+member)
						.get("/api/v3/member/getCardsOnFileByMember/29970")
						.then()
//						.log().all()
						.assertThat()
						.statusCode(404)
						.statusLine("HTTP/1.1 404 Not Found")
						.body("Message", not(hasKey("Credit card not found")))
						.time(lessThan(5L),TimeUnit.SECONDS)
						
						
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
