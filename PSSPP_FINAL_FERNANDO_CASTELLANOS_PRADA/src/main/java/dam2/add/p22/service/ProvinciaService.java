package dam2.add.p22.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import dam2.add.p22.model.Provincia;

public class ProvinciaService {
	
//	public static void main(String[] args) {
//
//		String cadenaJson = leerUrl("https://raw.githubusercontent.com/IagoLast/pselect/master/data/municipios.json");
//		
//		//String cadenaJson = leerUrl("https://raw.githubusercontent.com/IagoLast/pselect/master/data/provincias.json");
//
//		Provincia[] provincias = new Gson().fromJson(cadenaJson, Provincia[].class);
//
//		for (Provincia pro : provincias) {
//			//ProvinciaDAO.anadirProvincia(pro);
//		}
//	}
	
	
	public static String leerUrl(String sUrl) {
		String output = "";
		try {
			URL url = new URL(sUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				// si la respuesta del servidor es distinta al codigo 200 lanzaremos una
				// Exception
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			// creamos un StringBuilder para almacenar la respuesta del web service
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = br.read()) != -1) {
				sb.append((char) cp);
			}
			// en la cadena output almacenamos toda la respuesta del servidor
			output = sb.toString();
			// System.out.println(output);

			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return output;
	}

}
