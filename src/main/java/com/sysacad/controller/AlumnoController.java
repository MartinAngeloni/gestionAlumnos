package com.sysacad.controller;

import com.sysacad.model.*;
import com.sysacad.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Map;

import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;

/**
 * Created by martin on 20/4/2017.
 */


@RestController
public class AlumnoController {


	//probando cambios
    @PostMapping("/alumno")
    ResponseEntity<Alumno> crearAlumno(@RequestBody Alumno alumno,
                                       @RequestHeader int legajoUsuario,
                                       @RequestHeader String contrasenia)throws Exception{

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                repositorioPersona.add(alumno);

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno);
    }



    @GetMapping("/alumno/{legajo}")
    ResponseEntity<Alumno> obtenerAlumno(@PathVariable int legajo,
                                         @RequestHeader int legajoUsuario,
                                         @RequestHeader String contrasenia) throws Exception{

        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarUsuario(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajo);

                if(alumnoEncontrado != null){
                    return ResponseEntity.ok(alumnoEncontrado);
                }else throw new EntityNotFoundException();

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/alumno/{legajo}")
    ResponseEntity<Alumno> actualizarAlumno(@RequestBody Alumno alumno,
                                            @PathVariable int legajo,
                                            @RequestHeader int legajoUsuario,
                                            @RequestHeader String contrasenia) throws Exception{

        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajo); //buscamos alumno en repositorio

                if (alumnoEncontrado != null) {

                    ABM.modificarAlumno(alumnoEncontrado, alumno);
                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

            return ResponseEntity.ok().body(alumno);

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }



    @DeleteMapping("/alumno/{legajo}")
    ResponseEntity<Persona> eliminarAlumno(@PathVariable int legajo,
                                           @RequestHeader int legajoUsuario,
                                           @RequestHeader String contrasenia) throws Exception{

        Alumno alumnoEliminar = null;

        try {

            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEliminar = Busqueda.encontrarAlumno(legajo);

                if (alumnoEliminar != null) {

                    ABM.bajaAlumno(alumnoEliminar);
                    return ResponseEntity.ok().body(alumnoEliminar);

                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/alumno/listado")
    ResponseEntity<List<Alumno>> listarAlumnos(@RequestHeader int legajoUsuario,
                                               @RequestHeader String contrasenia) throws Exception{
        List<Alumno> listaAlumnos;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                listaAlumnos = Busqueda.listaAlumnos();

                if (listaAlumnos != null) {

                    return ResponseEntity.ok(listaAlumnos);
                } else throw new EntityNotFoundException();
            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping("/alumno/{legajoAlumno}/asignar/regularidad") //admin asigna una materia regular al alumno
    ResponseEntity<Materia> asignarMateriaRegular(@PathVariable int legajoAlumno,
                                                  @RequestHeader int idMateria,
                                                  @RequestHeader int legajoUsuario,
                                                  @RequestHeader String contrasenia) throws Exception{

        Materia materiaAsignar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaAsignar = Busqueda.materiaCursando(alumnoEncontrado, idMateria);


                if (materiaAsignar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasCursando().remove(idMateria); //quitamos el cursado de la materia
                    alumnoEncontrado.getListaMateriasRegular().put(idMateria, materiaAsignar); //ahora la tiene ne regular
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new EntityNotFoundException();


            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaAsignar);
    }


    //obtener lista de materias regular del alumno
    @GetMapping("/alumno/listado/materias/regular")
    ResponseEntity<Map<Integer, Materia>> listarMateriasRegular(@RequestHeader int legajoUsuario,
                                                                @RequestHeader String contrasenia) throws Exception{


        try {

            if (Login.validarAlumno(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaMateriasRegular(legajoUsuario));

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/alumno/{legajoAlumno}/eliminar/regular") //admin asigna una materia aprobada al alumno
    ResponseEntity<Materia> eliminarMateriaRegular(@PathVariable int legajoAlumno,
                                                   @RequestHeader int idMateria,
                                                   @RequestHeader int legajoUsuario,
                                                   @RequestHeader String contrasenia) throws Exception{

        Materia materiaEliminar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaEliminar = Busqueda.materiaRegular(alumnoEncontrado, idMateria);

                if (materiaEliminar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasRegular().remove(idMateria); //quitamos el cursado de la materia
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaEliminar);
    }



    @PutMapping("/alumno/{legajoAlumno}/asignar/aprobada") //admin asigna una materia aprobada al alumno
    ResponseEntity<Materia> asignarMateriaAprobada(@PathVariable int legajoAlumno,
                                                   @RequestHeader int idMateria,
                                                   @RequestHeader int legajoUsuario,
                                                   @RequestHeader String contrasenia) throws Exception{

        Materia materiaAsignar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaAsignar = Busqueda.materiaRegular(alumnoEncontrado, idMateria);

                if (materiaAsignar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasRegular().remove(idMateria); //quitamos el cursado de la materia
                    alumnoEncontrado.getListaMateriasAprobadas().put(idMateria, materiaAsignar); //ahora la tiene ne regular
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new EntityNotFoundException();

            }
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaAsignar);
    }

    //obtener lista de materias aprobadas del alumno
    @GetMapping("/alumno/listado/materias/aprobadas")
    ResponseEntity<Map<Integer, Materia>> listarMateriasAprobadas(@RequestHeader int legajoUsuario,
                                                                  @RequestHeader String contrasenia) throws Exception{


        try {
            if (Login.validarAlumno(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaMateriasAprobadas(legajoUsuario));

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/alumno/{legajoAlumno}/eliminar/aprobada") //admin asigna una materia aprobada al alumno
    ResponseEntity<Materia> eliminarMateriaAprobada(@PathVariable int legajoAlumno,
                                                    @RequestHeader int idMateria,
                                                    @RequestHeader int legajoUsuario,
                                                    @RequestHeader String contrasenia) throws Exception{

        Materia materiaEliminar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaEliminar = Busqueda.materiaAprobada(alumnoEncontrado, idMateria);

                if (materiaEliminar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasAprobadas().remove(idMateria); //quitamos el cursado de la materia
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new Exception();

            }else throw new Exception();

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaEliminar);
    }

    @PutMapping("/alumno/inscribirse/cursado/{idMateria}")
    ResponseEntity<Materia> inscribirseACursado(@PathVariable int idMateria,
                                                @RequestHeader int legajoAlumno,
                                                @RequestHeader String contrasenia) throws Exception{

        Carrera carreraDeAlumno = null;
        Materia materiaInscribirse = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAlumno(legajoAlumno, contrasenia)) {


                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                carreraDeAlumno = alumnoEncontrado.getCarrera();

                if (carreraDeAlumno.getListaMaterias() != null && carreraDeAlumno.getListaMaterias().containsKey(idMateria)) {
                    materiaInscribirse = carreraDeAlumno.getListaMaterias().get(idMateria);
                }


                if (materiaInscribirse != null) {

                    if (Inscripcion.cursado(alumnoEncontrado, materiaInscribirse)) {
                        alumnoEncontrado.getListaMateriasCursando().put(materiaInscribirse.getId(), materiaInscribirse); //asignamos a su lista de regulares
                        ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                    }else throw new Exception(); //para capturar un badrequest

                } else throw new EntityNotFoundException(); //arrojamos una excepcion para json

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build(); //capturamos y mandamos un not found a json
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build(); //para informar que no es posible inscribir
        }
        return ResponseEntity.ok(materiaInscribirse);
    }

    //obtener lista de materias cursando del alumno
    @GetMapping("/alumno/listado/materias/cursando")
    ResponseEntity<Map<Integer, Materia>> listarMateriasCursando(@RequestHeader int legajoUsuario,
                                                                 @RequestHeader String contrasenia) throws Exception{


        try {
            if (Login.validarAlumno(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaMateriasCursando(legajoUsuario));

            } else throw new Exception();

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/alumno/eliminar/cursando/{legajoAlumno}") //admin asigna una materia aprobada al alumno
    ResponseEntity<Materia> eliminarMateriaCursando(@PathVariable int legajoAlumno,
                                                    @RequestHeader int idMateria,
                                                    @RequestHeader int legajoUsuario,
                                                    @RequestHeader String contrasenia) throws Exception{

        Materia materiaEliminar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaEliminar = Busqueda.materiaCursando(alumnoEncontrado, idMateria);


                if (materiaEliminar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasCursando().remove(idMateria); //quitamos el cursado de la materia
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new Exception();



            }else throw new Exception();

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaEliminar);
    }



    @PutMapping("/alumno/inscribirse/final/{idMateria}")
    ResponseEntity<Materia> inscribirseAFinal(@PathVariable int idMateria,
                                              @RequestHeader int legajoAlumno,
                                              @RequestHeader String contrasenia) throws Exception{

        Materia materiaInscribirse = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAlumno(legajoAlumno, contrasenia)) {


                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaInscribirse = Busqueda.materiaRegular(alumnoEncontrado, idMateria);


                if (materiaInscribirse != null) {

                    if (Inscripcion.inscribirseFinal(alumnoEncontrado, materiaInscribirse)) {
                        alumnoEncontrado.getListaMateriasRendirFinal().put(materiaInscribirse.getId(), materiaInscribirse); //asignamos a su lista de regulares
                        ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                    }else throw new Exception(); //para capturar un badrequest

                } else throw new EntityNotFoundException(); //arrojamos una excepcion para json

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build(); //capturamos y mandamos un not found a json
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build(); //para informar que no es posible inscribir
        }
        return ResponseEntity.ok(materiaInscribirse);
    }

    //obtener lista de materias cursando del alumno
    @GetMapping("/alumno/listado/materias/final")
    ResponseEntity<Map<Integer, Materia>> listarMateriasARendirFinal(@RequestHeader int legajoUsuario,
                                                                     @RequestHeader String contrasenia) throws Exception{

        Alumno alumno = null;

        try {
            if (Login.validarAlumno(legajoUsuario, contrasenia)) {

                return ResponseEntity.ok(Busqueda.listaMateriasFinal(legajoUsuario));

            } else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/alumno/eliminar/final/{legajoAlumno}") //admin asigna una materia aprobada al alumno
    ResponseEntity<Materia> eliminarMateriaFinal(@PathVariable int legajoAlumno,
                                                 @RequestHeader int idMateria,
                                                 @RequestHeader int legajoUsuario,
                                                 @RequestHeader String contrasenia) throws Exception{

        Materia materiaEliminar = null;
        Alumno alumnoEncontrado = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaEliminar = Busqueda.materiaFinal(alumnoEncontrado, idMateria);

                if (materiaEliminar != null && alumnoEncontrado != null) {

                    alumnoEncontrado.getListaMateriasRendirFinal().remove(idMateria); //borramos la materia
                    ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);

                }else throw new EntityNotFoundException();



            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materiaEliminar);
    }

    //insertamos un examen con su nota en la materia del alumno
    @PutMapping("alumno/{legajoAlumno}/asignar/examen/parcial")
    ResponseEntity<Examen> asignarExamenParcial (@PathVariable int legajoAlumno,
                                                 @RequestBody Examen examen,
                                                 @RequestHeader int idMateria,
                                                 @RequestHeader int legajoUsuario,
                                                 @RequestHeader String contrasenia)throws Exception{


        Alumno alumnoEncontrado = null;
        Materia materiaCursando = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaCursando = Busqueda.materiaCursando(alumnoEncontrado, idMateria);


                if (alumnoEncontrado != null && materiaCursando != null) {

                    if(Verificacion.examenParcial(examen)){
                        alumnoEncontrado.getListaMateriasCursando().get(idMateria).getListaExamenes().add(examen);
                        ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);
                    }else throw new Exception();

                } else throw new EntityNotFoundException();

            }else throw new EntityNotFoundException();

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();//si el examen no coincide con el estado de la materia
        }

        return ResponseEntity.ok(examen);

    }



    @PutMapping("alumno/asignar/examen/final/{legajoAlumno}")
    ResponseEntity<Examen> asignarExamenFinal(@PathVariable int legajoAlumno,
                                              @RequestBody Examen examen,
                                              @RequestHeader int idMateria,
                                              @RequestHeader int legajoUsuario,
                                              @RequestHeader String contrasenia)throws Exception{


        Alumno alumnoEncontrado = null;
        Materia materiaAprobada = null;

        try {
            if (Login.validarAdministrador(legajoUsuario, contrasenia)) {

                alumnoEncontrado = Busqueda.encontrarAlumno(legajoAlumno);
                materiaAprobada = Busqueda.materiaAprobada(alumnoEncontrado, idMateria);

                if (alumnoEncontrado != null && materiaAprobada != null) {
                    System.out.println("ni el alumno ni la materia son nulos");
                    if(Verificacion.examenFinal(examen)){
                        System.out.println("el examen es correcto");
                        alumnoEncontrado.getListaMateriasAprobadas().get(idMateria).getListaExamenes().add(examen);
                        ABM.modificarAlumno(alumnoEncontrado, alumnoEncontrado);
                    }else throw new Exception();

                } else throw new EntityNotFoundException();


            }else throw new EntityNotFoundException();


        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build(); //si el examen no coincide con el estado de la materia
        }
        return ResponseEntity.ok(examen);

    }




}
