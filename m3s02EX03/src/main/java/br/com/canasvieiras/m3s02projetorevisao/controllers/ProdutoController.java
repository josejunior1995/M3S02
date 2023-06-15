package br.com.canasvieiras.m3s02projetorevisao.controllers;

import br.com.canasvieiras.m3s02projetorevisao.entities.Produto;
import br.com.canasvieiras.m3s02projetorevisao.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok(produtoService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Produto produto) {
        try {
            produto.setId(null);
            return ResponseEntity.ok(produtoService.salvar(produto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            if (id == null) {
                throw new Exception("Id é obrigatório!");
            }
            produto.setId(id);
            return ResponseEntity.ok(produtoService.salvar(produto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.apagar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
