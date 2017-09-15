package com.sysacad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by martin on 20/4/2017.
 */
public class Materia {


    private static int id_increment = 1;
    private int id;
    private String nombre;
    private Dictado dictado;
    private boolean seCursa;
    private boolean seRinde;
    private List<Horario> listaHorarios;
    private List<Examen> listaExamenes;
    private Map<Integer, Materia> listaMateriaRequerida;
    @JsonIgnore
    private Map<Integer, Alumno> listaAlumnos;


    public Materia(){
        this.listaHorarios = new ArrayList<>();
        this.listaExamenes = new ArrayList<>();
        this.listaMateriaRequerida = new HashMap<>();
        this.listaAlumnos = new HashMap<>();
        this.id = id_increment;
        id_increment++;
    }

    public Materia(String nombre, int id){
        this.nombre = nombre;
        this.id = id;
        this.listaHorarios = new ArrayList<>();
        this.listaExamenes = new ArrayList<>();
        this.listaMateriaRequerida = new HashMap<>();
        this.listaAlumnos = new HashMap<>();
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


    public Dictado getDictado() {
        return dictado;
    }

    public void setDictado(Dictado dictado) {
        this.dictado = dictado;
    }

    public boolean isSeCursa() {
        return seCursa;
    }

    public void setSeCursa(boolean seCursa) {
        this.seCursa = seCursa;
    }

    public boolean isSeRinde() {
        return seRinde;
    }

    public void setSeRinde(boolean seRinde) {
        this.seRinde = seRinde;
    }

    public List<Horario> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<Horario> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public List<Examen> getListaExamenes() {
        return listaExamenes;
    }

    public void setListaExamenes(List<Examen> listaExamenes) {
        this.listaExamenes = listaExamenes;
    }

    public Map<Integer, Materia> getListaMateriaRequerida() {
        return listaMateriaRequerida;
    }

    public void setListaMateriaRequerida(Map<Integer, Materia> listaMateriaRequerida) {
        this.listaMateriaRequerida = listaMateriaRequerida;
    }

    public Map<Integer, Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(Map<Integer, Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }


    @Override
    public boolean equals(Object o){

        if(o instanceof Materia && ((Materia) o).getId() == this.id){
            return true;
        }

        return false;
    }

}
