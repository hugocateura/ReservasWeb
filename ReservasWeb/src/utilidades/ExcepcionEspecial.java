package utilidades;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.lang.Exception;
import java.lang.Throwable;

public class ExcepcionEspecial extends Exception implements Serializable  {
	
	public ExcepcionEspecial(String msg,Level errorLevel) {
		super(msg);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,msg);
    	}	
	}
