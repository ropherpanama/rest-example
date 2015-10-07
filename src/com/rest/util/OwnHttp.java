package com.rest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.rest.services.model.ApiMessage;

/**
 * @author Spanky
 */
public class OwnHttp {

	/**
	 * Este metodo realiza un POST
	 * @param url direccion del servidor destino
	 * @param input entrada de tipo STRING en formato JSON que sera enviada al servidor
	 * @return respuesta del servidor/proceso
	 */
	public static String postByJSON(String url, String input) {
		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			StringEntity se = new StringEntity(input);
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
		
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_ERROR, "no se recibio ningun dato del servidor"));

			return result;
			
		} catch (Exception e) {
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_UNKNOWN_ERROR, e.getLocalizedMessage()));
		}
	}

	/**
	 * Este metodo realiza un POST
	 * @param url direccion del servidor destino
	 * @param object Objeto JAVA que sera enviado en el POST, el objeto sera parseado a JSON antes de
	 * ser enviado
	 * @return respuesta del servidor/proceso
	 */
	public static String postJSONByObject(String url, Object object) {
		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			StringEntity se = new StringEntity(Parser.objetoAJson(object));
			httpPost.setEntity(se);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
		
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_ERROR, "no se recibio ningun dato del servidor"));

			return result;
			
		} catch (Exception e) {
			return Parser.objetoAJson(new ApiMessage(ApiMessage.TYPE_ERROR, ApiMessage.CODE_UNKNOWN_ERROR, e.getLocalizedMessage()));
		}
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}
}
