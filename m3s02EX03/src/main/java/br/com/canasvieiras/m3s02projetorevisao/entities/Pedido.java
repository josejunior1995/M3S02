package br.com.canasvieiras.m3s02projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date criacao = new Date();

    @Column(nullable = false)
    private Date alteracao = new Date();

    @Column(nullable = false)
    private String numeroPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Endereco endereco;

    @Column(length = 2, nullable = false)
    private String status = "PE";

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalItens;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalFrete;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal totalPedido;

    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PedidoItem> itens = new ArrayList<>();

}
