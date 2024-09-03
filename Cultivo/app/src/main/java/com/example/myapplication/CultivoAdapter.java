package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CultivoAdapter extends ArrayAdapter<Cultivo> {
    public CultivoAdapter(Context context, ArrayList<Cultivo> cultivos) {
        super(context, 0, cultivos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el objeto Cultivo en esta posición
        Cultivo cultivo = getItem(position);

        // Verificar si la vista ya existe (reutilización)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cultivo, parent, false);
        }

        // Obtener las referencias a los elementos de la vista
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        // Configurar los datos
        nombreTextView.setText(cultivo.getTipo());

        // Devolver la vista configurada
        return convertView;
    }
}
