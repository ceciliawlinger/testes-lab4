package br.newtonpaiva.lab.tcc.application.controllers;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.newtonpaiva.lab.tcc.domain.entity.Professor;
import br.newtonpaiva.lab.tcc.domain.service.CursoService;
import br.newtonpaiva.lab.tcc.domain.service.ProfessorService;
import br.newtonpaiva.lab.tcc.api.professores.response.ProfessorResponse;
import br.newtonpaiva.lab.tcc.common.exception.NotFoundException;

public class ProfessorControllerTest {
  @Mock
  private ProfessorService professorService;
  @Mock
  private CursoService cursoService;

  private ProfessorController unit;

  @BeforeEach
  public void setup() {
    unit = new ProfessorController(professorService, cursoService);
  }

  @Test

  void test_getAll() {
    var professores = new ArrayList<Professor>();
    var expected = new ArrayList<ProfessorResponse>();

    for (int i = 0; i < 3; i++) {
      professores.add(new Professor().withId(UUID.randomUUID().toString()));
      expected.add(ProfessorResponse.buildFrom(professores.get(i)));
    }

    Mockito.when(professorService.getProfessores()).thenReturn(professores);
    var result = unit.getAll();

    assertEquals(200, result.getStatusCode().value());
    assertEquals(expected, result.getBody());

    Mockito.verify(professorService).getProfessores();
  }

  void test_getById_withValidId_shouldReturnProfessor() {

    var id = UUID.randomUUID().toString();
    var professor = new Professor().withId(id);

    Mockito.when(professorService.getById(id)).thenReturn(professor);

    var result = unit.getById(Optional.of(id));

    assertEquals(200, result.getStatusCode().value());
    assertEquals(ProfessorResponse.buildFrom(professor), result.getBody());

    Mockito.verify(professorService.getById(id));

  }

  void test_getById_withValidId_shouldThrowNotFoundException() {
    var id = UUID.randomUUID().toString();
    var expected = String.format("%s com ID [%s] não encontrado.", "Professor", id);

    Mockito.when(professorService.getById(id)).thenThrow(new NotFoundException(expected));

    try {
      unit.getById(Optional.of(id));
      fail("Expected NotFoundException");
    } catch (NotFoundException ex) {
      assertEquals(expected, ex.getMessage());
    }

    Mockito.verify(professorService.getById(id));
  }

}