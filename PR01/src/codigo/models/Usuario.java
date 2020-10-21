package codigo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import codigo.db.DbObject;

public class Usuario extends DbObject{

	private static final String TABLE = "person";
	private static final ArrayList COLS = getArrayCols();
	
	private int id = 0;
	public String login = "";  
	public String pass = "";  
	public int edad = 0;
	
	@Override
	public int getId() {
		return this.id;
	}

	private static ArrayList getArrayCols() {
		ArrayList list = new ArrayList();
		list.add("login");
		list.add("pass"); 	
		list.add("edad"); 

		return list;
	}

	@Override
	public String getTable() {
		return TABLE;
	}

	@Override
	public ArrayList getCols() {
		return COLS;
	}

	@Override
	public ArrayList getValues() {
		ArrayList list = new ArrayList();
		list.add(this.login);
		list.add(this.pass); 	
		list.add(this.edad); 
		return list;
	}

	@Override
	public DbObject parse(ResultSet rs) throws SQLException {
		Usuario usu = new Usuario();
		usu.id = rs.getInt("id");
		usu.login = rs.getString("login");
		usu.pass = rs.getString("pass");
		usu.edad = rs.getInt("edad");
		
		return usu;
	}

	public void setId(int id) {
		this.id = id;
	}
	  

}
