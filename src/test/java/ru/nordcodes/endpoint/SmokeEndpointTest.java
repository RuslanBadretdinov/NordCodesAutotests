package ru.nordcodes.endpoint;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.nordcodes.extensions.DIExtension;
import ru.nordcodes.helpers.MyServiceHelper;
import ru.nordcodes.utils.PropsStore;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(DIExtension.class)
@DisplayName("/endpoint - smoke")
public class SmokeEndpointTest {

    @Inject
    private Injector guiceInjector;
    @Inject
    private PropsStore propsStore;
    @Inject
    private MyServiceHelper myServiceHelper;

    @Test
    public void endPoint200Test() {
        myServiceHelper
                .specEndpoint()
                .queryParam("token", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                .queryParam("action", "ACTION")
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("result", equalTo("OK"))
                .body(myServiceHelper.endpointThrowJsonSchema(200));
    }

    @Test
    public void endPoint400Test() {
        myServiceHelper
                .specEndpoint()
                .queryParam("token", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                .queryParam("action", "ACTION")
                .post()
                .then()
                .log().all()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("result", equalTo("ERROR"))
                .body("message", equalTo("token: должно соответствовать \"^[0-9A-F]{32}$\""))
                .body(myServiceHelper.endpointThrowJsonSchema(400));
    }
}
