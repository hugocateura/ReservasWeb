package datos;
import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.Level;

import utilidades.ExcepcionEspecial;


public class FactoryConnection implements Serializable{
	private String driver="com.mysql.jdbc.Driver";
	private String host="node148140-sysres.jelasticlw.com.br"; //agregar node148140-sysres.jelasticlw.com.br cuando lo subimos al servidor //
	private String port="3306";
	private String user="admin";
	private String password="admin";
	private String db="bd_reserva";
	private Connection conn=null;
	private int cantConn=0;
	
	private static FactoryConnection instancia; 	//para tener solo y exclusivamente un único objeto de una clase.
	
	private FactoryConnection() throws ClassNotFoundException, Exception
	{
		try 
		{
			//para definir un drier y despues usarlo en una conexion.  
			//poder usar distintos driver solo cambiando el string entre las comillas.
			Class.forName(driver); 
		} 
		catch (ClassNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	public static FactoryConnection getinstancia() throws ClassNotFoundException, Exception //devuelve la unica conexion
	{
		if (FactoryConnection.instancia == null){FactoryConnection.instancia=new FactoryConnection();}
		return FactoryConnection.instancia;
	} 
	
	
	public Connection getConn() throws Exception, SQLException
	{
		
		try 
		{
			if(conn==null || conn.isClosed()){
			conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password+"&useSSL=false");}
		} 
		catch (SQLException e) 
		{
			throw new ExcepcionEspecial(e,"Error al intentar conectarse a la Base de Datos", Level.ERROR);
		}
		catch (Exception e) 
		{
			throw e;
		}
		cantConn++;
		return conn;
    }	
	
	public void releaseConn() throws SQLException,Exception{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		
	}
}