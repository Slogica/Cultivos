package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cultivo.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y las columnas
    private static final String TABLE_CULTIVOS = "cultivos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TIPO = "tipo";
    private static final String COLUMN_FECHA_CULTIVO = "fecha_cultivo";
    private static final String COLUMN_FECHA_COSECHA = "fecha_cosecha";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CULTIVOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIPO + " TEXT, " +
                COLUMN_FECHA_CULTIVO + " INTEGER, " +
                COLUMN_FECHA_COSECHA + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CULTIVOS);
        onCreate(db);
    }

    // Método para agregar un cultivo a la base de datos
    public void agregarCultivo(Cultivo cultivo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIPO, cultivo.getTipo());
        values.put(COLUMN_FECHA_CULTIVO, cultivo.getFechaCultivo().getTime());
        values.put(COLUMN_FECHA_COSECHA, cultivo.getFechaCosecha().getTime());

        db.insert(TABLE_CULTIVOS, null, values);
        db.close();
    }

    // Método para obtener todos los cultivos
    public ArrayList<Cultivo> obtenerTodosLosCultivos() {
        ArrayList<Cultivo> cultivos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CULTIVOS, null);

        if (cursor.moveToFirst()) {
            do {
                String tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO));
                long fechaCultivoMillis = cursor.getLong(cursor.getColumnIndex(COLUMN_FECHA_CULTIVO));
                long fechaCosechaMillis = cursor.getLong(cursor.getColumnIndex(COLUMN_FECHA_COSECHA));

                Date fechaCultivo = new Date(fechaCultivoMillis);
                Date fechaCosecha = new Date(fechaCosechaMillis);

                Cultivo cultivo = new Cultivo(tipo, fechaCultivo, fechaCosecha);
                cultivos.add(cultivo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cultivos;
    }
}
