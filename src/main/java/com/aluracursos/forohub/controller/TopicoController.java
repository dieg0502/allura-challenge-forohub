package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.topico.DatosTopico;
import com.aluracursos.forohub.topico.Topico;
import com.aluracursos.forohub.topico.TopicoRepository;
import com.aluracursos.forohub.usuario.Usuario;
import com.aluracursos.forohub.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosTopico datos, UriComponentsBuilder uriBuilder) {
        var topico = new Topico(datos);
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(datos.idUsuario()).toUri();

        return ResponseEntity.created(uri).body(new DatosTopico(topico));
    }
    public void eliminar() {

    }

}
