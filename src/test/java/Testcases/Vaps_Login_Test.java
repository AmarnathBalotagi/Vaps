package Testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Action.actions;
import Base.base;

import Pages.LoginPage;

@Listeners(Utils.TestListener.class)
public class Vaps_Login_Test extends base  {
	 LoginPage lp;
	    actions ac;
	   
	    @Parameters("browser")
	@BeforeMethod
    public void setUpTest(String browser) {
        base.setup(browser);
        lp = new LoginPage(getDriver());
        ac = new actions(getDriver());
    }
	
	@Test(priority = 1)
	public void validLogin() throws InterruptedException {
		lp.adminLogin("Adminvv", "Password@123");
		Thread.sleep(3000);
		
		String ExpectedURL="https://ivrm-uat1-next.azurewebsites.net/";
		Assert.assertEquals(getDriver().getCurrentUrl(),ExpectedURL );
		
		
	}
	@Test(priority = 2)
	public void invalidLogin() throws InterruptedException {
		lp.adminLogin("Amar", "admin123");
		Thread.sleep(3000);
		String ExpectedURL="https://ivrm-uat1-next.azurewebsites.net/";
		Assert.assertEquals(getDriver().getCurrentUrl(),ExpectedURL );
		
		
	}
	
	

}
