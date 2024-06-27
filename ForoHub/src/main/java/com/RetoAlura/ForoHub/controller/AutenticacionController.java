package com.RetoAlura.ForoHub.controller;

import com.RetoAlura.ForoHub.domain.usuario.DatosAutenticacion;
import com.RetoAlura.ForoHub.domain.usuario.Usuario;
import com.RetoAlura.ForoHub.infra.security.DatosJWTToken;
import com.RetoAlura.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    final
    AuthenticationManager authenticationManager;

    final
    TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacion datosAutenticacion){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.nombre(),
                datosAutenticacion.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));

    }
}
