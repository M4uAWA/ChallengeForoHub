package com.m4uawa.forohub.topico;

import java.time.LocalDateTime;

public record DTOTopico(
        
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        int autor,
        int curso) {

}
