package ebay.qa.test;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
//@ExtendedCucumberOptions(
//        jsonReport = "target/81/cucumber.json",
//        jsonUsageReport = "target/81/cucumber-usage.json",
//        usageReport = true,
//        detailedReport = true,
//        detailedAggregatedReport = true,
//        overviewReport = true,
//        overviewChartsReport = true,
//        pdfPageSize = "A4 Landscape",
//        toPDF = true,
//        outputFolder = "target/81",
//        retryCount = 3)

@CucumberOptions(
plugin = {"pretty","html:target/cucumber-html-report", "json:target/cucumber-json-report.json" },
//        plugin = {"html:target/81", "json:target/81/cucumber.json",
//                "pretty:target/81/cucumber-pretty.txt",
//                "usage:target/81/cucumber-usage.json", "junit:target/81/cucumber-results.xml"},
features = {"src/test/features"},
glue= {"ebay.qa.testautomation"},
//tags = {"@Scenario001"},
        monochrome = true
)

public class TestRunner {
	
}
