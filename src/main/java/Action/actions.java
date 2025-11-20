package Action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class actions extends base {
	WebDriver driver;
	WebDriverWait wait ;
	JavascriptExecutor js;
	 // Constructor to initialize driver and wait
    public actions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor)driver;
    }

	
	public void Click(WebElement Element) {
		
		wait.until(ExpectedConditions.elementToBeClickable(Element));
		Element.click();


	}
	public void Input(WebElement Element, String input) {
		wait.until(ExpectedConditions.elementToBeSelected(Element));
		Element.sendKeys(input);
	}
	public void dd(String title,String option )  {
		
		WebElement SelectBtn = driver.findElement(By.xpath("//label[text()='"+title+"']/following::div[contains(@class,'oxd-select-text')][1]"));
		wait.until(ExpectedConditions.elementToBeClickable(SelectBtn));
		SelectBtn.click(); 
		WebElement options = driver.findElement(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div/span[text()='"+option+"']"));
		options.click();
}
	
	public void scrollto(WebElement Element) {
		js.executeScript("arguments[0].scrollIntoView(true);", Element);
		
		
	}
	public void scrollVertical(int y) {
		String px = String.valueOf(y);
		js.executeScript("window.scrollBy("+px+",0)"); 
	
	}
	
	public void takeScreenshot(WebDriver driver, String fileName) {
        try {
            
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
   
            String folder = System.getProperty("user.dir") + "/Screenshort/";

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            
            File dest = new File(folder + fileName + "_" + timeStamp + ".png");

            FileUtils.copyFile(src, dest);

            System.out.println("Permanent screenshot saved at: " + dest.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public  void selectDate(WebDriver driver, int targetYear, String targetMonth, int targetDay) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1: Open Year dropdown
            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'flex justify-end ')]//button")));
            yearDropdown.click();

            // Step 2: Select Year
            List<WebElement> yearOptions = driver.findElements(By.cssSelector("[role='option']"));
            for (WebElement option : yearOptions) {
                if (option.getText().trim().equals(String.valueOf(targetYear))) {
                    option.click();
                    break;
                }
            }

            // Step 3: Handle Month Navigation
            WebElement prevBtn = driver.findElement(By.cssSelector("button[aria-label='Go to previous month']"));
            WebElement nextBtn = driver.findElement(By.cssSelector("button[aria-label='Go to next month']"));

            String[] months = {"January", "February", "March", "April", "May", "June",
                               "July", "August", "September", "October", "November", "December"};

            while (true) {
                String headerText = driver.findElement(By.cssSelector("[id^='react-day-picker']")).getText().trim();
                String[] parts = headerText.split(" ");
                String currentMonth = parts[0];
                int currentYear = Integer.parseInt(parts[1]);

                int currentIndex = Arrays.asList(months).indexOf(currentMonth);
                int targetIndex = Arrays.asList(months).indexOf(targetMonth);

                if (currentYear > targetYear ||
                        (currentYear == targetYear && currentIndex > targetIndex)) {
                    prevBtn.click();
                    Thread.sleep(200);
                } else if (currentYear < targetYear ||
                        (currentYear == targetYear && currentIndex < targetIndex)) {
                    nextBtn.click();
                    Thread.sleep(200);
                } else {
                    break;
                }
            }

            // Step 4: Select Day
            List<WebElement> allButtons = driver.findElements(By.tagName("button"));
            for (WebElement day : allButtons) {
                if (day.getText().trim().equals(String.valueOf(targetDay))) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", day);
                    day.click();
                    break;
                }
            }

            System.out.println("Date selected successfully: " + targetDay + " " + targetMonth + " " + targetYear);

        } catch (Exception e) {
            System.out.println("Failed to select date: " + e.getMessage());
        }
    }

	public void dropdownSelect(String DD, String value) {
		
		WebElement dropdown = driver.findElement(By.xpath("//span[text()='Select "+DD.toLowerCase()+"']/.."));
		wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[normalize-space(text())='"+value+"']/parent::div")));
       // WebElement option = driver.findElement(By.xpath("//span[text()='"+value+"']/.."));
        WebElement option = driver.findElement(By.xpath("//div[@role='option']//span[normalize-space(text())='"+value+"']/parent::div"));
        option.click();
	}
	 public void scrollDivToBottom(By locator) {
	        WebElement div = driver.findElement(locator);
	        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", div);
	    }

		public void scrollElementToMiddle(WebElement element) {
			js.executeScript(
					"var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
							+ "var elementTop = arguments[0].getBoundingClientRect().top;"
							+ "window.scrollBy(0, elementTop-(viewPortHeight/2));",
					element);
		}
	    
	    public void scrollDivToTop(By locator) {
	        WebElement div = driver.findElement(locator);
	        js.executeScript("arguments[0].scrollTop = 0;", div);
	    }

	    // Scroll inside div by pixels
	    public void scrollDivByPixels(WebElement InDiv, int pixels) {
	      
	        js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + " + pixels + ";", InDiv);
	    }

	    // Scroll to element inside div
	    public void scrollToElementInsideDiv(By parentDiv, By innerElement) {
	        WebElement div = driver.findElement(parentDiv);
	        WebElement element = driver.findElement(innerElement);
	        js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", div, element);
	    }
}
