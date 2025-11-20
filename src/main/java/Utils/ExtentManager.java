package Utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.base;

public class ExtentManager extends base {
	private static ExtentReports extent;
	
	public static ExtentReports getReporter(WebDriver driver) {
		
		if (extent==null) {
			String reportPath = System.getProperty("user.dir")+"/Reports/TestReports.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setDocumentTitle("Orange HRM");
			spark.config().setResourceCDN("Amar");
			spark.config().setTheme(Theme.DARK);
			extent =new ExtentReports();
			extent.attachReporter(spark);
			
			 extent.setSystemInfo("Tester", "Amarnath Balotagi");
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	            if (driver != null) {
	                Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	                String browserName = caps.getBrowserName();
	                String browserVersion = caps.getBrowserVersion();
	                extent.setSystemInfo("Browser", browserName + " : " + browserVersion);
	              
	            }
	            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		return extent;
	}

}
