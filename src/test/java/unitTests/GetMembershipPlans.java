package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;

public class GetMembershipPlans extends base {
	
	String companyId;
	String valueAssertions;
	String APIKey;
	String clubId;
	String includeAllClubs;
	String includeIndividualPlans;
	String planCategoryId;
	String duration;
	String durationType;
	String availableOnlineOnly;
	String planType;
	int count;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		APIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		valueAssertions = prop.getProperty("valueAssertions");
		
		/*
		 * IndividualPlansFilter values:-  Family Plan, Individual Plan.  Comment the parameter for All
		 * AgreementCategoryIdFilter :- Comment the parameter for All
		 * DurationFilterValue :- Comment the parameter for All
		 * DurationFilter :- Months, Weeks Comment the parameter for All
		 * OnlineFilter :- OnlineOnly, OnlineAndClub, NotAvailableOnline.  Comment the parameter for All
		 * PlanTypeFilter :- OpenEnded, Terms, TermsWRollover. Comment the parameter for All
		  */
		
	}
	
	@Test (testName="All Parameters",description="PBI: 179765 and PBI 185540")
	public void allParameters() {
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?"
								+clubId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());
													

	}		

	@Test (testName="Required Parameters Only",description="PBI: 179765 and PBI 185540")
	public void requiredParametersOnly() {
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());							

	}		

	@Test (testName="Specific Agreement Category",description="PBI: 179765 and PBI 185540")
	public void specificPlanCategory() {
		
				String planCategoryId = prop.getProperty("planCategory1Id");
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&AgreementCategoryIdFilter="+planCategoryId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.PlanCategoryId.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanCategoryIdReturned = js.getString("Result["+i+"].PlanCategoryId");
													   				
							Assert.assertTrue(PlanCategoryIdReturned.equals(planCategoryId)); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());									

	}	
	
	@Test (testName="Plan Type - Open Ended",description="PBI: 179765 and PBI 185540")
	public void planTypeOpenEnded() {

				String planType = "OpenEnded";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&PlanTypeFilter="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.PlanType.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanTypeReturned = js.getString("Result["+i+"].PlanType");
																				   				
							Assert.assertTrue(PlanTypeReturned.equals(planType)); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());								

	}	
	
	@Test (testName="Plan Type - Terms - Default Duration",description="PBI: 179765 and PBI 185540")
	public void planTypeTermsDefaultDuration() {

				String planType = "Terms";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&PlanTypeFilter="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.PlanType.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanTypeReturned = js.getString("Result["+i+"].PlanType");
																				   				
							Assert.assertTrue(PlanTypeReturned.equals(planType)); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());								

	}
	
	@Test (testName="Plan Type - Terms 2 Weeks",description="PBI: 179765 and PBI 185540")
	public void planTypeTerms2Weeks() {

				String planType = "Terms";
				String duration = "2";
				String durationType = "Weeks";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&PlanTypeFilter="+planType+"&DurationFilter="+durationType+"&DurationFilterValue="+duration+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.PlanType.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanTypeReturned = js.getString("Result["+i+"].PlanType");
																				   				
							Assert.assertTrue(PlanTypeReturned.equals(planType)); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());									

	}
	
	@Test (testName="Plan Type - Terms 2 Months",description="PBI: 179765 and PBI 185540")
	public void planTypeTerms2Months() {

				String planType = "Terms";
				String duration = "2";
				String durationType = "Months";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&PlanTypeFilter="+planType+"&DurationFilter="+durationType+"&DurationFilterValue="+duration+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						count = js.getInt("Result.PlanType.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanTypeReturned = js.getString("Result["+i+"].PlanType");
																				   				
							Assert.assertTrue(PlanTypeReturned.equals(planType)); 
							
						}
						
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());									

	}
	
	@Test (testName="Plan Type - Terms With Rollover",description="PBI: 179765 and PBI 185540")
	public void planTypeTermsWithRollover() {

				String planType = "TermsWRollover";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&PlanTypeFilter="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.PlanType.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String PlanTypeReturned = js.getString("Result["+i+"].PlanType");
																				   				
							Assert.assertTrue(PlanTypeReturned.equals(planType)); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());									

	}
	
	@Test (testName="Available Online Only Disabled",description="PBI: 179765 and PBI 185540")
	public void availableOnlineOnlyDisabled() {

				String OnlineFilter = "NotAvailableOnline";
				String membershipPlanNotAvailableOnlineId = prop.getProperty("membershipPlanNotAvailableOnlineId");
				String membershipPlanNotAvailableOnlineDescription = prop.getProperty("membershipPlanNotAvailableOnlineDescription");
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId=1&OnlineFilter="+OnlineFilter+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanNotAvailableOnlineId));
						Assert.assertTrue(js.getString("Result.Description").contains(membershipPlanNotAvailableOnlineDescription));
						
						}
	}
	
	@Test (testName="Available Online Only Enabled",description="PBI: 179765 and PBI 185540")
	public void availableOnlineOnlyEnabled() {

				String OnlineFilter = "OnlineOnly";
				String membershipPlanAvailableOnlineId = prop.getProperty("membershipPlanAvailableOnlineId");
				String membershipPlanAvailableOnlineDescription = prop.getProperty("membershipPlanAvailableOnlineDescription");
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId=1&OnlineFilter="+OnlineFilter+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

												
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());								
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanAvailableOnlineId));
						Assert.assertTrue(js.getString("Result.Description").contains(membershipPlanAvailableOnlineDescription));
						
						}
	}
	
	@Test (testName="Plan Available in Specific Club",description="PBI: 179765 and PBI 185540")
	public void planAvailableInSpecificClub() {

				String clubId = "1";
				String membershipPlanClub1OnlyId = prop.getProperty("membershipPlanClub1OnlyId");
				String membershipPlanClub1OnlyDescription = prop.getProperty("membershipPlanClub1OnlyDescription");
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
												
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());								
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanClub1OnlyId));
						Assert.assertTrue(js.getString("Result.Description").contains(membershipPlanClub1OnlyDescription));
						
						}
	}

	@Test (testName="Plan Not Available in Specific Club",description="PBI: 179765 and PBI 185540")
	public void planNotAvailableInSpecificClub() {

				String clubId = "2";
				String membershipPlanClub1OnlyId = prop.getProperty("membershipPlanClub1OnlyId");
				String membershipPlanClub1OnlyDescription = prop.getProperty("membershipPlanClub1OnlyDescription");
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
			
						JsonPath js = ReusableMethods.rawToJson(res);
												
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());	
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(!js.getString("Result.PlanId").contains(membershipPlanClub1OnlyId));
						Assert.assertTrue(!js.getString("Result.Description").contains(membershipPlanClub1OnlyDescription));
						
						}
	}

	@Test (testName="Family Plans Only",description="PBI: 179765 and PBI 185540")
	public void familyPlansOnly() {

				String IndividualPlansFilter = "FamilyPlan";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId=1&IndividualPlansFilter="+IndividualPlansFilter+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.IsIndividualPlan.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String IsIndividualPlan = js.getString("Result["+i+"].IsIndividualPlan");
																				   				
							Assert.assertEquals(IsIndividualPlan, "false"); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].MembershipAreas[0].Description").isBlank());	
	}

	@Test (testName="Individual Plans Only",description="PBI: 179765 and PBI 185540")
	public void individualPlansOnly() {

				String IndividualPlansFilter = "IndividualPlan";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId=1&IndividualPlansFilter="+IndividualPlansFilter+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						count = js.getInt("Result.IsIndividualPlan.size()");
						
						for (int i = 0; i<count; i++)
							
						{
							String IsIndividualPlan = js.getString("Result["+i+"].IsIndividualPlan");
																				   				
							Assert.assertEquals(IsIndividualPlan, "true"); 
							
						}
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Description").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPaymentOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].Services[0].Description").isBlank());
							}
	
	@Test (testName="Club Not Found",description="PBI: 179765 and PBI 185540")
	public void clubNotFound() {

				String clubId = "99999";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(401)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(js.getString("Message").equals("Invalid authorization credentials (Club Does Not Exist)"));
						
	}
	
	@Test (testName="Plan Category Not Found",description="PBI: 179765 and PBI 185540")
	public void planCategoryNotFound() {

				String planCategoryId = "99999";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
					.get("/api/v3/agreement/getmembershipplans?ClubId="+clubId+"&AgreementCategoryIdFilter="+planCategoryId+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(404)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(js.getString("Message").equals("Nothing found"));
						
	}
	
}
