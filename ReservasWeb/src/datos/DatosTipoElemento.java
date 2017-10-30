package datos;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entidades.*;
import utilidades.ExcepcionEspecial;

public class DatosTipoElemento implements Serializable
{
	
	public ArrayList<TipoElemento> buscarTodo(Persona pers) throws Exception
	{
		Statement stm=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> listadotipoelemento= new ArrayList<TipoElemento>();
		
		if(!((pers.getCategoria()).equals("Online"))){
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("select * from tipoelemento");
			if(rs!=null){
				while(rs.next()){
					TipoElemento tipoele=new TipoElemento();
					tipoele.setId(rs.getInt("id"));
					tipoele.setNombre(rs.getString("nombre"));
					tipoele.setCant_max_reservas(rs.getInt("cant_max_reservas"));
					tipoele.setReservaEncargado(rs.getBoolean("reservaEncargado"));
					tipoele.setLimiteMaxHorasReserva(rs.getInt("limiteMaxHorasReserva"));
					tipoele.setCantMaxDiasAnticipacion(rs.getInt("cantMaxDiasAnticipacion"));
					listadotipoelemento.add(tipoele);
					}
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		}
		else{
			try 
			{
				pstm = FactoryConnection.getinstancia().getConn().prepareStatement("select * from tipoelemento where reservaEncargado=b'0'");
//				pstm.(1, false);
				rs = pstm.executeQuery();
				if(rs!=null){
					while(rs.next()){
						TipoElemento tipoele=new TipoElemento();
						tipoele.setId(rs.getInt("id"));
						tipoele.setNombre(rs.getString("nombre"));
						tipoele.setCant_max_reservas(rs.getInt("cant_max_reservas"));
						tipoele.setReservaEncargado(rs.getBoolean("reservaEncargado"));
						tipoele.setLimiteMaxHorasReserva(rs.getInt("limiteMaxHorasReserva"));
						tipoele.setCantMaxDiasAnticipacion(rs.getInt("cantMaxDiasAnticipacion"));
						listadotipoelemento.add(tipoele);
						}
				}
			} 
			catch (Exception e) 
			{
				throw e;
			}
		}
		try {
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			
			throw e;
		}
		
		return listadotipoelemento;
		
	}

	public ArrayList<TipoElemento> devolverTodoTipoElemento() throws Exception
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> listadotipoelemento= new ArrayList<TipoElemento>();
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("select * from tipoelemento");
			if(rs!=null){
				while(rs.next()){
					TipoElemento tipoele=new TipoElemento();
					tipoele.setId(rs.getInt("id"));
					tipoele.setNombre(rs.getString("nombre"));
					tipoele.setCant_max_reservas(rs.getInt("cant_max_reservas"));
					tipoele.setReservaEncargado(rs.getBoolean("reservaEncargado"));
					tipoele.setLimiteMaxHorasReserva(rs.getInt("limiteMaxHorasReserva"));
					tipoele.setCantMaxDiasAnticipacion(rs.getInt("cantMaxDiasAnticipacion"));
					listadotipoelemento.add(tipoele);
					}
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		try {
			if(stm!=null){
				stm.close();
				FactoryConnection.getinstancia().releaseConn();
			}
			
		} catch (Exception e) {
			throw e;
		}
		return listadotipoelemento;	
		
	}
	
	
	public void agregarTipoElemento (TipoElemento tipoele) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO tipoelemento(nombre,cant_max_reservas,reservaEncargado,limiteMaxHorasReserva,cantMaxDiasAnticipacion) VALUES (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1, tipoele.getNombre());
			pstm.setInt(2, tipoele.getCant_max_reservas());
			pstm.setBoolean(3,tipoele.getReservaEncargado());
			pstm.setInt(4, tipoele.getLimiteMaxHorasReserva());
			pstm.setInt(5, tipoele.getCantMaxDiasAnticipacion());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				tipoele.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void eliminarTipoElemento(TipoElemento tipoele) throws Exception, ExcepcionEspecial
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"DELETE FROM tipoelemento WHERE id=?");
			pstm.setInt(1, tipoele.getId());
			pstm.executeUpdate();
		}catch (MySQLIntegrityConstraintViolationException exc) {
			throw new ExcepcionEspecial("Imposible eliminar, Tipo de Elemento tiene Elementos dependientes", Level.ERROR);
		}  
		catch (SQLException exc) {
			throw new ExcepcionEspecial("Imposible eliminar, Tipo de Elemento tiene Elementos dependientes", Level.ERROR);
		} 
			catch(Exception e){
				throw e;		
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (SQLException exc) {
			throw new ExcepcionEspecial("Error de conexión", Level.ERROR);			
		}		
	}
	
	public void modificarTipoElemento(TipoElemento tipoele) throws Exception
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE tipoelemento SET nombre=?,cant_max_reservas=?,reservaEncargado=?,limiteMaxHorasReserva=?,cantMaxDiasAnticipacion=?  WHERE id=?");
			pstm.setString(1, tipoele.getNombre());
			pstm.setInt(2, tipoele.getCant_max_reservas());
			pstm.setBoolean(3,tipoele.getReservaEncargado());
			pstm.setInt(4, tipoele.getLimiteMaxHorasReserva());
			pstm.setInt(5, tipoele.getCantMaxDiasAnticipacion());
			pstm.setInt(6, tipoele.getId());
			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}	
		
	}
	
	public TipoElemento buscarTipoElemento(TipoElemento tipoele) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("Select * from tipoelemento where id=?");
			pstm.setInt(1, tipoele.getId());
			rs=pstm.executeQuery();
			if(rs!=null){
				while(rs.next())
					{
					tipoele.setId(rs.getInt("id"));
					tipoele.setNombre(rs.getString("nombre"));
					tipoele.setCant_max_reservas(rs.getInt("cant_max_reservas"));
					tipoele.setReservaEncargado(rs.getBoolean("reservaEncargado"));
					tipoele.setLimiteMaxHorasReserva(rs.getInt("limiteMaxHorasReserva"));
					tipoele.setCantMaxDiasAnticipacion(rs.getInt("cantMaxDiasAnticipacion"));
					}
						}
			} 
			catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}	
		
		
		return tipoele;
	}
	
}

