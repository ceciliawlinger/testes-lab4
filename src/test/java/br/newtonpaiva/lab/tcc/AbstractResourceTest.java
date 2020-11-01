package br.newtonpaiva.lab.tcc;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractResourceTest {

    @LocalServerPort
    int port;

    protected RequestSpecification createRequest() {
        return given()
            .log()
            .all()
            .port(port)
            .accept(JSON)
            .contentType(JSON)
            .urlEncodingEnabled(false);
    }

    protected Response getResourceAndExpectStatusCode(String url, String id, int status) {
        var response = createRequest().when()
            .pathParam("id", id)
            .get(url)
            .thenReturn();

        response
            .then()
            .assertThat()
            .statusCode(status);

            return response;
    }
}