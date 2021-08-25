import beans.TrelloBoard;
import constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static core.TrelloBoardServiceObject.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloBoardTest extends TrelloBaseTest {

    @Test
    public void checkGetBoardById() {
        TrelloBoard expectedBoard = new TrelloBoard();
        expectedBoard.setId(boardId);
        expectedBoard.setName(defaultTrelloBoard.getName());
        expectedBoard.setDesc(defaultTrelloBoard.getDesc());
        expectedBoard.setClosed(false);

        TrelloBoard actualBoard = getTrelloBoardFromResponse(boardRequestBuilder()
                .setMethod(Method.GET)
                .setId(boardId)
                .buildRequest().sendRequest(Endpoints.BOARDS + boardId)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .time(lessThan(1000L))
                .extract().response());

        assertThat(actualBoard, is(expectedBoard));
    }

    @Test
    public void checkBoardUpdate() {
        String newName = RandomStringUtils.randomAlphabetic(3, 7);
        String newDesc = RandomStringUtils.randomAlphabetic(10, 30);
        TrelloBoard updatedBoard = getTrelloBoardFromResponse(boardRequestBuilder()
                .setMethod(Method.PUT)
                .setName(newName)
                .setDescription(newDesc)
                .buildRequest()
                .sendRequest(Endpoints.BOARDS + boardId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(1000L))
                .extract().response());

        assertThat(updatedBoard.getId(), is(boardId));
        assertThat(updatedBoard.getName(), not(defaultTrelloBoard.getName()));
        assertThat(updatedBoard.getName(), is(newName));
        assertThat(updatedBoard.getDesc(), not(defaultTrelloBoard.getDesc()));
        assertThat(updatedBoard.getDesc(), is(newDesc));
    }

}
