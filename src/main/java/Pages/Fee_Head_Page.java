package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Action.actions;
import Base.base;

public class Fee_Head_Page extends base {
	WebDriver driver;
	actions ac = new actions();
	WebDriverWait wait ;
	@FindBy (xpath="//button/span[text()='Fees Management']")
	WebElement Fee_Mgmt;
	@FindBy (xpath="//button/div/span[text()='Masters']")
	WebElement Fee_Masters;
	@FindBy (xpath="//a//div/span[text()='Master Fee Head']")
	WebElement Fee_Head_Tab;
	@FindBy (xpath="//h3[text()='Add New Fee Head']")
	WebElement Add_New_Fee_Head_Header;
	@FindBy (xpath="//button[text()='Add fee Head']")
	WebElement Add_Fee_Head;
	@FindBy (xpath="//input[@id='fmH_FeeName']")
	WebElement Fee_Head_Name;
	@FindBy (id="specialFeeHead")
	WebElement Spl_Fee_Head_Checkbox;
	@FindBy (id="pdaExpense")
	WebElement PDA_Expense_Checkbox;
	@FindBy (id="refundable")
	WebElement Refundable_Checkbox;
	@FindBy (xpath="//button/div[text()='Save Master Fees Head']")
	WebElement Save_Fee_Head;
	
	
	
	public Fee_Head_Page(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	public void addFeeHead(String feeHeadName, String feeTypeCode) {
		ac.Click(Fee_Mgmt);
		ac.Click(Fee_Masters);
		ac.scrollto(Fee_Head_Tab);
		wait.until(ExpectedConditions.visibilityOf(Fee_Head_Tab));
		Add_Fee_Head.click();
		
		ac.scrollVertical(1300);
		System.out.println("Auto focus done");
		Fee_Head_Name.sendKeys(feeHeadName);
		String feeType = "";
		
	    switch (feeTypeCode.toUpperCase()) {
	        case "G":  feeType = "General"; break;
	        case "T":  feeType = "Transport"; break;
	        case "E":  feeType = "ExcessAmount"; break;
	        case "R":  feeType = "Registration"; break;
	        case "A":  feeType = "Application"; break;
	        case "H":  feeType = "Hostel"; break;
	        case "N":  feeType = "New Admission"; break;
	        case "S":  feeType = "Swiping"; break;
	        case "F":  feeType = "Fine"; break;
	        case "TR": feeType = "Transport Registration"; break;
	        default:
	            throw new IllegalArgumentException("Invalid Fee Head Type Code: " + feeTypeCode);
	    }
		ac.DD_fee("Fee Head Type", feeType);
		ac.Click(Spl_Fee_Head_Checkbox);
        ac.Click(PDA_Expense_Checkbox);	
        ac.Click(Refundable_Checkbox);
             //   ac.Click(Save_Fee_Head);
		
	}
	

}
