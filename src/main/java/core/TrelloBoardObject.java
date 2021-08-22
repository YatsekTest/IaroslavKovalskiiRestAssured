package core;

import beans.TrelloBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static constants.Tags.*;

public class TrelloBoardObject extends TrelloBaseObject {

    private TrelloBoardObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static ApiRequestBuilder requestBuilderBoard() {
        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private Map<String, String> parameters = new HashMap<>();
        private Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public ApiRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public ApiRequestBuilder setId(String boardId) {
            parameters.put(ID.tag, boardId);
            return this;
        }

        public ApiRequestBuilder setName(String name) {
            parameters.put(NAME.tag, name);
            return this;
        }

        public ApiRequestBuilder setDescription(String description) {
            parameters.put(DESCRIPTION.tag, description);
            return this;
        }

        public TrelloBoardObject buildRequest() {
            return new TrelloBoardObject(requestMethod, parameters, path);
        }
    }

    public static TrelloBoard getBoard(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloBoard>() {
        }.getType());
    }

}
