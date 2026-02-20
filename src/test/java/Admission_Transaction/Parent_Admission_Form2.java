package Admission_Transaction;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Action.actions;
import Base.base;
import Pages.Admission;
import Pages.LoginPage;

public class Parent_Admission_Form2 extends base {
	
	LoginPage lp;
    actions ac;
    Admission adm;
    @Parameters("browser")
@BeforeClass
public void setUpTest(@Optional("chrome") String browser) {
    base.setup(browser);
    lp = new LoginPage(getDriver());
    ac = new actions();
    adm = new Admission(getDriver());
}
@Test(priority = 1)
public void preadmissionFormTest() throws InterruptedException {
    	lp.adminLogin("Next", "Amar@123");
    	adm.navigateToPreadmissionApplication();  	
}
@Test(priority = 2)
public void fillPreadmissionFormTest() throws InterruptedException {
    	adm.fillPreadmissionForm();
    	} 

}
