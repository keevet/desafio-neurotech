package com.neurotech.desafio.neurotech_desafio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neurotech.desafio.neurotech_desafio.dto.ProdutoDTO;
import com.neurotech.desafio.neurotech_desafio.dto.ProdutoMinDTO;
import com.neurotech.desafio.neurotech_desafio.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoMinDTO> findAll(){

        return produtoService.findAll();

    }
    
    @GetMapping(value = "/{id}")
    public ProdutoDTO findById(@PathVariable Long id){

        return produtoService.findById(id);

    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto) {
        ProdutoDTO newDto = produtoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newDto.getId())
                        .toUri();
         return ResponseEntity.created(uri).body(newDto);
    }

  
}