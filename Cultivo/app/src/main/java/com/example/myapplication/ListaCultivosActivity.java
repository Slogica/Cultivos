package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListaCultivosActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cultivos);

        // Inicializar el DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Obtener la lista de cultivos desde la base de datos
        ArrayList<Cultivo> listaCultivos = databaseHelper.obtenerTodosLosCultivos();

        // Configurar la ListView para mostrar los cultivos
        ListView listView = findViewById(R.id.listViewCultivos);
        CultivoAdapter adapter = new CultivoAdapter(this, listaCultivos, databaseHelper);
        listView.setAdapter(adapter);

        // Configurar el botón de regreso
        Button botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(v -> finish());
    }
}