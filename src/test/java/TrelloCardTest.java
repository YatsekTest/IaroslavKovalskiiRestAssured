import beans.TrelloCard;
import beans.TrelloList;
import constants.Endpoints;
import core.TrelloCardServiceObject;
import core.TrelloListServiceObject;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloCardTest extends TrelloBaseTest {

    @Test
    public void checkUpdateCard() {
        TrelloList trelloList = TrelloListServiceObject.createDefaultList(boardId);
        TrelloCard trelloCard = TrelloCardServiceObject
                .createDefaultCard(boardId, trelloList.getId());
        String cardId = trelloCard.getId();
        String randomName = RandomStringUtils.randomAlphabetic(3, 7);
        String randomDesc = RandomStringUtils.randomAlphabetic(10, 30);

        TrelloCard updatedCard = TrelloCardServiceObject
                .getTrelloCard(TrelloCardServiceObject
                        .cardRequestBuilder()
                        .setMethod(Method.PUT)
                        .setName(randomName)
                        .setDesc(randomDesc)
                        .buildRequest()
                        .sendRequest(Endpoints.CARDS + cardId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response());
        assertThat(updatedCard.getId(), equalTo(cardId));
        assertThat(updatedCard.getName(), equalTo(randomName));
        assertThat(updatedCard.getDesc(), equalTo(randomDesc));
    }

}
