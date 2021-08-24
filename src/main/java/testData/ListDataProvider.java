package testData;

import org.testng.annotations.DataProvider;

import static constants.DefaultListData.*;

public class ListDataProvider {

    @DataProvider
    public static Object[] defaultListNames() {
        return new Object[][]{{DEFAULT_LIST_TO_DO, DEFAULT_LIST_DOING, DEFAULT_LIST_DONE}};
    }
}
