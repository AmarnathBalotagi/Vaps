package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Action.actions;
import Base.base;

public class Admission extends base {
	WebDriver driver;
	actions ac = new actions();
	//Actions AC = new Actions(getDriver());
	WebDriverWait wait ;
	@FindBy (xpath="//span[text()='Operation Tools']")
	WebElement Operation_Tools;
	@FindBy (xpath="//p[text()='Admission']/../..")
	WebElement Admission;
	@FindBy (xpath="//span[text()='Masters']")
	WebElement Adm_Masters;
	@FindBy (xpath="//span[text()='Transactions']")
	WebElement Adm_Transactions;
	@FindBy (xpath="//span[normalize-space()='Admission Form']")
	WebElement Adm_admForm;
	@FindBy (xpath="//button[normalize-space()='Preadmission Application']")
	WebElement Preadmission_App;
	@FindBy (xpath="//SPAN[text()='Click to upload']/..")
	WebElement upload_Photo;
	@FindBy (id="pasR_FirstName")
	WebElement First_Name;
	@FindBy (id="pasR_MiddleName")
	WebElement Middle_Name;
	@FindBy (id="pasR_LastName")		
	WebElement Last_Name;
	@FindBy (id="pasR_BirthPlace")		
	WebElement Birth_Place;
	@FindBy (id="pasR_emailId")		
	WebElement Email_ID;
	@FindBy (id="pasR_AadharNo")		
	WebElement Aadhar_No;
	@FindBy (xpath ="//input[@id='pasR_MobileNo']")		
	WebElement Mobile_No;
	@FindBy (xpath="(//button[@role='combobox'])[1]")		
	WebElement Select_Religion;
	@FindBy (xpath="//input[@class='flex-1 bg-transparent text-sm outline-none']")		
	WebElement dropdown_Search;
	@FindBy (xpath="//span[text()='Pick a date']/..")		
	WebElement dob;
	@FindBy (xpath="//button[contains(@class,'line-clamp-1 w-full')]")		
	WebElement Class;
	@FindBy (xpath="//button[text()='Next']")		
	WebElement Next;
	@FindBy (xpath="//input[@id='student_photo']")		
	WebElement Photo_student;
	@FindBy (xpath="//div[@class='max-h-[calc(100vh-2rem)] overflow-y-auto p-6 md:p-8']")		
	WebElement InDiv;
	@FindBy (id="pasR_FatherName")		
	WebElement FatherName;
	
	@FindBy (xpath="//input[@id='father_photo']")		
	WebElement Father_Photo;
	@FindBy (xpath="//label[@for='pasR_FatherNationality']/following-sibling::button")		
	WebElement Father_Nationality;
	@FindBy (xpath="//input[@id='mother_photo']")		
	WebElement Mother_Photo;
	@FindBy (id="pasR_MotherName")		
	WebElement MotherName;
	@FindBy (xpath="//label[@for='pasR_MotherNationality']/following-sibling::button")		
	WebElement Mother_Nationality;
	@FindBy (xpath="//tr[@class='bg-orange-50']//input[contains(@id,'document')]")		
	List<WebElement> docUploadList;
	@FindBy (xpath="//button[@role='radio']/following-sibling::label[normalize-space()='I Accept']")		
	WebElement I_Accept_Radio;
	@FindBy (xpath="//button[text()='Submit Application']")		
	WebElement Submit_Application;
	
	static String StudentPhoto = System.getProperty("user.dir") + "/src/test/resources/Stu.jpg";
	static String FatherPhoto = System.getProperty("user.dir") + "/src/test/resources/father.jpg";
	static String MotherPhoto = System.getProperty("user.dir") + "/src/test/resources/mother.jpg";
	static String DocumentPath = System.getProperty("user.dir") + "/src/test/resources/Doc.pdf";
	

	public Admission(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}
	
	
	public void navigateToPreadmissionApplication() throws InterruptedException {
		Operation_Tools.click();
		getDriver().navigate().to("https://ivrm-uat1-next.azurewebsites.net/admission/preadmission/transactions/application-form");
		//Admission.click();
		//ac.setZoomPercentageAction(70);
		ac.setZoom2(70);
		/*ac.scrollElementToMiddle(Adm_Transactions);
		ac.Click(Adm_Transactions);
		ac.scrollElementToMiddle(Preadmission_App);
		wait.until(ExpectedConditions.elementToBeClickable(Preadmission_App));*/
		Thread.sleep(3000);
		ac.Click(Preadmission_App);
		
	}

	public void fillPreadmissionForm() throws InterruptedException {
	    Thread.sleep(2000);
		ac.scrollto(upload_Photo);
		Photo_student.sendKeys(StudentPhoto);
		First_Name.sendKeys("Sai");
		Middle_Name.sendKeys("A");
		Last_Name.sendKeys("Loma");	
		dob.click();
		ac.selectDate(2022,"June",15);
		Mobile_No.click();
		Mobile_No.sendKeys("9876543210");
	//	ac.Input(Birth_Place, "LapLand");
		Birth_Place.sendKeys("lapland");
		ac.dropdownSelect("Religion", "CHRISTIAN");
		ac.dropdownSelect("Caste", "Baptism");
		ac.dropdownSelect("Gender", "Male");
		Class.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pre Nursery Amar']/..")));
        WebElement option = driver.findElement(By.xpath("//span[text()='Pre Nursery Amar']/.."));
        option.click();
        ac.scrollDivByPixels(InDiv,120);
		Email_ID.sendKeys("darshand087@gmail.com");
		
		Thread.sleep(2000);
		ac.dropdownSelect("Nationality", "India");
		Aadhar_No.sendKeys("888840401212");
		ac.scrollDivByPixels(InDiv,120);
		
		ac.dropdownSelect("option", "Yes");
		Thread.sleep(2000);
		ac.scrollVertical(150);
		//ac.forceScroll(Next);
		Next.click();
		Thread.sleep(2000);
		ac.scrollElementToMiddle(Next);
		Next.click();
		ac.scrollElementToMiddle(Father_Photo);
		Father_Photo.sendKeys(FatherPhoto);
		FatherName.sendKeys("Robert Auto");
		ac.dropdownSelect("Nationality", "India");
		ac.scrollElementToMiddle(Mother_Photo);
		Mother_Photo.sendKeys(MotherPhoto);
		MotherName.sendKeys("Emily Auto");
		
		ac.dropdownSelect("Nationality", "India");
		Next.click();
		for (WebElement docUpload : docUploadList) {
			ac.scrollto(docUpload);
			docUpload.sendKeys(DocumentPath);
			Thread.sleep(1000);
		}
		ac.scrollElementToMiddle(Next);
		Next.click();
		ac.scrollElementToMiddle(I_Accept_Radio);
		I_Accept_Radio.click();
		Submit_Application.click();
		
		
		
	}

}

