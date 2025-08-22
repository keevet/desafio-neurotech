package com.neurotech.desafio.neurotech_desafio.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neurotech.desafio.neurotech_desafio.dto.ProdutoDTO;
import com.neurotech.desafio.neurotech_desafio.dto.ProdutoMinDTO;
import com.neurotech.desafio.neurotech_desafio.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/produtos")
@Validated
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;

    
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Página de produtos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.dto.ProdutoMinDTO.class))),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
            content = @Content(schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.controllers.exceptions.StandardError.class))),
        @ApiResponse(responseCode = "401", description = "Não autenticado")
    })
     @Operation(summary = "01 - Listar produtos")
     @GetMapping
    public Page<ProdutoMinDTO> findAll(
        @Parameter(description = "Filtro por nome (contains, ignore case)") @RequestParam(required = false) String nome,
        @Parameter(description = "Página (>=0)") @RequestParam(defaultValue = "0") @Min(value = 0, message = "page deve ser >= 0") int page,
        @Parameter(description = "Tamanho da página (1..100)") @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
        @Parameter(description = "Campo de ordenação (ex.: preco)") @RequestParam(defaultValue = "preco") String sort,
        @Parameter(description = "Direção (asc|desc)") @RequestParam(defaultValue = "asc") @Pattern(regexp = "(?i)asc|desc", message = "direction deve ser asc ou desc") String direction
    ) {
        return produtoService.findAllPaged(nome, page, size, sort, direction);
    }
    
    @Operation(summary = "02 - Busca produto por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autenticado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
            content = @Content(schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.controllers.exceptions.StandardError.class)))
    })
    @GetMapping(value = "/{id}")
    public ProdutoDTO findById(@PathVariable Long id){

        return produtoService.findById(id);

    }

    @Operation(summary = "03 - Cria um novo produto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado",
            headers = @Header(name = "Location", description = "URL do recurso criado")),
        @ApiResponse(responseCode = "400", description = "JSON inválido/tipo errado",
            content = @Content(schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.controllers.exceptions.StandardError.class))),
        @ApiResponse(responseCode = "401", description = "Não autenticado"),
        @ApiResponse(responseCode = "422", description = "Violação de validação",
            content = @Content(schema = @Schema(implementation = com.neurotech.desafio.neurotech_desafio.controllers.exceptions.ValidationError.class)))
    })
    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO newDto = produtoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newDto.getId())
                        .toUri();
         return ResponseEntity.created(uri).body(newDto);
    }

    @Operation(summary = "04 - Atualiza parcialmente um produto (campos não enviados não são alterados)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualizado"),
        @ApiResponse(responseCode = "400", description = "JSON inválido/tipo errado"),
        @ApiResponse(responseCode = "401", description = "Não autenticado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
        @ApiResponse(responseCode = "422", description = "Violação de validação")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO updatedDto = produtoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "05 - Remove um produto")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Removido"),
        @ApiResponse(responseCode = "401", description = "Não autenticado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
  
}