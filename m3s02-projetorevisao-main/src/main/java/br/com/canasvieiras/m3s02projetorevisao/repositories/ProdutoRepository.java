package br.com.canasvieiras.m3s02projetorevisao.repositories;

import br.com.canasvieiras.m3s02projetorevisao.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
