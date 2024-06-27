package com.RetoAlura.ForoHub.domain.topico;

import com.RetoAlura.ForoHub.domain.curso.DatosRespuestaCurso;
import com.RetoAlura.ForoHub.domain.usuario.DatosRespuestaUsuario;

import java.util.Date;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, Date fechaCreacion, DatosRespuestaUsuario autor,
                                   DatosRespuestaCurso curso) {
}
