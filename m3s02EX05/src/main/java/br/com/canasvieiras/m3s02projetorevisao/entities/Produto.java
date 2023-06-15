package br.com.canasvieiras.m3s02projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date criacao = new Date();

    @Column(nullable = false)
    private Date alteracao = new Date();

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(columnDefinition = "text")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer lote;

}
