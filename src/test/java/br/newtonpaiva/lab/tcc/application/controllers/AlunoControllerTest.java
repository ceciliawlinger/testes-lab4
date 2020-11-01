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

import br.newtonpaiva.lab.tcc.api.alunos.response.AlunoResponse;
import br.newtonpaiva.lab.tcc.common.exception.NotFoundException;
import br.newtonpaiva.lab.tcc.domain.entity.Aluno;
import br.newtonpaiva.lab.tcc.domain.service.AlunoService;
import br.newtonpaiva.lab.tcc.domain.service.CursoService;

@ExtendWith(MockitoExtension.class)
public class AlunoControllerTest {

  @Mock
  private AlunoService alunoService;
  @Mock
  private CursoService cursoService;

  private AlunoController unit;

  @BeforeEach
  public void setup() {
    unit = new AlunoController(alunoService, cursoService);
  }

  @Test

  void test_getAll() {
    var alunos = new ArrayList<Aluno>();
    var expected = new ArrayList<AlunoResponse>();

    for (int i = 0; i < 3; i++) {
      alunos.add(new Aluno().withId(UUID.randomUUID().toString()));
      expected.add(AlunoResponse.buildFrom(alunos.get(i)));
    }

    Mockito.when(alunoService.getAlunos()).thenReturn(alunos);
    var result = unit.getAll();

    assertEquals(200, result.getStatusCode().value());
    assertEquals(expected, result.getBody());

    Mockito.verify(alunoService).getAlunos();

  }

  void test_getById_withValidId_shouldReturnAluno() {

    var id = UUID.randomUUID().toString();
    var aluno = new Aluno().withId(id);

    Mockito.when(alunoService.getById(id)).thenReturn(aluno);

    var result = unit.getById(Optional.of(id));

    assertEquals(200, result.getStatusCode().value());
    assertEquals(AlunoResponse.buildFrom(aluno), result.getBody());

    Mockito.verify(alunoService.getById(id));

  }

  void test_getById_withValidId_shouldThrowNotFoundException() {
    var id = UUID.randomUUID().toString();
    var expected = String.format("%s com ID [%s] não encontrad.", "Aluno", id);

    Mockito.when(alunoService.getById(id)).thenThrow(new NotFoundException(expected));

    try {
      unit.getById(Optional.of(id));
      fail("Expected NotFoundException");
    } catch (NotFoundException ex) {
      assertEquals(expected, ex.getMessage());
    }

    Mockito.verify(alunoService).getById(id);
  }
}