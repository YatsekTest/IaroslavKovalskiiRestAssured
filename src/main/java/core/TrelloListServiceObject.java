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

public class TrelloListServiceObject extends TrelloBaseServiceObject {

    public TrelloListServiceObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static ListRequestBuilder listRequestBuilder() {
        return new ListRequestBuilder();
    }

    public static class ListRequestBuilder {
        private Map<String, String> parameters = new HashMap<>();
        private Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ListRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public ListRequestBuilder setName(String name) {
            parameters.put(NAME.tag, name);
            return this;
        }

        public ListRequestBuilder setId(String id) {
            parameters.put(ID.tag, id);
            return this;
        }

        public ListRequestBuilder setClosed(Boolean isClosed) {
            parameters.put(CLOSED.tag, isClosed.toString());
            return this;
        }

        public ListRequestBuilder setIdBoard(String idBoard) {
            parameters.put(ID_BOARD.tag, idBoard);
            return this;
        }

        public ListRequestBuilder setBoardId(String boardId) {
            path.put(ID.tag, boardId);
            return this;
        }

        public ListRequestBuilder setListId(String listId) {
            path.put(ID.tag, listId);
            return this;
        }

        public TrelloListServiceObject buildRequest() {
            return new TrelloListServiceObject(requestMethod, parameters, path);
        }
    }

    public static TrelloList getTrelloList(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloList>() {
        }.getType());
    }

    public static List<TrelloList> getTrelloLists(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<TrelloList>>() {
        }.getType());
    }

    public static TrelloList createDefaultList(String idBoard) {
        return getTrelloList(listRequestBuilder()
                .setMethod(Method.POST)
                .setName(RandomStringUtils.randomAlphabetic(3, 7))
                .setIdBoard(idBoard)
                .buildRequest()
                .sendRequest(Endpoints.LISTS)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response());
    }

}
