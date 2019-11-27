/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.travis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class Alumnos implements AlumnoDAO{
	
	public String URL="jdbc:mysql://localhost:3306/calidad2"+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	
	public void addAlumno(Alumno alumno) {
            try{
                String name,lastName,id;
                Integer age;
                Float avg;
                id=alumno.getId();
                name=alumno.getName();
                lastName=alumno.getLastName();
                age=alumno.getAge();
                avg=alumno.getAverage();
                Connection con= DriverManager.getConnection(URL, "calidaduser", "FoXterr1er");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("INSERT INTO alumno2 VALUES" +"('"+id+"','"+name+"','"+lastName+"',"+age+","+avg+");");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}

	public void removeAlumno(String alumno, String lastName) {
            try{
                Connection con= DriverManager.getConnection(URL, "calidaduser", "FoXterr1er");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("DELETE FROM alumno2 WHERE alumno_name='"+alumno+"' AND alumno_LastNameP='"+lastName+"';");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}

	public void updateAlumnoPromedio(Alumno alumno, Float promedio) {
		try{
                String alum;
                alum=alumno.getId();
                Connection con= DriverManager.getConnection(URL, "calidaduser", "FoXterr1er");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("UPDATE alumno2 SET average = "+promedio+" WHERE "+ "alumno_id='"+alum+"';");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
		
	}

	public void getAlumno(String id) {
		try{
                Connection con= DriverManager.getConnection(URL, "calidaduser", "FoXterr1er");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("SELECT *FROM alumno2 WHERE alumno_id= '"+id+"';");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}
}

	
