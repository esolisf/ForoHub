package com.RetoAlura.ForoHub.domain.topico;

import com.RetoAlura.ForoHub.domain.curso.Curso;
import com.RetoAlura.ForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String status,

        @NotNull
        Usuario autor,

        @NotNull
        Curso curso
) {
}
