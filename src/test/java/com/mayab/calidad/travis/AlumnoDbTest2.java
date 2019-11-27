package com.mayab.calidad.travis;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static junit.framework.TestCase.assertEquals;
import org.dbunit.Assertion;
import org.junit.Test;
import static org.junit.Assert.*;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

/**
 *
 * @author Angel
 */
public class AlumnoDbTest2 extends DBTestCase {
    
    public String URL="jdbc:mysql://localhost:3306/calidad2"+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public AlumnoDbTest2(String name){
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, URL);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "calidaduser");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "FoXterr1er");
            try{
                Connection con= DriverManager.getConnection(URL, "calidaduser", "FoXterr1er");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("INSERT INTO alumno2(alumno_name,alumno_LastNameP,age,average) VALUES" +"('Alex','Diaz',19,8.7);");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }

    }

    
    public AlumnoDbTest2() {
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        
        InputStream xmlFile= getClass().getResourceAsStream("/Empty.xml");
        
        return new FlatXmlDataSetBuilder().build(xmlFile);
        
    }
    @Before
    public void setUp() throws Exception{
       super.setUp();
        IDatabaseConnection conn= getConnection();
        try{
            DatabaseOperation.CLEAN_INSERT.execute(conn, getDataSet());
        }finally{
            conn.close();
        }
    }
    @Test
    public void test1() throws Exception {
        IDatabaseConnection conn = getConnection();
        assertEquals(0, conn.getRowCount("alumno2"));
        conn.close();
    }
    @Test
    public void insertTest()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("10","Carlos","Peralta",22,79.7f);
        Alumnos dao= new Alumnos();
        dao.addAlumno(a);
        
        assertEquals(1,conn.getRowCount("alumno2"));
        conn.close();
        
    }
    @Test
    public void removeTest()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("12","Raul","Peralta",22,79.7f);
        Alumnos dao= new Alumnos();
        dao.removeAlumno("Raul", "Peralta");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumno2");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("Empty.xml"));
        ITable expectedTable = expectedDataSet.getTable("Empty");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
        conn.close();
//        IDatabaseConnection conn = getConnection();
//        Alumno a = new Alumno("12","Raul","Peralta",22,79.7f);
//        Alumnos dao= new Alumnos();
//        dao.removeAlumno("Raul", "Peralta");
//        
//        assertEquals(1,conn.getRowCount("alumno2"));
//        conn.close();   
    }
    @Test
    public void updateTest()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("13","Majo","Perez",22,79.7f);
       Alumnos dao= new Alumnos();
        dao.updateAlumnoPromedio(a, 85.9f);
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumno2");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("Update.xml"));
        ITable expectedTable = expectedDataSet.getTable("Empty");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
        conn.close();
//        IDatabaseConnection conn = getConnection();
//        Alumno a = new Alumno("13","Majo","Perez",22,79.7f);
//        Alumnos dao= new Alumnos();
//        dao.updateAlumnoPromedio(a, 85.9f);
//        
//        assertEquals(2,conn.getRowCount("alumno2"));
//        conn.close();   
    }
    @Test
    public void getTest()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("15","Carla","Pera",20,79.7f);
        AlumnoDAO dao= new Alumnos();
        dao.getAlumno("15");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumno2");

        // Load expected data from an XML dataset
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("Update.xml"));
        ITable expectedTable = expectedDataSet.getTable("Empty");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
        conn.close();
        
//        IDatabaseConnection conn = getConnection();
//        Alumno a = new Alumno("15","Carla","Pera",20,79.7f);
//        AlumnoDAO dao= new Alumnos();
//        dao.getAlumno("15");
//        
//        assertEquals(1,conn.getRowCount("alumno2"));
//        conn.close();   
    }
    
    
}
