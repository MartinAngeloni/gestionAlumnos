package com.sysacad.utn;

import com.sysacad.model.*;
import com.sysacad.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sysacad.repositorios.CarreraRepositorio.repositorioCarrera;
import static com.sysacad.repositorios.MateriaRepositorio.repositorioMateria;
import static com.sysacad.repositorios.PersonaRepositorio.repositorioPersona;
import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysacadutnApplicationTests {

	@Before
	public void setUp() throws Exception {

		Examen examen = new Examen();




		Administrador administradorUnico = new Administrador(1, "admin26");
		repositorioPersona.add(administradorUnico);

		Alumno alumno = new Alumno("Martin", "Angeloni", 22100, "jack24");
		Materia materia2 = new Materia("ProgramacionI", 1);
		alumno.getListaMateriasCursando().put(1, materia2);
		alumno.getListaMateriasRegular().put(1, materia2);
		alumno.getListaMateriasAprobadas().put(1, materia2);
		alumno.getListaMateriasRendirFinal().put(1, materia2);
		Carrera carrera2 = new Carrera("TSP", 2);
		carrera2.getListaMaterias().put(1, materia2);
		alumno.setCarrera(carrera2);
		repositorioPersona.add(alumno);

		Carrera carrera = new Carrera("Tecnico Superior en Programacion", 1);
		Materia materia = new Materia("ProgramacionI", 1);
		repositorioMateria.add(materia);
		carrera.getListaMaterias().put(1, materia);
		carrera.getListaAlumnos().put(22100, alumno);

		repositorioCarrera.add(carrera);

	}

	@Test
	public void loginValido() {
		Boolean resultado = Login.validarUsuario(1,"admin26");
		Boolean esperado = true;
		assertTrue(esperado == resultado);
	}

	@Test
	public void loginInvalido() {
		Boolean resultado = Login.validarUsuario(2,"asd");
		Boolean esperado = false;
		assertTrue(esperado == resultado);
	}

	@Test
	public void loginAdministradorValido(){

		boolean resultado = Login.validarAdministrador(1, "admin26");
		boolean esperado = true;
		assertTrue(esperado == resultado);

	}

	@Test
	public void loginAdministradorInvalido(){

		boolean resultado = Login.validarAdministrador(2, "asd");
		boolean esperado = false;
		assertTrue(esperado == resultado);

	}

	@Test
	public void loginAlumnoValido(){

		boolean resultado = Login.validarAlumno(22100, "jack24");
		boolean esperado = true;
		assertTrue(esperado == resultado);
	}

	@Test
	public void loginAlumnoInvalido(){

		boolean resultado = Login.validarAlumno(1, "admin26");
		boolean esperado = false;
		assertTrue(esperado == resultado);
	}

	@Test
	public void encontrarAlumno(){

		Alumno alumno = new Alumno("Martin", "Angeloni", 22100, "jack24");
		Alumno alumnoPrueba = Busqueda.encontrarAlumno(22100);
		assertEquals(alumnoPrueba, alumno);
	}

	@Test
	public void listarAlumnos(){

		List<Alumno> listaAlumnos = new ArrayList<>();

		listaAlumnos = Busqueda.listaAlumnos();

		assertEquals(1, listaAlumnos.size());

	}

	@Test
	public void encontrarCarrera(){

		Carrera carrera = repositorioCarrera.get(0);

		Carrera carreraPrueba = Busqueda.encontrarCarrera(1);
		assertEquals(carreraPrueba, carrera);

	}

	@Test
	public void carreraListaMaterias(){

		Map<Integer, Materia> listaMaterias = new HashMap<>();

		listaMaterias = Busqueda.carreraListaMaterias(1);

		assertEquals(1, listaMaterias.size());

	}

	@Test
	public void carreraListaAlumnos(){

		Map<Integer, Alumno> listaAlumnos = new HashMap<>();
		listaAlumnos = Busqueda.carreraListaAlumnos(1);

		assertEquals(1, listaAlumnos.size());

	}

	@Test
	public void listaCarreras(){

		List<Carrera> listaCarrera = new ArrayList<>();
		listaCarrera = Busqueda.listaCarreras();

		assertEquals(1, listaCarrera.size());

	}

	@Test
	public void encontrarMateria(){

		Materia materia = repositorioMateria.get(0);
		Materia materiaResultado = Busqueda.encontrarMateria(1);

		assertEquals(materiaResultado, materia);

	}


	@Test
	public void listaMaterias(){

		List<Materia> listaMateria = new ArrayList<>();
		listaMateria = Busqueda.listaMaterias();

		assertEquals(1, listaMateria.size());

	}

	//verificar si tiene materia cursando
	@Test
	public void alumnoMateriaCursando(){

		Materia materiaEsperada = repositorioMateria.get(0);
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		Materia materiaResultado = Busqueda.materiaCursando(alumno, 1);

		assertEquals(materiaResultado, materiaEsperada);

	}

	//verifico el caso opuesto de la materia cursando
	@Test
	public void alumnoMateriaCursandoOpuesto(){


		Materia materiaEsperada = new Materia("LABII", 2);
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		Materia materiaResultado = Busqueda.materiaCursando(alumno, 1);

		assertFalse(materiaEsperada.equals(materiaResultado));

	}

	//verificamos que tenga una lista de materais cursando
	@Test
	public void alumnoListaMateriasCursando(){


		Map<Integer, Materia> listaMateriasCursando = new HashMap<>();
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		listaMateriasCursando = Busqueda.listaMateriasCursando(22100);

		assertEquals(1, listaMateriasCursando.size());
	}

	@Test
	public void alumnoMateriaRegular(){


		Materia materiaRegular;
		Materia materia2 = new Materia("ProgramacionI", 1);
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		materiaRegular = Busqueda.materiaRegular(alumno, 1);

		assertEquals(materia2, materiaRegular);
	}


	@Test
	public void alumnoListaMateriasRegular(){


		Map<Integer, Materia> listaMateriasRegular = new HashMap<>();
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		listaMateriasRegular = Busqueda.listaMateriasRegular(22100);

		assertEquals(1, listaMateriasRegular.size());
	}


	@Test
	public void alumnoListaMateriasAprobadas(){

		Map<Integer, Materia> listaMateriasAprobadas = new HashMap<>();
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		listaMateriasAprobadas = Busqueda.listaMateriasAprobadas(22100);

		assertEquals(1, listaMateriasAprobadas.size());
	}

	@Test
	public void alumnoMateriaAprobada(){


		Materia materiaAprobada;
		Materia materia2 = new Materia("ProgramacionI", 1);
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		materiaAprobada = Busqueda.materiaAprobada(alumno, 1);

		assertEquals(materia2, materiaAprobada);
	}


	@Test
	public void alumnoListaMateriasFinal(){

		Map<Integer, Materia> listaMateriasFinal = new HashMap<>();
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		listaMateriasFinal = Busqueda.listaMateriasFinal(22100);

		assertEquals(1, listaMateriasFinal.size());
	}

	@Test
	public void alumnoMateriaFinal(){


		Materia materiaFinal;
		Materia materia2 = new Materia("ProgramacionI", 1);
		Alumno alumno = (Alumno) repositorioPersona.get(1); //cero es admin

		materiaFinal = Busqueda.materiaAprobada(alumno, 1);

		assertEquals(materia2, materiaFinal);
	}

	@Test
	public void inscripcionCursado(){

		boolean permiso;
		Materia materia2 = new Materia("ProgramacionI", 1);
		Alumno alumno = (Alumno) repositorioPersona.get(1);

		permiso = Inscripcion.cursado(alumno, materia2);

		assertTrue(permiso == true);

	}



	@Test
	public void inscripcionFinal(){

		boolean permiso;
		Materia materia2 = new Materia("ProgramacionI", 1);
		Alumno alumno = (Alumno) repositorioPersona.get(1);

		permiso = Inscripcion.inscribirseFinal(alumno, materia2);

		assertTrue(permiso == true);
	}

	@Test
	public void verificarExamenParcial(){

		boolean permisoExamen;
		Examen examen = new Examen("perimerExamen", 10, TipoExamen.parcial);

		permisoExamen = Verificacion.examenParcial(examen);

		assertTrue(permisoExamen == true);

	}

	@Test
	public void verificarExamenParcialRecuperatorio(){

		boolean permisoExamen;
		Examen examen = new Examen("perimerExamen", 10, TipoExamen.recuperatorio);

		permisoExamen = Verificacion.examenParcial(examen);

		assertTrue(permisoExamen == true);

	}

	//verificamos un examene final correcto
	@Test
	public void verificarExamenFinal(){

		boolean permisoExamen;
		Examen examen = new Examen("perimerExamen", 10, TipoExamen.mesaFinal);

		permisoExamen = Verificacion.examenFinal(examen);

		assertTrue(permisoExamen == true);

	}

	@Test
	public void verificarHorario(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 6, 45, 23, 0);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == true);
	}

	//verificamos horario con un horario incorrecto
	@Test
	public void verificarHorarioIncorrecto(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 18, 45, 17, 0);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == false);
	}

	//verificamos horario con un minuto inicio incorrecto
	@Test
	public void verificarHorarioIncorrecto2(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 18, 70, 23, 0);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == false);
	}

	//verificamos horario con un minuto fin incorrecto
	@Test
	public void verificarHorarioIncorrecto3(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 18, 5, 23, 60);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == false);
	}

	//verificamos horario con un horario inicio incorrecto
	@Test
	public void verificarHorarioIncorrecto4(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 25, 45, 17, 0);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == false);
	}

	//verificamos horario con un horario fin incorrecto
	@Test
	public void verificarHorarioIncorrecto5(){

		boolean horarioCorrecto;
		Horario horario = new Horario(Dia.jueves, 17, 45, 25, 0);

		horarioCorrecto = Verificacion.validarHorario(horario);

		assertTrue(horarioCorrecto == false);
	}

	@After
	public void after(){

		repositorioPersona.clear();
		repositorioCarrera.clear();
		repositorioMateria.clear();

	}




	/*
	@Test
	public void validarHorario() {

		Boolean resultado = Verificacion.validarHorario();
		Boolean esperado = true;
		assertTrue(esperado == resultado);
	}
	*/

}
