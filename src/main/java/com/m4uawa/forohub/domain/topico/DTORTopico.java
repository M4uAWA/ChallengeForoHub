package com.m4uawa.forohub.domain.topico;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DTORTopico(
        
        @NotBlank String titulo,
        @NotBlank String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        @Positive int autor,
        @Positive int curso) {

}
