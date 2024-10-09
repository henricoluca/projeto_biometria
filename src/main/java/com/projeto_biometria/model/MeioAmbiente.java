package com.projeto_biometria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Template de Meio Ambiente - Atributos da tabela do banco de dados
@Entity
public class MeioAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String nivel_acesso_requerido;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNivel_acesso_requerido() {
        return nivel_acesso_requerido;
    }

    public void setNivel_acesso_requerido(String nivelAcessoRequerido) {
        this.nivel_acesso_requerido = nivelAcessoRequerido;
    }
}
