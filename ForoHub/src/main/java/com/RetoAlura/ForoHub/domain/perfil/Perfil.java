package com.RetoAlura.ForoHub.domain.perfil;

import com.RetoAlura.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Table(name = "perfil")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "perfiles",fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    public Long getId() {
        return id;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public String getAuthority() {
        return nombre;
    }
}
