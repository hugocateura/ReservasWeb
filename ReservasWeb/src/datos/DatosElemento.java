package datos;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import entidades.*;
import utilidades.ExcepcionEspecial;
import utilidades.ExcepcionesEscritorio;


public class DatosElemento implements Serializable
{
	
	public ArrayList<Elemento> buscarTodo() throws Exception
	{
		Statement stm=null;
		ResultSet rs=null;
		ArrayList<Elemento> elemento= new ArrayList<Elemento>();
		
		try 
		{
			stm = FactoryConnection.getinstancia().getConn().createStatement();
			rs = stm.executeQuery("SELECT id,nombre,tipo FROM elemento");
			if(rs!=null){
				while(rs.next()){
					Elemento ele=new Elemento();
					ele.setId(rs.getInt("id"));
					ele.setNombre(rs.getString("nombre"));
					ele.setTipo(new TipoElemento());
					ele.getTipo().setId(rs.getInt("tipo"));
					elemento.add(ele);
				}
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stm!=null) stm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			
			throw e ;
		}
		
		return elemento;
		
	}
	
	public Elemento buscarPorNombre(Elemento ele) throws Exception{ 
		PreparedStatement stm= null;
		ResultSet rs= null;
		Elemento elemento = null;
		
		
		try {
			stm = FactoryConnection.getinstancia().getConn().prepareStatement
					("SELECT * FROM elemento WHERE nombre=?");
			stm.setString(1,ele.getNombre());
			rs=stm.executeQuery();
			if(rs!=null && rs.next()){
				elemento=new Elemento();
				elemento.setId(rs.getInt("id"));
				elemento.setNombre(rs.getString("nombre"));
				ele.setTipo(new TipoElemento());
				ele.getTipo().setId(rs.getInt("tipo"));
				ele.getTipo().setNombre(rs.getString("nombre"));
				ele.getTipo().setCant_max_reservas(rs.getInt("cant_max_reservas"));
				}
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
		
		
		return elemento;
	}
	
	public void agregarElemento (Elemento ele) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"INSERT INTO elemento(nombre,tipo) VALUES (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ele.getNombre());
			pstm.setInt(2, ele.getTipo().getId());
			pstm.executeUpdate();
			rs=pstm.getGeneratedKeys();
			if(rs!=null && rs.next()){
				ele.setId(rs.getInt(1));
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
	
	public void eliminarElemento(Elemento ele) throws Exception
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"DELETE FROM elemento WHERE id=?");
			pstm.setInt(1, ele.getId());
			pstm.executeUpdate();
		} catch (SQLException exc) {
			throw new ExcepcionEspecial("Imposible eliminar, existen reservas del Elemento", Level.ERROR);
		} 
		catch (Exception e) {
			throw e;
		}
		
		try {
			if(pstm!=null)pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (SQLException exc) {
			throw new ExcepcionEspecial("Error de conexi�n", Level.ERROR);				
		}		
	}
	
	public void modificarElemento(Elemento ele) throws Exception
	{
		PreparedStatement pstm = null;
		
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"UPDATE elemento SET nombre=?,tipo=? WHERE id=?");
			pstm.setString(1, ele.getNombre());
			pstm.setInt(2, (ele.getTipo().getId()));
			pstm.setInt(3, ele.getId());
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

	public ArrayList<Elemento> devolverDisponibles(TipoElemento tipoElemento,String fechaDesde, String fechaHasta) throws Exception {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Elemento> elemento= new ArrayList<Elemento>();
		
		try 
		{
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement(
					"SELECT * FROM elemento WHERE id NOT IN (SELECT res.elemento FROM reserva res WHERE res.fechaHoraDesde BETWEEN ? AND ? OR res.fechaHoraDesde BETWEEN ? AND ? AND estado = ?) having tipo = ?");
			pstm.setString(1, fechaDesde);
			pstm.setString(2, fechaHasta);
			pstm.setString(3, fechaDesde);
			pstm.setString(4, fechaHasta);
			pstm.setString(5, "Activa");
			pstm.setInt(6, tipoElemento.getId());
			rs = pstm.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Elemento ele=new Elemento();
					ele.setId(rs.getInt("id"));
					ele.setNombre(rs.getString("nombre"));
					ele.setTipo(new TipoElemento());
					ele.getTipo().setId(rs.getInt("tipo"));
					elemento.add(ele);
				}
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			FactoryConnection.getinstancia().releaseConn();
		} catch (Exception e) {
			throw e ;
		}
		
		return elemento;
	}
	
	public Elemento buscarElemento(Elemento elemento) throws Exception
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			pstm = FactoryConnection.getinstancia().getConn().prepareStatement("Select * from elemento where id=?");
			pstm.setInt(1, elemento.getId());
			rs=pstm.executeQuery();
			if(rs!=null){
				while(rs.next())
					{
					elemento.setId(rs.getInt("id"));
					elemento.setNombre(rs.getString("nombre"));
					elemento.setTipo(new TipoElemento());
					elemento.getTipo().setId(rs.getInt("tipo"));
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
		return elemento;
	}
	
		
}

