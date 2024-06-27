package com.RetoAlura.ForoHub.domain.topico;

public record DatosListadoTopico(Long id, String titulo, String mensaje, String fechaCreacion, String status, String autor, String curso) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getStatus(), topico.getAutor().getNombre(),topico.getCurso().getNombre());
    }
}
