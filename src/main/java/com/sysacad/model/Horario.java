package com.sysacad.model;

/**
 * Created by martin on 9/5/2017.
 */
public class Horario {

    private Dia dia;
    private int horaInicio;
    private int minutosInicio;
    private int horaFin;
    private int minutosFin;

    public Horario(){

    }

    public Horario(Dia dia, int horaInicio, int minutosInicio, int horaFin, int minutosFin){
        this.dia = dia;
        this. horaInicio = horaInicio;
        this.minutosInicio = minutosInicio;
        this.horaFin = horaFin;
        this.minutosFin = minutosFin;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutosInicio() {
        return minutosInicio;
    }

    public void setMinutosInicio(int minutosInicio) {
        this.minutosInicio = minutosInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getMinutosFin() {
        return minutosFin;
    }

    public void setMinutosFin(int minutosFin) {
        this.minutosFin = minutosFin;
    }
}
