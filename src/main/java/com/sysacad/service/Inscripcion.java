package com.sysacad.service;

import com.sysacad.model.Alumno;
import com.sysacad.model.Carrera;
import com.sysacad.model.Materia;

import java.util.Map;

/**
 * Created by martin on 15/5/2017.
 */
public class Inscripcion {


    public static boolean cursado(Alumno alumno, Materia materiaInscribir){ //funciona

        Integer id = materiaInscribir.getId();
        Carrera carrera = alumno.getCarrera();

            if(carrera.getListaMaterias().containsKey(id)) {
                if (materiaInscribir.getListaMateriaRequerida().isEmpty()) {
                    //alumno.getListaMateriasCursando().put(id, materiaInscribir);
                    return true;
                } else {

                    for (Map.Entry<Integer, Materia> entry : materiaInscribir.getListaMateriaRequerida().entrySet()) {
                        //System.out.println(entry.getKey() + "/" + entry.getValue());
                        if (!alumno.getListaMateriasRegular().containsKey(entry.getKey())) {
                            return false;
                        }

                    }
                }

                //verifico que ya no este inscripto
                if (!alumno.getListaMateriasCursando().containsKey(id)) {
                    //alumno.getListaMateriasCursando().put(id, materiaInscribir);
                    return true;
                }
            }
        return false;
    }


    public static boolean inscribirseFinal (Alumno alumno, Materia materiaInscribir){

        Integer id = materiaInscribir.getId();

        Carrera carrera = alumno.getCarrera();

        if(carrera.getListaMaterias().containsKey(id)) {
            if (materiaInscribir.getListaMateriaRequerida().isEmpty() && alumno.getListaMateriasRegular().containsKey(id)) {
                //alumno.getListaMateriasRendirFinal().put(id, materiaInscribir);
                return true;
            } else {

                for (Map.Entry<Integer, Materia> entry : materiaInscribir.getListaMateriaRequerida().entrySet()) {

                    if (!alumno.getListaMateriasAprobadas().containsKey(entry.getKey())) {
                        return false;
                    }

                }

            }

            return true;
        }
        return false;

    }


}
