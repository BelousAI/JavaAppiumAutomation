package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "dfddfg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchByTitleAndDescription() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Yamaha";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForElementBycTitleAndDescription("Yamaha Corporation", "Japanese multinational corporation and conglomerate");
        SearchPageObject.waitForElementBycTitleAndDescription("Yamaha Motor Company", "Motorcycles manufacturer");
        SearchPageObject.waitForElementBycTitleAndDescription("Yamaha YZF-R1", "Sport bike");
    }
}
