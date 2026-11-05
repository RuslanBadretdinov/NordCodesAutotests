package ru.nordcodes.helpers;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import ru.nordcodes.utils.DirManager;
import ru.nordcodes.utils.PropsStore;

import static io.restassured.RestAssured.given;

public class MyServiceHelper {
    private final PropsStore propsStore;
    private final String baseUrl;
    private final String xApiKey;

    public MyServiceHelper(PropsStore propsStore) {
        this.propsStore = propsStore;
        this.baseUrl = propsStore.getProperties().getProperty("base.url");
        this.xApiKey = propsStore.getProperties().getProperty("x.api.key");
    }

    public RequestSpecification specEndpoint() {
        return given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("/endpoint")
                .header("X-Api-Key", xApiKey)
                .header("action", "ACTION")
                .contentType(ContentType.URLENC)
                .accept(ContentType.JSON);
    }

    @SneakyThrows
    public JsonSchemaValidator endpointThrowJsonSchema(int status) {
        String jsonSchema = DirManager.readFromClasspath(
                String.format("json_schemas/my_service/endpoint/endpoint_%d.json", status));
        return JsonSchemaValidator.matchesJsonSchema(jsonSchema);
    }
}
