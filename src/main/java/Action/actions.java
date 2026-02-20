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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.base;

public class actions extends base {
	
	WebDriverWait wait ;
	JavascriptExecutor js;
	Select select;
	Actions actions;
	 // Constructor to initialize driver and wait
    public actions() {
        
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        this.js = (JavascriptExecutor)getDriver();
        this.actions = new Actions(getDriver());
        
    }
	
	public void Click(WebElement Element) {
		
		wait.until(ExpectedConditions.elementToBeClickable(Element));
		Element.click();
	}
	public void actionsClick(WebElement Element) {
        
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        actions.moveToElement(Element).click().build().perform();
    }
	
	
	
	public void Input(WebElement Element, String input) {
		wait.until(ExpectedConditions.presenceOfElementLocated((By) Element));
		Element.sendKeys(input);
	}
	public void TopNav( String text) {
		WebElement element = getDriver().findElement(
	        By.xpath("//span[normalize-space()='"+text+"']/../../../..")
	    );
	    js.executeScript("arguments[0].scrollIntoView({inline:'center'}); arguments[0].click();", element);
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    wait.until(driver ->
        js.executeScript("return document.readyState")
          .toString()
          .equals("complete"));
	    js.executeScript("window.scrollTo(0, 0);");
	    
	}
	public void HardWait(int wait) {try {
		Thread.sleep(wait);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}}
	public void Scroll_toEle_ActionsE(WebElement element) {

	    actions.moveToElement(element).perform();
	}
	public void AddBtn(String text) {
		WebElement element = getDriver().findElement(
				By.xpath("//button[normalize-space()='"+text+"']")
				);
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		wait.until(driver ->
        js.executeScript("return document.readyState")
          .toString()
          .equals("complete"));
		
	}
	public void SaveBtn() {
		WebElement element = getDriver().findElement(
				By.xpath("//button[@type='submit']")
				);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		actions.scrollToElement(element).perform();
		element.click();
	}
	public void setZoom(int Percentage) {
		String zoomPercentage= String.valueOf(Percentage);
		
	    js.executeScript("document.body.style.zoom='" + zoomPercentage + "'");
	}
	public void setZoom2(double percentage) {
	    double scale = percentage / 100.0;
	    js.executeScript("document.body.style.transform = 'scale(" + scale + ")';");
	    js.executeScript("document.body.style.transformOrigin = '0 0';"); // Keeps it aligned to top-left
	}
	
	public void waitForPageReadyState() {

	    wait.until(driver ->
	        js.executeScript("return document.readyState")
	          .toString()
	          .equals("complete")
	    );
	}

	public void setZoomPercentageAction(int targetZoom) {


	    int defaultZoom = 100;
	    int zoomStep = 10;   // Chrome standard step

	    int difference = defaultZoom - targetZoom;

	    int times = difference / zoomStep;

	    for (int i = 0; i < times; i++) {

	        actions.keyDown(Keys.CONTROL)
	               .sendKeys(Keys.SUBTRACT)
	               .keyUp(Keys.CONTROL)
	               .perform();
	    }
	}
	public void scrollto(WebElement Element) {
		js.executeScript("arguments[0].scrollIntoView(true);", Element);
		
		
	}
	
