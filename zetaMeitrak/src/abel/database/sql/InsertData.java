package abel.database.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://192.168.0.100:1433;databaseName=MEITRACK";
	String user = "abel";
	String password = "1234";
	
	Connection conn;
	
	public InsertData(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la BD");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inertInto (String[] data){
		String queryString = "INSERT INTO REPORTE VALUES ('" + data[0] + "','" +
																data[1] + "','" +
																data[2] + "','" +
																data[3] + "','" +
																data[4] + "','" +
																data[5] + "','" +
																data[6] + "','" +
																data[7] + "','" +
																data[8] + "','" +
																data[9] + "','" +
																data[10] + "')";
		try {
			PreparedStatement pst = conn.prepareStatement(queryString);
			pst.executeUpdate();
			System.out.println("Datos insertados con exito en la BD");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
																
	}

}
