package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.helper.cidade.CidadesQuery;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQuery {
	public List<Cidade> findByEstadoCodigoOrderByNomeAsc(Long codigoEstado);
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
}
