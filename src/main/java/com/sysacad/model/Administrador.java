package com.sysacad.model;

/**
 * Created by martin on 20/4/2017.
 */
public class Administrador extends Persona {

    public Administrador(){

    }

    public Administrador(int legajo, String contrasenia){
        this.legajo = legajo;
        this.contrasenia = contrasenia;
    }




    @Override
    public boolean equals(Object o){
        if(o instanceof Administrador && this.getLegajo() == ((Administrador) o).getLegajo()){
            return true;
        }
        return false;
    }
}
