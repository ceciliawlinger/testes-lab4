package br.newtonpaiva.lab.tcc.api.professores.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Data
@With
public class ProfessorRequest {

    private String matricula;

    private String nomeCompleto;

    private String formação;

    private String cursoId;

    public String getCursoId() {
        return null;
    }
}
