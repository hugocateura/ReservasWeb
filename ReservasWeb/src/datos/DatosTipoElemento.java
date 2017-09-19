package datos;

import java.sql.*;
import java.util.ArrayList;
import entidades.*;

public class DatosTipoElemento 
{
	
	public ArrayList<TipoElemento> buscarTodo(Persona pers) throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tipoelemento= new ArrayList<TipoElemento>();
		
		if(pers.getCategoria()!="Online"){
		try 
		{
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("select * from tipoelemento");
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
					tipoelemento.add(tipoele);
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
						tipoelemento.add(tipoele);
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
		
		return tipoelemento;
		
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
	
	public void eliminarTipoElemento(TipoElemento tipoele) throws Exception
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"DELETE FROM tipoelemento WHERE id=?");
			pstm.setInt(1, tipoele.getId());
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
	
}

