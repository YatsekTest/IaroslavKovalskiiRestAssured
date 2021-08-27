import beans.TrelloList;
import constants.Endpoints;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import testData.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

import static core.TrelloListServiceObject.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloListTest extends TrelloBaseTest {

    @Test(dataProviderClass = ListDataProvider.class, dataProvider = "defaultListNames")
    public void checkDefaultListsNames(Object[] listNames) {

        List<TrelloList> listCards = getTrelloListsFromResponse(listRequestBuilder()
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

    @Test
    public void checkListCreate() {
        TrelloList actualList = createDefaultList(boardId);
        TrelloList expectedList = new TrelloList();
        expectedList.setId(actualList.getId());
        expectedList.setName(actualList.getName());
        expectedList.setClosed(false);
        expectedList.setIdBoard(boardId);

        assertThat(actualList, is(expectedList));
    }

    @Test
    public void checkListNameUpdate() {
        TrelloList trelloList = createDefaultList(boardId);
        String newListName = RandomStringUtils.randomAlphabetic(3, 7);
        TrelloList updatedTrelloList = getTrelloListFromResponse(listRequestBuilder()
                .setMethod(Method.PUT)
                .setName(newListName)
                .buildRequest()
                .sendRequest(Endpoints.LISTS + trelloList.getId())
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract().response());

        assertThat(updatedTrelloList.getId(), is(trelloList.getId()));
        assertThat(updatedTrelloList.getName(), not(trelloList.getName()));
        assertThat(updatedTrelloList.getName(), is(newListName));
    }

}
