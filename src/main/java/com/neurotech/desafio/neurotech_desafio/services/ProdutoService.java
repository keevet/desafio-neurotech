package com.neurotech.desafio.neurotech_desafio.services;

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
}