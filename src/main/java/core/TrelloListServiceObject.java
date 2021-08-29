package core;

import beans.TrelloList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constants.Endpoints;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.Tags.*;
import static org.hamcrest.Matchers.lessThan;

public class TrelloListServiceObject extends TrelloBaseServiceObject {

    public TrelloListServiceObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static ListRequestBuilder listRequestBuilder() {
        return new ListRequestBuilder();
    }

    public static class ListRequestBuilder {
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ListRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public ListRequestBuilder setName(String name) {
            parameters.put(NAME.getTag(), name);
            return this;
        }

        public ListRequestBuilder setId(String id) {
            parameters.put(ID.getTag(), id);
            return this;
        }

        public ListRequestBuilder setClosed(Boolean isClosed) {
            parameters.put(CLOSED.getTag(), isClosed.toString());
            return this;
        }

        public ListRequestBuilder setIdBoard(String idBoard) {
            parameters.put(ID_BOARD.getTag(), idBoard);
            return this;
        }

        public ListRequestBuilder setBoardId(String boardId) {
            path.put(ID.getTag(), boardId);
            return this;
        }

        public ListRequestBuilder setListId(String listId) {
            path.put(ID.getTag(), listId);
            return this;
        }

        public TrelloListServiceObject buildRequest() {
            return new TrelloListServiceObject(requestMethod, parameters, path);
        }
    }

    public static TrelloList getTrelloListFromResponse(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloList>() {
        }.getType());
    }

    public static List<TrelloList> getTrelloListsFromResponse(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<TrelloList>>() {
        }.getType());
    }

    public static TrelloList createDefaultList(String idBoard) {
        return getTrelloListFromResponse(listRequestBuilder()
                .setMethod(Method.POST)
                .setName(RandomStringUtils.randomAlphabetic(3, 7))
                .setIdBoard(idBoard)
                .buildRequest()
                .sendRequest(Endpoints.LISTS)
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract()
                .response());
    }

}
