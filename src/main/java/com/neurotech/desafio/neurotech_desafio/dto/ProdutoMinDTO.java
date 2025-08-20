package com.neurotech.desafio.neurotech_desafio.dto;

import com.neurotech.desafio.neurotech_desafio.entities.Produto;

public class ProdutoMinDTO{

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoMinDTO(Produto produto){

        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }



}