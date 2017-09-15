package com.sysacad.model;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin on 20/4/2017.
 */
public class Alumno extends Persona {

    private Carrera carrera;
    private Estado estado;
    private Map<Integer, Materia> listaMateriasCursando;
    private Map<Integer, Materia> listaMateriasRegular;
    private Map<Integer, Materia> listaMateriasAprobadas;
    private Map<Integer, Materia> listaMateriasRendirFinal;

    public Alumno(){

        this.listaMateriasAprobadas = new HashMap<>();
        this.listaMateriasCursando = new HashMap<>();
        this.listaMateriasRegular = new HashMap<>();
        this.listaMateriasRendirFinal = new HashMap<>();
        this.estado = Estado.regular; //por defecto
    }

    public Alumno(String nombre, String apellido, int legajo, String contrasenia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.contrasenia = contrasenia;
        this.listaMateriasAprobadas = new HashMap<>();
        this.listaMateriasCursando = new HashMap<>();
        this.listaMateriasRegular = new HashMap<>();
        this.listaMateriasRendirFinal = new HashMap<>();
    }


    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Map<Integer, Materia> getListaMateriasCursando() {
        return listaMateriasCursando;
    }

    public void setListaMateriasCursando(Map<Integer, Materia> listaMateriasCursando) {
        this.listaMateriasCursando = listaMateriasCursando;
    }

    public Map<Integer, Materia> getListaMateriasRegular() {
        return listaMateriasRegular;
    }

    public void setListaMateriasRegular(Map<Integer, Materia> listaMateriasRegular) {
        this.listaMateriasRegular = listaMateriasRegular;
    }

    public Map<Integer, Materia> getListaMateriasAprobadas() {
        return listaMateriasAprobadas;
    }

    public void setListaMateriasAprobadas(Map<Integer, Materia> listaMateriasAprobadas) {
        this.listaMateriasAprobadas = listaMateriasAprobadas;
    }

    public Map<Integer, Materia> getListaMateriasRendirFinal() {
        return listaMateriasRendirFinal;
    }

    public void setListaMateriasRendirFinal(Map<Integer, Materia> listaMateriasRendirFinal) {
        this.listaMateriasRendirFinal = listaMateriasRendirFinal;
    }

    @Override
    public boolean equals(Object o){

        if(o instanceof Alumno && ((Alumno) o).getLegajo() == this.legajo){
            return true;
        }
        return false;
    }

}
