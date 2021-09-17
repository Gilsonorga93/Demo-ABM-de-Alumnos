package com.educacionit.interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public interface ConexionMariaDB {
	default Connection getConexion() {
		Connection conexion = null;
		// archivos de propiedades
		Properties propiedades = new Properties();
		
		try {
			
			File archivo = new File("src/recursos/database.properties");
			//System.out.println(archivo.getAbsolutePath());
			propiedades.load(new FileInputStream(archivo));
			
			String url = propiedades.getProperty("db.url");
			String user = propiedades.getProperty("db.user");
			String pass = propiedades.getProperty("db.pass","1234");
			
			String driver = propiedades.getProperty("db.driver");
			Class.forName(driver);
			
			//Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, pass);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return conexion;
	}
}

