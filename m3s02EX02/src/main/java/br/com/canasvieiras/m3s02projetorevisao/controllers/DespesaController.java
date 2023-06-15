package br.com.canasvieiras.m3s02projetorevisao.controllers;

import br.com.canasvieiras.m3s02projetorevisao.entities.Despesa;
import br.com.canasvieiras.m3s02projetorevisao.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
