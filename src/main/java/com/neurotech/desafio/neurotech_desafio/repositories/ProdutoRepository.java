package com.neurotech.desafio.neurotech_desafio.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neurotech.desafio.neurotech_desafio.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}