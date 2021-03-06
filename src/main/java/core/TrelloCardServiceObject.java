package core;

import beans.TrelloCard;
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

public class TrelloCardServiceObject extends TrelloBaseServiceObject {

    public TrelloCardServiceObject(Method requestMethod, Map<String, String> parameters, Map<String, String> path) {
        super(requestMethod, parameters, path);
    }

    public static CardRequestBuilder cardRequestBuilder() {
        return new CardRequestBuilder();
    }

    public static class CardRequestBuilder {
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> path = new HashMap<>();
        private Method requestMethod = Method.GET;

        public CardRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public CardRequestBuilder setId(String id) {
            parameters.put(ID.getTag(), id);
            return this;
        }

        public CardRequestBuilder setClosed(Boolean isClosed) {
            parameters.put(CLOSED.getTag(), isClosed.toString());
            return this;
        }

        public CardRequestBuilder setDesc(String desc) {
            parameters.put(DESCRIPTION.getTag(), desc);
            return this;
        }

        public CardRequestBuilder setIdBoard(String idBoard) {
            parameters.put(ID_BOARD.getTag(), idBoard);
            return this;
        }

        public CardRequestBuilder setIdList(String idList) {
            parameters.put(ID_LIST.getTag(), idList);
            return this;
        }

        public CardRequestBuilder setName(String name) {
            parameters.put(NAME.getTag(), name);
            return this;
        }

        public CardRequestBuilder setSubscribed(Boolean isSubscribed) {
            parameters.put(SUBSCRIBED.getTag(), isSubscribed.toString());
            return this;
        }

        public TrelloCardServiceObject buildRequest() {
            return new TrelloCardServiceObject(requestMethod, parameters, path);
        }
    }

    public static TrelloCard getTrelloCardFromResponse(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<TrelloCard>() {
        }.getType());
    }

    public static TrelloCard createDefaultCard(String idBoard, String idList) {
        return getTrelloCardFromResponse(cardRequestBuilder()
                .setMethod(Method.POST)
                .setIdBoard(idBoard)
                .setIdList(idList)
                .setName(RandomStringUtils.randomAlphabetic(3, 7))
                .setDesc(RandomStringUtils.randomAlphabetic(10, 30))
                .setClosed(false)
                .setSubscribed(false)
                .buildRequest()
                .sendRequest(Endpoints.CARDS)
                .then()
                .assertThat()
                .spec(correctResponseSpecification())
                .extract()
                .response());
    }

}
