/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.dbunit;

/**
 *
 * @author Angel
 */

public interface AlumnoDAO {
	public void addAlumno(Alumno alumno);
	public void removeAlumno(String Alumno, String lastName);
	public void updateAlumnoPromedio(Alumno alumno, Float promedio);
	public void getAlumno(String id);
	
}
