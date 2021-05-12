package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.ReusableDates;
import resources.base;

public class GetMembershipPlanDetails extends base {
	
	String companyId;
	String valueAssertions;
	String APIKey;
	String clubId;
	String planId;
	String effectiveDate;

	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		APIKey = prop.getProperty("X-Api-Key");
		companyId = prop.getProperty("X-CompanyId");
		clubId = prop.getProperty("X-Club1Id");
		valueAssertions = prop.getProperty("valueAssertions");
		
		clubId = prop.getProperty("X-Club1Id");
		planId = prop.getProperty("membershipPlanAvailableOnlineId");
		effectiveDate = ReusableDates.getCurrentDatePlusXDays(10);
	}
	
	@Test (testName="All Parameters",description="PBI: 179765")
	public void allParameters() {
		
		Response res = 

		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", APIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/agreement/getmembershipplandetails/"+planId+"?EffectiveDate="+effectiveDate)
		.then()
//			.log().body()
			.assertThat()
			.statusCode(200)
			.extract().response();
	
			JsonPath js = ReusableMethods.rawToJson(res);
			
			Assert.assertTrue(res.getTime() >= 60L);

			Assert.assertTrue(js.getString("Result.PlanId").equals(planId));
			Assert.assertFalse(js.getString("Result.PlanDescription").isBlank());
			Assert.assertFalse(js.getString("Result.PlanType").isBlank());
			Assert.assertFalse(js.getString("Result.Inactive").isBlank());
			Assert.assertFalse(js.getString("Result.PlanCategoryId").isBlank());
			Assert.assertFalse(js.getString("Result.PlanCategory").isBlank());
			Assert.assertFalse(js.getString("Result.BillingCategoryId").isBlank());
			Assert.assertFalse(js.getString("Result.BillingCategoryDescription").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipTypeId").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipTypeName").isBlank());
			Assert.assertFalse(js.getString("Result.IsIndividualPlan").isBlank());
			Assert.assertFalse(js.getString("Result.IsRenewalPlan").isBlank());
			Assert.assertFalse(js.getString("Result.IsApplyAgeRestriction").isBlank());
			Assert.assertFalse(js.getString("Result.MinimumAge").isBlank());
			Assert.assertFalse(js.getString("Result.MaximumAge").isBlank());
			Assert.assertFalse(js.getString("Result.MinimumMemberCount").isBlank());
			Assert.assertFalse(js.getString("Result.MaximumMemberCount").isBlank());
			Assert.assertFalse(js.getString("Result.RenewalPlanType").isBlank());
			Assert.assertFalse(js.getString("Result.SellAsGroupPlan").isBlank());
			Assert.assertFalse(js.getString("Result.IsCorpResponsible").isBlank());
			Assert.assertFalse(js.getString("Result.CorporationId").isBlank());
			Assert.assertFalse(js.getString("Result.PercentageOfTotalDuesToPay").isBlank());
			Assert.assertFalse(js.getString("Result.HasMaximumTotalDues").isBlank());
			Assert.assertFalse(js.getString("Result.MaximumTotalDues").isBlank());
			Assert.assertFalse(js.getString("Result.AvailableOnline").isBlank());
			Assert.assertFalse(js.getString("Result.IsAssignRenewalWindow").isBlank());
			Assert.assertFalse(js.getString("Result.RenewalWindowType").isBlank());
			Assert.assertFalse(js.getString("Result.RenewalTimeFrame").isBlank());
			Assert.assertTrue(js.getString("Result").contains("RenewalAgreementExpiresFrom"));
			Assert.assertTrue(js.getString("Result").contains("RenewalAgreementExpiresTo"));
			Assert.assertFalse(js.getString("Result.IsRenewalForAllCategories").isBlank());
			Assert.assertFalse(js.getString("Result.PaidInFull").isBlank());
			Assert.assertFalse(js.getString("Result.DurationType").isBlank());
			Assert.assertFalse(js.getString("Result.IsTemplateRequired").isBlank());
			Assert.assertFalse(js.getString("Result.IsPrimaryAgreementPlan").isBlank());
			Assert.assertFalse(js.getString("Result.IsStatementFOP").isBlank());
			Assert.assertFalse(js.getString("Result.IsNotBilledFOP").isBlank());
			Assert.assertFalse(js.getString("Result.IsCreditCardFOP").isBlank());
			Assert.assertFalse(js.getString("Result.IsCheckingFOP").isBlank());
			Assert.assertFalse(js.getString("Result.IsSavingsFOP").isBlank());
			Assert.assertFalse(js.getString("Result.MaxFreeMonthExtension").isBlank());
			Assert.assertFalse(js.getString("Result.MaxFreeMonthExtensionType").isBlank());
			Assert.assertFalse(js.getString("Result.ProrateFee").isBlank());
			Assert.assertTrue(js.getString("Result").contains("PrintedContractFileName"));
			Assert.assertFalse(js.getString("Result.UnlimitedClubAccess").isBlank());
			Assert.assertFalse(js.getString("Result.ClubAccessMode").isBlank());
			Assert.assertFalse(js.getString("Result.PrimarySalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.PrimaryCommissionPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.SecondarySalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.SecondaryCommissionPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.OtherSalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.OtherCommissionPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.IsAllClubSelected").isBlank());
			Assert.assertFalse(js.getString("Result.BillingCycleId").isBlank());
			Assert.assertTrue(js.getString("Result").contains("StartDate"));
			Assert.assertTrue(js.getString("Result").contains("EndDate"));
			Assert.assertFalse(js.getString("Result.UsePlanSpecificBilling").isBlank());
			//Assert.assertFalse(js.getString("Result.BaseDate").isBlank());
			Assert.assertFalse(js.getString("Result.BaseDateFrequency").isBlank());
			Assert.assertFalse(js.getString("Result.BaseDateFrequencyType").isBlank());
			Assert.assertFalse(js.getString("Result.RecurringOffset").isBlank());
			Assert.assertFalse(js.getString("Result.RecurringOffsetType").isBlank());
			Assert.assertTrue(js.getString("Result").contains("FixedExpirationDate"));
			Assert.assertFalse(js.getString("Result.DefaultSalesperson").isBlank());
			Assert.assertFalse(js.getString("Result.DefaultSalespersonId").isBlank());
			//Assert.assertFalse(js.getString("Result.DefaultSalespersonFirstName").isBlank());
			//Assert.assertFalse(js.getString("Result.DefaultSalespersonLastName").isBlank());
			Assert.assertFalse(js.getString("Result.AllowJolSalespersonSelection").isBlank());
			Assert.assertFalse(js.getString("Result.MemberGroups[0].Id").isBlank());
			Assert.assertFalse(js.getString("Result.MemberGroups[0].Name").isBlank());
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("Address"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("Address2"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("City"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("State"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("Zipcode"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("Phone"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("Officer"));
			Assert.assertFalse(js.getString("Result.MemberGroups[0].CheckMemberBalance").isBlank());
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("GroupNumber"));
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("InsuranceProviderID"));
			Assert.assertFalse(js.getString("Result.MemberGroups[0].Eligible").isBlank());

			Assert.assertFalse(js.getString("Result.MemberGroupSets[0].MemberGroupSetId").isBlank());
			Assert.assertFalse(js.getString("Result.MemberGroupSets[0].MemberGroupSetDescription").isBlank());
			Assert.assertFalse(js.getString("Result.MemberGroupSets[0].MemberGroups[0].MemberGroupId").isBlank());
			Assert.assertFalse(js.getString("Result.MemberGroupSets[0].MemberGroups[0].MemberGroupName").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ItemId").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ItemName").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ItemType").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsServiceArea").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsServiceVisit").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsServiceDues").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].DownPaymentPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].DownPaymentFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].DownPaymentOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RecurringChargePrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RecurringChargeFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RecurringChargeOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].FreezeChargePrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].FreezeChargeFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].FreezeChargeOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ProrationAmountPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ProrationAmountFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].ProrationAmountPaymentOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaxDiscDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaxDiscRecurring").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaxDiscProration").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsDownPaymentCommision").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsRecurringChargeCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsRenewalCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsFreezeChargeCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsProrationAmountCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSetUp").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionType").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].Commission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalDownPaymentPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalDownPaymentFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalDownPaymentOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsRenewalDownPaymentCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalProrationAmountPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalProrationAmountFirstFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].RenewalProrationAmountOtherFamily").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].IsRenewalProrationAmountCommission").isBlank());
			Assert.assertFalse(js.getString("Result.Details.SaleDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.EffectiveDate.").isBlank());
			Assert.assertFalse(js.getString("Result.Details.ExpirationDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.ProrationDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.FirstPaymentDue").isBlank());
			Assert.assertFalse(js.getString("Result.Details.RecurringFeeId").isBlank());
			Assert.assertTrue(js.getString("Result.Details").contains("RecurringFeeName"));
			Assert.assertFalse(js.getString("Result.Details.RecurringFee").isBlank());
			Assert.assertTrue(js.getString("Result.Details").contains("RecurringFeePostDate"));
			Assert.assertFalse(js.getString("Result.Details.RecurringFeeBillingCycleId").isBlank());
			Assert.assertTrue(js.getString("Result.Details").contains("RecurringFeeBillingCycleName"));
			Assert.assertFalse(js.getString("Result.Details.IsRecurringFeeMandatory").isBlank());
			Assert.assertFalse(js.getString("Result.Details.IsRecurringFeeDefault").isBlank());
			Assert.assertFalse(js.getString("Result.Details.PlanIntervalString").isBlank());
			Assert.assertFalse(js.getString("Result.Details.TotalBillingCycles").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AllowedBillingCycles[0].Id").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AllowedBillingCycles[0].IntervalCount").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AllowedBillingCycles[0].IntervalType").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AllowedBillingCycles[0].Description").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].Id").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].FromDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ToDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].NextDuesDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ProrationDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ProrationOffset").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].BillingOffset").isBlank());
			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].BillingCycleId").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceId").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceName").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceType").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].IsAddon").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].IsDefaultSelected").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ProratedServiceId").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentProrationPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RecurringPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RolloverPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].FreezePrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ValuePerDayPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountDownPaymentPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountRecurringPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountProrationPrimary").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentProrationFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RecurringFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RolloverFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].FreezeFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ValuePerDayFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountDownPaymentFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountRecurringFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountProrationFirstDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].DownPaymentProrationOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RecurringOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].RolloverOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].FreezeOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ValuePerDayOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountDownPaymentOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountRecurringOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].MaxDiscountProrationOtherDependent").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].Visits").isBlank());
			Assert.assertTrue(js.getString("Result.Details.Services[0]").contains("TaxRates"));
			Assert.assertTrue(js.getString("Result.Details.Services[0]").contains("ProratedServiceTaxRates"));
			Assert.assertFalse(js.getString("Result.Details.Services[0].ProratedVisits").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PricingMethod").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].IsRecurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ProratedItemName").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipAreas[0].Id").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipAreas[0].Description").isBlank());
			Assert.assertFalse(js.getString("Result.AvailableClubs[0].Id").isBlank());
			Assert.assertFalse(js.getString("Result.AvailableClubs[0].Name").isBlank());
			
		if (valueAssertions.equals("true")) {
			Assert.assertEquals(prop.getProperty("membershipPlanAvailableOnlineDescription"), js.getString("Result.PlanDescription"));
			//Assert.assertEquals(prop.getProperty("planSummary"), js.getString("Result.PlanSummary"));
			Assert.assertEquals(prop.getProperty("planType"), js.getString("Result.PlanType"));
			Assert.assertEquals("false", js.getString("Result.Inactive"));
			Assert.assertEquals(prop.getProperty("planCategoryId"), js.getString("Result.PlanCategoryId"));
			Assert.assertEquals(prop.getProperty("planCategory"), js.getString("Result.PlanCategory"));
			Assert.assertEquals(prop.getProperty("billingCategoryId"), js.getString("Result.BillingCategoryId"));
			Assert.assertEquals(prop.getProperty("billingCategoryDescription"), js.getString("Result.BillingCategoryDescription"));
			Assert.assertEquals(prop.getProperty("membershipTypeId"), js.getString("Result.MembershipTypeId"));
			Assert.assertEquals(prop.getProperty("membershipTypeName"), js.getString("Result.MembershipTypeName"));
			Assert.assertEquals(prop.getProperty("isIndividualPlan"), js.getString("Result.IsIndividualPlan"));
			Assert.assertEquals(prop.getProperty("isRenewalPlan"), js.getString("Result.IsRenewalPlan"));
			Assert.assertEquals(prop.getProperty("isApplyAgeRestriction"), js.getString("Result.IsApplyAgeRestriction"));
			Assert.assertEquals(prop.getProperty("minimumAge"), js.getString("Result.MinimumAge"));
			Assert.assertEquals(prop.getProperty("maximumAge"), js.getString("Result.MaximumAge"));
			Assert.assertEquals(prop.getProperty("minimumMemberCount"), js.getString("Result.MinimumMemberCount"));
			Assert.assertEquals(prop.getProperty("maximumMemberCount"), js.getString("Result.MaximumMemberCount"));
			Assert.assertEquals(prop.getProperty("renewalPlanType"), js.getString("Result.RenewalPlanType"));
			//Assert.assertEquals(prop.getProperty("sellAsGroupPlan"), js.getString("Result.SellAsGroupPlan"));
			Assert.assertEquals(prop.getProperty("isCorpResponsible"), js.getString("Result.IsCorpResponsible"));
			Assert.assertEquals(prop.getProperty("corporationId"), js.getString("Result.CorporationId"));
			Assert.assertEquals(prop.getProperty("percentageOfTotalDuesToPay"), js.getString("Result.PercentageOfTotalDuesToPay"));
			Assert.assertEquals(prop.getProperty("hasMaximumTotalDues"), js.getString("Result.HasMaximumTotalDues"));
			Assert.assertEquals(prop.getProperty("maximumTotalDues"), js.getString("Result.MaximumTotalDues"));
			Assert.assertEquals(prop.getProperty("availableOnline"), js.getString("Result.AvailableOnline"));
			Assert.assertEquals(prop.getProperty("isAssignRenewalWindow"), js.getString("Result.IsAssignRenewalWindow"));
			Assert.assertEquals(prop.getProperty("renewalWindowType"), js.getString("Result.RenewalWindowType"));
			Assert.assertEquals(prop.getProperty("renewalTimeFrame"), js.getString("Result.RenewalTimeFrame"));
			Assert.assertNull(js.getString("Result.RenewalAgreementExpiresFrom"));
			Assert.assertNull(js.getString("Result.RenewalAgreementExpiresTo"));
			Assert.assertEquals(prop.getProperty("isRenewalForAllCategories"), js.getString("Result.IsRenewalForAllCategories"));
			Assert.assertEquals(prop.getProperty("duration"), js.getString("Result.Duration"));
			Assert.assertEquals(prop.getProperty("durationType"), js.getString("Result.DurationType"));
			Assert.assertEquals(prop.getProperty("paidInFull"), js.getString("Result.PaidInFull"));
			Assert.assertEquals(prop.getProperty("isTemplateRequired"), js.getString("Result.IsTemplateRequired"));
			Assert.assertEquals(prop.getProperty("isPrimaryAgreementPlan"), js.getString("Result.IsPrimaryAgreementPlan"));
			Assert.assertEquals(prop.getProperty("isStatementFOP"), js.getString("Result.IsStatementFOP"));
			Assert.assertEquals(prop.getProperty("isNotBilledFOP"), js.getString("Result.IsNotBilledFOP"));
			Assert.assertEquals(prop.getProperty("isCreditCardFOP"), js.getString("Result.IsCreditCardFOP"));
			Assert.assertEquals(prop.getProperty("isCheckingFOP"), js.getString("Result.IsCheckingFOP"));
			Assert.assertEquals(prop.getProperty("isSavingsFOP"), js.getString("Result.IsSavingsFOP"));
			Assert.assertEquals(prop.getProperty("maxFreeMonthExtension"), js.getString("Result.MaxFreeMonthExtension"));
			Assert.assertEquals(prop.getProperty("maxFreeMonthExtensionType"), js.getString("Result.MaxFreeMonthExtensionType"));
			Assert.assertEquals(prop.getProperty("prorateFee"), js.getString("Result.ProrateFee"));
			Assert.assertTrue(js.getString("Result.PrintedContractFileName").isBlank());
			Assert.assertEquals(prop.getProperty("unlimitedClubAccess"), js.getString("Result.UnlimitedClubAccess"));
			Assert.assertEquals(prop.getProperty("clubAccessMode"), js.getString("Result.ClubAccessMode"));
			Assert.assertEquals(prop.getProperty("primarySalesPercentage"), js.getString("Result.PrimarySalesPercentage"));
			Assert.assertEquals(prop.getProperty("primaryCommissionPercentage"), js.getString("Result.PrimaryCommissionPercentage"));
			Assert.assertEquals(prop.getProperty("secondarySalesPercentage"), js.getString("Result.SecondarySalesPercentage"));
			Assert.assertEquals(prop.getProperty("secondaryCommissionPercentage"), js.getString("Result.SecondaryCommissionPercentage"));
			Assert.assertEquals(prop.getProperty("otherSalesPercentage"), js.getString("Result.OtherSalesPercentage"));
			Assert.assertEquals(prop.getProperty("otherCommissionPercentage"), js.getString("Result.OtherCommissionPercentage"));
			Assert.assertEquals(prop.getProperty("isAllClubSelected"), js.getString("Result.IsAllClubSelected"));
			Assert.assertEquals(prop.getProperty("billingCycleId"), js.getString("Result.BillingCycleId"));
			//Assert.assertNull(js.getString("Result.StartDate"));
			//Assert.assertNull(js.getString("Result.EndDate"));
			Assert.assertEquals(prop.getProperty("usePlanSpecificBilling"), js.getString("Result.UsePlanSpecificBilling"));
			Assert.assertEquals(prop.getProperty("baseDate"), js.getString("Result.BaseDate"));
			Assert.assertEquals(prop.getProperty("baseDateFrequency"), js.getString("Result.BaseDateFrequency"));
			Assert.assertEquals(prop.getProperty("baseDateFrequencyType"), js.getString("Result.BaseDateFrequencyType"));
			Assert.assertEquals(prop.getProperty("recurringOffset"), js.getString("Result.RecurringOffset"));
			Assert.assertEquals(prop.getProperty("recurringOffsetType"), js.getString("Result.RecurringOffsetType"));
			Assert.assertNull(js.getString("Result.FixedExpirationDate"));
			Assert.assertEquals(prop.getProperty("defaultSalesperson"), js.getString("Result.DefaultSalesperson"));
			Assert.assertEquals(prop.getProperty("defaultSalespersonId"), js.getString("Result.DefaultSalespersonId"));
			Assert.assertEquals(prop.getProperty("defaultSalespersonFirstName"), js.getString("Result.DefaultSalespersonFirstName"));
			Assert.assertEquals(prop.getProperty("defaultSalespersonLastName"), js.getString("Result.DefaultSalespersonLastName"));
			Assert.assertEquals(prop.getProperty("allowJolSalespersonSelection"), js.getString("Result.AllowJolSalespersonSelection"));
						



	}		
}
}
