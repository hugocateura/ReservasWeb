package datos;

import java.sql.*;
import java.util.Date;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import entidades.*;
import utilidades.ExcepcionEspecial;


import java.io.Serializable;
import java.nio.channels.SelectableChannel;
import java.security.KeyStore.ProtectionParameter;

public class DatosReserva implements Serializable
{
	ArrayList<Reserva>listadoRes = new ArrayList<Reserva>();
	
	public ArrayList<Reserva> buscarTodo() throws Exception,SQLException
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM reserva");
			rs = pstm.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reserva res = new Reserva();
					res.setId(rs.getInt("id"));
					res.setElemento(new Elemento());
					res.getElemento().setId(rs.getInt("elemento"));
					res.setTipo(new TipoElemento());
					res.getTipo().setId(rs.getInt("tipo"));
					res.setPersona(new Persona());
					res.getPersona().setId(rs.getInt("persona"));
					res.setFechaHoraDesde(rs.getString("fechaHoraDesde"));
					res.setFechaHoraHasta(rs.getString("fechaHoraHasta"));
					res.setObservacion(rs.getString("observacion"));
					res.setEstado(rs.getString("estado"));
					listadoRes.add(res);
				}
			}
			
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una reserva en la base de datos", Level.ERROR);	
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
		
		return listadoRes;
	}
	
		
	public ArrayList<Reserva> reservasPendientesPersona(Persona pers) throws Exception,SQLException
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM reserva r where r.persona = ? and r.fechaHoraDesde >= NOW() and r.estado = ?");
			pstm.setInt(1,pers.getId());
			pstm.setString(2, "Activa");
			rs=pstm.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reserva res = new Reserva();
					res.setId(rs.getInt("id"));
					res.setElemento(new Elemento());
					res.getElemento().setId(rs.getInt("elemento"));
					res.setTipo(new TipoElemento());
					res.getTipo().setId(rs.getInt("tipo"));
					res.setPersona(new Persona());
					res.getPersona().setId(rs.getInt("persona"));
					res.setFechaHoraDesde(rs.getString("fechaHoraDesde"));
					res.setFechaHoraHasta(rs.getString("fechaHoraHasta"));
					res.setObservacion(rs.getString("observacion"));
					listadoRes.add(res);
				}
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible buscar una reserva pendiente en la base de datos", Level.ERROR);		
		} catch (Exception e) {
			throw e;
		}
		
		try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
		
		return listadoRes;
	}
	public void agregarReserva(Reserva res) throws Exception,SQLException
	{
		ResultSet rs = null;
		PreparedStatement pstm = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO reserva(elemento,tipo,persona,fechaHoraDesde,fechaHoraHasta,observacion,estado) VALUES (?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, res.getElemento().getId());
			pstm.setInt(2, res.getTipo().getId());
			pstm.setInt(3, res.getPersona().getId());
			pstm.setString(4, res.getFechaHoraDesde());
			pstm.setString(5, res.getFechaHoraHasta());
			pstm.setString(6, res.getObservacion());
			pstm.setString(7, "Activa");
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			if(rs!=null & rs.next())
			{
				res.setId(rs.getInt(1));
			}
		
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible agregar una reserva en la base de datos", Level.ERROR);	
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

	public void eliminarReserva(Reserva res) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("DELETE FROM reserva WHERE id=?");
			pstm.setInt(1, res.getId());
			pstm.executeUpdate();
		} 
		catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"Error de integridad, no se puede eliminar la Reserva", Level.ERROR);
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
		
	}
		
	public void modificarReserva(Reserva res) throws Exception,SQLException
	{
		PreparedStatement pstm = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("UPDATE reserva SET elemento=?, tipo=?, persona=?, fechaHoraDesde=?, fechaHoraHasta=?, observacion=? WHERE id=?");
			pstm.setInt(1, res.getElemento().getId());
			pstm.setInt(2, res.getTipo().getId());
			pstm.setInt(3, res.getPersona().getId());
			pstm.setString(4, res.getFechaHoraDesde());
			pstm.setString(5, res.getFechaHoraHasta());
			pstm.setString(6, res.getObservacion());
			pstm.setInt(7, res.getId());
			pstm.executeUpdate();
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible modificar una reserva en la base de datos", Level.ERROR);	
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
	

public ArrayList<Elemento> obtenerElementos(int tipo, String fechaHoraDesde, String fechaHoraHasta) throws Exception,SQLException
	{
		ArrayList<Elemento> listadoElementos = new ArrayList<Elemento>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT elemento FROM reserva WHERE tipo=? AND fechaHoraDesde>=? AND fechaHoraHasta<=?");
			pstm.setInt(1, tipo);
			pstm.setString(2, fechaHoraDesde);
			pstm.setString(3, fechaHoraHasta);
			rs = pstm.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					Elemento elemento=new Elemento();
					elemento.setId(rs.getInt("id"));			
					listadoElementos.add(elemento);
				}
			}
		} catch (SQLException exc) {
			throw new ExcepcionEspecial(exc,"No es posible obtener elementos disponibles en la base de datos", Level.ERROR);	
		} catch (Exception e) {
			throw e;
		}
		
		return listadoElementos;
	}

