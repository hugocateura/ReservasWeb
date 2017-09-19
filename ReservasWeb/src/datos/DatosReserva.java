package datos;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import entidades.*;
import utilidades.ExcepcionesEscritorio;

import java.nio.channels.SelectableChannel;
import java.security.KeyStore.ProtectionParameter;

public class DatosReserva 
{
	ArrayList<Reserva>listadoRes = new ArrayList<Reserva>();
	
	public ArrayList<Reserva> buscarTodo() throws Exception
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
			
			
		} catch (SQLException e) {
			throw e;
		} catch (ExcepcionesEscritorio e) {
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
	
		
	public ArrayList<Reserva> reservasPendientesPersona(Persona pers) throws Exception
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
			
		} catch (SQLException e) {
			throw e;
		} catch (ExcepcionesEscritorio e) {
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
	public void agregarReserva(Reserva res) throws Exception
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
			
		} catch (SQLException e) {
			System.out.println(e);
			//throw e;
		} catch (ExcepcionesEscritorio e) {
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

	public void eliminarReserva(Reserva res) throws Exception
	{
		PreparedStatement pstm = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("DELETE FROM reserva WHERE id=?");
			pstm.setInt(1, res.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} catch (ExcepcionesEscritorio e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
		
	}
		
	public void modificarReserva(Reserva res) throws Exception
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
		} catch (SQLException e) {
			throw e;
		} catch (ExcepcionesEscritorio e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e;
		}
	}
	

public ArrayList<Elemento> obtenerElementos(int tipo, String fechaHoraDesde, String fechaHoraHasta) throws Exception
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
		} catch (SQLException e) {
			throw e;
		} catch (ExcepcionesEscritorio e) {
			throw e;
		}
		
		return listadoElementos;
	}

public void cancelarReserva(Reserva res) throws Exception
{
	PreparedStatement pstm = null;
	
	try {
		pstm = FactoryConnection.getinstancia().getConn().prepareStatement("UPDATE reserva SET estado=? WHERE id=?");
		pstm.setString(1, "Cancelada");
		pstm.setInt(2, res.getId());
		pstm.executeUpdate();
	} catch (SQLException e) {
		throw e;
	} catch (ExcepcionesEscritorio e) {
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

