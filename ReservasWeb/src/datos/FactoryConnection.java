package datos;
import java.sql.*;

import utilidades.ExcepcionesEscritorio;

public class FactoryConnection {
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="admin";
	private String password="admin";
	private String db="bd_reserva";
	private Connection conn=null;
	private int cantConn=0;
	
	private static FactoryConnection instancia; 	//para tener solo y exclusivamente un �nico objeto de una clase.
	
	private FactoryConnection() 
	{
		try 
		{
			//para definir un drier y despues usarlo en una conexion.  
			//poder usar distintos driver solo cambiando el string entre las comillas.
			Class.forName(driver); 
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static FactoryConnection getinstancia() //devuelve la unica conexion
	{
		if (FactoryConnection.instancia == null){FactoryConnection.instancia=new FactoryConnection();}
		return FactoryConnection.instancia;
	} 
	
	
	public Connection getConn() throws SQLException, ExcepcionesEscritorio
	{
		
		try 
		{
			if(conn==null || conn.isClosed()){
			conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password+"&useSSL=false");}
		} 
		catch (SQLException e) 
		{
			throw new ExcepcionesEscritorio(e,"Error al intentar conectarse a la Base de Datos");
		}
		cantConn++;
		return conn;
    }	
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}