package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CultivoAdapter extends ArrayAdapter<Cultivo> {
    private DatabaseHelper databaseHelper;

    public CultivoAdapter(Context context, ArrayList<Cultivo> cultivos, DatabaseHelper dbHelper) {
        super(context, 0, cultivos);
        this.databaseHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cultivo cultivo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cultivo, parent, false);
        }

        // Referencias a los elementos de la vista
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        TextView fechaCosechaTextView = convertView.findViewById(R.id.fechaCosechaTextView);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        // Configurar los datos
        nombreTextView.setText(cultivo.getTipo());
        fechaCosechaTextView.setText("Fecha de Cosecha: " + new SimpleDateFormat("dd/MM/yyyy").format(cultivo.getFechaCosecha()));

        // Manejar el evento de eliminación
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar el cultivo de la base de datos
                databaseHelper.eliminarCultivo(cultivo);

                // Remover el cultivo de la lista y notificar el cambio
                remove(cultivo);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
