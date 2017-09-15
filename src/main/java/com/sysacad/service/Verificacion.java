package com.sysacad.service;

import com.sysacad.model.Examen;
import com.sysacad.model.Horario;
import com.sysacad.model.Materia;
import com.sysacad.model.TipoExamen;

/**
 * Created by martin on 8/7/2017.
 */
public class Verificacion {

    public static boolean examenParcial (Examen examen){

        if(examen.getNota()>=1 && examen.getNota() <=10){
            if(examen.getTipoExamen() == TipoExamen.parcial || examen.getTipoExamen() == TipoExamen.recuperatorio){
                return true;
            }
        }
        return false;
    }

    public static boolean examenFinal (Examen examen){

        if(examen.getNota()>=1 && examen.getNota() <=10){
            if(examen.getTipoExamen() == TipoExamen.mesaFinal){
                return true;
            }
        }
        return false;
    }


    //a partir de aqui: Horario
    public static boolean validarHorario(Horario horario){

        if(horario.getHoraInicio() >= 6 && horario.getHoraInicio() <=22){

            if(horario.getHoraFin()>= 7 && horario.getHoraFin() <= 23){

                if((horario.getHoraInicio() < horario.getHoraFin()) &&
                        (horario.getHoraInicio() != horario.getHoraFin())){

                    if(horario.getMinutosInicio()>= 0 && horario.getMinutosInicio() <= 59){

                        if(horario.getMinutosFin()>= 0 && horario.getMinutosFin() <= 59){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    //asignar materia requerida a otra materia
    public static boolean asignarMateriaRequerida(Materia materiaRequerida, Materia matetriaTratar){

        if(!materiaRequerida.equals(matetriaTratar)){
            return true;
        }
        return false;
    }

}
