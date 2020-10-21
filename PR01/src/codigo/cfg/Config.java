package codigo.cfg;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult; 

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import codigo.db.ConfigDB;

public class Config { 
	
	private static final String CONFIG_FILE = "config.xml";
	private Document configFile;
	private static Config instance;

	private Config() {
		this.checkConfigFile();
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}
	 
	
	private void checkConfigFile(){
		File file = new File(CONFIG_FILE);
		if (!file.exists()) { 
			// Creamos la Config
			configFile = createDocument(file);
		}
		// Cargamos la Config
		configFile = loadDocument(file);
	}
	
	private Document loadDocument(File file) { 
		
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(file);
			  
			  
			  NodeList nList = doc.getElementsByTagName("conexion");
			  if (nList.getLength() > 0) {
				  Node data = nList.item(0);
				  String driver = "";
				  String host = "";
				  String user = "";
				  String pass = "";
				  if (data.hasChildNodes()) {
					  NodeList childs = data.getChildNodes();
					  for (int idx = 0; idx < childs.getLength(); idx++) {
						  Node item = childs.item(idx); 
						  String name = item.getNodeName();
						  if (name.equals("driver")) {
							  driver = item.getTextContent();
						  }
						  if (name.equals("host")) {
							  host = item.getTextContent();
						  }
						  if (name.equals("user")) {
							  user = item.getTextContent();		  
						  }
						  if (name.equals("pass")) {
							  pass = item.getTextContent();
						  }
					  }
				  } 
				  // Configurar la conexion de base de datos
				  ConfigDB.setDBConexion(driver, host, user, pass);
			  }
			  
			  nList = doc.getElementsByTagName("queries");
			  if (nList.getLength() > 0) {
				  Node data = nList.item(0);
				  if (data.hasChildNodes()) {
					  NodeList childs = data.getChildNodes();
					  for (int idx = 0; idx < childs.getLength(); idx++) {
						  Node item = childs.item(idx); 
						  String query = item.getTextContent();
						  query = query.replaceAll("\\r\\n|\\r|\\n|\\t","").trim();
						  if (!query.isEmpty()) {
							  // Consultas de creación de la base de datos
							  ConfigDB.addQuery( query );
						  } 
					  }
				  }
			  }
			  
			  
			  // getTextContent
			  
			} catch(Exception e) {
			  e.printStackTrace();
			}
		
		return null;
	}

	private Document createDocument(File file) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.newDocument();
			
			  // =============================== 
			  Element config = doc.createElement("configuration");
			  doc.appendChild(config);	
			  
			  Element conex = doc.createElement("conexion");
			  doc.appendChild(config);
			  
			  Element driver = doc.createElement("driver");
			  driver.appendChild(doc.createTextNode("com.mysql.jdbc.Driver"));
			  
			  conex.appendChild(driver);
			  
			  Element host = doc.createElement("host");
			  host.appendChild(doc.createTextNode("jdbc:mysql://127.0.0.1:3306/clase?useSSL=false"));
			  
			  conex.appendChild(host);
			  
			  Element user = doc.createElement("user");
			  user.appendChild(doc.createTextNode("root"));
			  
			  conex.appendChild(user);
			  
			  Element pass = doc.createElement("pass");
			  pass.appendChild(doc.createTextNode("root"));
			  
			  conex.appendChild(pass);
			  
			  
			  Element queries = doc.createElement("queries");
			  config.appendChild(queries);		
			  
			  Element query01 = doc.createElement("query");
			  query01.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS `usuario` (\n"
			  		+ "	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
			  		+ "	`login` VARCHAR(50) NOT NULL,\n"
			  		+ "	`pass` VARCHAR(50) NOT NULL,\n"
			  		+ "	`edad` INT(10) UNSIGNED NOT NULL,\n"
			  		+ "	PRIMARY KEY (`id`)\n"
			  		+ ")\n"
			  		+ "COLLATE='latin1_swedish_ci'\n"
			  		+ "ENGINE=InnoDB\n"
			  		+ ";\n"
			  		+ ""));
			  queries.appendChild(query01);	  
			  
			  Element query02 = doc.createElement("query");
			  query02.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS `persona` (\n"
			  		+ "	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
			  		+ "	`name` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_spanish_ci',\n"
			  		+ "	`lastname` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'latin1_spanish_ci',\n"
			  		+ "	PRIMARY KEY (`id`)\n"
			  		+ ")\n"
			  		+ "COLLATE='latin1_spanish_ci'\n"
			  		+ "ENGINE=InnoDB\n"
			  		+ ";"));
			  queries.appendChild(query02);	  
			  
			  Element query03 = doc.createElement("query");
			  query03.appendChild(doc.createTextNode("CREATE TABLE IF NOT EXISTS `producto` (\n"
			  		+ "	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
			  		+ "	`name` VARCHAR(50) NOT NULL,\n"
			  		+ "	`desc` VARCHAR(75) NOT NULL,\n"
			  		+ "	`precio` INT(10) UNSIGNED NOT NULL,\n"
			  		+ "	`stock` INT(10) UNSIGNED NOT NULL,\n"
			  		+ "	PRIMARY KEY (`id`)\n"
			  		+ ")\n"
			  		+ "COLLATE='latin1_swedish_ci'\n"
			  		+ "ENGINE=InnoDB\n"
			  		+ ";"));
			  queries.appendChild(query03);	   
			  
			   
			  
			  // ===============================
			  
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  
			  
			  StreamResult result = new StreamResult(file);		 

			  transformer.transform(source, result);
			   
			  System.out.println(file.getAbsolutePath());
			  
			  System.out.println("FILE EXISTS: "+file.exists());
			  
			  return doc;
		}catch(Exception e) {
			System.err.println(e.getMessage());
		} 
		return null;
	}
}
