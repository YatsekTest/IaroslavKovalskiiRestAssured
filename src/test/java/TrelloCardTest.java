import beans.TrelloCard;
import beans.TrelloList;
import constants.Endpoints;
import core.TrelloListServiceObject;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.List;

import static core.TrelloCardServiceObject.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloCardTest extends TrelloBaseTest {

    @Test
    public void cardUpdateTest() {
        TrelloList trelloList = TrelloListServiceObject.createDefaultList(boardId);
        TrelloCard trelloCard = createDefaultCard(boardId, trelloList.getId());
        String cardId = trelloCard.getId();
        String randomName = RandomStringUtils.randomAlphabetic(3, 7);
        String randomDesc = RandomStringUtils.randomAlphabetic(10, 30);

        TrelloCard updatedCard = getTrelloCardFromResponse(cardRequestBuilder()
                .setMethod(Method.PUT)
                .setName(randomName)
                .setDesc(randomDesc)
                .buildRequest()
                .sendRequest(Endpoints.CARDS + cardId)
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract().response());

        assertThat(updatedCard.getId(), equalTo(cardId));
        assertThat(updatedCard.getName(), equalTo(randomName));
        assertThat(updatedCard.getDesc(), equalTo(randomDesc));
    }

    @Test(testName = "When one of two cards in list is closed, then list size equals 1.")
    public void whenOneOfTwoCardsInListIsClosedThenListSizeEqualsOne() {
        TrelloList trelloList = TrelloListServiceObject.createDefaultList(boardId);
        TrelloCard firstCard = createDefaultCard(boardId, trelloList.getId());
        TrelloCard secondCard = createDefaultCard(boardId, trelloList.getId());
        List<TrelloList> trelloLists = getTrelloLists(trelloList);

        assertThat(trelloLists, hasSize(2));

        TrelloCard secondCardClosed = getTrelloCardFromResponse(cardRequestBuilder()
                .setMethod(Method.PUT)
                .setClosed(true)
                .buildRequest()
                .sendRequest(Endpoints.CARDS + secondCard.getId())
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract().response());

        trelloLists = getTrelloLists(trelloList);

        assertThat(trelloLists, hasSize(1));
        assertThat(trelloLists.get(0).getId(), is(firstCard.getId()));
        assertThat(trelloLists.get(0).getIdBoard(), is(firstCard.getIdBoard()));
        assertThat(trelloLists.get(0).getName(), is(firstCard.getName()));
        assertThat(secondCardClosed.getClosed(), is(true));
        assertThat(secondCardClosed.getId(), is(secondCard.getId()));
    }

    private List<TrelloList> getTrelloLists(TrelloList trelloList) {
        return TrelloListServiceObject
                .getTrelloListsFromResponse(TrelloListServiceObject
                        .listRequestBuilder()
                        .setListId(trelloList.getId())
                        .buildRequest()
                        .sendRequest(Endpoints.LISTS_CARDS)
                        .then()
                        .assertThat()
                        .spec(correctResponseSpecification())
                        .extract().response());
    }

}
