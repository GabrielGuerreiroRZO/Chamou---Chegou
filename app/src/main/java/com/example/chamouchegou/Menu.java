package com.example.chamouchegou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button BotaoCalcular;
    private Button BotaoCadastrar;
    private Button BotaoLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BotaoCalcular = findViewById(R.id.BotaoCalcular);
        BotaoCalcular.setOnClickListener(ClickCalcular);
        BotaoCadastrar = findViewById(R.id.BotaoCadastrar);
        BotaoCadastrar.setOnClickListener(ClickCadastrar);
        BotaoLista = findViewById(R.id.BotaoLista);
        BotaoLista.setOnClickListener(ClickLista);

    }

    View.OnClickListener ClickCalcular = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Menu.this, Calcular.class); // Ativa o botao para segunda tela.
            startActivity(intent); // Abre a janela.
        }
    };

    View.OnClickListener ClickCadastrar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Menu.this, Cadastro.class); // Ativa o botao para segunda tela.
            startActivity(intent); // Abre a janela.
        }
    };

    View.OnClickListener ClickLista = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Menu.this, ListaBancoActivity.class); // Ativa o botao para segunda tela.
            startActivity(intent); // Abre a janela.
        }
    };


}


