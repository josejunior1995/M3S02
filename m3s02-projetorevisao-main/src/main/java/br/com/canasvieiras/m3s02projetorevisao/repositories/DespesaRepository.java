package br.com.canasvieiras.m3s02projetorevisao.repositories;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
