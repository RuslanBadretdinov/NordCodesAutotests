package ru.nordcodes.helpers;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.nordcodes.utils.props.PropsStore;

import static io.restassured.RestAssured.given;

public class EndpointHelper {
    private final PropsStore propsStore;
    private final String baseUrl;
    private final String basePath;
    private final String xApiKey;

    public EndpointHelper(PropsStore propsStore) {
        this.propsStore = propsStore;
        this.baseUrl = propsStore.getProperties().getProperty("base.url");
        this.basePath = "/endpoint";
        this.xApiKey = propsStore.getProperties().getProperty("x.api.key");
    }

    public RequestSpecification reqSpec() {
        return given()
                .log().all()
                .baseUri(baseUrl)
                .basePath(basePath)
                .header("X-Api-Key", xApiKey)
                .header("action", "ACTION")
                .contentType(ContentType.URLENC)
                .accept(ContentType.JSON);
    }
}
