package com.sysacad.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 20/4/2017.
 */
public class Facultad {

    private List<Carrera> listaCarreras;

    public Facultad(){
        this.listaCarreras = new ArrayList<>();
    }

    public List<Carrera> getListaCarreras() {
        return listaCarreras;
    }

    public void setListaCarreras(List<Carrera> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }
}
