package br.newtonpaiva.lab.tcc.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.newtonpaiva.lab.tcc.domain.entity.Curso;
import br.newtonpaiva.lab.tcc.domain.repo.CursoRespository;

@ExtendWith(MockitoExtension.class)
public class CursosServiceImplTest {

  @Mock
  private CursoRespository repo;
  private CursoServiceImpl unit;

  @BeforeEach
  public void setup() {
    unit = new CursoServiceImpl(repo);
  }

  @Test
  public void testGetById_withValidId_shouldReturnObjectSuccessfully() {
    // given
    String id = UUID.randomUUID().toString();
    Curso c = new Curso();
    c.setId(id);

    // mock definitions
    when(repo.findById(id)).thenReturn(Optional.of(c));

    // test
    Curso result = unit.getById(id);

    // assert
    assertEquals(c, result);

    // verify
    verify(repo).findById(id);
  }

}