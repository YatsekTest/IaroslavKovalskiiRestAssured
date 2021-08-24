package core;

import beans.TrelloBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static constants.Tags.*;

public class TrelloBoardServiceObject extends TrelloBaseServiceObject {

    private TrelloBoardServiceObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static boardRequestBuilder boardRequestBuilder() {
        return new boardRequestBuilder();
    }

    public static class boardRequestBuilder {
        private Map<String, String> parameters = new HashMap<>();
        private Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public boardRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public boardRequestBuilder setId(String boardId) {
            parameters.put(ID.tag, boardId);
            return this;
        }

        public boardRequestBuilder setName(String name) {
            parameters.put(NAME.tag, name);
            return this;
        }

        public boardRequestBuilder setDescription(String description) {
            parameters.put(DESCRIPTION.tag, description);
            return this;
        }

        public TrelloBoardServiceObject buildRequest() {
            return new TrelloBoardServiceObject(requestMethod, parameters, path);
        }
    }

    public static TrelloBoard getBoard(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloBoard>() {
        }.getType());
    }

}
