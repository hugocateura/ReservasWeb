package datos;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entidades.*;
import utilidades.ExcepcionEspecial;


public class DatosPersona implements Serializable 
{
	
	public ArrayList<Persona> buscarTodo() throws Exception,SQLException
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("select id,dni,nombre,apellido,usuario,contrasena,habilitado,categoria from personas");
			if(rs!=null){
				while(rs.next()){
					Persona persona=new Persona();
					persona.setId(rs.getInt("id"));
					persona.setDni(rs.getString("dni"));
					persona.setNombre(rs.getString("nombre"));
					persona.setApellido(rs.getString("apellido"));
					persona.setUsuario(rs.getString("usuario"));
					persona.setContrasena(rs.getString("contrasena"));
					persona.setHabilitado(rs.getBoolean("habilitado"));
					persona.setCategoria(rs.getString("categoria"));
					pers.add(persona);
				}
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);
		}catch (Exception ex){
			throw ex;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			
			throw e;
		}
		
		return pers;
		
	}
	
	public ArrayList<Persona> buscarUsuariosExternos() throws Exception,SQLException
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		ArrayList<Persona> personas= new ArrayList<Persona>();
		
		try 
		{
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("select id,dni,nombre,apellido,usuario,contrasena,habilitado,categoria from personas where categoria = ?");
			pstm.setString(1, "Online");
			rs = pstm.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Persona persona=new Persona();
					persona.setId(rs.getInt("id"));
					persona.setDni(rs.getString("dni"));
					persona.setNombre(rs.getString("nombre"));
					persona.setApellido(rs.getString("apellido"));
					persona.setUsuario(rs.getString("usuario"));
					persona.setContrasena(rs.getString("contrasena"));
					persona.setHabilitado(rs.getBoolean("habilitado"));
					persona.setCategoria(rs.getString("categoria"));
					personas.add(persona);
				}
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona externa en la base de datos", Level.ERROR);
		}catch (Exception e) {
			throw e;
		}
	
		try {
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		return personas;
	}
	
	
	public void agregarPersona (Persona pers) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO personas(dni,nombre,apellido,usuario,contrasena,habilitado,categoria) VALUES (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1, pers.getDni());
			pstm.setString(2, pers.getNombre());
			pstm.setString(3, pers.getApellido());
			pstm.setString(4, pers.getUsuario());
			pstm.setString(5, pers.getContrasena());
			pstm.setBoolean(6, pers.isHabilitado());
			pstm.setString(7, pers.getCategoria());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				pers.setId(rs.getInt(1));
			}
		}
		catch (Exception e) {
			throw new ExcepcionEspecial(e,"No se puede dar de alta, ya existe el usuario", Level.ERROR);
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void eliminarPersona(Persona perselimina) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"DELETE FROM personas WHERE id=?");
			pstm.setInt(1, perselimina.getId());
			pstm.executeUpdate();
		}
		catch (MySQLIntegrityConstraintViolationException exc) {
			throw new ExcepcionEspecial(exc,"Imposible eliminar,  la Persona posee reservas", Level.ERROR);
		}  
		catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"Imposible eliminar,  la Persona posee reservas", Level.ERROR);
		} 
		catch(Exception e){
			throw e;		
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception exc) {
			throw exc;			
		}		
	}
	
	public void modificarPersona(Persona persmodifica) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE personas SET dni=?, nombre=?,apellido=?,usuario=?,contrasena=?,categoria=?,habilitado=? WHERE id=?");
			pstm.setString(1, persmodifica.getDni());
			pstm.setString(2, persmodifica.getNombre());
			pstm.setString(3, persmodifica.getApellido());
			pstm.setString(4, persmodifica.getUsuario());
			pstm.setString(5, persmodifica.getContrasena());
			pstm.setString(6, persmodifica.getCategoria());
			pstm.setBoolean(7, persmodifica.isHabilitado());
			pstm.setInt(8, persmodifica.getId());
			pstm.executeUpdate();
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible modificar una persona en la base de datos", Level.ERROR);
		}catch (Exception e){
			throw e;
		}
		
		finally
		{
			try {
				if(pstm!=null)pstm.close();
				FactoryConnection.getinstancia().releaseConn();
			} catch (Exception e) {
				throw e;
			}	
		}
		
	}



	public Persona buscarPersonaPorUsuyClave(Persona pers) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Persona persona=null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM personas WHERE usuario=? AND contrasena=?");
			pstm.setString(1, pers.getUsuario());
			pstm.setString(2, pers.getContrasena());
			rs=pstm.executeQuery();
			if(rs!=null)
			{	rs.next();
					persona=new Persona();
					persona.setId(rs.getInt("id"));
					persona.setDni(rs.getString("dni"));
					persona.setNombre(rs.getString("nombre"));
					persona.setApellido(rs.getString("apellido"));
					persona.setUsuario(rs.getString("usuario"));
					persona.setContrasena(rs.getString("contrasena"));
					persona.setHabilitado(rs.getBoolean("habilitado"));
					persona.setCategoria(rs.getString("categoria"));
				
			}
			else{
				throw new Exception();
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
		return persona;
	}
	
	public Persona getPersona(Persona pers) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Persona persona=null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM personas WHERE id=?");
			pstm.setInt(1, pers.getId());
			rs=pstm.executeQuery();
			if(rs!=null)
			{	rs.next();
					persona=new Persona();
					persona.setId(rs.getInt("id"));
					persona.setDni(rs.getString("dni"));
					persona.setNombre(rs.getString("nombre"));
					persona.setApellido(rs.getString("apellido"));
					persona.setUsuario(rs.getString("usuario"));
					persona.setContrasena(rs.getString("contrasena"));
					persona.setHabilitado(rs.getBoolean("habilitado"));
					persona.setCategoria(rs.getString("categoria"));				
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una persona en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
				
		try {
			if(pstm!=null)pstm.close();
			if(rs!=null)rs.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
		return persona;
	}
}

