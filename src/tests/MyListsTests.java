package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        String first_article_title = "Java (programming language)";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_title);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        String second_article_title = "Java (software platform)";
        SearchPageObject.clickByArticleWithSubstring(second_article_title);

        ArticlePageObject.waitForTitleElement();
        //ArticlePageObject.addArticleToMyList(name_of_folder);


//        SearchPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
//                "Cannot find button to open article options",
//                5
//        );
//
//        SearchPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='Add to reading list']"),
//                "Cannot find option to add article to reading list",
//                5
//        );
//
//        SearchPageObject.waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text='" + name_of_folder + "']"),
//                "Cannot find folder by " + name_of_folder,
//                5
//        );

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);
        MyListsPageObject.clickByArticleWithTitle(second_article_title);

        String actual_article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "The title of the article does not match",
                second_article_title,
                actual_article_title
        );
    }
}
