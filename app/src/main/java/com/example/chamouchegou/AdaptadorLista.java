package com.example.chamouchegou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorLista extends BaseAdapter {
    private Context context;

    public AdaptadorLista(Context context, ArrayList<String> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    private ArrayList<String> listItem;

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntidadeLista item = (EntidadeLista) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.single_lista,null);
        TextView item_nome = convertView.findViewById(R.id.item_nome);
        TextView item_bairro = convertView.findViewById(R.id.item_bairro);
        TextView item_apelido = convertView.findViewById(R.id.item_apelido);

        item_nome.setText(item.getNome());
        item_bairro.setText(item.getBairro());
        item_apelido.setText(item.getApelido());

        return convertView;
    }
}
