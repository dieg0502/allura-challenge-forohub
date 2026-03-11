package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosTopico(
        String titulo,
        String mensaje,
        @JsonAlias("nombreCurso") String curso,
        Integer idUsuario
) {
    public DatosTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getCurso(), topico.getUsuario().getId().intValue());
    }
}
