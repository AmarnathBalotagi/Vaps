package just;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class inactivate {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("https://ivrm-uat1-next.azurewebsites.net/VVVS");
		Actions ac = new Actions(driver);
		
		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys("Adminvv");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Password@123");
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		WebElement Admission = driver.findElement(By.xpath("//span[@class='hidden font-medium sm:inline-block'][normalize-space()='Admission']"));
			Admission.click();
		WebElement Masters = driver.findElement(By.xpath("//div[@class='flex items-center gap-2']//span[contains(text(),'Masters')]"));
			Masters.click();
		WebElement Master_Religon = driver.findElement(By.xpath("//span[normalize-space()='Master Religion']"));
		    Master_Religon.click();
		    	Thread.sleep(4000);
		WebElement rowperpage = driver.findElement(By.xpath("//button[@role='combobox']"));
		ac.scrollToElement(rowperpage).perform();
		WebElement Gotolast = driver.findElement(By.xpath("//button[@aria-label='Go to last page']"));
		Gotolast.click();
		Thread.sleep(3000);
	
		WebElement rowperpage1= driver.findElement(By.xpath("//button[@role='combobox']"));
		rowperpage1.click();
			            ac.sendKeys(Keys.ARROW_DOWN)
			            .sendKeys(Keys.ARROW_DOWN)
			            .sendKeys(Keys.ARROW_DOWN)
			            .sendKeys(Keys.ARROW_DOWN)
			            .sendKeys(Keys.ENTER)
			            .build() .perform();
			            Thread.sleep(2000);
			            WebElement Search = driver.findElement(By.xpath("//input[@id='table-search-input']"));
						ac.scrollToElement(Search).perform();
						Thread.sleep(2000);
						
			           for (int i = 1; i <= 50; i++) {
			                WebElement row = driver.findElement(By.xpath("(//span[@class='font-medium text-xs text-green-700'][normalize-space()='Active'])["+i+"]"));
			                String rowText = row.getText();
			               
			                if (rowText.equalsIgnoreCase("Active")) {
			                    WebElement deactivateToggle = driver.findElement(By.xpath("(//button[@role='switch'])["+i+"]"));
			                    ac.scrollToElement(deactivateToggle).perform();
			                    deactivateToggle.click();
			                    Thread.sleep(2000);
			                    WebElement deactivateBtn = driver.findElement(By.xpath("//button[@type='submit']"));
			                    deactivateBtn.click();
			                    Thread.sleep(2000);
			                    
			                }
			            }
			           
		
	}

}
