package com.neurotech.desafio.neurotech_desafio.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação", description = "Realizar autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired private AuthenticationManager authManager;
  @Autowired private JwtService jwtService;

  @Operation(summary = "Autentica e retorna JWT")
  @ApiResponses({
  @ApiResponse(responseCode = "200", description = "OK", content = @Content(
      schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.auth.LoginResponse.class))),
  @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
    String token = jwtService.generateToken(auth.getName());
    return ResponseEntity.ok(new LoginResponse(token));
  }
}
