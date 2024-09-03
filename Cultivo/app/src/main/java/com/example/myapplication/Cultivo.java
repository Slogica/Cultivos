package com.example.myapplication;

import java.io.Serializable;
import java.util.Date;

public class Cultivo implements Serializable {
    private String tipo;
    private Date fechaCultivo;
    private Date fechaCosecha;

    // Constructor
    public Cultivo(String tipo, Date fechaCultivo, Date fechaCosecha) {
        this.tipo = tipo;
        this.fechaCultivo = fechaCultivo;
        this.fechaCosecha = fechaCosecha;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaCultivo() {
        return fechaCultivo;
    }

    public void setFechaCultivo(Date fechaCultivo) {
        this.fechaCultivo = fechaCultivo;
    }

    public Date getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(Date fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }
}
