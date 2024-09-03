package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListaCultivosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cultivos);

        // Obtener la intención que inició esta actividad
        Intent intent = getIntent();

        // Recuperar la lista de cultivos desde el Intent
        ArrayList<Cultivo> listaCultivos = (ArrayList<Cultivo>) intent.getSerializableExtra("listaCultivos");

        // Configurar la ListView para mostrar los cultivos
        ListView listView = findViewById(R.id.listViewCultivos);

        // Usar el adaptador personalizado para mostrar los cultivos
        CultivoAdapter adapter = new CultivoAdapter(this, listaCultivos);
        listView.setAdapter(adapter);

        // Configurar el botón de regreso
        Button botonVolver = findViewById(R.id.botonVolver);
        botonVolver.setOnClickListener(v -> finish()); // Termina la actividad actual y vuelve a la anterior
    }
}