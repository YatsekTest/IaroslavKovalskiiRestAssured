import beans.TrelloBoard;
import constants.Endpoints;
import core.TrelloBoardServiceObject;
import io.restassured.http.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloBaseTest {

    protected String boardId;
    protected TrelloBoard defaultTrelloBoard;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        defaultTrelloBoard = TrelloBoardServiceObject
                .getBoard(TrelloBoardServiceObject
                        .boardRequestBuilder()
                        .setMethod(Method.POST)
                        .setName(RandomStringUtils.randomAlphabetic(3, 7))
                        .buildRequest()
                        .sendRequest(Endpoints.BOARDS_ENDPOINT));
        boardId = defaultTrelloBoard.getId();
        assertThat(boardId, notNullValue());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        TrelloBoardServiceObject
                .boardRequestBuilder()
                .setMethod(Method.DELETE)
                .buildRequest()
                .sendRequest(Endpoints.BOARDS_ENDPOINT + boardId);
        TrelloBoardServiceObject
                .boardRequestBuilder()
                .setMethod(Method.GET)
                .buildRequest()
                .sendRequest(Endpoints.BOARDS_ENDPOINT + boardId)
                .then()
                .assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
