package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.MemberPL;
import resources.ReusableMethods;
import resources.base;
import resources.myActions;

public class AddCardOnFileByMember extends base {
	
			static String aPIKey;
			static String companyId;
			static String clubId;
			
			static String updateActiveAgreements;
			static String customerId;
			static String cardNumber;
			static String cardType;
			static String expirationMonth;
			static String expirationYear;
			static String cardHolderName;
			static String addressLine1;
			static String addressLine2;
			static String city;
			static String stateProvince;
			static String postalCode;
			
			static String addressIsSameAsMemberAddress;
			static String useInPos;
			static String setAsHouseAccount;
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		aPIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		
		updateActiveAgreements = "false";
		addressIsSameAsMemberAddress = "false";
		useInPos = "true";
		setAsHouseAccount = "true";
		 
		cardNumber = prop.getProperty("changeCCMember1AccountNumber");
		cardType = prop.getProperty("changeCCMember1CardType");
		expirationMonth = prop.getProperty("changeCCMember1ExpirationMonth");
		expirationYear = prop.getProperty("changeCCMember1ExpirationYear");
		cardHolderName = prop.getProperty("changeCCMember1NameOnCard");
		addressLine1 = prop.getProperty("changeCCMember1AddressLine1");
		addressLine2 = prop.getProperty("changeCCMember1AddressLine2");
		city = prop.getProperty("changeCCMember1City");
		stateProvince = prop.getProperty("changeCCMember1StateProvince");
		postalCode = prop.getProperty("changeCCMember1PostalCode");
	}
	
	@Test (testName="Card Added", description="PBI:150278")
	public void cardAdded() {
		
				customerId = myActions.createMember(aPIKey, companyId, clubId);

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.addCardOnFileByMemberCardNumberOnly(
						updateActiveAgreements,
						customerId,
						cardNumber,
						expirationMonth,
						expirationYear,
						cardHolderName,
						addressIsSameAsMemberAddress,
						addressLine1,
						addressLine2,
						city,
						stateProvince,
						postalCode,
						useInPos,
						setAsHouseAccount))
				.post("/api/v3/member/addcardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200);	
			
				Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
				
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Result[0].ExpirationDate").contains(expirationMonth));
				Assert.assertTrue(js.getString("Result[0].ExpirationDate").contains(expirationYear));
				Assert.assertEquals(js.getString("Result[0].NameOnCard"),cardHolderName);
				Assert.assertEquals(js.getString("Result[0].Address.AddressLine1"),addressLine1);
				Assert.assertEquals(js.getString("Result[0].Address.AddressLine2"),addressLine2);
				Assert.assertEquals(js.getString("Result[0].Address.City"),city);
				Assert.assertEquals(js.getString("Result[0].Address.PostalCode"),postalCode);
				Assert.assertEquals(js.getString("Result[0].Address.StateProvince"),stateProvince);
				Assert.assertEquals(js.getString("Result[0].CardType"),cardType);
				Assert.assertEquals(js.getString("Result[0].IsHouseAccount"),setAsHouseAccount);
	}
		
}






















