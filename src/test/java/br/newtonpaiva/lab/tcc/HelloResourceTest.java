package br.newtonpaiva.lab.tcc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HelloResourceTest extends AbstractResourceTest{

    @Test
    public void testHelloResource() {
        createRequest().when()
            .get("/v1/hello").then().assertThat().statusCode(200);
    }
}
