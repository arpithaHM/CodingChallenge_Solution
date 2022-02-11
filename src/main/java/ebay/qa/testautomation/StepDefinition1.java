package ebay.qa.testautomation;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HomePage;
import utils.basePage;

import java.net.MalformedURLException;
import java.text.ParseException;

public class StepDefinition1 {
//WebDriver driver  = basePage.driver;

	public StepDefinition1() {
		//PageFactory.initElements(driver, this);
	}

	HomePage home;

	@Before
    public void setup() throws MalformedURLException {
        basePage.initializeDriver();
    }

	@Given("I am a non-registered customer")
	public void nonRegistered()
	{
		System.out.println("No registered user");
	}

	@And("^I navigate to \"([^\"]*)\"$")
	public void validateHomePageStep(String url){
		home = new HomePage(basePage.driver);
		home.validateHomePage(url);
	}

    @When("^I search for \"([^\"]*)\" in category \"([^\"]*)\" and subCatagory \"([^\"]*)\" and model \"([^\"]*)\"$")
    public void iSearchFor(String item, String category, String subCategory, String model)  throws InterruptedException{
        home = new HomePage(basePage.driver);
        home.searchProduct(item, category, subCategory, model);
    }

    @Then("^I get a list of matching results for \"([^\"]*)\"$")
    public void iGetAListOfMatchingResults(String item) throws InterruptedException {
	    home = new HomePage(basePage.driver);
	    home.validateSearchResults(item);
    }

    @And("^the resulting items cards show: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" or show \"([^\"]*)\" tag$")
    public void theResultingItemsCardsShowOrShowTag(String arg0, String arg1, String arg2, String arg3) throws InterruptedException, ParseException {
	    home = new HomePage(basePage.driver);
        home.validateItemCardValues();
    }

    @Then("^I can sort the results by \"([^\"]*)\"$")
    public void iCanSortTheResultsBy(String sortOption)  {
        home = new HomePage(basePage.driver);
        home.sortItemsList(sortOption);
    }

//    @And("^the results are listed in the page in the correct order$")
//    public void theResultsAreListedInThePageInTheCorrectOrder() {
//        home = new HomePage(basePage.driver);
//        home.validateSortedList();
//    }

    @And("^the results are listed in the page in the \"([^\"]*)\" order$")
    public void theResultsAreListedInThePageInTheOrder(String correctOrder){
        home = new HomePage(basePage.driver);
        home.validateSortedList(correctOrder);
    }

    @Then("^I can filter the results by \"([^\"]*)\"$")
    public void iCanFilterTheResultsBy(String buyItNow) {
        home = new HomePage(basePage.driver);
        home.applyFilter(buyItNow);
    }

    @And("^all the results shown in the page have the \"([^\"]*)\" tag$")
    public void allTheResultsShownInThePageHaveTheTag(String buyItNowTag) {
        home = new HomePage(basePage.driver);
        home.validateSearchResultsByTagName(buyItNowTag);
    }

    @And("^I can verify that the results shown as per the the selected category \"([^\"]*)\"$")
    public void iCanVerifyThatTheResultsShownAsPerTheTheSelectedCategory(String item) throws InterruptedException {
        home = new HomePage(basePage.driver);
        home.validateSearchResults(item);
    }

    @After
    public void browserCloase() throws Exception {
        basePage.tearDown();
    }

    @And("^the results show more than one page$")
    public void theResultsShowMoreThanOnePage() {
        home = new HomePage(basePage.driver);
        home.validatePagination();
    }

    @Given("^I am on a news website \"([^\"]*)\"$")
    public void iAmOnANewsWebsite(String url){
        home = new HomePage(basePage.driver);
        home.validateHomePage(url);    }
}
