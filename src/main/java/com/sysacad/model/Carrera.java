package com.sysacad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin on 20/4/2017.
 */
public class Carrera {

    private static int id_increment = 1;
    private int id;
    private String nombre;
    private Map<Integer, Materia> listaMaterias;
    @JsonIgnore
    private Map<Integer, Alumno> listaAlumnos;


    public Carrera(){

        this.listaMaterias = new HashMap<>();
        this.listaAlumnos = new HashMap<>();
        id = id_increment;
        id_increment++;
    }

    public Carrera(String nombre, int id){

        this.nombre = nombre;
        this.listaMaterias = new HashMap<>();
        this.listaAlumnos = new HashMap<>();
        this.id = id;
    }


    public Map<Integer, Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(Map<Integer, Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public Map<Integer, Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(Map<Integer, Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Carrera && this.getId() == ((Carrera) o).getId()){
            return true;
        }
        return false;
    }

}
