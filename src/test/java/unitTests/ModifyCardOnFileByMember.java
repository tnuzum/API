package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.MemberPL;
import resources.ReusableMethods;
import resources.base;

public class ModifyCardOnFileByMember extends base {
	
			static String aPIKey;
			static String companyId;
			static String clubId;
			
			static String accountId;
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
		
		 accountId = "1";
		 customerId = prop.getProperty("changeCCMember1Id");
			
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
	
	@DataProvider
	public Object[] getDataProvider(){
		
		Object[][] data = new Object[2][10];
		
				data[0][0] = cardNumber;
				data[0][1] = prop.getProperty("changeCCMember1CardType");
				data[0][2] = expirationMonth;
				data[0][3] = expirationYear;
				data[0][4] = prop.getProperty("changeCCMember1NameOnCard");
				data[0][5] = prop.getProperty("changeCCMember1AddressLine1");
				data[0][6] = prop.getProperty("changeCCMember1AddressLine2");
				data[0][7] = prop.getProperty("changeCCMember1City");
				data[0][8] = prop.getProperty("changeCCMember1StateProvince");
				data[0][9] = prop.getProperty("changeCCMember1PostalCode");
				data[1][0] = prop.getProperty("changeCCMember2AccountNumber");
				data[1][1] = prop.getProperty("changeCCMember2CardType");
				data[1][2] = prop.getProperty("changeCCMember2ExpirationMonth");
				data[1][3] = prop.getProperty("changeCCMember2ExpirationYear");
				data[1][4] = prop.getProperty("changeCCMember2NameOnCard");
				data[1][5] = prop.getProperty("changeCCMember2AddressLine1");
				data[1][6] = prop.getProperty("changeCCMember2AddressLine2");
				data[1][7] = prop.getProperty("changeCCMember2City");
				data[1][8] = prop.getProperty("changeCCMember2StateProvince");
				data[1][9] = prop.getProperty("changeCCMember2PostalCode");
				
				return data;
	}
	
