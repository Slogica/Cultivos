package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;


public class ListaCultivosActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cultivos);

        databaseHelper = new DatabaseHelper(this);

        // Obtener cultivos de la base de datos
        ArrayList<Cultivo> listaCultivos = databaseHelper.obtenerTodosLosCultivos();
        ListView listView = findViewById(R.id.listViewCultivos);
        CultivoAdapter adapter = new CultivoAdapter(this, listaCultivos, databaseHelper);
        listView.setAdapter(adapter);

        // Configurar botón "Inicio" para volver a la pantalla principal
        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ListaCultivosActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Configurar botón "Lista" (ya estamos en la lista, no hacemos nada)
        Button btnLista = findViewById(R.id.btnLista);
        btnLista.setOnClickListener(v -> {
            // No hacemos nada porque ya estamos en la lista
        });
    }
}