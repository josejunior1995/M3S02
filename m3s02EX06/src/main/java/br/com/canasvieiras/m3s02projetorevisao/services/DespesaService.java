package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import br.com.canasvieiras.m3s02projetorevisao.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa salvar(Optional<Despesa> despesa) {
        return despesaRepository.save(despesa);
    }

    public List<Despesa> buscarTodas() {
        return despesaRepository.findAll();
    }

    public List<Despesa> buscarPorStatus(String status) {
        return despesaRepository.findByStatus(status);
    }

    public Optional<Despesa> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }

    public boolean apagar(Long id) {
        try {
            despesaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Despesa pagarDespesa(Long id) throws Exception {
        Optional<Despesa> despesaOpt = despesaRepository.findById(id);
        if (despesaOpt.isEmpty()) {
            throw new Exception("Despesa não encontrada!");
        }

        Despesa despesa = despesaOpt.get();
        if (despesa.getStatus().equals("Pago")) {
            throw new Exception("A despesa já está paga e não pode ser alterada!");
        }

        despesa.setDataPagamento(LocalDate.now());
        despesa.setStatus("Pago");

        return despesaRepository.save(despesa);

    }
    public void estornarDespesa(Despesa despesa) throws Exception {
        if (despesa.getStatus().equals("Pago")) {
            despesa.setDataPagamento(null);
            despesa.setStatus("Pendente");
            despesaRepository.save(despesa);
        } else {
            throw new Exception("Apenas despesas pagas podem ser estornadas.");
        }
    }

}
