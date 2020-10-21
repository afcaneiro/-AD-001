package codigo;
 
import java.sql.SQLException;
import java.util.ArrayList;

import codigo.cfg.Config;
import codigo.db.DbObject;
import codigo.models.Persona;
import codigo.models.Producto;
import codigo.models.Usuario;

public class Principal {

	public static void main(String[] args) { 
				
		Config.getInstance();
		
		try {
			
			Persona per = new Persona();
			per.name     = "Don";
			per.lastname = "Pepito";
			
			per.insertar();
			
			for (DbObject ob : per.list()) {
				Persona p1 = (Persona)ob;
				System.out.println(p1.toString());
			}
			
			per.name     = "Don";
			per.lastname = "Jose";
			
			per.update();
			
			for (DbObject ob : per.list()) {
				Persona p1 = (Persona)ob;
				System.out.println(p1.toString());
			}
			
			per.delete();
			
			for (DbObject ob : per.list()) {
				Persona p1 = (Persona)ob;
				System.out.println(p1.toString());
			}
			
			System.out.println("Fin Personas");
			
			Producto prod= new Producto();
			
			prod.name="producto1";
			prod.desc="descripcion";
			prod.precio=5;
			prod.stock=2;
			
			prod.insertar();
			
			for(DbObject ob : prod.list()){
				Producto p1 = (Producto)ob;
				System.out.println(p1.toString());
			}
			
			prod.name="producto1mod";
			prod.desc="descripcion1mod";
			prod.precio=10;
			prod.stock=4;	
			
			prod.update();
			
			for(DbObject ob : prod.list()){
				Producto p1 = (Producto)ob;
				System.out.println(p1.toString());
			}
			
			prod.delete();
			
			for(DbObject ob : prod.list()){
				Producto p1 = (Producto)ob;
				System.out.println(p1.toString());
			}
			
			System.out.println("Fin Productos");
			
			
			Usuario usu = new Usuario();
			
			usu.login="login_prueba1";
			usu.pass="pass_prueba1";
			usu.edad=25;
			
			usu.insertar();
			
			
			usu.login="login_prueba2";
			usu.pass="pass_prueba2";
			usu.edad=30;
			
			usu.insertar();
			
			for(DbObject ob : usu.list()){
				Usuario u1 = (Usuario)ob;
				System.out.println(u1.toString());
			}
			
			usu.setId(2);
			usu.update();
			
			for(DbObject ob : usu.list()){
				Usuario u1 = (Usuario)ob;
				System.out.println(u1.toString());
			}
			
			usu.setId(1);
			usu.delete();
			
			for(DbObject ob : usu.list()){
				Usuario u1 = (Usuario)ob;
				System.out.println(u1.toString());
			}
			
			System.out.println("Fin Usuarios");
			
		}catch(SQLException e) {	
			System.out.println( e.getMessage() );
		} 
		// create a database connection

		/*
		 * 
		 * statement.executeUpdate("drop table if exists person");
		 * 
		 * statement.executeUpdate("create table person (id integer, name string)");
		 * 
		 * statement.executeUpdate("insert into person values(1, 'leo')");
		 * statement.executeUpdate("insert into person values(2, 'yui')");
		 * 
		 * 
		 * 
		 * ResultSet rs = statement.executeQuery("select * from person");
		 * while(rs.next()) { // read the result set System.out.println("name = " +
		 * rs.getString("name")); }
		 * 
		 * 
		 * //
		 */

	}

}
