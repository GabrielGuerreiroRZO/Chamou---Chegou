package com.example.chamouchegou;

import androidx.annotation.NonNull;

public class Tarefa {
    private String descricao;
    private int prioridade;


    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;


    }

    public Tarefa(String toString) {

    }

    public String getDescricao() {
        return descricao;
    }



}
