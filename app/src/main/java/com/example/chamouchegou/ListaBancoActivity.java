package com.example.chamouchegou;

import android.content.Intent;
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




        mBancoHelper = new BancoHelper(this);
        Cursor result = mBancoHelper.getData();
        this.getData(result);

        mListaBanco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaBancoActivity.this, ListaDeCompra.class);
                startActivity(intent);

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
                listBanco.add(new EntidadeLista(cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            }


            adapter = new ArrayAdapter(this, R.layout.single_lista, R.id.item_nome, listBanco) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView textNome = (TextView) view.findViewById(R.id.item_nome);
                    TextView textBairro = (TextView) view.findViewById(R.id.item_bairro);
                    TextView textApelido = (TextView) view.findViewById(R.id.item_apelido);
                    textNome.setText(listBanco.get(position).getNome());
                    textBairro.setText(listBanco.get(position).getBairro());
                    textApelido.setText(listBanco.get(position).getApelido());

                    return view;
                }
            };

            mListaBanco.setAdapter(adapter);
        }

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


