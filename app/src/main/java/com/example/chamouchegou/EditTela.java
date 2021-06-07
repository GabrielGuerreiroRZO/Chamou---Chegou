package com.example.chamouchegou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditTela  extends AppCompatActivity {
        private static final String TAG = "EditTela";
        private Button BotaoSalvar, BotaoDeletar;
        private EditText edit_Nome;
        BancoHelper mBancoHelper;
        private String selectdName,selectdBairro,selectdApelido;
        private int selectedID;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edittela_layout);
            BotaoSalvar = findViewById(R.id.BotaoSalvar);
            BotaoDeletar= findViewById(R.id.BotaoDeletar);
            edit_Nome = findViewById(R.id.edit_Nome);
            mBancoHelper = new BancoHelper(this);

            Intent receiveIntent = getIntent();
            selectedID = receiveIntent.getIntExtra("id",-1);
            selectdName = receiveIntent.getStringExtra("name");
            edit_Nome.setText(selectdName);

            BotaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item = edit_Nome.getText().toString();
                    if (!item.equals("")){
                        mBancoHelper.updateName(item,selectedID,selectdName);

                    }else {
                        Toast.makeText(getApplicationContext(), "Falha. Nenhum nome informado. ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            BotaoDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBancoHelper.deleteName(selectedID,selectdName,selectdBairro,selectdApelido);
                    edit_Nome.setText("");
                    Toast.makeText(getApplicationContext(), "Dados Removidos !  ", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
