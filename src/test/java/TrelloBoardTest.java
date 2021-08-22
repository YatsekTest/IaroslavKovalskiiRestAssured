import beans.TrelloBoard;
import constants.Endpoints;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static core.TrelloBoardObject.*;
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

        TrelloBoard actualBoard = getBoard(requestBuilderBoard()
                .setMethod(Method.GET)
                .setId(boardId)
                .buildRequest().sendRequest(Endpoints.BOARDS_ENDPOINT + boardId)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()); // что это?

        assertThat(actualBoard, is(expectedBoard));
    }

}
