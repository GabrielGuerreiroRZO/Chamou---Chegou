package com.example.chamouchegou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button BotaoIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BotaoIniciar = findViewById(R.id.BotaoIniciar);
        BotaoIniciar.setText("Iniciar");
        BotaoIniciar.setOnClickListener(ClickIniciar);

    }

    View.OnClickListener ClickIniciar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Menu.class); // Ativa o botao para segunda tela.
            startActivity(intent); // Abre a janela.
        }
    };
}