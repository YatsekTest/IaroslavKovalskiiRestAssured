package core;

import beans.TrelloBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constants.Endpoints;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static constants.Tags.*;
import static org.hamcrest.Matchers.lessThan;

public class TrelloBoardServiceObject extends TrelloBaseServiceObject {

    private TrelloBoardServiceObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static boardRequestBuilder boardRequestBuilder() {
        return new boardRequestBuilder();
    }

    public static class boardRequestBuilder {
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public boardRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public boardRequestBuilder setId(String boardId) {
            parameters.put(ID.getTag(), boardId);
            return this;
        }

        public boardRequestBuilder setName(String name) {
            parameters.put(NAME.getTag(), name);
            return this;
        }

        public boardRequestBuilder setDescription(String description) {
            parameters.put(DESCRIPTION.getTag(), description);
            return this;
        }

        public TrelloBoardServiceObject buildRequest() {
            return new TrelloBoardServiceObject(requestMethod, parameters, path);
        }
    }

    public static TrelloBoard getTrelloBoardFromResponse(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloBoard>() {
        }.getType());
    }

    public static TrelloBoard createDefaultBoard() {
        return getTrelloBoardFromResponse(TrelloBoardServiceObject
                .boardRequestBuilder()
                .setMethod(Method.POST)
                .setName(RandomStringUtils.randomAlphabetic(3, 7))
                .buildRequest()
                .sendRequest(Endpoints.BOARDS)
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract()
                .response());
    }

}
