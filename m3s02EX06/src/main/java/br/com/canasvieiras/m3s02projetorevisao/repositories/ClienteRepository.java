package br.com.canasvieiras.m3s02projetorevisao.repositories;

import br.com.canasvieiras.m3s02projetorevisao.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
