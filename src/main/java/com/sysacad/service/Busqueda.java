package com.sysacad.service;

import com.sysacad.model.Alumno;
import com.sysacad.model.Carrera;
import com.sysacad.model.Materia;
import com.sysacad.model.Persona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sysacad.repositorios.CarreraRepositorio.repositorioCarrera;
import static com.sysacad.repositorios.MateriaRepositorio.repositorioMateria;
import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;

/**
 * Created by martin on 8/7/2017.
 */
public class Busqueda {


    public static Alumno encontrarAlumno(int legajoAlumno){

        Alumno alumno = null;

        for(Persona p: repositorioPersona){
            if(p.getLegajo() == legajoAlumno && p instanceof Alumno){
                return (Alumno) p;
            }
        }
        return alumno;
    }



    public static List<Alumno> listaAlumnos(){

        List<Alumno> lista = new ArrayList<>();

        for(Persona p: repositorioPersona){
            if(p instanceof Alumno){
                lista.add((Alumno) p);
            }
        }

        return lista;
    }

    //buscamos una materia cursando del alumno
    public static Materia materiaCursando(Alumno alumno, Integer idMateria){

        Materia materiaCursando = null;

        if(alumno != null && alumno.getListaMateriasCursando().containsKey(idMateria)){
            materiaCursando = alumno.getListaMateriasCursando().get(idMateria);
        }
        return materiaCursando;
    }

    //lista de materias cursando del alumno
    public static Map<Integer, Materia> listaMateriasCursando(int legajoAlumno){

        Map<Integer, Materia> listaMateriasCursando = new HashMap<>();

        for(Persona p: repositorioPersona){
            if( p.getLegajo() == legajoAlumno && p instanceof Alumno){
                listaMateriasCursando = ((Alumno) p).getListaMateriasCursando();
            }
        }

        return listaMateriasCursando;
    }


    //lista de materias regular del alumno
    public static Map<Integer, Materia> listaMateriasRegular(int legajoAlumno){

        Map<Integer, Materia> listaMateriasRegular = new HashMap<>();

        for(Persona p: repositorioPersona){
            if( p.getLegajo() == legajoAlumno && p instanceof Alumno){
                listaMateriasRegular = ((Alumno) p).getListaMateriasRegular();
            }
        }

        return listaMateriasRegular;
    }

    //buscamos una materia regular del alumno
    public static Materia materiaRegular(Alumno alumno, Integer idMateria){

        Materia materiaRegular = null;

        if(alumno != null && alumno.getListaMateriasRegular().containsKey(idMateria)){
            materiaRegular = alumno.getListaMateriasRegular().get(idMateria);
        }
        return materiaRegular;
    }


    //lista de materias aprobadas del alumno
    public static Map<Integer, Materia> listaMateriasAprobadas(int legajoAlumno){

        Map<Integer, Materia> listaMateriasAprobadas = new HashMap<>();

        for(Persona p: repositorioPersona){
            if( p.getLegajo() == legajoAlumno && p instanceof Alumno){
                listaMateriasAprobadas = ((Alumno) p).getListaMateriasAprobadas();
            }
        }

        return listaMateriasAprobadas;
    }

    //buscamos una materia aprobada del alumno
    public static Materia materiaAprobada(Alumno alumno, Integer idMateria){

        Materia materiaAprobada = null;

        if(alumno != null && alumno.getListaMateriasAprobadas().containsKey(idMateria)){
            materiaAprobada = alumno.getListaMateriasAprobadas().get(idMateria);
        }
        return materiaAprobada;
    }

    //lista de materias por rendir final del alumno
    public static Map<Integer, Materia> listaMateriasFinal(int legajoAlumno){

        Map<Integer, Materia> listaMateriasFinal = new HashMap<>();

        for(Persona p: repositorioPersona){
            if( p.getLegajo() == legajoAlumno && p instanceof Alumno){
                listaMateriasFinal = ((Alumno) p).getListaMateriasRendirFinal();
            }
        }

        return listaMateriasFinal;
    }

    //buscamos una materia por rendir final del alumno
    public static Materia materiaFinal(Alumno alumno, Integer idMateria){

        Materia materiaFinal = null;

        if(alumno != null && alumno.getListaMateriasRendirFinal().containsKey(idMateria)){
            materiaFinal = alumno.getListaMateriasRendirFinal().get(idMateria);
        }
        return materiaFinal;
    }

    //a partir de aqui: Carrera

    //alta carrera
    public static Carrera encontrarCarrera(int idCarrera){

        Carrera carrera = null;

        for(Carrera c: repositorioCarrera){
            if(c.getId() == idCarrera){
                carrera = c;
            }
        }
        return carrera;
    }


    public static Map<Integer, Materia> carreraListaMaterias(int idCarrera){

        Map<Integer, Materia> listaMaterias = new HashMap<>();

        for(Carrera c: repositorioCarrera){
            if(c.getId() == idCarrera){
                listaMaterias = c.getListaMaterias();
            }
        }

        return listaMaterias;
    }

    public static Map<Integer, Alumno> carreraListaAlumnos(int idCarrera){

        Map<Integer, Alumno> listaAlumnos = new HashMap<>();

        for(Carrera c: repositorioCarrera){
            if(c.getId() == idCarrera){
                if(!c.getListaAlumnos().isEmpty()) {
                    listaAlumnos = c.getListaAlumnos();
                }
            }
        }

        return listaAlumnos;
    }

    public static List<Carrera> listaCarreras(){

        List<Carrera> listaCarreras = new ArrayList<>();

        for(Carrera c: repositorioCarrera){
            listaCarreras.add(c);
        }

        return listaCarreras;
    }


    //a partir de aqui: MATERIA

    public static Materia encontrarMateria(int idMateria){

        Materia materia = null;

        for(Materia m: repositorioMateria){
            if(m.getId() == idMateria){
                materia = m;
            }
        }

        return materia;
    }

    public static ArrayList<Materia> listaMaterias(){

        ArrayList<Materia> listaMaterias = new ArrayList<>();

        for(Materia m: repositorioMateria){
           listaMaterias.add(m);
        }

        return listaMaterias;
    }


}
