package codigo.db;

import java.util.ArrayList;

public  class  ConfigDB {	
	
	private ConfigDB() {}
	
	public static String DB_DRIVER = "";
	public static String DB_HOST   = "";
	public static String DB_USER   = "";
	public static String DB_PASS   = "";
	
	public static void setDBConexion(String driver, String host, String user, String pass) {
		DB_DRIVER = driver;
		DB_HOST   = host;
		DB_USER   = user;
		DB_PASS   = pass;
	}

	public static ArrayList<String> queries = new ArrayList<String>(); 
	
	public static void addQuery(String query) {
		queries.add(query);
	} 
	
}
