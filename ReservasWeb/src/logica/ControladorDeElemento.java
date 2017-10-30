package logica;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import datos.DatosElemento;
import datos.DatosTipoElemento;
import entidades.Elemento;
import entidades.Persona;
import entidades.TipoElemento;
import utilidades.ExcepcionEspecial;

public class ControladorDeElemento implements Serializable{
			private DatosElemento baseElemento;
			private DatosTipoElemento baseTipoElemento;
			
			public ControladorDeElemento()
			{
				baseElemento = new DatosElemento();
				baseTipoElemento = new DatosTipoElemento();
			}
			
			public void crearElemento(Elemento ele) throws Exception, ExcepcionEspecial{
	
				if (ele.getNombre().length() != 0){
						try{
							baseElemento.agregarElemento(ele);
						}
						catch (Exception a){
							throw new ExcepcionEspecial("tipo de elemento", Level.ERROR);
						}
					}
				else{
					throw new ExcepcionEspecial("nombre", Level.ERROR);
					}		
						
			};
			
			public void borrarElemento (Elemento ele) throws Exception{
				try {
					baseElemento.eliminarElemento(ele);
				}
				catch (ExcepcionEspecial e) {
					throw e;
				}
				catch (Exception e) {
					throw e;
				}
			};
			
			public void modificarElemento(Elemento ele) throws Exception{
					
					if (ele.getNombre().length() != 0){
							try{
								baseElemento.modificarElemento(ele);
							}
							catch (Exception a){
								throw new ExcepcionEspecial("tipo de elemento", Level.ERROR);
							}
						}
					else{
						throw new ExcepcionEspecial("nombre", Level.ERROR);
						}		
							
				};
			
			public Elemento buscarElemento(Elemento ele) throws Exception{
				return baseElemento.buscarElemento(ele);
			}
			

			public Elemento consultaPorNombre(Elemento ele) throws Exception{
				return baseElemento.buscarPorNombre(ele);
			}
			
			public ArrayList<Elemento> consultarTodo() throws Exception{
				
				ArrayList<Elemento> elementos = baseElemento.buscarTodo();
				ControladorTipoDeElemento ctrlTipo = new ControladorTipoDeElemento();
				
				
				for (Elemento ele : elementos){
					TipoElemento tipoEle = new TipoElemento();
					tipoEle.setId(ele.getTipo().getId());
					tipoEle = ctrlTipo.buscarTipoElemento(tipoEle);
					ele.setTipo(tipoEle);
				}
				return  elementos;
			}
			
			public ArrayList<TipoElemento> getTipoElemento(Persona pers) throws Exception
			{
				return baseTipoElemento.buscarTodo(pers);
			}

			public ArrayList<Elemento> getElementosDisponibles(TipoElemento tipoElemento, String fechaDesde, String fechaHasta) throws Exception{
				
				return baseElemento.devolverDisponibles(tipoElemento,fechaDesde,fechaHasta);
			}
			

}