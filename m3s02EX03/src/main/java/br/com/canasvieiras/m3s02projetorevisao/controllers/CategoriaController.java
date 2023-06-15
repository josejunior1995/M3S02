package br.com.canasvieiras.m3s02projetorevisao.controllers;

import br.com.canasvieiras.m3s02projetorevisao.entities.Categoria;
import br.com.canasvieiras.m3s02projetorevisao.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok(categoriaService.buscarTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Categoria categoria) {
        try {
            categoria.setId(null);
            return ResponseEntity.ok(categoriaService.salvar(categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            if (id == null) {
                throw new Exception("Id é obrigatório!");
            }
            categoria.setId(id);
            return ResponseEntity.ok(categoriaService.salvar(categoria));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.apagar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
