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

    public ProdutoDTO(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

   

    



}