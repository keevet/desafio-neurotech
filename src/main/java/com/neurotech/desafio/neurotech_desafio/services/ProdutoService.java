package com.neurotech.desafio.neurotech_desafio.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neurotech.desafio.neurotech_desafio.dto.ProdutoDTO;
import com.neurotech.desafio.neurotech_desafio.dto.ProdutoMinDTO;
import com.neurotech.desafio.neurotech_desafio.entities.Produto;
import com.neurotech.desafio.neurotech_desafio.repositories.ProdutoRepository;

@Service
public class ProdutoService{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){

        Produto produto = produtoRepository.findById(id).get();

        return new ProdutoDTO(produto);
    }

    public List<ProdutoMinDTO> findAll(){

        List<Produto> result = produtoRepository.findAll();
        return result.stream().map(x -> new ProdutoMinDTO(x)).toList();

    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        entity.setDataCriacao(LocalDateTime.now());
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
    
    Produto entity = produtoRepository.findById(id).orElse(new Produto());

    if (dto.getNome() != null) {
        entity.setNome(dto.getNome());
    }
    if (dto.getDescricao() != null) {
        entity.setDescricao(dto.getDescricao());
    }
    if (dto.getPreco() != null) {
        entity.setPreco(dto.getPreco());
    }
    if (dto.getQuantidadeEstoque() != null) {
        entity.setQuantidadeEstoque(dto.getQuantidadeEstoque());
    }

    entity = produtoRepository.save(entity);
    return new ProdutoDTO(entity);
    }
     
    @Transactional
    public void delete(Long id) {

    produtoRepository.deleteById(id);
    
    }

}