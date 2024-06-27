package com.RetoAlura.ForoHub.controller;


import com.RetoAlura.ForoHub.domain.curso.DatosRespuestaCurso;
import com.RetoAlura.ForoHub.domain.topico.*;
import com.RetoAlura.ForoHub.domain.usuario.DatosRespuestaUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topico")
public class TopicoController {
    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> nuevoTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),
                topico.getMensaje(),topico.getFechaCreacion(),new DatosRespuestaUsuario(topico.getAutor().getNombre(),
                topico.getAutor().getCorreo()),new DatosRespuestaCurso(topico.getCurso().getNombre(),topico.getCurso().getCategoria()));
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 5) Pageable pag){
        return ResponseEntity.ok(topicoRepository.findAll(pag).map(DatosListadoTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico=topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),new DatosRespuestaUsuario(topico.getAutor().getNombre(),topico.getAutor().getCorreo()),
                new DatosRespuestaCurso(topico.getCurso().getNombre(),topico.getCurso().getCategoria())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.delete(topicoOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoId(@PathVariable Long id){
        Topico topico=topicoRepository.getReferenceById(id);
        var datosTopico=new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),new DatosRespuestaUsuario(topico.getAutor().getNombre(),topico.getAutor().getCorreo()),
                new DatosRespuestaCurso(topico.getCurso().getNombre(),topico.getCurso().getCategoria()));
        return ResponseEntity.ok(datosTopico);
    }
}
