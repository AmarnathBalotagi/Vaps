package Fee_Happy_Flow;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Action.actions;
import Base.base;
import Pages.Groups;
import Pages.LoginPage;

public class Fee_Flow_TC extends base{
	LoginPage lp;
    actions ac;
    Groups gp;
    
    String Name ="FF";
    String GroupName = Name +" School Fees";//Dont Edit
    String Head_Name1 = Name +" Tution Fees";//Dont Edit
    String Head_Name2 = Name +" Hostel Fees";//Dont Edit
    String Term_Name = Name +" Term";//Dont Edit
    String Installment_Name  = Name +" Installment";//Dont Edit
    String Installment_1  = Name +" IST-1";//Dont Edit
    String Installment_2  = Name +" IST-2";//Dont Edit
    String Class_cat  = Name +" Category";//Dont Edit
    
    
    @Parameters("browser")
@BeforeClass
public void setUpTest(@Optional("chrome") String browser) {
    base.setup(browser);
    lp = new LoginPage(getDriver());
    ac = new actions();
    gp = new Groups(getDriver());
}
@Test(priority = 1)
public void FeeFlow() throws InterruptedException {
	lp.adminLogin("Adminvv", "Password@123");
	gp.Fee_Matster_Nav();
	gp.addGroup(this.GroupName);
	gp.addTerm(this.Term_Name);
	gp.addInstallmment(this.Installment_Name, this.Installment_1,this.Installment_2);
	
    }


}
