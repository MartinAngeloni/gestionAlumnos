package com.sysacad.model;



/**
 * Created by martin on 20/4/2017.
 */
public class Examen {

    private String descripcion;
    private int nota;
    private TipoExamen tipoExamen; //enumeracion

    public Examen(){

    }

    public Examen(String descripcion, int nota, TipoExamen tipoExamen){

        this.descripcion = descripcion;
        this.nota = nota;
        this.tipoExamen = tipoExamen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public TipoExamen getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(TipoExamen tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
}
