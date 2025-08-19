package com.m4uawa.forohub.domain.topico;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="Topico")
@Entity(name= "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private int autor;
    private int curso;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (status == null) {
            status = StatusTopico.ABIERTO;
        }
    }

    public Topico(DTORTopico data) {
        this.id = null;
        this.autor = data.autor();
        this.curso = data.curso();
        this.fechaCreacion = data.fechaCreacion();
        this.status = data.status();
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();

    }

    public void updateData(DTOUTopico data) {
        if(data.titulo() != null && !data.titulo().equals("")){
            this.titulo = data.titulo();
        }
        if(data.mensaje() != null && !data.mensaje().equals("")){
            this.mensaje = data.mensaje();
        }
        if(data.autor() > 0){
            this.autor = data.autor();
        }
        if(data.curso() > 0){
            this.curso = data.curso();
        }
        if(data.status() != null){
            this.status = data.status();
        }

    }
}
