package com.aluracursos.forohub.topico;

import com.aluracursos.forohub.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String curso;
    private LocalDateTime fechaCreacion;
    @ManyToOne()
    private Usuario usuario;

    public Topico(DatosTopico datos, Usuario usuario) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.curso = datos.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
    }

    public Topico(@Valid DatosTopico datos) {
    }
}
