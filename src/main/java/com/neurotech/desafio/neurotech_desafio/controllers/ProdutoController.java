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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "/produtos")
@Validated
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;

    
     @GetMapping
    public Page<ProdutoMinDTO> findAll(
        @RequestParam(required = false) String nome,
        @RequestParam(defaultValue = "0") @Min(value = 0, message = "page deve ser >= 0") int page,
        @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
        @RequestParam(defaultValue = "preco") String sort,
        @RequestParam(defaultValue = "asc") @Pattern(regexp = "(?i)asc|desc", message = "direction deve ser asc ou desc") String direction
    ) {
        return produtoService.findAllPaged(nome, page, size, sort, direction);
    }
    
    @GetMapping(value = "/{id}")
    public ProdutoDTO findById(@PathVariable Long id){

        return produtoService.findById(id);

    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO newDto = produtoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newDto.getId())
                        .toUri();
         return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO updatedDto = produtoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
  
}