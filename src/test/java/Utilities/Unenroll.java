package Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.ReusableMethods;
import resources.base;

public class Unenroll extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="UnEnroll",description="PBI:143588")
	public void unenroll(){
				
				int customerId = 248;
				int enrollmentId = 11270;
				int invoiceId = 13185;
				String companyId = prop.getProperty("X-CompanyId");
//				ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
				ReusableMethods.deleteEnrollment(companyId, enrollmentId, customerId);
				ReusableMethods.deleteInvoice(companyId, invoiceId, customerId);
	}
}
