package com.neurotech.desafio.neurotech_desafio.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.neurotech.desafio.neurotech_desafio.entities.Produto;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private LocalDateTime dataCriacao;

    public ProdutoDTO(Produto entity){

        BeanUtils.copyProperties(entity, this);

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    



}