package com.m4uawa.forohub.topico;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="Topico")
@Entity(name= "Topico")
public class Topico {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private int autor;
    private int curso;

    public Topico(DTOTopico data) {
        this.id = null;
        this.autor = data.autor();
        this.curso = data.curso();
        this.fechaCreacion = data.fechaCreacion();
        this.status = data.status();
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();

    }
}
