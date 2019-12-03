package com.mayab.calidad.travis.dbunit;
public interface AlumnoDAO {
	public String addAlumno(Alumno alumno);
	public String removeAlumno(String Alumno, String lastName);
	public String updateAlumnoPromedio(Alumno alumno, Float promedio);
	public boolean getAlumno(String id);
	
}
