import beans.TrelloBoard;
import constants.Endpoints;
import core.TrelloBoardServiceObject;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

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

        TrelloBoard actualBoard = TrelloBoardServiceObject
                .getBoard(TrelloBoardServiceObject
                        .boardRequestBuilder()
                        .setMethod(Method.GET)
                        .setId(boardId)
                        .buildRequest().sendRequest(Endpoints.BOARDS_ENDPOINT + boardId)
                        .then().assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(ContentType.JSON)
                        .time(lessThan(500L))
                        .extract().response());

        assertThat(actualBoard, is(expectedBoard));
    }

}
