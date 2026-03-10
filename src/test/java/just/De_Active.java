package just;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Action.actions;
import Base.base;

import Pages.Deaticve_Page;
import Pages.LoginPage;

public class De_Active extends base {
	LoginPage lp;
    actions ac;
    Deaticve_Page da;
    @Parameters("browser")
@BeforeClass
public void setUpTest(@Optional("chrome") String browser) {
    base.setup(browser);
    lp = new LoginPage(getDriver());
    ac = new actions();
    da = new Deaticve_Page(getDriver());
}
@Test(priority = 1)
public void deActiveTest() throws InterruptedException {
	lp.adminLogin("Adminvv", "Password@123");
	da.Pre_Masters_deActive("Master Category");
}
	
	

}
