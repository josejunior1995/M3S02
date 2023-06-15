package br.com.canasvieiras.m3s02projetorevisao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date criacao = new Date();

    @Column(nullable = false)
    private Date alteracao = new Date();

    @Column(length = 30, nullable = false)
    private String nome;

    private String descricao;

}
