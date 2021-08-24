import beans.TrelloList;
import constants.Endpoints;
import core.TrelloListServiceObject;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import testData.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloListTest extends TrelloBaseTest {

    @Test(dataProviderClass = ListDataProvider.class, dataProvider = "defaultListNames")
    public void checkGetBoardDefaultLists(Object[] listNames) {

        List<TrelloList> listCards = TrelloListServiceObject
                .getTrelloLists(TrelloListServiceObject
                        .listRequestBuilder()
                        .setMethod(Method.GET)
                        .setBoardId(boardId)
                        .buildRequest()
                        .sendRequest(Endpoints.BOARDS_LISTS)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().response());

        List<String> actualListNames = new ArrayList<>();
        for (TrelloList card : listCards) {
            actualListNames.add(card.getName());
        }

        List<String> expectedListNames = new ArrayList<>();
        for (Object o : listNames) {
            expectedListNames.add(o.toString());
        }

        assertThat(actualListNames, is(expectedListNames));
    }

}
