package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testData.DataFromPropertiesFile;

import java.util.Map;

import static constants.DataFilePath.AUTH_DATA_FILE_PATH;
import static constants.Endpoints.BASE_URL;
import static constants.Tags.*;

public class TrelloBaseServiceObject {

    protected Method requestMethod;
    protected Map<String, String> parameters;
    protected Map<String, String> path;

    public TrelloBaseServiceObject(Method requestMethod,
                                   Map<String, String> parameters,
                                   Map<String, String> path) {
        this.requestMethod = requestMethod;
        this.parameters = parameters;
        this.path = path;
    }

    public Response sendRequest(String endpoint) {
        parameters.put(KEY.tag,
                DataFromPropertiesFile.getData(AUTH_DATA_FILE_PATH).getProperty(API_KEY.tag));
        parameters.put(TOKEN.tag,
                DataFromPropertiesFile.getData(AUTH_DATA_FILE_PATH).getProperty(TOKEN.tag));
        return RestAssured
                .given(requestSpecification())
                .log().all()
                .pathParams(path)
                .queryParams(parameters)
                .request(requestMethod, endpoint)
                .prettyPeek();
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

}
