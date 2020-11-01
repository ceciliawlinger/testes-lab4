package br.newtonpaiva.lab.tcc.domain.service;

import static org.junit.Assert.fail;
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

import br.newtonpaiva.lab.tcc.common.exception.NotFoundException;
import br.newtonpaiva.lab.tcc.domain.entity.Aluno;
import br.newtonpaiva.lab.tcc.domain.repo.AlunoRepository;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceImplTest {

    @Mock
    private AlunoRepository repo;

    private AlunoServiceImpl unit;

    @BeforeEach
    public void setup() {
        unit = new AlunoServiceImpl(repo);
    }

    @Test
    public void testGetById_withValidId_shouldReturnObjectSuccessfully() {
        // given
        String id = UUID.randomUUID().toString();
        Aluno a = new Aluno();
        a.setId(id);

        // mock definitions
        when(repo.findById(id)).thenReturn(Optional.of(a));

        // test
        Aluno result = unit.getById(id);

        // assert
        assertEquals(a, result);

        // verify
        verify(repo).findById(id);
    }

}
