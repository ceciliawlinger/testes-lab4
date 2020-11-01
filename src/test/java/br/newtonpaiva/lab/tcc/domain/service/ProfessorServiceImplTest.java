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

import br.newtonpaiva.lab.tcc.domain.entity.Professor;
import br.newtonpaiva.lab.tcc.domain.repo.ProfessorRepository;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceImplTest {
  @Mock
  private ProfessorRepository repo;
  private ProfessorServiceImpl unit;

  @BeforeEach
  public void setup() {
    unit = new ProfessorServiceImpl();
  }

  @Test
  public void testGetById_withValidId_shouldReturnObjectSuccessfully() {
    // given
    String id = UUID.randomUUID().toString();
    Professor p = new Professor();
    p.setId(id);

    // mock definitions
    when(repo.findById(id)).thenReturn(Optional.of(p));

    // test
    Professor result = unit.getById(id);

    // assert
    assertEquals(p, result);

    // verify
    verify(repo).findById(id);
  }
}