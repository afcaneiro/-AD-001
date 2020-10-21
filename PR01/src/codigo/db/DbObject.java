package codigo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DbObject {
	
	private Conexion ctx = Conexion.getInstance();
	 
	public abstract int   getId();
	
	/**
	 * 
	 * @return Devuelve el nombre la tabla de base de datos
	 */
	public abstract String   getTable();
	
	/**
	 * 
	 * @return Devuelve las columnas que tiene el objecto en la tabla de base de datos
	 */
	public abstract ArrayList getCols();
	
	/**
	 * 
	 * @return Devuelve los valores del objeto en concreto de tipo DbObject
	 */
	public abstract ArrayList getValues();
	
	/**
	 * 
	 * @param rs
	 * @return Nos devuelve 1 objeto de tipo DBObject transformado por las columnas de la clase
	 * @throws SQLException
	 */
	public abstract DbObject parse(ResultSet rs) throws SQLException;
	
	public void insertar() throws SQLException { 
		ctx.insertar(getTable(), getCols(), getValues());
		
	} 
	
	/**
	 * 
	 * @return Devuelve todos los objetos de la clase
	 * @throws SQLException
	 */
	public ArrayList<DbObject> list() throws SQLException {
		ArrayList<DbObject> lista = new ArrayList<DbObject>();
		ResultSet rs = ctx.select(getTable());
		while (rs.next()) {
			// read the result set
			DbObject obj = this.parse(rs);
			lista.add(obj);
		}
		
		return lista;
	}
	
	/**
	 * Actualiza en la base de datos el objeto, <strong>Usa el ID para identificar cual tiene que actualizar</strong>
	 * @throws SQLException
	 */
	public void update() throws SQLException { 
		ctx.update(getTable(), getId(), getCols(), getValues());
	}
	
	/**
	 * Borra en la base de datos el objeto, <strong>Usa el ID para identificar cual tiene que actualizar</strong>
	 * @throws SQLException
	 */
	public void delete() throws SQLException {
		ctx.delete(getTable(), getId());
	}
	
}
