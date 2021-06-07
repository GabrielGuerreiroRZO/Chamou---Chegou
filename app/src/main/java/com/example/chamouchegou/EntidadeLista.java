package com.example.chamouchegou;

import android.widget.TextView;

import androidx.annotation.NonNull;

public class EntidadeLista  {

    private String item_nome;
    private String item_bairro;
    private String item_apelido;

    public EntidadeLista(String nome, String bairro, String apelido){
        this.item_nome = nome;
        this.item_bairro = bairro;
        this.item_apelido = apelido;

    }

    public  String getNome(){ return item_nome; }
    public String getBairro(){return  item_bairro; }
    public String getApelido() {return  item_apelido; }


}
