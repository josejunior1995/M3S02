package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import br.com.canasvieiras.m3s02projetorevisao.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa lançarDespesa(Despesa despesa) {
        despesa.setStatus("Pendente");
        despesa.setDataPagamento(null);
        return despesaRepository.save(despesa);
    }
}