	public void waitForLoaderToDisappear() {

	    try {

	        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.cssSelector("#nprogress")
	        ));

	    } catch (Exception e) {

	        // Loader not present → continue safely
	    }
	}

	public void scrollVertical(int y) {
		js.executeScript("window.scrollBy(0, arguments[0]);", y);
	}
	public void forceScroll(WebElement element) {
	    Point p = element.getLocation();
	    js.executeScript("window.scrollTo(0, arguments[0]);", p.getY());
	}

	public void scrollToTopofPage() {
	    js.executeScript("window.scrollTo(0, 0);");
	}
	public void scrollToBottomofPage() {
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	public void takeScreenshot( String fileName) {
        try {
            
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);   
            String folder = System.getProperty("user.dir") + "/Screenshort/";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());          
            File dest = new File(folder + fileName + "_" + timeStamp + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("Permanent screenshot saved at: " + dest.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public  void selectDate( int targetYear, String targetMonth, int targetDay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        try {
            // Step 1: Open Year dropdown
            WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'flex justify-end ')]//button")));
            yearDropdown.click();

            // Step 2: Select Year
            List<WebElement> yearOptions = getDriver().findElements(By.cssSelector("[role='option']"));
           
            for (WebElement option : yearOptions) {
            	System.out.println(option.getText().trim());
                if (option.getText().trim().equals(String.valueOf(targetYear))) {
                	wait.until(ExpectedConditions.elementToBeClickable(option));
                	scrollToElementInsideDiv("(//div[@role='presentation'])[2]", option);
                    option.click();
                    break;
                }
            }

            // Step 3: Handle Month Navigation
            WebElement prevBtn = getDriver().findElement(By.cssSelector("button[aria-label='Go to previous month']"));
            WebElement nextBtn = getDriver().findElement(By.cssSelector("button[aria-label='Go to next month']"));

            String[] months = {"January", "February", "March", "April", "May", "June",
                               "July", "August", "September", "October", "November", "December"};

            while (true) {
                String headerText = getDriver().findElement(By.cssSelector("[id^='react-day-picker']")).getText().trim();
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
            List<WebElement> allButtons = getDriver().findElements(By.tagName("button"));
            for (WebElement day : allButtons) {
                if (day.getText().trim().equals(String.valueOf(targetDay))) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", day);
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
		
		WebElement dropdown = getDriver().findElement(By.xpath("//span[text()='Select "+DD.toLowerCase()+"']/.."));
		wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[normalize-space(text())='"+value+"']/parent::div")));
       // WebElement option = driver.findElement(By.xpath("//span[text()='"+value+"']/.."));
        WebElement option = getDriver().findElement(By.xpath("//div[@role='option']//span[normalize-space(text())='"+value+"']/parent::div"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[normalize-space(text())='"+value+"']/parent::div")));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        option.click();
	}
	
	public void DD_fee(String DD, String value) {
		
		WebElement dropdown = getDriver().findElement(By.xpath("//label [text()='"+DD+"']/following-sibling::button"));
		wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
       // WebElement option = driver.findElement(By.xpath("//span[text()='"+value+"']/.."));
        WebElement option = getDriver().findElement(By.xpath("//span[text()='"+value+"']/.."));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        option.click();
	}
	public void DD_Multi_fee(String DD, String value) {
		
		WebElement dropdown = getDriver().findElement(By.xpath("//label [text()='"+DD+"']/following-sibling::button"));
		wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='"+value+"']/..")));
       // WebElement option = driver.findElement(By.xpath("//span[text()='"+value+"']/.."));
        WebElement option = getDriver().findElement(By.xpath("//span[normalize-space()='"+value+"']/.."));
        option.click();
	}
	
	
	public void DD_Acad_fee(String value) {
		
		WebElement dropdown = getDriver().findElement(By.xpath("//label[text()='Academic Year ']/following-sibling::button"));
		wait.until(ExpectedConditions.elementToBeClickable(dropdown));
		dropdown.click();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+value+"']/..")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='"+value+"']/..")));
		// WebElement option = driver.findElement(By.xpath("//span[text()='"+value+"']/.."));
		WebElement option = getDriver().findElement(By.xpath("//span[normalize-space()='"+value+"']/.."));
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
		option.click();
	}
	
	
	 public void scrollDivToBottom(By locator) {
	        WebElement div = getDriver().findElement(locator);
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
	        WebElement div = getDriver().findElement(locator);
	        js.executeScript("arguments[0].scrollTop = 0;", div);
	    }

	    // Scroll inside div by pixels
	    public void scrollDivByPixels(WebElement InDiv, int pixels) {
	      
	        js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + " + pixels + ";", InDiv);
	    }

	    // Scroll to element inside div
	    public void scrollToElementInsideDiv(String  parentDiv, WebElement element) {
	        WebElement div = getDriver().findElement(By.xpath(parentDiv));
	   
	        js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", div, element);
	    }
}
