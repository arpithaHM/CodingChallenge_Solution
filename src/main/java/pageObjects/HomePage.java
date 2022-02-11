package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.basePage;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends basePage{

    WebDriver driver;
    public static ArrayList<Double> itemList;
    public static ArrayList<Double> itemListDesc;

//    private HomePage(){
//        itemList = new ArrayList<>();
//    }


    @FindBy(xpath = "//input[@placeholder='Search for anything']")
    WebElement Search;

    @FindBy(xpath = "//div[@id='gAC']//li[2]")
    WebElement SearchItem;

    @FindBy(xpath = "//select[@id='gh-cat']")
    WebElement Category;

    @FindBy(xpath = "//div[@id=\'ResultSetItems\']//ul[@id=\'ListViewInner\']//li")
    WebElement searchResults;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//h3//a[@title]")
    List<WebElement> searchResultsTitle;

//    @FindBy(xpath = "//div[@id='gdpr-banner']//button")
//    WebElement cookies;

    @FindBy(xpath = "//input[@value='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@id='CenterPanelInner']//a[@title='Auction']")
    WebElement auctionWithoutAds;

    @FindBy(xpath = "//ul[@class='fake-tabs__items']//li[2]")
    WebElement auction;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//ul//li[@class='lvprice prc']")
    List<WebElement> itemPrice;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//ul//li[@class='lvshipping']//span[@class='ship']")
    List<WebElement> itemPostagePrice;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//ul//li[@class='lvformat']")
    List<WebElement> itemNoOfBids;

    @FindBy(xpath = "//div[@id='cbrt']//a[@title='Buy it now']")
    WebElement Buy_it_now;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//ul//li[@class='lvformat']//span//span")
    List<WebElement> itemBuyItNow;

    @FindBy(xpath = "//div[@id='LeftPanel']//input[@aria-label='Apple iPhone 11 Pro']")
    WebElement model;

    @FindBy(xpath = "//div[@id='DashSortByContainer']//a[@aria-controls='SortMenu']")
    WebElement sortRange;

    @FindBy(xpath = "//ul[@id='SortMenu']")
    WebElement sortMenu;

    @FindBy(xpath = "//button[@aria-label='Accept privacy terms and settings']")
    WebElement privacyPolicy;

    @FindBy(xpath = "//div[@id='ResultSetItems']//ul[@id='ListViewInner']//li[@r]//ul//li[@class='lvformat']")
    WebElement buyItNowTagTitle;

    @FindBy(xpath = "//table[@id='Pagination']//td//a")
    List<WebElement>  pagination;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void validateHomePage(String url)
    {
        driver.get(url);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay");
    }

    public void searchProduct(String item, String category, String subCategory, String model) throws InterruptedException {
        Search.clear();
        Search.sendKeys(item);
        Thread.sleep(3000);
        //SearchItem.click();
        Thread.sleep(3000);
        selectAnItem(category);
        Thread.sleep(2000);
        searchButton.click();
        Thread.sleep(4000);
        Boolean isPresent = driver.findElements(By.xpath("//li[@data-expansion='OPEN_PRIMARY']//ul//li//a//span[contains(text(),'"+ subCategory +"')]")).size() > 0;
        if(isPresent)
        {
            driver.findElement(By.xpath("//li[@data-expansion='OPEN_PRIMARY']//ul//li//a//span[contains(text(),'"+ subCategory +"')]")).click();
        }
        else{
        SelectSubCategory(subCategory);}
        Thread.sleep(3000);
        selectModel(model);
        String actualSearch = Search.getAttribute("value");
        Assert.assertEquals(item, actualSearch);
//        if(driver.findElement(By.xpath("//div[@id='gdpr-banner']//button")).isDisplayed())
//        {
//            driver.findElement(By.xpath("//div[@id='gdpr-banner']//button")).click();
//        }
    }

    public void selectAnItem(String category)
    {
        Select categoryList = new Select(Category);
        categoryList.selectByVisibleText(category);
    }

    private void SelectSubCategory(String subCategory)
    {
        WebElement ele = driver.findElement(By.xpath("//div[@id='LeftPanelInner']//a[contains(text(),'"+ subCategory +"')]"));
        ele.click();
    }

    private void selectModel(String model){
        if(driver.findElements(By.xpath("//li[@name='Model']//a//input[@aria-label='" + model + "']")).size() > 0){
            WebElement ele = driver.findElement(By.xpath("//li[@name='Model']//a//input[@aria-label='" + model + "']"));
            ele.click();
        }else {
            WebElement ele = driver.findElement(By.xpath("//div[@id='LeftPanel']//input[@aria-label='" + model + "']"));
            ele.click();
        }
    }

    public void validateSearchResults(String item) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> list = searchResultsTitle;
//        if(driver.findElements(By.xpath("//div[@id='CenterPanelInner']//a[@title='Auction']")).size()>0){
//            auctionWithoutAds.click();
//        }else{
//            auction.click();}
        for(WebElement lists : list){
            String listText = lists.getText();
            System.out.println(listText);
                boolean flag = listText.toLowerCase().contains(item.toLowerCase());
//                try {
//                    Assert.assertEquals(flag, true);
//                }
//                catch(Exception e)
//                {
//                    System.out.println("search results not matching");
//                }
        }
    }

    public void validateItemCardValues() throws InterruptedException, ParseException {
        Thread.sleep(2000);
        if(driver.findElements(By.xpath("//div[@id='CenterPanelInner']//a[@title='Auction']")).size()>0){
            auctionWithoutAds.click();
        }else{
        auction.click();}
        itemList = new ArrayList<>();
        itemListDesc = new ArrayList<>();
        List<WebElement> priceList = itemPrice;
        List<WebElement> postagePrice = itemPostagePrice;
        List<WebElement> noOfBids = itemNoOfBids;

        for(WebElement price : priceList){
            String priceTag = price.getText().substring(1);
            priceTag = priceTag.replaceAll(",", "");
            double itemPrice = Double.parseDouble(priceTag);
            itemList.add(itemPrice);
            itemListDesc.add(itemPrice);
            boolean flag = price.isDisplayed();
            Assert.assertEquals(flag, true);
        }

        Collections.sort(itemList);
        Collections.sort(itemListDesc, Collections.reverseOrder());

        System.out.println(itemList);
        for(WebElement postageP : postagePrice){
            String postagePriceValue = postageP.getText();
            boolean flag = postageP.isDisplayed();
            Assert.assertEquals(flag, true);
        }

        for(WebElement bid : noOfBids){
            String bidValue = bid.getText();
            boolean flag = bid.isDisplayed();
            Assert.assertEquals(flag, true);
        }
    }

    public void sortItemsList(String sortOption)
    {
        sortRange.click();
        List<WebElement> options = driver.findElements(By.xpath("//ul[@id='SortMenu']//li//a"));
        for (WebElement opt : options) {
            if (opt.getText().equals(sortOption)) {
                opt.click();
                return;
            }
        }
        throw new NoSuchElementException("Can't find " + sortOption + " in dropdown");
    }

    public void validateSortedList(String correctOrder)
    {

        List<WebElement> priceList = itemPrice;
        ArrayList<Double> list = new ArrayList();
        System.out.println(itemList);
        for(WebElement price : priceList){
            String priceTag = price.getText().substring(1);
            priceTag = priceTag.replaceAll(",", "");
            double itemPrice = Double.parseDouble(priceTag);
            list.add(itemPrice);
            boolean flag = price.isDisplayed();
        }
        if(correctOrder.equalsIgnoreCase("Lowest price")) {
        Assert.assertEquals(itemList, list);}
        else{
            Assert.assertEquals(itemListDesc, list);}

    }

    public void applyFilter(String buyItNow)
    {
        Buy_it_now.click();
    }

    public void validateSearchResultsByTagName(String buyItNowTag)
    {
        List<WebElement> buyItNowList = itemBuyItNow;
        for(WebElement buyItNow : buyItNowList){
            String listText = buyItNow.getAttribute("title");
            System.out.println(listText);
            boolean flag = listText.toLowerCase().contains(buyItNowTag.toLowerCase());
            Assert.assertEquals(flag, true);
        }
    }

    public void validatePagination()
    {
        boolean flag = driver.findElements(By.xpath("//div[@class='s-pagination']//ol//li")).size()>0;
        if(flag){
            Assert.assertEquals(flag, true);
        }else{
            boolean paginationPresent = pagination.size()>0;
            Assert.assertEquals(paginationPresent, true);
        List<WebElement> paginationList = pagination;}

    }

}

