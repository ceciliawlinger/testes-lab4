package br.newtonpaiva.lab.tcc;

import static br.newtonpaiva.lab.tcc.common.enums.RegimeDeCurso.PRESENCIAL;
import static br.newtonpaiva.lab.tcc.common.enums.RegimeDeCurso.SEMI_PRESENCIAL;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.newtonpaiva.lab.tcc.api.cursos.request.CursoRequest;
import br.newtonpaiva.lab.tcc.api.cursos.response.CursoResponse;

@RunWith(SpringRunner.class)
public class CursosResourceTest extends AbstractResourceTest{

    private static final String PATH_CURSOS = "/v1/cursos";

    @Test
    public void testGetAll_shouldReturnStatusOk() {
        createRequest().when()
            .get(PATH_CURSOS)
            .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testGetById_withInvalidId_shouldReturnNotFoundException() {
        // given
        var id = "1931fdb3-2143-4cbc-933a-984f8d5f2955";

        // test/assert
        getResourceAndExpectStatusCode(PATH_CURSOS + "/{id}", id, 404)
            .then()
            .assertThat()
            .statusCode(404)
            .body("timestamp", CoreMatchers.notNullValue())
            .body("status", equalTo(404))
            .body("error", equalTo("NotFoundException"))
            .body("message",
                equalTo("Curso com ID [1931fdb3-2143-4cbc-933a-984f8d5f2955] não encontrado.")
            );
    }

    @Test
    public void testCreate() {
        // given
        String id = null;
        var request = new CursoRequest(id, "Exatas", "Ciência da Computação", PRESENCIAL, 8);

        // test/assert
        createRequest()
            .when()
            .body(request)
            .post(PATH_CURSOS)
            .then()
            .assertThat()
            .statusCode(201)
            .body("id", CoreMatchers.notNullValue())
            .body("area", equalTo("Exatas"))
            .body("nome", equalTo("Ciência da Computação"))
            .body("regime", equalTo("PRESENCIAL"))
            .body("numSemestre", equalTo(8));
    }

    @Test
    public void testUpdate() {
        // given
        String id = null;
        var request = new CursoRequest(id, "Exatas", "Ciência da Computação", PRESENCIAL, 8);

        // create
        var response = createRequest()
            .when()
            .body(request)
            .post(PATH_CURSOS)
            .thenReturn();

        response
            .then()
            .assertThat()
            .statusCode(201);

        var salvo = response.body().as(CursoResponse.class);
        id = salvo.getId();

        var update = new CursoRequest(id, "Exatas", "Sistemas de Informação", SEMI_PRESENCIAL, 7);

        // test/assert
        createRequest()
            .when()
            .body(update)
            .put(PATH_CURSOS + "/" + salvo.getId())
            .then()
            .assertThat()
            .statusCode(200)
            .body("id", CoreMatchers.notNullValue())
            .body("area", equalTo(update.getArea()))
            .body("nome", equalTo(update.getNome()))
            .body("regime", equalTo(update.getRegime().toString()))
            .body("numSemestre", equalTo(update.getNumSemestre()));
    }

    @Test
    public void testDelete() {
        // given
        String id = null;
        var request = new CursoRequest(id, "Exatas", "Ciência da Computação", PRESENCIAL, 8);

        // create
        var response = createRequest()
            .when()
            .body(request)
            .post(PATH_CURSOS)
            .thenReturn();

        response
            .then()
            .assertThat()
            .statusCode(201);

        var salvo = response.body().as(CursoResponse.class);
        id = salvo.getId();

        getResourceAndExpectStatusCode(PATH_CURSOS + "/{id}", id, 200);

        // test
        createRequest().when()
            .pathParam("id", id)
            .delete(PATH_CURSOS + "/{id}")
            .then()
            .assertThat()
            .statusCode(204);

        // assert
        getResourceAndExpectStatusCode(PATH_CURSOS + "/{id}", id, 404);
    }

}
