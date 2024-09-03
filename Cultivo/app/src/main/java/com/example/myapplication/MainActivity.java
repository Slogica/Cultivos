package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Cultivo> listaCultivos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el Spinner
        Spinner spinnerCultivo = findViewById(R.id.spinnerCultivo);

        // Crear un ArrayAdapter usando un array de strings y un layout predeterminado para el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cultivos_array, android.R.layout.simple_spinner_item);

        // Especificar el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplicar el adaptador al spinner
        spinnerCultivo.setAdapter(adapter);

        // Configurar el botón para registrar el cultivo
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la selección del Spinner y la fecha del DatePicker
                String tipoCultivo = spinnerCultivo.getSelectedItem().toString();
                DatePicker datePicker = findViewById(R.id.datePickerCultivo);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                Calendar fechaCultivo = Calendar.getInstance();
                fechaCultivo.set(year, month, day);

                // Determinar los días hasta la cosecha según el tipo de cultivo
                int diasHastaCosecha = 0;
                switch (tipoCultivo) {
                    case "Tomates":
                        diasHastaCosecha = 80;
                        break;
                    case "Cebollas":
                        diasHastaCosecha = 120;
                        break;
                    case "Lechugas":
                        diasHastaCosecha = 60;
                        break;
                    case "Apio":
                        diasHastaCosecha = 85;
                        break;
                    case "Choclo":
                        diasHastaCosecha = 90;
                        break;
                }

                // Calcular la fecha de cosecha
                fechaCultivo.add(Calendar.DAY_OF_YEAR, diasHastaCosecha);
                Date fechaCosecha = fechaCultivo.getTime();

                // Crear un objeto Cultivo y agregarlo a la lista
                Cultivo nuevoCultivo = new Cultivo(tipoCultivo, fechaCultivo.getTime(), fechaCosecha);
                listaCultivos.add(nuevoCultivo);

                // Mostrar un Toast notificando que el cultivo se ha registrado exitosamente
                String mensaje = "Cultivo de " + tipoCultivo + " registrado. Fecha de cosecha probable: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaCosecha);
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });

        // Configurar el botón para ver la lista de cultivos
        Button btnVerCultivos = findViewById(R.id.btnVerCultivos);
        btnVerCultivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la ListaCultivosActivity
                Intent intent = new Intent(MainActivity.this, ListaCultivosActivity.class);

                // Pasar la lista de cultivos a la nueva actividad
                intent.putExtra("listaCultivos", listaCultivos);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }
}