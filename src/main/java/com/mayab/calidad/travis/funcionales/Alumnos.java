package com.mayab.calidad.travis.funcionales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.PreparedStatement;


public class Alumnos implements AlumnoDAO{
	
	public String URL="jdbc:mysql://localhost/calidad"+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	
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
                Connection con= DriverManager.getConnection(URL, "root", "");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("INSERT INTO calidad.alumno VALUES" +"('"+id+"','"+name+"','"+lastName+"',"+age+","+avg+");");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}

	public void removeAlumno(String alumno, String lastName) {
            try{
                Connection con= DriverManager.getConnection(URL, "root", "");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("DELETE FROM calidad.alumno WHERE alumno_name='"+alumno+"' AND alumno_LastNameP='"+lastName+"';");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}

	public void updateAlumnoPromedio(Alumno alumno, Float promedio) {
		try{
                String alum;
                alum=alumno.getId();
                Connection con= DriverManager.getConnection(URL, "root", "");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("UPDATE calidad.alumno SET average = "+promedio+" WHERE "+ "alumno_id='"+alum+"';");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
		
	}

	public void getAlumno(String id) {
		try{
                Connection con= DriverManager.getConnection(URL, "root", "");
                Statement st;
                st= con.createStatement();
		String query = "SELECT *FROM calidad.alumno WHERE alumno_id= '"+id+"';";
                PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.execute();
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
	}
}

	
