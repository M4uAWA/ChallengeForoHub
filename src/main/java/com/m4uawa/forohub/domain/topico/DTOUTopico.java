package com.m4uawa.forohub.domain.topico;

public record DTOUTopico(
        
        String titulo,
        String mensaje,
        StatusTopico status,
        int autor,
        int curso) {

}
