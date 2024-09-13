package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Ya estamos en la pantalla principal, no hacemos nada
                return true;
            } else if (id == R.id.nav_lista) {
                // Navegar a la Lista de cultivos
                Intent intent = new Intent(MainActivity.this, ListaCultivosActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        // Inicializar DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

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

                // Guardar el cultivo en la base de datos
                databaseHelper.agregarCultivo(nuevoCultivo);

                // Mostrar un Toast notificando que el cultivo se ha registrado exitosamente
                String mensaje = "Cultivo de " + tipoCultivo + " registrado. Fecha de cosecha probable: " + new SimpleDateFormat("dd/MM/yyyy").format(fechaCosecha);
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }
}