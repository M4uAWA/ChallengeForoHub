package com.m4uawa.forohub.domain.topico;

import java.time.LocalDateTime;

public record DTODTopico(
    
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    StatusTopico status,
    int autor,
    int curso
) {
    public DTODTopico(Topico topico){
        this(topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getFechaCreacion(),
        topico.getStatus(),
        topico.getAutor(),
        topico.getCurso());
    }
    
}
