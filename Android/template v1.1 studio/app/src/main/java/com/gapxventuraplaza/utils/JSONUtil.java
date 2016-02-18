package com.gapxventuraplaza.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class JSONUtil {

	public static final String TAG = "JSONUtils";

	public static Object[] login(String username, String password) {
        HttpPost post = new HttpPost(ConstantsUtils.URL_LOGIN);
        try {
            post.setHeader("content-type", "application/json");
            JSONObject dato = new JSONObject();
            dato.put("login", username);
            String x = encriptar(password);
            dato.put("password", x);

            StringEntity entity = new StringEntity(dato.toString());

            post.setEntity(entity);

        } catch (Exception E) {
            E.printStackTrace();
        }
		return getJSON(post,2);
	}

    public static Object[] supervisionesDeLugar(String idlugar) {
        HttpPost post = new HttpPost(ConstantsUtils.URL_SUPERVISIONDELUGAR);
        try {
            post.setHeader("content-type", "application/json");
            JSONObject dato = new JSONObject();
            dato.put("idlugar", idlugar);

            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return getJSON(post,2);
    }

    public static Object[] lugarPorId(String idlugar) {
        HttpPost post = new HttpPost(ConstantsUtils.URL_LUGARPORID);
        try {
            post.setHeader("content-type", "application/json");
            JSONObject dato = new JSONObject();
            dato.put("idlugar", idlugar);

            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return getJSON(post,2);
    }

    public static Object[] calificacionActividadPorlugar(String idlugar) {
        HttpPost post = new HttpPost(ConstantsUtils.URL_CALIFICACIONACTIVIDADPORLUGAR);
        try {
            post.setHeader("content-type", "application/json");
            JSONObject dato = new JSONObject();
            dato.put("idlugar", idlugar);
            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return getJSON(post,2);
    }

    public static Object[] guardarEvaluacion(String idlugar, String usuario, JSONArray evaluacion, String fecha) {
        HttpPost post = new HttpPost(ConstantsUtils.URL_EVALUACION);

        try {
            post.setHeader("content-type", "application/json");
            JSONObject dato = new JSONObject();

            dato.put("idlugar", idlugar);
            dato.put("usuario", usuario);
            dato.put("fecha", fecha);
            dato.put("evaluacion", evaluacion.toString());
            System.out.println("dato.toString()"+dato.toString());
            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return getJSON(post,2);
    }


    private static String encriptar(String password) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }

	
	/*
	 * Metodo que permite hacer una peticion de archivo json
	 * @params recibe un objetod de tipo HttpGet en donde lleva la URL de donde debe hacer la petición y los parametros
	 * @return retorna un vector de 2 posiciones Object[]. En la primer posicion [0] guarda el String del archivo JSON 
	 * y en la segunda[1] guarda el tipo de error que se presento si existieron problemas al hacer la petición
	 */
	public static Object[] getJSON(Object http, int type) {
        HttpGet get = new HttpGet();
        HttpPost post = new HttpPost();
        if(type==1){
            get = (HttpGet)http;
        }else{
            post = (HttpPost)http;
        }
		Object[] res = new Object[2];
		boolean error = false;
		InputStream is = null;
		try {
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
			HttpConnectionParams.setSoTimeout(httpParameters, 5000);
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpResponse response;
            if(type==1){
                response = httpclient.execute(get);
            }else {
                response = httpclient.execute(post);
            }
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (HttpHostConnectException e) {
			res[1] = "No es posible acceder al Servidor, por favor revise su conexión";
			error = true;
		} catch (SocketTimeoutException e) {
			res[1] = "No es posible acceder al Servidor, por favor revise su conexión";
			error = true;
		} catch (ConnectTimeoutException e) {
			res[1] = "No es posible acceder al Servidor, por favor revise su conexión";
			error = true;
		} catch (Exception e) {
            error = true;
			System.out.println("Error haciendo Http Get");
		}

		if (!error) {
			res[1] = null;
			try {
				BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "utf8"), 8192);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				res[0] = sb.toString();
			} catch (Exception e) {
				System.out.println("Error leyendo linea");
			}
		}
		return res;
	}




}
