package ru.nordcodes.endpoint;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.nordcodes.extensions.DIExtension;
import ru.nordcodes.helpers.EndpointHelper;
import ru.nordcodes.utils.props.PropsStore;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(DIExtension.class)
@DisplayName("/endpoint - smoke")
public class SmokeEndpointTest {

    @Inject
    private Injector guiceInjector;
    @Inject
    private PropsStore propsStore;
    @Inject
    private EndpointHelper endpointHelper;

    @Test
    public void negEndPointTest() {
        endpointHelper
                    .reqSpec()
                    .queryParam("token", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
                    .queryParam("action", "ACTION")
                    .post()
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("result", equalTo("OK"));
    }
}
