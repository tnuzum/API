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
		effectiveDate = ReusableDates.getCurrentDatePlusXDays1(10);
		
	}
	
	@Test (testName="All Parameters",description="PBI: 179765 and PBI 185540")
	public void allParameters() {
		
		Response res = 

		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", APIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/agreement/getmembershipplandetails/"+planId+"?ClubId="+clubId+"&EffectiveDate="+effectiveDate+"")
		
		.then()
		.log().body()
			.assertThat()
			.statusCode(200)
			.extract().response();
		
			JsonPath js = ReusableMethods.rawToJson(res);
			
			Assert.assertTrue(res.getTime() >= 60L);

			Assert.assertTrue(js.getString("Result.PlanId").equals(planId));
			Assert.assertFalse(js.getString("Result.PlanDescription").isBlank());
			Assert.assertFalse(js.getString("Result.PlanType").isBlank());
			Assert.assertFalse(js.getString("Result.Inactive").isBlank());
			Assert.assertFalse(js.getString("Result.PlanCategory.Id").isBlank());
			Assert.assertFalse(js.getString("Result.PlanCategory.Name").isBlank());
			Assert.assertFalse(js.getString("Result.BillingCategory.Id").isBlank());
			Assert.assertFalse(js.getString("Result.BillingCategory.Description").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipType.Id").isBlank());
			Assert.assertFalse(js.getString("Result.MembershipType.Name").isBlank());
			Assert.assertFalse(js.getString("Result.IsIndividualPlan").isBlank());
			Assert.assertFalse(js.getString("Result.IsRenewalPlan").isBlank());
			Assert.assertFalse(js.getString("Result.PlanRestrictions.AgeRestriction").isBlank());
			Assert.assertFalse(js.getString("Result.PlanRestrictions.AgeRestriction.MinimumAge").isBlank());
			Assert.assertFalse(js.getString("Result.PlanRestrictions.AgeRestriction.MaximumAge").isBlank());
			Assert.assertFalse(js.getString("Result.PlanRestrictions.MinimumMemberCount").isBlank());
			Assert.assertFalse(js.getString("Result.PlanRestrictions.MaximumMemberCount").isBlank());
			Assert.assertFalse(js.getString("Result.RenewalPlanType").isBlank());
			Assert.assertFalse(js.getString("Result.CorporationSettings.SellAsGroupPlan").isBlank());
			Assert.assertFalse(js.getString("Result.CorporationSettings.IsCorporationResponsible").isBlank());
			Assert.assertFalse(js.getString("Result.CorporationSettings.CorporationId").isBlank());
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
			
			Assert.assertFalse(js.getString("Result.AcceptableFop.Statement").isBlank());
			Assert.assertFalse(js.getString("Result.AcceptableFop.NotBilled").isBlank());
			Assert.assertFalse(js.getString("Result.AcceptableFop.CreditCard").isBlank());
			Assert.assertFalse(js.getString("Result.AcceptableFop.Checking").isBlank());
			Assert.assertFalse(js.getString("Result.AcceptableFop.Savings").isBlank());
			
			Assert.assertFalse(js.getString("Result.FreeExtensionSettings.MaximumFreeMonthsExtension").isBlank());
			Assert.assertFalse(js.getString("Result.FreeExtensionSettings.FreeExtensionUnitType").isBlank());
			
			Assert.assertFalse(js.getString("Result.ProrateFee").isBlank());
			Assert.assertTrue(js.getString("Result").contains("PrintedContractFileName"));
			Assert.assertFalse(js.getString("Result.UnlimitedClubAccess").isBlank());
			Assert.assertFalse(js.getString("Result.ClubAccessMode").isBlank());
			
			Assert.assertFalse(js.getString("Result.CommissionSettings.PrimarySalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.CommissionSettings.PrimaryCommissionPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.CommissionSettings.SecondarySalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.CommissionSettings.SecondaryCommissionPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.CommissionSettings.OtherSalesPercentage").isBlank());
			Assert.assertFalse(js.getString("Result.CommissionSettings.OtherCommissionPercentage").isBlank());
			
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
			
			Assert.assertFalse(js.getString("Result.DefaultSalesperson.SalespersonId").isBlank());
			Assert.assertFalse(js.getString("Result.DefaultSalesperson.SalespersonFirstName").isBlank());
			Assert.assertFalse(js.getString("Result.DefaultSalesperson.SalespersonLastName").isBlank());
			Assert.assertFalse(js.getString("Result.DefaultSalesperson.AllowJolSalespersonSelection").isBlank());
			
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
			Assert.assertTrue(js.getString("Result.MemberGroups[0]").contains("InsuranceProviderId"));
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
			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.RecurringCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.RenewalCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.FreezeCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.ProrationAmount").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.RenewalDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].PrimaryMemberPricing.RenewalProrationAmount").isBlank());
			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.RecurringCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.RenewalCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.FreezeCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.ProrationAmount").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.RenewalDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].SecondaryMemberPricing.RenewalProrationAmount").isBlank());
			
			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.RecurringCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.RenewalCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.FreezeCharge").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.ProrationAmount").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.RenewalDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].OtherMemberPricing.RenewalProrationAmount").isBlank());
			
			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaximumDiscounts.MaximumDownPaymentDiscount").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaximumDiscounts.MaximumRecurringDiscount").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].MaximumDiscounts.MaximumProrationDiscount").isBlank());
			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsDownPaymentCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsRecurringChargeCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsRenewalCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsFreezeChargeCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsProrationAmountCommission").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.CommissionSetUp").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.CommissionType").isBlank());
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.Commission").isBlank());			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsRenewalDownPaymentCommission").isBlank());			
			Assert.assertFalse(js.getString("Result.ClubPricing[0].CommissionSettings.IsRenewalProrationAmountCommission").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.AgreementDates.SaleDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AgreementDates.EffectiveDate.").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AgreementDates.ExpirationDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AgreementDates.ProrationDate").isBlank());
			Assert.assertFalse(js.getString("Result.Details.AgreementDates.FirstPaymentDue").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.RecurringFee.RecurringFeeId").isBlank());
			Assert.assertTrue(js.getString("Result.Details.RecurringFee").contains("RecurringFeeName"));
			Assert.assertFalse(js.getString("Result.Details.RecurringFee.RecurringFee").isBlank());
			Assert.assertTrue(js.getString("Result.Details.RecurringFee").contains("RecurringFeePostDate"));
			Assert.assertFalse(js.getString("Result.Details.RecurringFee.RecurringFeeBillingCycleId").isBlank());
			Assert.assertTrue(js.getString("Result.Details.RecurringFee").contains("RecurringFeeBillingCycleName"));
			Assert.assertFalse(js.getString("Result.Details.RecurringFee.IsRecurringFeeMandatory").isBlank());
			Assert.assertFalse(js.getString("Result.Details.RecurringFee.IsRecurringFeeDefault").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.PlanIntervalString").isBlank());
			Assert.assertFalse(js.getString("Result.Details.TotalBillingCycles").isBlank());
			Assert.assertFalse(js.getString("Result.Details.BillingCycle.Id").isBlank());
			Assert.assertFalse(js.getString("Result.Details.BillingCycle.IntervalCount").isBlank());
			Assert.assertFalse(js.getString("Result.Details.BillingCycle.IntervalUnitType").isBlank());
			Assert.assertFalse(js.getString("Result.Details.BillingCycle.Description").isBlank());
			
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].Id").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].FromDay").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ToDay").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].NextDuesDay").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ProrationDay").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].ProrationOffset").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].BillingOffset").isBlank());
//			Assert.assertFalse(js.getString("Result.Details.DateRangeSettingList[0].BillingCycleId").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceId").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceName").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ServiceType").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].IsAddon").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].IsDefaultSelected").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].ProratedServiceId").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.DownPaymentProration").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.Recurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.Rollover").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.Freeze").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.ValuePerDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.MaxDiscountDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.MaxDiscountRecurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].PrimaryMemberPricing.MaxDiscountProration").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.DownPaymentProration").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.Recurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.Rollover").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.Freeze").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.ValuePerDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.MaxDiscountDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.MaxDiscountRecurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].SecondaryMemberPricing.MaxDiscountProration").isBlank());
			
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.DownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.DownPaymentProration").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.Recurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.Rollover").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.Freeze").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.ValuePerDay").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.MaxDiscountDownPayment").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.MaxDiscountRecurring").isBlank());
			Assert.assertFalse(js.getString("Result.Details.Services[0].OtherMemberPricing.MaxDiscountProration").isBlank());
			
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
			Assert.assertEquals(prop.getProperty("planCategoryId"), js.getString("Result.PlanCategory.Id"));
			Assert.assertEquals(prop.getProperty("planCategory"), js.getString("Result.PlanCategory.Name"));
			Assert.assertEquals(prop.getProperty("billingCategoryId"), js.getString("Result.BillingCategory.Id"));
			Assert.assertEquals(prop.getProperty("billingCategoryDescription"), js.getString("Result.BillingCategory.Description"));
			Assert.assertEquals(prop.getProperty("membershipTypeId"), js.getString("Result.MembershipType.Id"));
			Assert.assertEquals(prop.getProperty("membershipTypeName"), js.getString("Result.MembershipType.Name"));
			Assert.assertEquals(prop.getProperty("isIndividualPlan"), js.getString("Result.IsIndividualPlan"));
			Assert.assertEquals(prop.getProperty("isRenewalPlan"), js.getString("Result.IsRenewalPlan"));
			Assert.assertEquals(prop.getProperty("minimumAge"), js.getString("Result.PlanRestrictions.AgeRestriction.MinimumAge"));
			Assert.assertEquals(prop.getProperty("maximumAge"), js.getString("Result.PlanRestrictions.AgeRestriction.MaximumAge"));
			Assert.assertEquals(prop.getProperty("minimumMemberCount"), js.getString("Result.PlanRestrictions.MinimumMemberCount"));
			Assert.assertEquals(prop.getProperty("maximumMemberCount"), js.getString("Result.PlanRestrictions.MaximumMemberCount"));
			Assert.assertEquals(prop.getProperty("renewalPlanType"), js.getString("Result.RenewalPlanType"));
			Assert.assertEquals(prop.getProperty("sellAsGroupPlan"), js.getString("Result.CorporationSettings.SellAsGroupPlan"));
			Assert.assertEquals(prop.getProperty("isCorpResponsible"), js.getString("Result.CorporationSettings.IsCorporationResponsible"));
			Assert.assertEquals(prop.getProperty("corporationId"), js.getString("Result.CorporationSettings.CorporationId"));
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
			Assert.assertEquals(prop.getProperty("isStatementFOP"), js.getString("Result.AcceptableFop.Statement"));
			Assert.assertEquals(prop.getProperty("isNotBilledFOP"), js.getString("Result.AcceptableFop.NotBilled"));
			Assert.assertEquals(prop.getProperty("isCreditCardFOP"), js.getString("Result.AcceptableFop.CreditCard"));
			Assert.assertEquals(prop.getProperty("isCheckingFOP"), js.getString("Result.AcceptableFop.Checking"));
			Assert.assertEquals(prop.getProperty("isSavingsFOP"), js.getString("Result.AcceptableFop.Savings"));
			Assert.assertEquals(prop.getProperty("maxFreeMonthExtension"), js.getString("Result.FreeExtensionSettings.MaximumFreeMonthsExtension"));
			Assert.assertEquals(prop.getProperty("maxFreeMonthExtensionType"), js.getString("Result.FreeExtensionSettings.FreeExtensionUnitType"));
			Assert.assertEquals(prop.getProperty("prorateFee"), js.getString("Result.ProrateFee"));
			Assert.assertTrue(js.getString("Result.PrintedContractFileName").isBlank());
			Assert.assertEquals(prop.getProperty("unlimitedClubAccess"), js.getString("Result.UnlimitedClubAccess"));
			Assert.assertEquals(prop.getProperty("clubAccessMode"), js.getString("Result.ClubAccessMode"));
			Assert.assertEquals(prop.getProperty("primarySalesPercentage"), js.getString("Result.CommissionSettings.PrimarySalesPercentage"));
			Assert.assertEquals(prop.getProperty("primaryCommissionPercentage"), js.getString("Result.CommissionSettings.PrimaryCommissionPercentage"));
			Assert.assertEquals(prop.getProperty("secondarySalesPercentage"), js.getString("Result.CommissionSettings.SecondarySalesPercentage"));
			Assert.assertEquals(prop.getProperty("secondaryCommissionPercentage"), js.getString("Result.CommissionSettings.SecondaryCommissionPercentage"));
			Assert.assertEquals(prop.getProperty("otherSalesPercentage"), js.getString("Result.CommissionSettings.OtherSalesPercentage"));
			Assert.assertEquals(prop.getProperty("otherCommissionPercentage"), js.getString("Result.CommissionSettings.OtherCommissionPercentage"));
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
			Assert.assertEquals(prop.getProperty("defaultSalespersonId"), js.getString("Result.DefaultSalesperson.SalespersonId"));
			Assert.assertEquals(prop.getProperty("defaultSalespersonFirstName"), js.getString("Result.DefaultSalesperson.SalespersonFirstName"));
			Assert.assertEquals(prop.getProperty("defaultSalespersonLastName"), js.getString("Result.DefaultSalesperson.SalespersonLastName"));
			Assert.assertEquals(prop.getProperty("allowJolSalespersonSelection"), js.getString("Result.DefaultSalesperson.AllowJolSalespersonSelection"));
						



	}		
}
	
	@Test (testName="Verify a messagere is turned when plan is not available at club",description="PBI: 179765 and PBI 185540")
	public void planNotAvailableInClub() {
		
		clubId = "2";
		
		Response res = 

		given()
//			.log().all()
			.header("accept", "application/json")
			.header("X-Api-Key", APIKey)
			.header("X-CompanyId", companyId)
			.header("X-ClubId", clubId)
		.when()
			.get("/api/v3/agreement/getmembershipplandetails/"+planId+"?ClubId="+clubId+"&EffectiveDate="+effectiveDate+"")
		
		.then()
//		.log().body()
			.assertThat()
			.statusCode(500)
			.extract().response();
		
			JsonPath js = ReusableMethods.rawToJson(res);
			
			Assert.assertTrue(res.getTime() >= 60L);

			Assert.assertTrue(js.getString("Message").equals("Internal server error - The remote server returned an unexpected response: (400) Bad Request."));
}
}
