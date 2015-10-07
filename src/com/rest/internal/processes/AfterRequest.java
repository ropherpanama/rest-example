package com.rest.internal.processes;

import com.rest.config.Configs;
import com.rest.services.model.ApiMessage;
import com.rest.services.model.VaspStatus;
import com.rest.util.Parser;

/**
 * Esta clase tomara las acciones de acuerdo al servicio consumido 
 * y su respuesta
 * @author Spanky
 */
public class AfterRequest {
	public static String executeInternalTask(int serviceFlag, String response) {
		try {
			VaspStatus v = null;
			if(response.contains("status")) {
				v = (VaspStatus) Parser.jsonAObjeto(response, new VaspStatus());
			}else {
				return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_ERROR, "No se recibe status valido [" + response + "]"));
			}
			
			if(serviceFlag == Configs.SERVICE_SUSCRIBE) {
				//acciones para el servicio de suscripcion
				if(v.getStatus() == 0) {
					//Hacer algo en concreto cuando el status devuelve un valor determinado
				} else if(v.getStatus() == 1) {
					//Hacer algo en concreto cuando el status devuelve un valor determinado
				} else if(v.getStatus() == 2) {
					//Hacer algo en concreto cuando el status devuelve un valor determinado
				}
			} else if(serviceFlag == Configs.SERVICE_UNSUSCRIBE) {
				//acciones para el servicio de desuscripcion
				if(v.getStatus() == 0) {
					//Hacer algo en concreto cuando el status devuelve un valor determinado
				}
			}
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_SUCCESS, ApiMessage.CODE_SUCCESS, "correcto"));
		}catch(Exception e) {
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_APP_ERROR, e.getLocalizedMessage())); 
		}
	}
}
