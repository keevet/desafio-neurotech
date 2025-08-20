package com.neurotech.desafio.neurotech_desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    

}