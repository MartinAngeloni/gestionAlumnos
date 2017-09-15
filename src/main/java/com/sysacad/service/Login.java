package com.sysacad.service;


import com.sysacad.model.Administrador;
import com.sysacad.model.Alumno;
import com.sysacad.model.Persona;


import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;

/**
 * Created by martin on 20/4/2017.
 */
public class Login {

    //verifica un logeo de cualquier usuario
    public static boolean validarUsuario(int legajoUsuario, String contrasenia){

        for(Persona a: repositorioPersona){
            if(a.getLegajo() == legajoUsuario){
                if(a.getContrasenia().equals(contrasenia)){
                    return true;
                }
            }
        }
        return false;
    }

    //para verificar que sea administrador
    public static boolean validarAdministrador(int legajoUsuario, String contrasenia){

        for(Persona p: repositorioPersona){
            if(p.getLegajo() == legajoUsuario){
                if(p.getContrasenia().equals(contrasenia)){
                    if(p instanceof Administrador){
                        return true;
                    }
                }
            }
        }
        return false;


    }

    //para verificar que sea alumno
    public static boolean validarAlumno(int legajoUsuario, String contrasenia){

        for(Persona p: repositorioPersona){
            if(p.getLegajo() == legajoUsuario){
                if(p.getContrasenia().equals(contrasenia)){
                    if(p instanceof Alumno){
                        return true;
                    }
                }
            }
        }
        return false;


    }




}
