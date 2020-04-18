package cast.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cast.CastHelper;
import cast.exceptions.NotStructCompException;
import model.*;

public class CastTest {
	
	private static Docente d;
	//private static Empleado e1;
	private static EmpleadoAdministrativo em;
	private static Estudiante es;
	private static Persona p;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d=new Docente(1, "Edna Krabbapel",1000000, "Lenguas");
		em=new EmpleadoAdministrativo(2, "Dwight Kurt Schrute III", 1500000, "Assistant (TO THE) Regional Manager");
		es=new Estudiante(3, "Napoleon Dynamite", false);
		p=new Persona(4, "Jane Doe");
	}

	/***
	 * Al convertir de EmpleadoAdmin a Persona, se debe crear un nuevo objeto de la clase Persona con los mismo atributos del empleado
	 */
	@Test
	public void testEEmpleadoAdminPersona() {
		Object o=CastHelper.cast(em, Persona.class);
		assertNotNull(o);
		assertTrue(o.getClass().equals(Persona.class));
		Persona p=(Persona)o;
		assertTrue(p.getCodigo()==em.getCodigo());
		assertTrue(p.getNombre().equals(em.getNombre()));
	}
	
	/***
	 * Al convertir de Persona a Empleado se debe generar una excepción NotStructCompException ya que no son compatibles
	 */
	@Test(expected=NotStructCompException.class)
	public void testEPersonaEmpleado() {
		Object o=CastHelper.cast(p, EmpleadoAdministrativo.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN01() {
		Object o=CastHelper.cast(d, EmpleadoAdministrativo.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN02() {
		Object o=CastHelper.cast(d, Estudiante.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN03() {
		Object o=CastHelper.cast(p, Docente.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN04() {
		Object o=CastHelper.cast(p, EmpleadoAdministrativo.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN05() {
		Object o=CastHelper.cast(p, Empleado.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN06() {
		Object o=CastHelper.cast(p, Estudiante.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN07() {
		Object o=CastHelper.cast(em, Docente.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN08() {
		Object o=CastHelper.cast(em, Estudiante.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN09() {
		Object o=CastHelper.cast(es, Empleado.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN10() {
		Object o=CastHelper.cast(es, EmpleadoAdministrativo.class);
	}
	
	@Test(expected=NotStructCompException.class)
	public void testN11() {
		Object o=CastHelper.cast(es, Docente.class);
	}
	
	@Test
	public void testP01() {
		Object o=CastHelper.cast(d, Persona.class);
		assertNotNull(o);
		assertTrue(o.getClass().equals(Persona.class));
		Persona per=(Persona)o;
		assertTrue(per.getCodigo()==d.getCodigo());
		assertTrue(per.getNombre().equals(d.getNombre()));
	}
	
	@Test
	public void testP02() {
		Object o=CastHelper.cast(d, Empleado.class);
		assertNotNull(o);
		assertTrue(o.getClass().equals(Empleado.class));
		Empleado emp=(Empleado)o;
		assertTrue(emp.getCodigo()==d.getCodigo());
		assertTrue(emp.getNombre().equals(d.getNombre()));
		assertTrue(emp.getSalario()==d.getSalario());
	}
	
	@Test
	public void testP03() {
		Object o=CastHelper.cast(em, Empleado.class);
		assertNotNull(o);
		assertTrue(o.getClass().equals(Empleado.class));
		Empleado emp=(Empleado)o;
		assertTrue(emp.getCodigo()==em.getCodigo());
		assertTrue(emp.getNombre().equals(em.getNombre()));
		assertTrue(emp.getSalario()==em.getSalario());
	}
	
	@Test
	public void testP04() {
		Object o=CastHelper.cast(es, Persona.class);
		assertNotNull(o);
		assertTrue(o.getClass().equals(Persona.class));
		Persona per=(Persona)o;
		assertTrue(per.getCodigo()==es.getCodigo());
		assertTrue(per.getNombre().equals(es.getNombre()));
	}

}
