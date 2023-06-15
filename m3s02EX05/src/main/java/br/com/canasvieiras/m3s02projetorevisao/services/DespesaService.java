package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import br.com.canasvieiras.m3s02projetorevisao.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;
    public List<Despesa> buscarTodas() {
        return despesaRepository.findAll();
    }
    public Despesa lançarDespesa(Despesa despesa) {
        despesa.setStatus("Pendente");
        despesa.setDataPagamento(null);
        return despesaRepository.save(despesa);
    }

    public Despesa buscarPorId(Long id) {
        return despesaRepository.findById(id).orElse(null);
    }

    public List<Despesa> buscarPorStatus(String status) {
        return despesaRepository.findByStatus(status);
    }

    public Despesa salvar(Despesa despesa) {
        return despesaRepository.save(despesa);
    }
}
