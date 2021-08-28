import beans.TrelloBoard;
import constants.Endpoints;
import core.TrelloBoardServiceObject;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class TrelloBaseTest {

    protected String boardId;
    protected TrelloBoard defaultTrelloBoard;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        defaultTrelloBoard = TrelloBoardServiceObject.createDefaultBoard();
        boardId = defaultTrelloBoard.getId();
        assertThat(boardId, notNullValue());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        TrelloBoardServiceObject
                .boardRequestBuilder()
                .setMethod(Method.DELETE)
                .buildRequest()
                .sendRequest(Endpoints.BOARDS + boardId);
        TrelloBoardServiceObject
                .boardRequestBuilder()
                .buildRequest()
                .sendRequest(Endpoints.BOARDS + boardId)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .time(lessThan(5000L));
    }
}
