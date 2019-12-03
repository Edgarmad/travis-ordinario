package com.mayab.calidad.travis.dbunit;
public interface AlumnoDAO {
	public Void addAlumno(Alumno alumno);
	public Void removeAlumno(String Alumno, String lastName);
	public Void updateAlumnoPromedio(Alumno alumno, Float promedio);
	public Void getAlumno(String id);
	
}
