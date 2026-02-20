package Fee_Masters;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Action.actions;
import Base.base;
import Pages.Fee_Head_Page;
import Pages.Groups;
import Pages.LoginPage;

public class Fee_Head_TC extends base {
	
	LoginPage lp;
    actions ac;
    Groups gp;
    @Parameters("browser")
@BeforeClass
public void setUpTest(@Optional("chrome") String browser) {
    base.setup(browser);
    lp = new LoginPage(getDriver());
    ac = new actions();
    gp = new Groups(getDriver());
}
@Test(priority = 1)
public void addFeeHeadTest() throws InterruptedException {
	lp.adminLogin("Adminvv", "Password@123");
	gp.addFeeHead("Test Fee Head Selenium");
    }


}
    

