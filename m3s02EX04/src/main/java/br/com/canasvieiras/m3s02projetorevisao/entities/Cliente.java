package br.com.canasvieiras.m3s02projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date criacao = new Date();

    @Column(nullable = false)
    private Date alteracao = new Date();

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 18, nullable = false)
    private String documento;

    @ManyToOne
    private Endereco enderecoPadrao;

}
