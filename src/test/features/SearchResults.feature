Feature: Search Results. Where one can sort, filter and see details of resulting products.

@001
Scenario: Search and verify results
Given I am a non-registered customer 
And I navigate to "www.ebay.co.uk"
When I search for "iphone 11 pro" in category "Mobile Phones & Communication" and subCatagory "Mobile & Smart Phones" and model "Apple iPhone 11 Pro"
Then I get a list of matching results for "iPhone 11"
And the resulting items cards show: "postage price", "No of bids", "price" or show "BuyItNow" tag
Then I can sort the results by "Lowest price"
And the results are listed in the page in the "Lowest price" order
Then I can sort the results by "Highest price"
And the results are listed in the page in the "Highest price" order
Then I can filter the results by "Buy it now"
And all the results shown in the page have the "Buy it now" tag

@002
Scenario: Search per category
Given I am a non-registered customer
And I navigate to "www.ebay.co.uk"
When I search for "iphone 11 pro" in category "Mobile Phones & Communication" and subCatagory "Mobile & Smart Phones" and model "Apple iPhone 11 Pro"
Then I get a list of matching results for "iPhone 11"
And I can verify that the results shown as per the the selected category "iPhone 11"
#
@003
Scenario: Search and navigate through results pages
Given I am a non-registered customer
And I navigate to "www.ebay.co.uk"
When I search for "iphone 11 pro" in category "Mobile Phones & Communication" and subCatagory "Mobile & Smart Phones" and model "Apple iPhone 11 Pro"
Then I get a list of matching results for "iPhone 11"
And the results show more than one page
#Then the user can navigate through the pages to continue looking at the items
