package utils;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberUsageReporting;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class basePage {

//    public static WebDriver Instance = null;
//    public static String browser = "Chrome";
//
//    public static void initialize(){
//        if(Instance == null){
//            if(browser.equalsIgnoreCase("Chrome")){
//                Instance = new ChromeDriver();
//            }
//        }
//    }




    public static WebDriver driver = null;

    public static void initializeDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "/Users/arpithahm/Desktop/CodingChallenge _Solution/browsers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
       // options.addExtensions(new File("/Users/arpithahm/Desktop/CodingChallenge/browsers/extension_3_58_0_0.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        options.merge(capabilities);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);
        //driver = new RemoteWebDriver(new URL("http://192.168.0.18:4444/wd/hub"),capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    public static void tearDown() throws Exception {
        driver.quit();

//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("cucumber-results");
//        results.setSourceFile("./src/test/resources/cucumber.json");
//        results.executeFeaturesOverviewReport();
//        CucumberUsageReporting report = new CucumberUsageReporting();
//        report.setOutputDirectory("target");
//        report.setJsonUsageFile("./src/test/resources/cucumber-usage.json");
//        report.executeReport();
    }
}
