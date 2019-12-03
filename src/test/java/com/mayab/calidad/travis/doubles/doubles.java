package com.mayab.calidad.travis.doubles;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.calidad.travis.doubles.Alumno;
import com.mayab.calidad.travis.doubles.AlumnoDAO;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.util.HashMap;

public class doubles{
	AlumnoDAO doubles;
	HashMap<Integer, Alumno> alumnos = new HashMap<Integer, Alumno>();
	
	@Before
	public void setUp() throws Exception {
		doubles = Mockito.mock(AlumnoDAO.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testInsert() {
		
		when(doubles.addAlumno(any(Alumno.class))).thenAnswer(new Answer<String>() {

			public String answer(InvocationOnMock invocation) throws Throwable {
				int before = alumnos.size();
				alumnos.put(0, new Alumno("001", "Ed", "Sheeran", 21, 9.1f));
				int after = alumnos.size();
				if(before == after)
					return "No se pudo agregar";
				else
					return "Se agrego alumno";
				
			}
		});
		assertThat(doubles.addAlumno(new Alumno("001", "Sheeran", "Sheeran", 21, 9.1f)), is(equalTo("Se agrego alumno")));
	}
	
	
	@Test
	public void testRemove() {
		when(doubles.removeAlumno(any(Alumno.class))).thenAnswer(new Answer<String>() {public String answer(InvocationOnMock invocation) throws Throwable {			
				alumnos.put(0, new Alumno("001", "Ed", "Sheeran", 21, 9.1f));
				int before = alumnos.size();
				alumnos.remove(0);
				int after = alumnos.size();
				if(after == before - 1) {
					return "Se elimino alumno";
				}
					
				else {
					return "No se pudo eliminar alumno";
				}
					
			}
			
		});
		assertThat(doubles.removeAlumno(new Alumno("001", "Ed", "Sheeran", 21, 9.1f)), is(equalTo("Se elimino alumno")));
	}
	@Test
	public void testUpdate() {
		when(doubles.updateAlumnoPromedio(any(Alumno.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				alumnos.put(0, new Alumno("001", "Ed", "Sheeran", 21, 9.1f));
				Alumno student = alumnos.get(0);
				double before = student.getAverage();
				student.setAverage(9.5f);
				double after = student.getAverage();
				if(before != after) {
					return "Se actualizo promedio";
				}else {
					return "No se pudo actualizar promedio";
				}
				
			}
			
		});
		assertThat(doubles.updateAlumnoPromedio(new Alumno("002", "Ricardo", "Hernan", 21, 10.0f)), is(equalTo("Se actualizo promedio")));
	}
	
	
	@Test
	public void testGet() {
		when(doubles.getAlumno(any(Alumno.class))).thenAnswer(new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				alumnos.put(0, new Alumno("001", "Ed", "Sheeran", 21, 9.1f));
				Alumno student = alumnos.get(0);
				String before = student.getName();
				student.setAverage(9.5f);
				String after = student.getName();
				if(before == after) {
					return "Se obtuvo el alumno";
				}else {
					return "No se obtuvo";
				}
				
			}
			
		});
		assertThat(doubles.getAlumno(new Alumno("002", "Ricardo", "Hernan", 21, 10.0f)), is(equalTo("Se obtuvo el alumno")));
	}
}
