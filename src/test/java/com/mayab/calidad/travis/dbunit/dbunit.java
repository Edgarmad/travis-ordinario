package com.mayab.calidad.travis.dbunit;
import com.mayab.calidad.travis.funcionales.Alumno;
import com.mayab.calidad.travis.funcionales.AlumnoDAO;
import com.mayab.calidad.travis.funcionales.Alumnos;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class dbunit extends DBTestCase {
    
    public String URL="jdbc:mysql://localhost:3306/calidad"+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public dbunit(String name){
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, URL);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
                    try{
                Connection con= DriverManager.getConnection(URL, "root", "");
                Statement st;
                st= con.createStatement();
                int isEx = st.executeUpdate("INSERT INTO alumno(alumno_name,alumno_LastNameP,age,average) VALUES" +"('Alex','Diaz',19,8.7);");
                con.close(); 
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    
    public dbunit() {
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        
        InputStream xmlFile= getClass().getResourceAsStream("/data.xml");
        
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
    public void testInsert()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("10","Carlos","Peralta",22,79.7f);
        Alumnos dao= new Alumnos();
        dao.addAlumno(a);
        assertEquals(4,conn.getRowCount("alumno"));
        conn.close();
    }
    @Test
    public void testRemove()throws Exception{
        IDatabaseConnection conn = getConnection();
        Alumno a = new Alumno("12","Raul","Peralta",22,79.7f);
        Alumnos dao= new Alumnos();
        dao.removeAlumno("Raul", "Peralta");
	assertEquals(3,conn.getRowCount("alumno"));
        conn.close();
    }
    @Test
    public void testUpdate()throws Exception{
	try
	{
           Connection con= DriverManager.getConnection(URL, "root", "");
           Statement st;
           st= con.createStatement();
           int isEx = st.executeUpdate("UPDATE alumno set average = 9.9 where alumno_id = 1");
           con.close();
        }catch (Exception e)
		{
           e.printStackTrace();
        }
	IDataSet databaseDataSet = getConnection().createDataSet();
	ITable actualTable = databaseDataSet.getTable("alumno");
	InputStream xmlFile = getClass().getResourceAsStream("/updatea.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
	ITable expectedTable = expectedDataSet.getTable("alumno");
        Assertion.assertEquals(expectedTable, actualTable);
    }
    @Test
    public void testGet()throws Exception{
        IDatabaseConnection conn = getConnection();
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumno");
        InputStream xmlFile = getClass().getResourceAsStream("/data.xml");
	IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(xmlFile);
	ITable expectedTable = expectedDataSet.getTable("alumno");
        Assertion.assertEquals(expectedTable, actualTable);
        conn.close();
    }
    
    
}