package stepDefinitions;

import com.netpay.constants.ApplicationConstants;
import com.netpay.frameworkweb.DriverManager;
import com.netpay.frameworkweb.DriverManagerFactory;
import com.netpay.frameworkweb.DriverType;
import com.pages.utilities.ApplicationProperties;
import com.pages.utilities.LoggerProperties;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ContextSteps {
    protected ApplicationProperties appPropertiesWeb;
    protected ApplicationProperties appPropertiesApi;
    protected ApplicationProperties appPropertiesEnvironment;
    private RemoteWebDriver driver;
    protected static String environment;
    protected DriverManager driverManager;
    protected DriverType driverType;
    protected LoggerProperties loggerProperties;
    private static Logger logger;
    protected static int passCount = 0;
    protected static int failCount = 0;
    public static final String TOKEN = "";
    public static final String CHANNEL = "";


    public RemoteWebDriver getDriver() {
        return driver;
    }

    public String getEnvironment() {
        return environment;
    }

    @Before(order = 1)
    public void setup(Scenario scenario) throws IOException {
        loggerProperties = LoggerProperties.getInstance();
        setLogger(loggerProperties.getLogger());
        String browser = System.getenv("browser");
        environment = System.getenv("envi");
        appPropertiesWeb = ApplicationProperties.getInstance(ApplicationConstants.WEBTESTS);
        if (browser == null) {
            browser = appPropertiesWeb.getProperty(ApplicationConstants.BROWSER_NAME);
        }
        if (environment == null) {
            environment = appPropertiesWeb.getProperty(ApplicationConstants.ENVIRONMENT);
        }
        appPropertiesEnvironment = ApplicationProperties.getEnvironment(environment);
        setBrowserType(browser);
        driverManager = DriverManagerFactory.getDriverManager(driverType);
        driver = driverManager.getDriver();
        if (!(driver == null) && browser.contains("grid")) {
            ((JavascriptExecutor) getDriver()).executeScript("sauce:job-name=" + scenario.getName());
            driver.setFileDetector(new LocalFileDetector());
        }

    }


    @Before(order = 2)
    public void configureWaitTimes() {
        int implicitWaitTime = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.IMPLICIT_WAIT_TIME));
        long pageloadWaitTime = Integer.parseInt(appPropertiesWeb.getProperty(ApplicationConstants.PAGE_LOAD_WAIT_TIME));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageloadWaitTime));
    }

    @Before(order = 3)
    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");

    }

    @After
    public static void countTests(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.info(scenario + " failed");
            failCount += 1;
        } else {
            passCount += 1;
        }
    }

    @After
    public void stopBrowser() {
        driverManager.quit();
    }

    @AfterAll
    public static void before_or_after_all() {
//    	SlackWebHook hook = new SlackWebHook();
//    	try {
//			hook.sendMessage(environment,passCount+failCount,failCount);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

    }


    protected void setBrowserType(String browserString) {
        if (browserString.equalsIgnoreCase("chrome")) {
            driverType = DriverType.CHROME;
        } else if (browserString.equalsIgnoreCase("firefox")) {
            driverType = DriverType.FIREFOX;
        } else if (browserString.equalsIgnoreCase("grid-chrome")) {
            driverType = DriverType.GRID_CHROME;
        } else if (browserString.equalsIgnoreCase("grid-firefox")) {
            driverType = DriverType.GRID_FIREFOX;
        } else {
            throw new AssertionError("Unsupported Browser");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ContextSteps.logger = logger;
    }


}
