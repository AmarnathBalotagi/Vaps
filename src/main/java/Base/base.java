package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import Utils.configReader;

public class base {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver setup(String browserName) {
        configReader.initProp();
        String url = configReader.getProperty("baseUrl");
        long timeout = Long.parseLong(configReader.getProperty("implicitWait"));

        WebDriver localDriver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        localDriver.get(url);
        
        driver.set(localDriver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
	
	
	

}
