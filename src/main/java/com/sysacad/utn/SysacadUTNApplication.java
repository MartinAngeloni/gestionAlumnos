package com.sysacad.utn;

import com.sysacad.controller.AlumnoController;
import com.sysacad.controller.MateriaController;
import com.sysacad.model.Administrador;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;

@SpringBootApplication
@ComponentScan(basePackageClasses = MateriaController.class)
@ComponentScan(basePackageClasses = AlumnoController.class)
public class SysacadUTNApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SysacadUTNApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Administrador administradorUnico = new Administrador();
		administradorUnico.setContrasenia("admin26");
		repositorioPersona.add(administradorUnico);
	}

}

