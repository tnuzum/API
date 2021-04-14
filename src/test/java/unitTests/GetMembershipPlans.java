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

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		APIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		valueAssertions = prop.getProperty("valueAssertions");
		
		includeAllClubs = "AllClubs"; // AllClubs(default), SingleClub, MultipleClubs
		includeIndividualPlans = "AllPlans"; // AllPlans(default), 0=FamilyPlan, 1=IndividualPlan
		planCategoryId = "-1"; // -1=All Agreement Categories(default)
		duration = "-1"; // -1=All Durations(default)
		durationType = "Months"; // Months(default), Weeks (Note: this parameter is ignored if duration = -1)
		availableOnlineOnly = "EnabledAndDisabled"; // EnabledAndDisabled(default), Disabled, Enabled
		planType = "AllPlans"; // AllPlans(default), OpenEnded, Terms, TermsWithRollover
	}
	
	@Test (testName="All Parameters",description="PBI: 179765")
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
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									

	}		

	@Test (testName="Required Parameters Only",description="PBI: 179765")
	public void requiredParametersOnly() {
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));								

	}		

	@Test (testName="Specific Agreement Category",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									

	}	
	
	@Test (testName="Plan Type - Open Ended",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].PlanType").equals(planType));
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));								

	}	
	
	@Test (testName="Plan Type - Terms - Default Duration",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].PlanType").equals(planType));
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));								

	}
	
	@Test (testName="Plan Type - Terms 2 Weeks",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].PlanType").equals(planType));
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									

	}
	
	@Test (testName="Plan Type - Terms 2 Months",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].PlanType").equals(planType));
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									

	}
	
	@Test (testName="Plan Type - Terms With Rollover",description="PBI: 179765")
	public void planTypeTermsWithRollover() {

				String planType = "TermsWithRollover";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].PlanType").equals(planType));
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									

	}
	
	@Test (testName="Available Online Only Disabled",description="PBI: 179765")
	public void availableOnlineOnlyDisabled() {

				String availableOnlineOnly = "Disabled";
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanNotAvailableOnlineId));
						Assert.assertTrue(js.getString("Result.PlanDescription").contains(membershipPlanNotAvailableOnlineDescription));
						
						}
	}
	
	@Test (testName="Available Online Only Enabled",description="PBI: 179765")
	public void availableOnlineOnlyEnabled() {

				String availableOnlineOnly = "Enabled";
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanAvailableOnlineId));
						Assert.assertTrue(js.getString("Result.PlanDescription").contains(membershipPlanAvailableOnlineDescription));
						
						}
	}
	
	@Test (testName="Plan Available in Specific Club",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(js.getString("Result.PlanId").contains(membershipPlanClub1OnlyId));
						Assert.assertTrue(js.getString("Result.PlanDescription").contains(membershipPlanClub1OnlyDescription));
						
						}
	}

	@Test (testName="Plan Not Available in Specific Club",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));									
						
						
					if (valueAssertions.equals("true")) {
						
						Assert.assertTrue(!js.getString("Result.PlanId").contains(membershipPlanClub1OnlyId));
						Assert.assertTrue(!js.getString("Result.PlanDescription").contains(membershipPlanClub1OnlyDescription));
						
						}
	}

	@Test (testName="Family Plans Only",description="PBI: 179765")
	public void familyPlansOnly() {

				String includeIndividualPlans = "FamilyPlan";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].IsIndividualPlan").equals("false"));
						Assert.assertTrue(!js.getString("Result.IsIndividualPlan").equals("true")); // Confirm none of the plans are Individual
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));
	}

	@Test (testName="Individual Plans Only",description="PBI: 179765")
	public void individualPlansOnly() {

				String includeIndividualPlans = "IndividualPlan";
		
				Response res = 

				given()
//						.log().all()
						.header("accept", "application/json")
						.header("X-Api-Key", APIKey)
						.header("X-CompanyId", companyId)
						.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(200)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);

						String planId = js.getString("Result[0].PlanId");
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(!js.getString("Result[0].PlanId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].PlanDescription").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsIndividualPlan").isBlank());
						Assert.assertTrue(js.getString("Result[0].IsIndividualPlan").equals("true"));
						Assert.assertTrue(!js.getString("Result.IsIndividualPlan").equals("false")); // Confirm none of the plans are Individual
						Assert.assertTrue(!js.getString("Result[0].PlanType").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtPrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalDownPmtOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargePrimary").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeFirstFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].TotalRecurringChargeOtherFamily").isBlank());
						Assert.assertTrue(!js.getString("Result[0].IsFeaturedPlan").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].AddOns[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].AddOns[0].PlanId").equals(planId));
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemId").isBlank());
						Assert.assertTrue(!js.getString("Result[0].ServiceAreas[0].ItemDescription").isBlank());
						Assert.assertTrue(js.getString("Result[0].ServiceAreas[0].PlanId").equals(planId));
	}
	
	@Test (testName="Club Not Found",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
					.then()
//						.log().body()
						.assertThat()
						.statusCode(401)
						.extract().response();
				
						JsonPath js = ReusableMethods.rawToJson(res);
						
						Assert.assertTrue(res.getTime() >= 60L);
						
						Assert.assertTrue(js.getString("Message").equals("Invalid authorization credentials (Club Does Not Exist)"));
						
	}
	
	@Test (testName="Plan Category Not Found",description="PBI: 179765")
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
						.get("/api/v3/agreement/getmembershipplans?"
								+ "IncludeAllClubs="+includeAllClubs+"&"
								+ "IncludeIndividualPlans="+includeIndividualPlans+"&"
								+ "PlanCategoryId="+planCategoryId+"&"
								+ "Duration="+duration+"&"
								+ "DurationType="+durationType+"&"
								+ "AvailableOnlineOnly="+availableOnlineOnly+"&"
								+ "PlanType="+planType+"")
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