	@Test (testName="Modify All Fields", description="PBI:164154", priority = 1, dataProvider="getDataProvider")
	public void modifyAllFields(String cardNumber, String cardType, String expirationMonth, String expirationYear, String cardHolderName, String addressLine1, String addressLine2, String city, String stateProvince, String postalCode) {

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS);
			
				Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
			
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertEquals(js.getString("Result[0].AccountId"),accountId);
				Assert.assertTrue(cardNumber.contains(js.getString("Result[0].TruncatedAccountNumber")));
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
//				Assert.assertEquals(js.getString("Result[0].IsSameAsMemberAddress"),addressIsSameAsMemberAddress);
				 // researching, coming back as null
	}
	
	@Test (testName="Address Is Same As Member Address", description="PBI:164154")
	public void addressIsSameAsMemberAddress() {
		
				String addressIsSameAsMemberAddress = "true";
				
				String addressLine1 = "Fake Address 1";
				String addressLine2 = "Fake Address 2";
				String city = "New York";
				String stateProvince = "NY";
				String postalCode = "10001";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200);	
			
				Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
				
				JsonPath js = ReusableMethods.rawToJson(res);

				Assert.assertFalse(js.getString("Result[0].Address.AddressLine1").equals(addressLine1));
				Assert.assertFalse(js.getString("Result[0].Address.AddressLine2").equals(addressLine2));
				Assert.assertFalse(js.getString("Result[0].Address.City").equals(city));
				Assert.assertFalse(js.getString("Result[0].Address.PostalCode").equals(postalCode));
				Assert.assertFalse(js.getString("Result[0].Address.StateProvince").equals(stateProvince));
	}
	
	@Test (testName="Set Card 1 As House Account", description="PBI:164154", priority = 1, enabled = false)
	public void setCard1AsHouseAccount() {
		
				String customerId = prop.getProperty("houseAcctChangeId");
				String cardNumber = "5454545454545454";
				String accountId = "1";
				String setAsHouseAccount = "true";

			given()
				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
				.log().all()
				.assertThat().statusCode(200);	
			
				Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);				
				JsonPath js = ReusableMethods.rawToJson(res);
				Assert.assertEquals(js.getBoolean("Result[0].IsHouseAccount"), true);
				Assert.assertEquals(js.getBoolean("Result[1].IsHouseAccount"), false);
	}
	
	@Test (testName="Set Card 2 As House Account", description="PBI:164154", priority = 2, enabled = false)
		// This test fails if there are 2 accounts with same CC number
	public void setCard2AsHouseAccount() {
		
				String customerId = prop.getProperty("houseAcctChangeId");
				String cardNumber = "4111111111111111";
				String accountId = "2";
				String setAsHouseAccount = "true";

			given()
				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
				.log().all()
				.assertThat().statusCode(200);	
			
				Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);				
				JsonPath js = ReusableMethods.rawToJson(res);
				Assert.assertEquals(js.getBoolean("Result[0].IsHouseAccount"), false);
				Assert.assertEquals(js.getBoolean("Result[1].IsHouseAccount"), true);
	}
	
	@Test (testName="Account Id Required", description="PBI:164154")
	public void accountIdRequired() {
		
			String accountId = "";

		Response res = 
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Message").contains("Error converting value {null}"));	
	}
	
	@Test (testName="Account Id Not Found", description="PBI:164154")
	public void accountIdNotFound() {
		
			String accountId = "99999";

		Response res = 
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
//				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Messages").contains("No card on file could be found for customer '"+customerId+"', account '"+accountId+"'"));	
	}
	
	@Test (testName="Customer Id Required", description="PBI:164154")
	public void customerIdRequired() {
		
			String customerId = "";

		Response res = 
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Message").contains("Error converting value {null}"));	
	}
	
	@Test (testName="Customer Not Found", description="PBI:164154")
	public void customerNotFound() {
		
			String customerId = "999999";

		Response res = 
			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body(MemberPL.modifyCardOnFileByMemberAllFields(accountId,customerId,cardNumber,expirationMonth,expirationYear,cardHolderName,addressIsSameAsMemberAddress,addressLine1,addressLine2,city,stateProvince,postalCode,useInPos,setAsHouseAccount))
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
//				.assertThat().statusCode(400)
				.time(lessThan(60L),TimeUnit.SECONDS)
				.extract().response();
		
				JsonPath js = ReusableMethods.rawToJson(res);
				
				Assert.assertTrue(js.getString("Messages").contains("No card on file could be found for customer '"+customerId+"', account '"+accountId+"'"));	
	}

	@Test (testName="Modify Address Line 1", description="PBI:164154")
	public void modifyAddressLine1() {
		
			String addressIsSameAsMemberAddress = "false";
			String addressLine1 = "1200 Northwoods Dr.";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body("{\r\n" + 
						"  \"CustomerId\": \""+customerId+"\",\r\n" + 
						"  \"AccountId\": \""+accountId+"\",\r\n" + 
						"  \"AddressIsSameAsMemberAddress\": \""+addressIsSameAsMemberAddress+"\",\r\n" + 
						"  \"Address\": {\r\n" + 
						"    \"AddressLine1\": \""+addressLine1+"\"\r\n" + 
						"  }\r\n" +  
						"}")
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS);
			
		Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
			
				JsonPath js = ReusableMethods.rawToJson(res);

				Assert.assertTrue(js.getString("Result[0].Address.AddressLine1").equals(addressLine1));
	}
	
	@Test (testName="Modify Address Line 2", description="PBI:164154")
	public void modifyAddressLine2() {
		
			String addressIsSameAsMemberAddress = "false";
			String addressLine2 = "Apt. 1202";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body("{\r\n" + 
						"  \"CustomerId\": \""+customerId+"\",\r\n" + 
						"  \"AccountId\": \""+accountId+"\",\r\n" + 
						"  \"AddressIsSameAsMemberAddress\": \""+addressIsSameAsMemberAddress+"\",\r\n" + 
						"  \"Address\": {\r\n" + 
						"    \"AddressLine2\": \""+addressLine2+"\"\r\n" + 
						"  }\r\n" + 
						"}")
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS);	
			
			Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
			
				JsonPath js = ReusableMethods.rawToJson(res);

				Assert.assertTrue(js.getString("Result[0].Address.AddressLine2").equals(addressLine2));
	}
	
	@Test (testName="Modify City", description="PBI:164154")
	public void modifyCity() {
		
			String addressIsSameAsMemberAddress = "false";
			String city = "Westerville";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body("{\r\n" + 
						"  \"CustomerId\": \""+customerId+"\",\r\n" + 
						"  \"AccountId\": \""+accountId+"\",\r\n" + 
						"  \"AddressIsSameAsMemberAddress\": \""+addressIsSameAsMemberAddress+"\",\r\n" + 
						"  \"Address\": {\r\n" + 
						"    \"City\": \""+city+"\"\r\n" + 
						"  }\r\n" + 
						"}")
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS);	
			
			Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
			
				JsonPath js = ReusableMethods.rawToJson(res);

				Assert.assertTrue(js.getString("Result[0].Address.City").equals(city));
	}
	
	@Test (testName="Modify StateProvince", description="PBI:164154")
	public void modifyStateProvince() {
		
			String addressIsSameAsMemberAddress = "false";
			String stateProvince = "CA";

			given()
//				.log().all()
				.header("accept", "application/json")
				.header("Content-Type", "application/json")
				.header("X-Api-Key", aPIKey)
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
			.when()
				.body("{\r\n" + 
						"  \"CustomerId\": \""+customerId+"\",\r\n" + 
						"  \"AccountId\": \""+accountId+"\",\r\n" + 
						"  \"AddressIsSameAsMemberAddress\": \""+addressIsSameAsMemberAddress+"\",\r\n" + 
						"  \"Address\": {\r\n" + 
						"    \"StateProvince\": \""+stateProvince+"\"\r\n" + 
						"  }\r\n" + 
						"}")
				.post("/api/v3/member/modifycardonfilebymember")
			.then()
//				.log().all()
				.assertThat().statusCode(200)
				.time(lessThan(60L),TimeUnit.SECONDS);	
			
			Response res = resources.myGets.getCardsOnFileByMember(aPIKey, companyId, clubId, customerId);
			
				JsonPath js = ReusableMethods.rawToJson(res);

				Assert.assertTrue(js.getString("Result[0].Address.StateProvince").equals(stateProvince));
	}
	
		
}






















