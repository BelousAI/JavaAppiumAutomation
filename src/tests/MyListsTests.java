package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String NAME_OF_FOLDER = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.cancelSyncSavedArticles();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "Java";
        String first_article_title = "Java (programming language)";
        String second_article_title = "Java (software platform)";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_title);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.cancelSyncSavedArticles();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine(search_line);
        }

        SearchPageObject.clickByArticleWithSubstring(second_article_title);

        ArticlePageObject.waitForTitleElement();

        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addSecondArticleToMyList(NAME_OF_FOLDER);
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

        assertTrue(
                "The number of remaining articles is not one.",
                MyListsPageObject.getCountOfElements() == 1
        );
    }
}
