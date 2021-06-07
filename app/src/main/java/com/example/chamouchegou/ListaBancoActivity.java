package com.example.chamouchegou;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListaBancoActivity extends AppCompatActivity {
    private static final String TAG = "ListaBancoActivity";
    BancoHelper mBancoHelper;
    private ListView mListaBanco;
    private ArrayList<EntidadeLista> listBanco = new ArrayList<EntidadeLista>();
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListaBanco = (ListView) findViewById(R.id.ListaBanco);
        listBanco = new ArrayList<>();

        //adaptadorLista = new AdaptadorLista(this, );

        mBancoHelper = new BancoHelper(this);
        Cursor result = mBancoHelper.getData();
        this.getData(result);

        mListaBanco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = mListaBanco.getItemAtPosition(position).toString();
                Toast.makeText(ListaBancoActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // ALT + Ctrl + L

    private void getData(Cursor result) {
        Cursor cursor = result;
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nenhum dado para exibir", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                // O que tem na posicao 1, 2 e 3 ? nome, bairro e apelido,
                listBanco.add(new EntidadeLista(cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            }

            // COmo eu importo o layout ?ele nao ta aprecendo la, faz assim, copia o que ta nele e joga no simple list
            // Deixe de maluquice kkk, funciona boto fér kkkk
            adapter = new ArrayAdapter(this, R.layout.single_lista, R.id.item_nome, listBanco) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView textNome = (TextView) view.findViewById(R.id.item_nome);
                    TextView textBairro = (TextView) view.findViewById(R.id.item_bairro);
                    TextView textApelido = (TextView) view.findViewById(R.id.item_apelido);
// DEUS, funcionou,aulas, kkkk boaa - Vou tentar jogar no seu layout lá, brocou. ta lindo ja, pegando o layout la e parece que parou de duplicar
                    // Tu fez oq ai q ta dando erro de comprimento ? foi tava cadastrando, duplicando ainda. Pelo console, ele registrou dois itens msm
                    // Olha lá o numero 6, tava em 4
                    textNome.setText(listBanco.get(position).getNome());
                    textBairro.setText(listBanco.get(position).getBairro());
                    textApelido.setText(listBanco.get(position).getApelido());

                    return view;
                }
            };

            mListaBanco.setAdapter(adapter);
            // Testa ai
        }
        /**
         * Onde vc ta cadastrando aq ? la em banco Helper
         */
        Log.d("Lista de Entidades", String.valueOf(listBanco.size()));
    }


    //private void populateListaBanco() {
    //Log.d(TAG, "populateListaBanco: Displaying data in the ListView.");
    //  Cursor data = mBancoHelper.getData();
    //  ArrayList<String> listBanco = new ArrayList<>();
    //  while (data.moveToNext()) {
    //    listBanco.add(data.getString(1));
    //    listBanco.add(data.getString(2));
    //  listBanco.add(data.getString(3));
    //}
    // ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listBanco);
    // mListaBanco.setAdapter(adapter);
    // mListaBanco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //   @Override
    // public void onItemClick(AdapterView<?> adapterView, View view, int i, long _id) {
    //   String name = adapterView.getItemAtPosition(i).toString();
    // Log.d(TAG, "onItemClick: Voce clickou em " + name);
    // Cursor data = mBancoHelper.getData(name);
    // int itemID = -1;
    // while (data.moveToNext()){
    // itemID = data.getInt(0);
    // }
    // if (itemID > -1) {
    // Log.d(TAG, "onItemClick: The ID é: " + itemID);
    // Intent intentEdit = new Intent(ListaBancoActivity.this, MapaGoogle.class);
    // intentEdit.putExtra("id",itemID);
    // intentEdit.putExtra("name",name);
    // startActivity(intentEdit);
    // }else {
    //  Toast.makeText(getApplicationContext(), "Nenhum ID associado.  ", Toast.LENGTH_SHORT).show();
    // }

    //}
    // });
    // }
}


