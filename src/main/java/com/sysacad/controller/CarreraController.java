package com.sysacad.controller;

import com.sysacad.model.*;
import com.sysacad.service.ABM;
import com.sysacad.service.Busqueda;
import com.sysacad.service.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by martin on 26/4/2017.
 */
@RestController
public class CarreraController {



    @PostMapping("/carrera")
    ResponseEntity<Carrera> crearCarrera(@RequestBody Carrera carrera,
                                         @RequestHeader int legajo,
                                         @RequestHeader String contrasenia) throws Exception{

        System.out.println("entro en crear");
        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                ABM.altaCarrera(carrera);

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrera);
    }

    @GetMapping("/carrera/{id}")
    ResponseEntity<Carrera> obtenerCarrera(@PathVariable int id,
                                           @RequestHeader int legajo,
                                           @RequestHeader String contrasenia)throws Exception{

        Carrera carreraEncontrada = null;

        try {
            if (Login.validarUsuario(legajo, contrasenia)) {

                carreraEncontrada = Busqueda.encontrarCarrera(id);

            }else throw new EntityNotFoundException();

            if (carreraEncontrada == null) {
                throw new EntityNotFoundException();
            }

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carreraEncontrada);

    }

    @PutMapping("/carrera/{id}")
    ResponseEntity<Carrera> actualizarCarrera(@PathVariable int id,
                                              @RequestBody Carrera carrera,
                                              @RequestHeader int legajo,
                                              @RequestHeader String contrasenia)throws Exception{

        Carrera carreraEncontrada = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                carreraEncontrada = Busqueda.encontrarCarrera(id);

                if (carreraEncontrada != null) {

                    ABM.modificarCarrera(carreraEncontrada, carrera);
                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(carrera);
    }

    @DeleteMapping("/carrera/{id}")
    ResponseEntity<Carrera> eliminarCarrera(@PathVariable int id,
                                            @RequestHeader int legajo,
                                            @RequestHeader String contrasenia) throws Exception{

        Carrera carreraEliminar = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                carreraEliminar = Busqueda.encontrarCarrera(id);

                if (carreraEliminar != null) {
                    ABM.bajaCarrera(carreraEliminar);
                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carreraEliminar);
    }

    @PutMapping("/carrera/asignar/materia/{idCarrera}")
    ResponseEntity<Materia> asignarMateria(@PathVariable int idCarrera,
                                           @RequestHeader int idMateria,
                                           @RequestHeader int legajo,
                                           @RequestHeader String contrasenia)throws Exception{

        Materia materiaAsignar = null;
        Carrera carreraEncontrada = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                materiaAsignar = Busqueda.encontrarMateria(idMateria);
                carreraEncontrada = Busqueda.encontrarCarrera(idCarrera);


                if (materiaAsignar != null && carreraEncontrada != null) {

                    carreraEncontrada.getListaMaterias().put(idMateria, materiaAsignar);
                    ABM.modificarCarrera(carreraEncontrada, carreraEncontrada);

                } else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materiaAsignar);
    }

    @GetMapping("/carrera/obtener/materias/{idCarrera}")
    ResponseEntity<Map<Integer, Materia>> obtenerListaMaterias(@PathVariable int idCarrera,
                                                               @RequestHeader int legajoUsuario,
                                                               @RequestHeader String contrasenia)throws Exception{
        Map<Integer, Materia> listaMaterias = new HashMap<>();

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                listaMaterias = Busqueda.carreraListaMaterias(idCarrera);

                if(!listaMaterias.isEmpty()) {
                    return ResponseEntity.ok(listaMaterias);
                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/carrera/eliminar/materia/{idCarrera}")
    ResponseEntity<Materia> eliminarMateriaAsignada (@PathVariable int idCarrera,
                                                     @RequestHeader int idMateria,
                                                     @RequestHeader int legajo,
                                                     @RequestHeader String contrasenia) throws Exception{
        Materia materiaEliminar = null;
        Carrera carreraEncontrada = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                materiaEliminar = Busqueda.encontrarMateria(idMateria);
                carreraEncontrada = Busqueda.encontrarCarrera(idCarrera);


                if (materiaEliminar != null && carreraEncontrada != null) {

                    carreraEncontrada.getListaMaterias().remove(idMateria);
                    ABM.modificarCarrera(carreraEncontrada, carreraEncontrada);

                } else throw new EntityNotFoundException();

            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaEliminar);
    }

    @PutMapping("/carrera/{idCarrera}/asignar/alumno")
    ResponseEntity<Alumno> asignarAlumno(@PathVariable int idCarrera,
                                         @RequestHeader int legajoAlumno,
                                         @RequestHeader int legajoUsuario,
                                         @RequestHeader String contrasenia)throws Exception{

        Alumno alumnoEncontrado = null;
        Carrera carreraEncontrada = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                carreraEncontrada = Busqueda.encontrarCarrera(idCarrera);



                if (alumnoEncontrado != null && carreraEncontrada != null) {

                    carreraEncontrada.getListaAlumnos().put(legajoAlumno, alumnoEncontrado);
                    alumnoEncontrado.setCarrera(carreraEncontrada); //le asignamos al alumno la carrera
                    ABM.modificarCarrera(carreraEncontrada, carreraEncontrada);
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                } else throw new EntityNotFoundException();

            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/carrera/obtener/alumnos/{idCarrera}")
    ResponseEntity<Map<Integer, Alumno>> obtenerListaAlumnos(@PathVariable int idCarrera,
                                                             @RequestHeader int legajoUsuario,
                                                             @RequestHeader String contrasenia) throws Exception{

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.carreraListaAlumnos(idCarrera));

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    //elimina alumno de carrera y carrera del alumno
    @DeleteMapping("/carrera/eliminar/alumno/{idCarrera}")
    ResponseEntity<Alumno> eliminarAlumnoEnCarrera(@PathVariable int idCarrera,
                                                   @RequestHeader int legajoAlumno,
                                                   @RequestHeader int legajoUsuario,
                                                   @RequestHeader String contrasenia)throws Exception{

        Alumno alumnoEliminar = null;
        Carrera carreraEncontrada = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEliminar = Busqueda.encontrarAlumno(legajoAlumno);
                carreraEncontrada = Busqueda.encontrarCarrera(idCarrera);


                if (alumnoEliminar != null && carreraEncontrada != null) {

                    carreraEncontrada.getListaAlumnos().remove(legajoAlumno);
                    alumnoEliminar.setCarrera(null);

                    ABM.modificarCarrera(carreraEncontrada, carreraEncontrada);
                    ABM.modificarAlumno(alumnoEliminar, alumnoEliminar); //el alumno no tendra mas carrera

                } else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumnoEliminar);
    }


    @GetMapping("/carrera/listado")
    ResponseEntity<List<Carrera>> listarCarreras(@RequestHeader int legajoUsuario,
                                               @RequestHeader String contrasenia) throws Exception{


        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaCarreras());

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }



}
