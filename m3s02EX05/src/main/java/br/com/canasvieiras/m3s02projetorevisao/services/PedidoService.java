package br.com.canasvieiras.m3s02projetorevisao.services;

import br.com.canasvieiras.m3s02projetorevisao.entities.Pedido;
import br.com.canasvieiras.m3s02projetorevisao.entities.PedidoItem;
import br.com.canasvieiras.m3s02projetorevisao.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public Pedido criar(Pedido pedido) throws Exception {

        pedido.setId(null);
        pedido.setCriacao(new Date());
        pedido.setAlteracao(new Date());

        pedido.setNumeroPedido("PE-novo");
        pedido.setStatus("PE");

        if (pedido.getCliente() == null) {
            throw new Exception("Cliente é obrigatório!");
        }
        pedido.setCliente(clienteService.buscarPorId(pedido.getCliente().getId()));

        if (pedido.getCliente().getEnderecoPadrao() == null) {
            throw new Exception("Cliente sem endereço!");
        }
        pedido.setEndereco(pedido.getCliente().getEnderecoPadrao());

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new Exception("Itens são obrigatórios!");
        }

        pedido.setTotalItens(BigDecimal.ZERO);
        for (PedidoItem item : pedido.getItens()) {
            item.setPedido(pedido);
            if (item.getProduto() == null) {
                throw new Exception("Produto é obrigatório!");
            }
            item.setProduto(produtoService.buscarPorId(item.getProduto().getId()));

            if (item.getQuantidade() == null || item.getQuantidade() == 0) {
                throw new Exception("Quantidade é obrigatória!");
            }

            item.setValorUnitario(item.getProduto().getPreco());
            item.setValorTotal(item.getValorUnitario().multiply(new BigDecimal(item.getQuantidade())));

            pedido.setTotalItens(pedido.getTotalItens().add(item.getValorTotal()));
        }

        pedido.setTotalFrete(new BigDecimal(10));

        pedido.setTotalPedido(pedido.getTotalItens().add(pedido.getTotalFrete()));

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

}
