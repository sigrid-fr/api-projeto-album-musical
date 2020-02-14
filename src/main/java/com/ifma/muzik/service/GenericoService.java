package com.ifma.muzik.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

class GenericoService<T> {

    private final JpaRepository<T, Integer> repository;

    GenericoService(JpaRepository<T, Integer> repository ) {
        this.repository = repository;
    }


    T salva(T entity) {
        return repository.save(entity);
    }


    List<T> buscaTodasAsEntities() {
        return repository.findAll();
    }


    T atualiza(T entity, Integer id) {
        T entityDoBanco = this.buscaPor(id );
        BeanUtils.copyProperties(entity, entityDoBanco, "id" );
        this.salva(entityDoBanco );

        return entityDoBanco;
    }


    T buscaPor(Integer id) {
        return repository.findById(id )
                         .orElseThrow(
                                 () ->new EmptyResultDataAccessException(1) );
    }

    public void excluir(Integer id) {
        this.repository.deleteById(id );
    }
}
