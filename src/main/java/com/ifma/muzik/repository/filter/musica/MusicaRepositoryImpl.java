package com.ifma.muzik.repository.filter.musica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.ifma.muzik.model.Musica;
import com.ifma.muzik.repository.filter.MusicaFiltro;

public class MusicaRepositoryImpl implements MusicaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Musica> filtrar(MusicaFiltro filtro) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);

		Root<Musica> produtoRoot = cq.from(Musica.class);

		Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot);

		cq.select(produtoRoot).where(restricoes).orderBy(cb.asc(produtoRoot.get("nome")));

		return manager.createQuery(cq).getResultList();
	}

	private Predicate[] criaRestricoes(MusicaFiltro filtro, CriteriaBuilder cb, Root<Musica> produtoRoot) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(filtro.getNome())) {
			predicates.add(cb.like(produtoRoot.get("nome"), "%" + filtro.getNome() + "%"));
		}

		if (filtro.getDuracaoDe() != null) {
			predicates.add(cb.ge(produtoRoot.get("duracaoDe"), filtro.getDuracaoDe()));
		}

		if (filtro.getDuracaoAte() != null) {
			predicates.add(cb.le(produtoRoot.get("duracaoAte"), filtro.getDuracaoAte()));
		}

		if (filtro.getAlbumId() != null) {
			Path<Integer> categoriaPath = produtoRoot.join("albums").<Integer>get("id");

			predicates.add(cb.equal(categoriaPath, filtro.getAlbumId()));
		}

		if (filtro.getArtistaId() != null) {
			Path<Integer> categoriaPath = produtoRoot.join("artistas").<Integer>get("id");

			predicates.add(cb.equal(categoriaPath, filtro.getArtistaId()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	// --------------------------- Com paginação
	// -----------------------------------------

	@Override
	public Page<Musica> filtrar(MusicaFiltro filtro, Pageable pageable) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);

		Root<Musica> produtoRoot = cq.from(Musica.class);

		Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot);

		TypedQuery<Musica> query = manager.createQuery(cq);
		adicionaRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(filtro));
	}

	private void adicionaRestricoesDePaginacao(TypedQuery<Musica> query, Pageable pageable) {
		Integer paginaAtual = pageable.getPageNumber();
		Integer totalObjetosPorPagina = pageable.getPageSize();
		Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

		// 0 a n-1, n - (2n -1), ...
		query.setFirstResult(primeiroObjetoDaPagina);
		query.setMaxResults(totalObjetosPorPagina);

	}

	private Long total(MusicaFiltro filtro) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Musica> rootProduto = cq.from(Musica.class);

		Predicate[] predicates = criaRestricoes(filtro, cb, rootProduto);
		cq.where(predicates);

		cq.select(cb.count(rootProduto));

		return manager.createQuery(cq).getSingleResult();
	}

}
