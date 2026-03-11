package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<DatosListaTopico> registrar(@RequestBody @Valid DatosTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var topico = new Topico(datos, usuarioRepository.getReferenceById(Long.valueOf(datos.idUsuario())));
        topicoRepository.save(topico);
        var uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosListaTopico(topico));
    }

    @GetMapping
    public ResponseEntity <Page<DatosListaTopico>> listar(@PageableDefault(size = 10, sort = {"curso"}) Pageable paginacion) {
        var page = topicoRepository.findAllByActivoTrue(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DatosListaTopico> actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizar(datos);

        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();

        return ResponseEntity.noContent().build();
    }

}
