package com.sysacad.controller;

import com.sysacad.model.*;
import com.sysacad.service.ABM;
import com.sysacad.service.Busqueda;
import com.sysacad.service.Login;
import com.sysacad.service.Verificacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;

/**
 * Created by martin on 21/4/2017.
 */

@RestController
public class MateriaController {


    @PostMapping("/materia")
    ResponseEntity<Materia> crearMateria(@RequestBody Materia materia,
                                         @RequestHeader int legajo,
                                         @RequestHeader String contrasenia) throws Exception {


        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                ABM.altaMateria(materia);

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materia);
    }

    @GetMapping("/materia/{id}")
    ResponseEntity<Materia> obtenerMateria(@PathVariable int id,
                                           @RequestHeader int legajo,
                                           @RequestHeader String contrasenia) throws Exception {

        Materia materiaEncontrada = null;

        try {
            if (Login.validarUsuario(legajo, contrasenia)) {

                materiaEncontrada = Busqueda.encontrarMateria(id);

                if(materiaEncontrada != null){
                    return ResponseEntity.ok(materiaEncontrada);
                }else throw new EntityNotFoundException();


            } else throw new EntityNotFoundException();


        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/materia/{id}")
    ResponseEntity<Materia> actualizarMateria(@PathVariable int id,
                                              @RequestHeader int legajo,
                                              @RequestHeader String contrasenia,
                                              @RequestBody Materia materia) throws Exception{

        Materia materiaEliminar = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                materiaEliminar = Busqueda.encontrarMateria(id);

                if (materiaEliminar != null) {

                    ABM.modificarMateria(materiaEliminar, materia);
                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(materia);
    }

    @DeleteMapping("/materia/{id}")
    ResponseEntity<Materia> eliminarMateria(@PathVariable int id,
                                            @RequestHeader int legajo,
                                            @RequestHeader String contrasenia) throws Exception{

        Materia materiaEliminar = null;

        try {
            if (Login.validarAdministrador(legajo, contrasenia)) {

                materiaEliminar = Busqueda.encontrarMateria(id);


                if (materiaEliminar != null) {
                    ABM.bajaMateria(materiaEliminar);
                }else throw new EntityNotFoundException();


            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(materiaEliminar);
    }

    @PutMapping("/materia/agregar/requerida/{idMateria}")
    ResponseEntity<Materia> agregarMateriaRequerida(@PathVariable int idMateria,
                                                    @RequestHeader int idRequerida,
                                                    @RequestHeader int legajoUsuario,
                                                    @RequestHeader String contrasenia)throws Exception{

        Materia materiaTratar = null;
        Materia materiaRequerida = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                materiaTratar = Busqueda.encontrarMateria(idMateria);
                materiaRequerida = Busqueda.encontrarMateria(idRequerida);


                if (materiaRequerida != null && materiaTratar != null) {
                    if (Verificacion.asignarMateriaRequerida(materiaRequerida, materiaTratar)) { //que la materia no se requiera a si misma

                        materiaTratar.getListaMateriaRequerida().put(idRequerida, materiaRequerida);
                        ABM.modificarMateria(materiaTratar, materiaTratar);

                    } else throw new Exception();

                } else throw new EntityNotFoundException();

            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(materiaRequerida);
    }


    //devuelve lista de materias del repositorio
    @GetMapping("materia/listado")
    ResponseEntity<List<Materia>> listarMaterias(@RequestHeader int legajoUsuario,
                                                 @RequestHeader String contrasenia) throws Exception{

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaMaterias());

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping("materia/horarios/{idMateria}")
    ResponseEntity<Horario> asignarHorario(@PathVariable int idMateria,
                                           @RequestHeader int legajoUsuario,
                                           @RequestHeader String contrasenia,
                                           @RequestBody Horario horario)throws Exception{

        Materia materiaEncontrada = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                materiaEncontrada = Busqueda.encontrarMateria(idMateria);

                if (materiaEncontrada != null) {

                    if(Verificacion.validarHorario(horario)) {
                        materiaEncontrada.getListaHorarios().add(horario);
                        ABM.modificarMateria(materiaEncontrada, materiaEncontrada);
                    }else throw new Exception();

                } else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(horario);
    }




}
