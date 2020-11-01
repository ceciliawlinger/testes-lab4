package br.newtonpaiva.lab.tcc.api.alunos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class AlunoRequest {

    private String matricula;

    private String anoSemestreDeEntrada;

    private String nomeCompleto;

    private String cursoId;
}
