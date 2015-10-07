package com.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.config.Configs;
import com.rest.internal.processes.AfterRequest;
import com.rest.services.model.ApiMessage;
import com.rest.util.OwnHttp;
import com.rest.util.Parser;

@Path("continent/api/")
public class EDGEServicesImpl {
	
	@GET
	@Path("prueba")
	@Produces({ MediaType.APPLICATION_JSON })
	public String abc() {
		try {
			return  Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_SUCCESS, ApiMessage.CODE_SUCCESS, "Esto funciona")); 
		} catch (Exception e) {
			e.printStackTrace();
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_APP_ERROR, "Error al procesar la solicitud")); 
		}
	}
	
	/**
	 * Implementacion del servicio de suscripcion
	 * @param input data del FORM que realice el POST
	 * @return estado del request (interno/request)
	 */
	@POST
	@Path("suscription")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String postit(String input) {
		try {
			String url = Configs.VASP_ROOT_URL + "userprofile/v1/notifySubscription";
			String response =  OwnHttp.postByJSON(url, input);
			//rutinas para evitar colocar mucho codigo en los webservices
			String internalStatus = AfterRequest.executeInternalTask(Configs.SERVICE_SUSCRIBE, response);
			//en este punto, de acuerdo a lo que se requiera; definir si se retorna la salida del request o del proceso interno
			return internalStatus;//por ahora retornamos el status del proceso interno
		}catch(Exception e) {
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_APP_ERROR, "Error al procesar la solicitud")); 
		}
	}
}
