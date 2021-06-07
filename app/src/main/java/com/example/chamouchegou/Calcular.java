package com.example.chamouchegou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calcular extends AppCompatActivity {
    private Button BotaoSomar;
    private Button BotaoSubtrair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        BotaoSomar = findViewById(R.id.BotaoSomar);
        BotaoSomar.setOnClickListener(ClickBotaoSomar);
        BotaoSubtrair = findViewById(R.id.BotaoSubtrair);
        BotaoSubtrair.setOnClickListener(ClickBotaoSubtrair);
    }

    public View.OnClickListener ClickBotaoSubtrair = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText ReceberNumero1 = findViewById(R.id.ReceberNumero1);
            EditText ReceberNumero2 = findViewById(R.id.ReceberNumero2);

            if ((ReceberNumero1.getText().toString().isEmpty())){
                ReceberNumero1.setError("Numero nao informado");
                return;
            }
            if ((ReceberNumero2.getText().toString().isEmpty())){
                ReceberNumero2.setError("Numero nao informado");
                return;
            }

            int n1 = Integer.parseInt(ReceberNumero1.getText().toString());
            int n2 = Integer.parseInt(ReceberNumero2.getText().toString());
            int subtrair = n1 - n2;
            String MsgResultado = "Resultado: " + subtrair;
            Toast.makeText(Calcular.this, "Resultado: " + subtrair, Toast.LENGTH_SHORT).show();
            TextView TvResultado = findViewById(R.id.ExibiResultado);
            TvResultado.setText(MsgResultado);

        }
    };

    public View.OnClickListener ClickBotaoSomar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText ReceberNumero1 = findViewById(R.id.ReceberNumero1);
            EditText ReceberNumero2 = findViewById(R.id.ReceberNumero2);

            if (TextUtils.isEmpty(ReceberNumero1.getText().toString())){
                ReceberNumero1.setError("Numero nao informado");
                return;
            }
            if (TextUtils.isEmpty(ReceberNumero2.getText().toString())){
                ReceberNumero2.setError("Numero nao informado");
                return;
            }

            int n1 = Integer.parseInt(ReceberNumero1.getText().toString());
            int n2 = Integer.parseInt(ReceberNumero2.getText().toString());
            int soma = n1 + n2;

            String MsgResultado = "Resultado: " + soma;
            Toast.makeText(Calcular.this, "Resultado: " + soma, Toast.LENGTH_SHORT).show();
            TextView TvResultado = findViewById(R.id.ExibiResultado);
            TvResultado.setText(MsgResultado);


        }
    };

}