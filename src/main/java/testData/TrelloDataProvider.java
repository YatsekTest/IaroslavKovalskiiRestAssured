package testData;

import org.testng.annotations.DataProvider;

import static constants.DefaultListNames.*;

public class TrelloDataProvider {

    @DataProvider
    public static Object[][] defaultListNames() {
        return new Object[][]{{DEFAULT_LIST_TO_DO, DEFAULT_LIST_DOING, DEFAULT_LIST_DONE}};
    }

    @DataProvider
    public static Object[][] cardNames() {
        return new Object[][]{{"Card1"}, {"Card2"}, {"Card3"}};
    }

}
