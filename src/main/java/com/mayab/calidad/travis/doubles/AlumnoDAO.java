package com.mayab.calidad.travis.doubles;
public interface AlumnoDAO {
	public String addAlumno(Alumno alumno);
	public String removeAlumno(Alumno Alumno);
	public String updateAlumnoPromedio(Alumno alumno);
	public boolean getAlumno(String id);
	
}
