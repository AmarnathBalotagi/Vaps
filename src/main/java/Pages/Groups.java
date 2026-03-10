package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Action.actions;

public class Groups {
	WebDriver driver;
	actions ac = new actions();
	WebDriverWait wait ;
	@FindBy (xpath="//button/span[text()='Fees Management']")
	WebElement Fee_Mgmt;
	@FindBy (xpath="//button/div/span[text()='Masters']")
	WebElement Fee_Masters;
	@FindBy (xpath="//a//div/span[text()='Master Fee Head']")
	WebElement Fee_Head_Tab;
	@FindBy (xpath="//button[text()='Add fee Head']")
	WebElement Add_Fee_Head;
	@FindBy (xpath="//input[@id='fmH_FeeName']")
	WebElement Fee_Head_Name;
	@FindBy (xpath="//button/div[text()='Save Master Fees Head']")
	WebElement Save_Fee_Head;
	//=====  Master Group Elements=====
	@FindBy (xpath="//input[@id='fmG_GroupName']")
	WebElement Fee_Group_Name;
	@FindBy (xpath="//textarea[@id='fmG_Remarks']")
	WebElement Fee_Group_Remakrs;
	@FindBy (xpath="//div[normalize-space()='Save Master Group']/..")
	WebElement Save_Fee_Group;
	@FindBy (xpath="//input[@id='fmT_Name']")
	WebElement Term_Name;
	@FindBy (xpath="//input[@id='fmI_Name']")
	WebElement Installment_Name;
	@FindBy (xpath="//input[@id='fmI_No_Of_Installments']")
	WebElement No_of_Installment;
	@FindBy (xpath="//input[@placeholder='Installment 1']")
	WebElement Installment_1;
	@FindBy (xpath="//input[@placeholder='Installment 2']")
	WebElement Installment_2;
	@FindBy (xpath="//input[@id='fmcC_ClassCategoryName']")
	WebElement Class_cat_Name;
	@FindBy (xpath="//input[@id='fmcC_ClassCategoryCode']")
	WebElement Class_cat_Code;

	
		
	
	public Groups(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	public void Fee_Matster_Nav() {
		ac.Click(Fee_Mgmt);
		ac.Click(Fee_Masters);
	}
	public void addFeeHead(String feeHeadName) {
		
		ac.scrollto(Fee_Head_Tab);
		wait.until(ExpectedConditions.visibilityOf(Fee_Head_Tab));
		ac.scrollToTopofPage();
		wait.until(ExpectedConditions.elementToBeClickable(Fee_Head_Tab));
		
		ac.actionsClick(Add_Fee_Head);
		
		ac.scrollVertical(1300);
		System.out.println("Auto focus done");
		Fee_Head_Name.sendKeys(feeHeadName);
		ac.DD_fee("Fee Head Type", "General");
             ac.Click(Save_Fee_Head);	
	}
	
	public void addGroup(String GroupName) {
		ac.TopNav("Master Fee Groups");
		ac.AddBtn("Add Master Group");
		ac.scrollto(Fee_Group_Name);
	    Fee_Group_Name.sendKeys(GroupName);
		Fee_Group_Remakrs.sendKeys(GroupName);
		ac.Click(Save_Fee_Group);
		ac.HardWait(2000);
		//Group mapped to Acad Year 
		ac.TopNav("Master Fee Yearly Groups");
		ac.AddBtn("Add Yearly Group");
		ac.scrollToBottomofPage();
		ac.HardWait(2000);
		ac.DD_Acad_fee("2025-2026");
		ac.DD_fee("Fee Groups ", GroupName);
		ac.SaveBtn();
		
	}
	
	public void addTerm(String TermName) {
		//TermFeeHead
				ac.TopNav("Master Fee Terms");
				ac.AddBtn("Add Fee Term");
				Term_Name.sendKeys(TermName);
				ac.SaveBtn();
	}
	
	public void addInstallmment(String installmentName, String installment1, String installment2) {
		//Installment
		ac.TopNav("Master Fee Installment");	
		ac.AddBtn("Add Fee Installment");
		Installment_Name.sendKeys(installmentName);
		ac.DD_fee("Installment Type ", "General");
		No_of_Installment.sendKeys("2");
		Installment_1.sendKeys(installment1);
		Installment_2.sendKeys(installment2);
		ac.SaveBtn();
	}
	public void add_Class_Category(String ClassCatName,String ClassName) {
		//Class Category
		ac.TopNav("Master Class Category");	
		ac.AddBtn("Add Class Category");
		Class_cat_Name.sendKeys(ClassCatName);
		Class_cat_Code.sendKeys(ClassCatName);
		ac.SaveBtn();
		//map Class category to class
		ac.TopNav("Master Yearly Class Category");
		ac.AddBtn("Add Yearly Category");
		ac.DD_Acad_fee("2025-2026");
		ac.scrollToBottomofPage();
		ac.DD_fee("Class Category ", ClassCatName);
		ac.DD_fee("Classes ", ClassName);
		ac.SaveBtn();
	}
	
	public void Term_FeeHead_mapping(String TermName,String FeeHead,String Inst1,String Inst2) {
		//Term_FeeHead mapping
		ac.TopNav("Master Term Fee Head");	
		ac.AddBtn("Add Term FeeHead");
		ac.scrollToBottomofPage();
		ac.DD_fee("Term Name ", TermName);
		ac.DD_fee("Fees Head ", FeeHead);
		ac.DD_fee("Installment Name ", Inst1);
		ac.DD_fee("Installment Name ", Inst2);
		ac.SaveBtn();
		ac.TopNav("Master Term Fee Head Due Date");
		ac.AddBtn("Add Term Fee Head Due Date");
		ac.scrollToBottomofPage();
	}
	
	
	

	}
