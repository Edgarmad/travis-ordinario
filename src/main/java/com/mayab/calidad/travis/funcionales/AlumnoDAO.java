package com.mayab.calidad.travis.funcionales;
public interface AlumnoDAO {
	public void addAlumno(Alumno alumno);
	public void removeAlumno(String Alumno, String lastName);
	public void updateAlumnoPromedio(Alumno alumno, Float promedio);
	public void getAlumno(String id);
	
}
