package br.com.canasvieiras.m3s02projetorevisao.controllers;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import br.com.canasvieiras.m3s02projetorevisao.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Despesa> lançarDespesa(@RequestBody Despesa despesa) {
        try {
            Despesa novaDespesa = despesaService.lançarDespesa(despesa);
            return ResponseEntity.ok(novaDespesa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/despesas/{id}")
    public ResponseEntity<String> alterarDespesa(@PathVariable Long id, @RequestBody Despesa despesaAtualizada) {
        try {
            Despesa despesaExistente = despesaService.buscarPorId(id);

            if (despesaExistente.getStatus().equals("Pago")) {
                return ResponseEntity.badRequest().body("A despesa não pode ser alterada porque está com status 'Pago'.");
            }


            despesaExistente.setCredor(despesaAtualizada.getCredor());
            despesaExistente.setDataVencimento(despesaAtualizada.getDataVencimento());
            despesaExistente.setValor(despesaAtualizada.getValor());
            despesaExistente.setDescricao(despesaAtualizada.getDescricao());


            Despesa despesaAtualizada = despesaService.salvar(despesaExistente);

            return ResponseEntity.ok("Despesa alterada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao alterar a despesa.");
        }
    }
    @GetMapping("/despesas")
    public ResponseEntity<List<Despesa>> consultarTodasDespesas() {
        try {
            List<Despesa> despesas = despesaService.buscarTodas();
            return ResponseEntity.ok(despesas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/status/{status}")
    public List<Despesa> buscarPorStatus(@PathVariable String status) {
        return despesaService.buscarPorStatus(status);
    }
    @PostMapping("/{id}/pagar")
    public ResponseEntity<String> pagarDespesa(@PathVariable Long id) {
        Despesa despesa = despesaService.buscarPorId(id);

        if (despesa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Despesa não encontrada");
        }

        if (despesa.getStatus().equals("Pago")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A despesa já está paga");
        }

        despesa.setDataPagamento(LocalDate.now());
        despesa.setStatus("Pago");

        despesaService.salvar(despesa);

        return ResponseEntity.ok("Despesa paga com sucesso");
    }

}
