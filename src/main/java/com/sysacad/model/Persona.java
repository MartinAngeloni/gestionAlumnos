package com.sysacad.model;



/**
 * Created by martin on 20/4/2017.
 */
public abstract class Persona implements Comparable<Persona>{


    protected static int legajo_Increment = 1;

    protected int legajo;
    protected String nombre;
    protected String apellido;
    protected String contrasenia;

    public Persona () {
        legajo = legajo_Increment;
        legajo_Increment++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof Persona && this.legajo == ((Persona) o).getLegajo()){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Persona p){
        if(this.getLegajo() > p.getLegajo()) return 1;
        if(this.getLegajo() < p.getLegajo()) return -1;
        return 0;
    }

}
