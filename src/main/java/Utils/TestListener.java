package Utils;

	import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Base.base;


	public class TestListener implements ITestListener {

	   
		
	    private static ExtentReports extent;
	    private static ThreadLocal <ExtentTest> test = new ThreadLocal<>();
	    
	    @Override public void onStart(ITestContext context) {
	    	 System.out.println("===== Test Suite Started =====");
	         extent = ExtentManager.getReporter(base.getDriver());
	    	}
	    
	    @Override public void onTestStart(ITestResult result) {
	    	ExtentTest extenttest = extent.createTest(result.getMethod().getMethodName());
	    	test.set(extenttest);
	    }
	    
	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	test.get().log(Status.PASS, "✅ Test Passed");
	    }
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	test.get().log(Status.FAIL, result.getThrowable());

	        WebDriver driver = base.getDriver();
	        if (driver != null) {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);
	            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            String destPath = System.getProperty("user.dir") 
	                    + "/Screenshort/" 
	                    + result.getName() + "_" + timeStamp + ".png";

	            try {
	                FileUtils.copyFile(src, new File(destPath));
	                System.out.println("📸 Screenshot saved at: " + destPath);
	                test.get().fail("Screenshot of failure", MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // Optional: you can also capture on success or skip

	    @Override
	    public void onTestSkipped(ITestResult result) {
	       test.get().skip(result.getThrowable());
	    }

	    @Override public void onFinish(ITestContext context) 
	    { System.out.println("===== Test Suite Completed =====");
	    	extent.flush();
	    }
	}


