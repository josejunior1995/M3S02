package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Categoria;
import br.com.canasvieiras.m3s02projetorevisao.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) throws Exception {

        Categoria categoriaBanco = new Categoria();
        if (categoria.getId() != null) {
            categoriaBanco = buscarPorId(categoria.getId());
        }
        categoria.setAlteracao(new Date());

        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new Exception("Nome é obrigatório!");
        }

        categoriaBanco.setAlteracao(categoria.getAlteracao());
        categoriaBanco.setNome(categoria.getNome());
        categoriaBanco.setDescricao(categoria.getDescricao());

        categoriaBanco = categoriaRepository.save(categoriaBanco);
        return categoriaBanco;
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) throws Exception {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isEmpty()) {
            throw new Exception("Categoria não encontrada!");
        }
        return categoriaOpt.get();
    }

    public boolean apagar(Long id) {
        try {
            Categoria categoria = buscarPorId(id);
            categoriaRepository.delete(categoria);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
