package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Action.actions;
import Base.base;

public class Deaticve_Page extends base {
	 WebDriver driver;
	    actions ac = new actions();
	    WebDriverWait wait;
	    Actions actions;
	    
	    @FindBy(xpath = "//span[text()='Admission']")
	    WebElement Admission;

	    @FindBy(xpath = "//span[text()='Masters']")
	    WebElement Adm_Masters;

	  //  @FindBy(xpath = "//button[@aria-label='Go to last page']")
	 //   WebElement Go_Last_Page;

	    By Go_Last_Page = By.xpath("//button[@aria-label='Go to last page']");
	    By Go_Next_Page = By.xpath("//button[@aria-label='Go to next page']");
	    By Prev_Page_Btn = By.xpath("//button[@aria-label='Go to previous page']");
	    By Toggle_Switch = By.xpath("//button[@role='switch'][@data-state='checked']");
	    By Rows_perpage = By.xpath("//button[@aria-autocomplete='none']");
	    By Option50_Rows = By.xpath("//span[text()='50']/..");

	   // @FindBy(xpath = "//button[@aria-autocomplete='none']")
	   // Rows_perpage;
//
	   // @FindBy(xpath = "//span[text()='50']/..")
	  // Option50_Rows;

	    @FindBy(xpath = "//span[text()='Deactivate']")
	    WebElement DeActive_Btn;

	    public Deaticve_Page(WebDriver driver) {
	        this.driver = driver;
	        this.actions = new Actions(getDriver());
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	    }

	    public void Pre_Masters_deActive(String tab) {

	        Admission.click();
	        Adm_Masters.click();

	        ac.TopNav(tab);
	        ac.waitForLoaderToDisappear();
	        ac.HardWait(1000);
	        ac.setZoomPercentageAction(80);
	        ac.scrollToBottomofPage();
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(Go_Next_Page));
	        driver.findElement(Go_Next_Page).click();

	       // wait.until(ExpectedConditions.presenceOfElementLocated(Prev_Page_Btn));
	       // driver.findElement(Prev_Page_Btn).click();

	        ac.waitForLoaderToDisappear();
	        ac.scrollToBottomofPage();
	        
	        wait.until(ExpectedConditions.presenceOfElementLocated(Rows_perpage));
	        driver.findElement(Rows_perpage).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(Option50_Rows));
	        driver.findElement(Option50_Rows).click();

	        ac.waitForLoaderToDisappear();
	        actions.keyDown(Keys.CONTROL)
            .sendKeys(Keys.SUBTRACT)
            .sendKeys(Keys.SUBTRACT)
            .keyUp(Keys.CONTROL).build()
            .perform();

	        // ✅ FINAL STALE-PROOF LOOP
	        while (true) {
	        	
	            List<WebElement> toggles = driver.findElements(Toggle_Switch);
System.out.println("Size" + toggles.size());
	            if (toggles.size() == 0) {
	                System.out.println("No Toggles Found");
	                break;
	            }

	            boolean foundActive = false;

	            for (int i = 0; i < toggles.size(); i++) {

	                try {

	                    // ✅ RE-FETCH ELEMENT SAFELY
	                    WebElement toggle = driver.findElements(Toggle_Switch).get(i);

	                    String state = toggle.getAttribute("aria-checked");

	                    if ("true".equals(state)) {
	                    	
	                        foundActive = true;

	                        ac.Scroll_toEle_ActionsE(toggle);

	                        wait.until(ExpectedConditions.elementToBeClickable(toggle));
	                        
	                        toggle.click();

	                        wait.until(ExpectedConditions.elementToBeClickable(DeActive_Btn)).click();

	                        ac.waitForPageReadyState();

	                        break;   // 🔥 DOM REFRESH SAFE
	                    }

	                } catch (org.openqa.selenium.StaleElementReferenceException e) {

	                    System.out.println("⚠ Grid refreshed → retrying...");
	                    break;  // RESTART LOOP SAFELY
	                }
	            }

	            if (!foundActive) {
	                System.out.println("✅ No Active Toggles Found");
	                break;
	            }
	        }
	    }
	
}

