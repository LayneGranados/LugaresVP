package com.special.utils;

public class ConstantsUtils {
	
	public static final String URL_ROOT_TWITTER_API = "https://api.twitter.com";
	public static final String URL_SEARCH = URL_ROOT_TWITTER_API + "/1.1/search/tweets.json?q=";
	public static final String URL_AUTHENTICATION = URL_ROOT_TWITTER_API + "/oauth2/token";
	//public static final String URL_GENERIC= "http://192.168.0.9:8080/LugaresVP/rest/";
	public static final String URL_GENERIC= "http://172.20.10.7:8080/LugaresVP/rest/";
    public static final String URL_LOGIN= URL_GENERIC+"login/inicio";
    public static final String URL_SUPERVISIONDELUGAR= URL_GENERIC+"supervision/lugar";
    public static final String URL_LUGARPORID= URL_GENERIC+"lugar/identificador";
    public static final String URL_CALIFICACIONACTIVIDADPORLUGAR= URL_GENERIC+"calificacionActividad/lugar";

}
