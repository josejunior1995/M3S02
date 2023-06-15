package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Categoria;
import br.com.canasvieiras.m3s02projetorevisao.entities.Produto;
import br.com.canasvieiras.m3s02projetorevisao.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Produto salvar(Produto produto) throws Exception {

        Produto produtoBanco = new Produto();
        if (produto.getId() != null) {
            produtoBanco = buscarPorId(produto.getId());
        }
        produto.setAlteracao(new Date());

        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new Exception("Nome é obrigatório!");
        }

        if (produto.getCategoria() == null) {
            throw new Exception("Categoria é obrigatória!");
        }
        Categoria categoria = categoriaService.buscarPorId(produto.getCategoria().getId());
        produto.setCategoria(categoria);

        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Preço é obrigatório e precisa ser maior que ZERO!");
        }

        if (produto.getLote() == null) {
            throw new Exception("Lote é obrigatório!");
        }

        produtoBanco.setAlteracao(produto.getAlteracao());
        produtoBanco.setNome(produto.getNome());
        produtoBanco.setDescricao(produto.getDescricao());
        produtoBanco.setCategoria(produto.getCategoria());
        produtoBanco.setPreco(produto.getPreco());
        produtoBanco.setLote(produto.getLote());

        produtoBanco = produtoRepository.save(produtoBanco);
        return produtoBanco;
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) throws Exception {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if (produtoOpt.isEmpty()) {
            throw new Exception("Produto não encontrada!");
        }
        return produtoOpt.get();
    }

    public boolean apagar(Long id) {
        try {
            Produto produto = buscarPorId(id);
            produtoRepository.delete(produto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
