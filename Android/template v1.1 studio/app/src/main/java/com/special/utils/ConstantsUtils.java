package com.special.utils;

public class ConstantsUtils {
	
	public static final String URL_ROOT_TWITTER_API = "https://api.twitter.com";
	public static final String URL_SEARCH = URL_ROOT_TWITTER_API + "/1.1/search/tweets.json?q=";
	public static final String URL_AUTHENTICATION = URL_ROOT_TWITTER_API + "/oauth2/token";
	public static final String URL_GENERIC= "http://192.168.0.11:8080/LugaresVP/rest/";
	//public static final String URL_GENERIC= "http://172.20.10.7:8080/LugaresVP/rest/";
    public static final String URL_LOGIN= URL_GENERIC+"login/inicio";
    public static final String URL_SUPERVISIONDELUGAR= URL_GENERIC+"supervision/lugar";
    public static final String URL_LUGARPORID= URL_GENERIC+"lugar/identificador";
    public static final String URL_CALIFICACIONACTIVIDADPORLUGAR= URL_GENERIC+"calificacionActividad/lugar";

    // ----------------ANTIGUOS---------------------------
	public static final String URL_HOME=  URL_GENERIC+"test/home.json";
	public static final String URL_SERVICIO_EN_CURSO= URL_GENERIC+"test/servicio_en_curso.json";
	public static final String URL_LIST_SERVICIOS_HOY= URL_GENERIC+"test/servicios_hoy.json";
	public static final String URL_LIST_SERVICIOS_MANANA= URL_GENERIC+"test/servicios_manana.json";
	public static final String URL_LIST_SERVICIOS_ACEPTAR= URL_GENERIC+"test/servicios_aceptar.json";
    public static final String URL_SERVICIOS= URL_GENERIC+"test/servicios.json";
    public static final String URL_SERVICIOS1= URL_GENERIC+"test/servicios1.json";
    public static final String URL_ESTADOS= URL_GENERIC+"estado/listAll.json";
    public static final String URL_CONFIGURACION= URL_GENERIC+"relacionEstado/getConfiguracion.json";

	public static final String CONSUMER_KEY = "idZuaRXOAHan4yYeeHLVA";
	public static final String CONSUMER_SECRET = "wYXcK0gWdZDqpHPOLqRO7chtBPJHharqEAHJHAmU";

}
