package com.example.chamouchegou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    BancoHelper  banco;
    private ConstraintLayout TelaCadastro;
    private Button botaoSalvar;
    private EditText CampoNome;
    private EditText CampoBairro;
    private EditText CampoApelido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        banco = new BancoHelper(this);
        setContentView(R.layout.activity_cadastro);
        TelaCadastro = findViewById(R.id.TelaCadastro);

        botaoSalvar = findViewById(R.id.BotaoSalvar);
        CampoNome = findViewById(R.id.CampoNome);
        CampoBairro = findViewById(R.id.CampoBairro);
        CampoApelido = findViewById(R.id.CampoApelido);
        AddData();
    }


    public void AddData(){

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PegaApelido = CampoApelido.getText().toString();

                if ((CampoNome.getText().toString().isEmpty())){
                    CampoNome.setError("Nome nao informado");
                    return;
                }


                if ((CampoApelido.getText().toString().isEmpty())){
                    CampoApelido.setError("Apelido nao informado");
                    return;
                }


                if ((CampoBairro.getText().toString().isEmpty())){
                    CampoBairro.setError("Telefone nao informado");
                    return;
                }

                boolean isInserted = banco.addData(CampoNome.getText().toString(), CampoBairro.getText().toString(), CampoApelido.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(getApplicationContext(), "Cadastro salvo com sucesso! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Cadastro.this, Menu.class);
                    Bundle info = new Bundle();
                    info.putString("CampoApelido", PegaApelido);
                    intent.putExtras(info);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Falha. Cadastro nao foi salvo ", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }



    }

