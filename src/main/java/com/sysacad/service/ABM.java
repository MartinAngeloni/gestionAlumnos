package com.sysacad.service;

import com.sysacad.model.*;

import static com.sysacad.repositorios.CarreraRepositorio.repositorioCarrera;
import static com.sysacad.repositorios.MateriaRepositorio.repositorioMateria;
import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;

/**
 * Created by martin on 8/7/2017.
 */
public class ABM {

    //a partir de aqui: ALUMNO

    //alta de alumno
    public static void altaAlumno(Alumno alumnoNuevo){

        repositorioPersona.add(alumnoNuevo);
    }

    //actualiza un alumno en repositorio
    public static void modificarAlumno(Alumno alumnoEliminar, Alumno alumnoNuevo){

        alumnoNuevo.setLegajo(alumnoEliminar.getLegajo());//conservamos el mismo legajo
        repositorioPersona.remove(alumnoEliminar);//eliminamos alumno encontrado
        repositorioPersona.add(alumnoNuevo); //aniadimos el alumno simulando actualizacion
    }

    //baja de alumno
    public static void bajaAlumno(Alumno alumnoEliminar){

        repositorioPersona.remove(alumnoEliminar);//eliminamos alumno
    }

    //a partir de aqui: CARRERA

    //alta de carrera
    public static void altaCarrera(Carrera carrera){

        repositorioCarrera.add(carrera);
    }

    //modificacion carrera
    public static void modificarCarrera(Carrera carreraEliminar, Carrera carreraNueva){

        carreraNueva.setId(carreraEliminar.getId());//conservamos el mismo id
        repositorioCarrera.remove(carreraEliminar);//eliminamos carrera
        repositorioCarrera.add(carreraNueva); //aniadimos carrera simulando actualizacion
    }

    //baja carrera
    public static void bajaCarrera(Carrera carrera){

        repositorioCarrera.remove(carrera);//eliminamos carrera
    }


    //a partir de aqui: MATERIAS

    public static void altaMateria(Materia materia){

        repositorioMateria.add(materia);
    }

    //actualizamos Materia
    public static void modificarMateria(Materia materiaEliminar, Materia materiaNueva){

        materiaNueva.setId(materiaEliminar.getId());//conservamos el mismo id
        repositorioMateria.remove(materiaEliminar);//eliminamos materia
        repositorioMateria.add(materiaNueva); //aniadimos materia simulando actualizacion
    }

    //baja materia
    public static void bajaMateria(Materia materia){

        repositorioMateria.remove(materia);//eliminamos materia
    }

}