public void cancelarReserva(Reserva res) throws Exception,SQLException
{
	PreparedStatement pstm = null;
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement("UPDATE reserva SET estado=? WHERE id=?");
		pstm.setString(1, "Anulada");
		pstm.setInt(2, res.getId());
		pstm.executeUpdate();
	} catch (SQLException exc) {
		throw new ExcepcionEspecial(exc,"No es posible cancelar una reserva en la base de datos", Level.ERROR);	
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

public String getFechaHoraActual() throws Exception,SQLException
{
	Statement stm=null;
	ResultSet rs=null;
	String fechaActual="";
	
	try {
		stm = FactoryConnection.getinstancia().getConn().createStatement();
		rs = stm.executeQuery("SELECT CURRENT_TIMESTAMP");
				
		if(rs!=null){
			while(rs.next()){
				fechaActual=(rs.getString("CURRENT_TIMESTAMP"));
			}
		}
		
	} catch (SQLException exc) {
		throw new ExcepcionEspecial(exc,"No es posible obtener fecha y hora actual en la base de datos", Level.ERROR);		
	} catch (Exception e) {
		throw e;
	}
	
	try {
		if(rs!=null)rs.close();
		if(stm!=null)stm.close();
		FactoryConnection.getinstancia().releaseConn();
	} catch (Exception e) {
		throw e;
	}
	
	return fechaActual;
}

public Reserva getReserva(Reserva res) throws Exception,SQLException
{
	
	PreparedStatement pstm=null;
	ResultSet rs=null;
	Reserva reserva = null;
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT * FROM reserva where id=?");
		pstm.setInt(1, res.getId());
		rs = pstm.executeQuery();
		if(rs!=null){
			while(rs.next()){
				reserva = new Reserva();
				reserva.setId(rs.getInt("id"));
				reserva.setElemento(new Elemento());
				reserva.getElemento().setId(rs.getInt("elemento"));
				reserva.setTipo(new TipoElemento());
				reserva.getTipo().setId(rs.getInt("tipo"));
				reserva.setPersona(new Persona());
				reserva.getPersona().setId(rs.getInt("persona"));
				reserva.setFechaHoraDesde(rs.getString("fechaHoraDesde"));
				reserva.setFechaHoraHasta(rs.getString("fechaHoraHasta"));
				reserva.setObservacion(rs.getString("observacion"));
				reserva.setEstado(rs.getString("estado"));
			}	
		}

	} catch (SQLException exc) {
		throw new ExcepcionEspecial(exc,"No es posible obtener una reserva en la base de datos", Level.ERROR);	
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
	return reserva;	

}

public int getActivasTipo(TipoElemento tipo, Persona pers) throws Exception, SQLException{
	int cantidad=0;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement("SELECT count(*) cant FROM reserva res INNER JOIN tipoelemento te ON res.tipo = te.id WHERE te.id = ? and fechaHoraDesde > CURRENT_TIMESTAMP() AND res.estado = 'Activa' AND res.persona = ?;");
		pstm.setInt(1, tipo.getId());
		pstm.setInt(2, pers.getId());
		rs = pstm.executeQuery();
		if(rs!=null){
			while(rs.next()){
				cantidad = rs.getInt("cant");
			}	
		}

	} catch (SQLException exc) {
		throw new ExcepcionEspecial(exc,"No es posible obtener las reserva activas en la base de datos", Level.ERROR);	
	} catch (Exception e) {
		throw e;
	}
	
	return cantidad;
}

}

